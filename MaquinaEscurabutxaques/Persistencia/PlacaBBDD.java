package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Domini.Carcassa;
import Domini.Placa;

public class PlacaBBDD {

	private ConnexioBBDD connexio;

	public PlacaBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	// Mètode que posa a ocupada o a lliure una placa amb id passat per
	// paràmetre
	public void ocuparPlaca(Placa placa) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Placa SET estatplaca = ? WHERE idPlaca = ?");
			pst.clearParameters();
			pst.setString(1,placa.getEstat());
			pst.setInt(2, placa.getId());
			ResultSet rs = pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error ocuparPlaca - " + e.getMessage());
		}
	}

	// Mètode que crea una placa recuperant la seva informacio de la BBDD amb el
	// seu id
	public Placa recuperarPlaca(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT preuplaca, estatPlaca FROM Placa WHERE idPlaca = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs;
//			ocuparPlaca(id, true);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Placa(id, rs.getString("estatPlaca"),
						rs.getInt("preuplaca"));
			} else {
				throw new Exception("Error no Existeix la Placa");
			}
		} catch (Exception e) {
			throw new Exception("Error crearPlaca - " + e.getMessage());
		}
	}

	// Mètode que retorna una llista de les plaques que estan lliures
	public LinkedList<Integer> obtenirPlaques() throws Exception {
		try {
			String sql = "SELECT idPlaca FROM Placa WHERE estatPlaca = ?";
			LinkedList<Integer> plaques = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setString(1, "LLIURE");
			rs = pstm.executeQuery();
			while (rs.next()) {
				plaques.add(rs.getInt("idPlaca"));
			}
			return plaques;
		} catch (Exception e) {
			throw new Exception("Error obtenirPlaques - " + e.getMessage());
		}
	}
}
