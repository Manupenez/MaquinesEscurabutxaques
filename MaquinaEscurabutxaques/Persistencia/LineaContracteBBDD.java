package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Domini.Contracte;
import Domini.LineaContracte;
import Domini.Maquina;

public class LineaContracteBBDD {

	private ConnexioBBDD connexio;

	public LineaContracteBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void inserirlineaContracte(Contracte cont, Maquina maquina)
			throws Exception {
		try {
			String sql = "INSERT INTO lineaContracte(idcontracte, idmaquina, dataalta) VALUES(?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.setInt(1, cont.getId());
			pst.setInt(2, maquina.getId());
			long data = cont.getDataAlta().getTime();
			pst.setDate(3, new Date(data));
			if (pst.executeUpdate() != 1) {
				throw new Exception("lineaContracte inserida incorrectament");
			}
		} catch (Exception e) {
			throw new Exception("Error inserirlineaContracte - "
					+ e.getMessage());
		}

	}

	public void donarBaixaLineas(LineaContracte lineaContracte)
			throws Exception {
		try {
			PreparedStatement pst2 = connexio
					.prepareStatement("UPDATE LineaContracte SET databaixa = ? WHERE idContracte = ?");
			pst2.clearParameters();
			long data = lineaContracte.getDataBaixa().getTime();
			pst2.setDate(1, new Date(data));
			pst2.setInt(2, lineaContracte.getIdContracte());
			pst2.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error donarBaixaLineas - " + e.getMessage());
		}
	}
	
	public LinkedList<LineaContracte> recuperarLiniesComerc(Contracte contracte) throws Exception{
		try{
			MaquinaBBDD maquinaBBDD = new MaquinaBBDD();
			ContracteBBDD contracteBBDD = new ContracteBBDD();
			LinkedList<LineaContracte> linies = new LinkedList<LineaContracte>();
			PreparedStatement pst = connexio
					.prepareStatement("SELECT dataalta, databaixa,idMaquina FROM LineaContracte WHERE idContracte = ?");
			pst.clearParameters();
			pst.setInt(1, contracte.getId());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				linies.add(new LineaContracte(rs.getDate("dataalta"), rs.getDate("databaixa"), maquinaBBDD.recuperarMaquina(rs.getInt("idMaquina")), contracte)); 
			}
			return linies;
		}catch(Exception e){
			throw new Exception("Error recuperarLiniesComerc - " + e.getMessage());
		}
		
	}
	//metode nou
		public int recuperarIdContracteMaquina(int idMaquina) throws Exception {
			try {
				PreparedStatement pst = connexio
						.prepareStatement("SELECT idContracte FROM LineaContracte WHERE idMaquina = ? AND dataBaixa IS NULL");
				pst.clearParameters();
				pst.setInt(1, idMaquina);
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
