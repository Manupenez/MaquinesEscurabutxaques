package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Domini.Contracte;
import Domini.Maquina;

public class LineaContracteBBDD {

	private ConnexioBBDD connexio;

	public LineaContracteBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
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

	public LinkedList<Integer> eliminarlinies(Contracte contracte)
			throws Exception {
		try {
			String sql = "SELECT idMaquina FROM LineaContracte WHERE idContracte = ?";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.clearParameters();
			pst.setInt(1, contracte.getId());
			ResultSet rs = pst.executeQuery();
			LinkedList<Integer> llista = new LinkedList<Integer>();
			while (rs.next()) {
				llista.add(rs.getInt("idMaquina"));
			}
			PreparedStatement pst2 = connexio
					.prepareStatement("UPDATE LineaContracte SET databaixa = ? WHERE idContracte = ?");
			pst2.clearParameters();
			long data = contracte.getDataBaixa().getTime();
			pst2.setDate(1, new Date(data));
			pst2.setInt(2, contracte.getId());
			pst2.executeQuery();
			return llista;
		} catch (Exception e) {
			throw new Exception("Error eliminarlinies - " + e.getMessage());
		}

	}
}
