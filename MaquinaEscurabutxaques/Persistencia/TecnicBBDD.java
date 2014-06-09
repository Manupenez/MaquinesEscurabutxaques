package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Domini.Carcassa;
import Domini.Maquina;
import Domini.Tecnic;
import Domini.Zona;

public class TecnicBBDD {

	private ConnexioBBDD connexio;

	public TecnicBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	// Mètode que retorna una llista amb els tècncis que poden revisar una
	// màquina,
	// aquests han de ser diferents de la id passada per paràmetre que és la id
	// del tècnic muntador
	public LinkedList<Integer> obtenirTecnicsPerRevisar(Maquina maquina)
			throws Exception {
		try {
			String sql = "SELECT idTecnic FROM Tecnic WHERE idTecnic <> ?";
			LinkedList<Integer> tecnics = new LinkedList<Integer>();
			PreparedStatement pstm;
			ResultSet rs;
			pstm = connexio.prepareStatement(sql);
			pstm.clearParameters();
			pstm.setInt(1, maquina.getIdTecnicMuntar());
			rs = pstm.executeQuery();
			while (rs.next()) {
				tecnics.add(rs.getInt("idTecnic"));
			}
			return tecnics;
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnicsPerRevisar - "
					+ e.getMessage());
		}
	}

	// Mètode que retorna una llista amb tots els id dels tècnics
	public LinkedList<Integer> obtenirTecnicsPerMuntar() throws Exception {
		try {
			String sql = "SELECT idTecnic FROM Tecnic";
			LinkedList<Integer> tecnics = new LinkedList<Integer>();
			ResultSet rs;
			Statement st = connexio.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				tecnics.add(rs.getInt("idTecnic"));
			}
			return tecnics;
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnicsPerMuntar - "
					+ e.getMessage());
		}
	}

	// Mètode que recuperant de BBDD la informació necessaria crea un tècnic
	public Tecnic recuperarTecnic(int id) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT nomTecnic, sou, nomZona FROM Tecnic WHERE idTecnic = ?");
			pst.clearParameters();
			pst.setInt(1, id);
			ResultSet rs;
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Tecnic(id, rs.getString("nomTecnic"),
						rs.getInt("sou"), new Zona(rs.getString("nomZona")));
			} else {
				throw new Exception("Error no Existeix el Tecnic");
			}
		} catch (Exception e) {
			throw new Exception("Error crearTecnic - " + e.getMessage());
		}
	}
	public Tecnic getTecnicMenysReparacionsZona(Zona zona) throws Exception {
		try{
			PreparedStatement pst = connexio.prepareStatement("select idtecnic,NOMTECNIC "
					+ "from (select t.idtecnic,t.NOMTECNIC, count(r.idtecnic)"
					+ "from tecnic t left join REPARACIO r on (t.idtecnic = r.idtecnic)"
					+ "where nomZona = ?"
					+ "GROUP BY t.IDTECNIC, t.NOMTECNIC"
					+ "order by count(r.idtecnic))"
					+ "where rownum=1");
			pst.clearParameters();
			pst.setString(1, zona.getNomZona());
			ResultSet rs;
			rs = pst.executeQuery();
			if (rs.next()) {				
				return this.recuperarTecnic(rs.getInt("idtecnic"));
			} else {
				throw new Exception("Error no Trobem el Tecnic");
			}
		}catch(Exception e){
			throw new Exception(""+e.getMessage());
		}
		
		
	}

}
