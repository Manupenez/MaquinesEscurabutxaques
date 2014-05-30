package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domini.Maquina;
import Domini.Reparacio;
import Domini.Tecnic;

public class ReparacioBBDD {

	private ConnexioBBDD connexio;

	public ReparacioBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public Reparacio recuperarReparacio(int id) throws Exception {
		PreparedStatement pst = connexio
				.prepareStatement("SELECT data, idTecnic, idReparacio, estatReparacio, idMaquina FROM Reparacio WHERE idReparacio = ?");
		pst.clearParameters();
		pst.setInt(1, id);
		ResultSet rs;
		rs = pst.executeQuery();
		if (rs.next()) {
			TecnicBBDD tecnicBBDD = new TecnicBBDD();
			Tecnic tecnic = tecnicBBDD.recuperarTecnic(rs.getInt("idTecnic"));
			MaquinaBBDD maquinaBBDD = new MaquinaBBDD();
			Maquina maquina = maquinaBBDD.recuperarMaquina(rs.getInt("idMaquina"));
			return new Reparacio(rs.getDate("data"),rs.getString("estatReparacio"),rs.getInt("idReparacio"),
					tecnic, maquina);
		} else {
			throw new Exception("Error no Existeix la Reparació");
		}
	}

	public void modificarReparacio(Reparacio reparacio)
			throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("UPDATE Reparacio SET estat = ? WHERE idReparacio = ?");
			pst.clearParameters();
			pst.setString(1, reparacio.getEstat());
			pst.setInt(2, reparacio.getIdReparacio());
		} catch (Exception e) {
			throw new Exception("Error modificarReparacio - " + e.getMessage());
		}
	}

	public void inserirReparacio(Reparacio reparacio)
			throws Exception {
		try {
			String sql = "INSERT INTO Reparacio(data, estatReparacio, idReparacio, idTecnic, idMaquina) VALUES(?,?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			PreparedStatement pst2 = connexio
					.prepareStatement("SELECT S_CONTRACTE.NEXTVAL FROM DUAL");
			pst2.clearParameters();
			ResultSet rs = pst2.executeQuery();
			rs.next();
			int retorn = rs.getInt(1);
			reparacio.setIdReparacio(retorn);
			long data = reparacio.getData().getTime();
			pst.setDate(1, new Date(data));
			pst.setString(2, reparacio.getEstat());
			pst.setInt(3, retorn);
			pst.setInt(4, reparacio.getTecnic().getId());
			pst.setInt(5, reparacio.getMaquina().getId());
			if (pst.executeUpdate() != 1) {
				throw new Exception("Reparació inserida incorrectament");
			}
		} catch (Exception e) {
			throw new Exception("Error inserirReparació - " + e.getMessage());
		}
	}

}
