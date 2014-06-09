package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domini.Contracte;

public class ContracteBBDD {

	private ConnexioBBDD connexio;

	public ContracteBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}
	public Contracte recuperarContracte (int idContracte) throws Exception{
		try{
			PreparedStatement pst =connexio.prepareStatement("SELECT * FROM contracte WHERE idContracte = ?");
			pst.clearParameters();
			pst.setInt(1, idContracte);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				double pagament,percentatge;
				
				pagament = rs.getDouble("pagament");
				if(rs.wasNull()) {
					pagament = -1;
				}
				
				percentatge = rs.getDouble("percentatge");
				if(rs.wasNull()) {
					percentatge = -1;
				}
				
				ComercBBDD comercBBDD = new ComercBBDD();
				
				return new Contracte
						(rs.getString("infoContracte"),rs.getDate("dataAlta"),rs.getDate("dataBaixa"), percentatge, 
								pagament, comercBBDD.recuperarComerc(rs.getInt("idComerc")),rs.getInt("idContracte"));
				}
			return null;
		}catch (Exception e) {
			throw new Exception("Error recuperarContracte - " + e.getMessage());
		}
	}
	public Contracte recuperarContracteComerc(int idComerc) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT infocontracte, dataalta, idcontracte FROM Contracte WHERE idComerc = ? AND databaixa IS NULL");
			pst.clearParameters();
			pst.setInt(1, idComerc);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				ComercBBDD comercBBDD = new ComercBBDD();
				
				return new Contracte(rs.getString("infocontracte"),
						rs.getDate("dataalta"),rs.getDate("databaixa"),rs.getDouble("percentatge"),rs.getDouble("pagament"),comercBBDD.recuperarComerc(rs.getInt("idcomerc")),rs.getInt("idContracte"));
			}
			return null;
		} catch (Exception e) {
			throw new Exception("Error recuperarContracteComerc - " + e.getMessage());
		}
	}

	public void guardarContracteModificat(Contracte contracte, int idComerc)
			throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Contracte SET informacio = ? WHERE idComerc = ?");
			pst.clearParameters();
			pst.setString(1, contracte.getInformacio());
			pst.setInt(2, idComerc);
			pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error guardarContracteModificat - "
					+ e.getMessage());
		}
	}

	public void posarDataBaixa(Contracte contracte) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Contracte SET databaixa = ? WHERE idcontracte = ?");
			pst.clearParameters();
			long data = contracte.getDataBaixa().getTime();
			pst.setDate(1, new Date(data));
			pst.setInt(2, contracte.getId());
			pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error posarDataBaixa - " + e.getMessage());
		}

	}

	public void inserirContracte(int idComerc, Contracte contracte)
			throws Exception {
		try {
			PreparedStatement pst;
			String sql;
			if(contracte.getPagament()==-1){
				sql = "INSERT INTO Contracte(idcontracte, infocontracte, dataalta, idcomerc, percentatge) VALUES(?,?,?,?,?)";
				
			}else{
				sql = "INSERT INTO Contracte(idcontracte, infocontracte, dataalta, idcomerc, pagament) VALUES(?,?,?,?,?)";
			}
			pst = connexio.prepareStatement(sql);
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_CONTRACTE.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			rs.next();
			int retorn = rs.getInt(1);
			contracte.setId(retorn);
			pst.setInt(1, retorn);
			pst.setString(2, contracte.getInformacio());
			long data = contracte.getDataAlta().getTime();
			pst.setDate(3, new Date(data));
			pst.setInt(4, idComerc);
			if(contracte.getPagament()==-1){
				pst.setDouble(5, contracte.getPercentatge());				
			}else{
				pst.setDouble(5, contracte.getPagament());
			}			
			if (pst.executeUpdate() != 1) {
				throw new Exception("Contracte inserit incorrectament");
			}
		} catch (Exception e) {
			throw new Exception("Error inserirContracte - " + e.getMessage());
		}
	}

	public int getIdContracte(String idComerc) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idContracte FROM Contracte WHERE idComerc = ?");
			pst.clearParameters();
			pst.setString(1, idComerc);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("idContracte");
			} else {
				return -1;
			}
		} catch (Exception e) {
			throw new Exception("Error getIdContracte - " + e.getMessage());
		}
	}

}
