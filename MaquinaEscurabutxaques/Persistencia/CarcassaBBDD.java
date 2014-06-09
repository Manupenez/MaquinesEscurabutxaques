package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Domini.Carcassa;

public class CarcassaBBDD {

	private ConnexioBBDD connexio;
	
	public CarcassaBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}


	// Mètode que indica quina carcassa volem deixar lliure o ocupada
	public void ocuparCarcassa(Carcassa carcassa) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE carcassa SET estatCarcassa = ? WHERE idCarcassa = ?");
			pst.clearParameters();
			pst.setString(1, carcassa.getEstat());
			pst.setInt(2, carcassa.getId());
			ResultSet rs = pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error ocuparCarcassa - " + e.getMessage());
		}
	}
	
	//Mètode que donada una id crea una nova carcassa amb la seva informació recuperada de la BBDD
	public Carcassa recuperarCarcassa(int id) throws Exception {
		PreparedStatement pst = connexio
				.prepareStatement("SELECT preucarcassa, estatCarcassa FROM Carcassa WHERE idCarcassa = ?");
		pst.clearParameters();
		pst.setInt(1, id);
		ResultSet rs;
		rs = pst.executeQuery();
		if (rs.next()) {
			return new Carcassa(id, rs.getString("estatCarcassa"),
					rs.getInt("preucarcassa"));
		} else {
			throw new Exception("Error no Existeix la Carcassa");
		}

	}

	//Mètode que retorna una llista de totes les id de les carcasses que estan lliures
	//polla
	public LinkedList<Integer> obtenirCarcasses() throws Exception {
		try {
			String sql = "SELECT idCarcassa FROM Carcassa WHERE estatCarcassa = ?";
			LinkedList<Integer> carcasses = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setString(1, "LLIURE");
			rs = pstm.executeQuery();
			while (rs.next()) {
				carcasses.add(rs.getInt("idCarcassa"));
			}
			return carcasses;
		} catch (Exception e) {
			throw new Exception("Error obtenirCarcasses - " + e.getMessage());
		}
	}

}
