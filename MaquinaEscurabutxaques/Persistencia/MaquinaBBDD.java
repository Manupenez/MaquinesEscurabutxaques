package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/*

 -gestio reparacions
 -gestio contracte
 -gestió recapacitó
 -informe anual minorista
 -informe anual majorista

 */

import Domini.Carcassa;
import Domini.Comerc;
import Domini.Maquina;
import Domini.Placa;
import Domini.Tecnic;

public class MaquinaBBDD {
	private ConnexioBBDD connexio;
	private CarcassaBBDD carcassaBBDD;
	private PlacaBBDD placaBBDD;
	private TecnicBBDD tecnicBBDD;

	public MaquinaBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
		this.carcassaBBDD = new CarcassaBBDD();
		this.placaBBDD = new PlacaBBDD();
		this.tecnicBBDD = new TecnicBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	// Mètode que retorna el id de la màquina
	public int obtenirIdMaquina(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idmaquina FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt("idmaquina");
			} else {
				return -1;
			}
		} catch (Exception e) {
			throw new Exception("Error obtenirIdMaquina - " + e.getMessage());
		}
	}

	// Mètode que insereix una màquina a la BBDD
	public int inserirMaquina(Maquina maquina) throws Exception {
		try {
			String sql = "INSERT INTO Maquina(idMaquina,idCarcassa,idPlaca,idTecnicMuntar,estatmaquina) VALUES(?,?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.clearParameters();
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_MAQUINA.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			rs.next();
			int retorn = rs.getInt(1);
			pst.setInt(1, retorn);
			pst.setInt(2, maquina.getIdCarcassa());
			pst.setInt(3, maquina.getIdPlaca());
			pst.setInt(4, maquina.getIdTecnicMuntar());
			pst.setString(5, maquina.getEstat());
			pst.executeUpdate();
			return retorn;
		} catch (Exception e) {
			throw new Exception("Error inserirMaquina - " + e.getMessage());
		}
	}

	// Mètode que intenta retornar una placa amb id especific
	public int getPlaca(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idPlaca FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt("idPlaca");
			} else {
				throw new Exception("Error la placa no existeix ");
			}
		} catch (Exception e) {
			throw new Exception("Error obtenirPlaca - " + e.getMessage());
		}
	}

	// Mètode que intenta retornar una carcassa amb id especific
	public int getCarcassa(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idCarcassa FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int retorn = rs.getInt("idCarcassa");
				return retorn;
			} else {
				throw new Exception("Error no existeix la carcassa ");
			}

		} catch (Exception e) {
			throw new Exception("Error getCarcassa - " + e.getMessage());
		}
	}

	// Mètode que retorna l'id del tècnic que ha muntat la màquina amb id passat
	// per paràmetre
	public int getTecnicMuntar(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idTecnicMuntar FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt("idTecnicMuntar");
			} else {
				throw new Exception("Error no existeix el tecnic ");
			}
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnicMuntar - " + e.getMessage());
		}
	}

	// Mètode que retorna l'id del tècnic que ha revisat la màquina amb l'id
	// passat per paràmetre
	public int getTecnicRevisar(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT idTecnicRevisar FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt("idTecnicRevisar");
			} else {
				throw new Exception("Error no existeix el tecnic ");
			}
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnicRevisar - "
					+ e.getMessage());
		}
	}

	// Mètode que retorna una llista amb les id de les màquines que estan en un
	// estat concret passat per paràmetre
	public LinkedList<Integer> obtenirMaquines(String estat) throws Exception {
		try {
			String sql = "SELECT idmaquina FROM Maquina WHERE estatmaquina = ?";
			LinkedList<Integer> maquines = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setString(1, estat);
			rs = pstm.executeQuery();
			while (rs.next()) {
				maquines.add(rs.getInt("idmaquina"));
			}
			return maquines;
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquines - " + e.getMessage());
		}
	}

	// Mètode que passant-li una màquina li posa el tècnic que l'ha revisat
	public void setTecnicRevisar(Maquina maquina) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Maquina SET idtecnicRevisar = ?, estatMaquina = ? WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, maquina.getIdTecnicRevisar());
			pst.setString(2, maquina.getEstat());
			pst.setInt(3, maquina.getId());
			ResultSet rs = pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error setTecnicRevisar - " + e.getMessage());
		}
	}

	// Mètode que crea maquines amb una id concreta recuperant de BBDD el que
	// necessita
	public Maquina recuperarMaquina(int id) throws Exception {
		try {
			Placa placa = placaBBDD.recuperarPlaca(this.getPlaca(id));
			Carcassa carcassa = carcassaBBDD.recuperarCarcassa(this
					.getCarcassa(id));
			Tecnic tecnicMuntar = tecnicBBDD.recuperarTecnic(this
					.getTecnicMuntar(id));
			Tecnic tecnicRevisar = tecnicBBDD.recuperarTecnic(this
					.getTecnicRevisar(id));

			PreparedStatement pst = connexio
					.prepareStatement("SELECT estatmaquina FROM Maquina WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String estat = rs.getString("estatmaquina");

			return new Maquina(carcassa, placa, tecnicMuntar, tecnicRevisar,
					estat, id);
		} catch (Exception e) {
			throw new Exception("Error crearMaquina - " + e.getMessage());
		}

	}

	// Mètode que modifica l'estat de la màquina a la BBDD
	public void modificarEstat(Maquina maquina) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Maquina SET estatmaquina = ? WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(2, maquina.getId());
			pst.setString(1, maquina.getEstat());
			ResultSet rs = pst.executeQuery();
		} catch (Exception e) {
			throw new Exception("Error modificarEstat - " + e.getMessage());
		}
	}

	public LinkedList<Integer> obtenirMaquinesXComerc(Comerc comerc)
			throws Exception {
		try {
			String sql = ("select m.idmaquina from maquina m "
					+ "join lineacontracte lc on (m.idmaquina = lc.idmaquina) "
					+ "join contracte c on (c.idcontracte = lc.idcontracte) "
					+ "where c.idcomerc = ? and lc.dataBaixa is null and m.estatmaquina = 'EN UN COMERÇ'");
			LinkedList<Integer> maquines = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setInt(1, comerc.getId());
			rs = pstm.executeQuery();
			while (rs.next()) {
				maquines.add(rs.getInt("idmaquina"));
			}
			return maquines;
		} catch (Exception e) {
			throw new Exception("Error obtenir màquines per comerç - "
					+ e.getMessage());
		}
	}

	public LinkedList<Integer> obtenirMaquines() throws Exception {
		try {
			String sql = "SELECT idmaquina FROM Maquina";
			LinkedList<Integer> maquines = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			rs = pstm.executeQuery();
			while (rs.next()) {
				maquines.add(rs.getInt("idmaquina"));
			}
			return maquines;
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquines - " + e.getMessage());
		}
	}
}
