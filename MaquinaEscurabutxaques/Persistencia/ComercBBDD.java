package Persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import Domini.Comerc;
import Domini.Contracte;
import Domini.Zona;

public class ComercBBDD {

	private ConnexioBBDD connexio;

	public ComercBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}

	public LinkedList<Integer> senseContracte() throws Exception {
		try {
			LinkedList<Integer> noContracte = new LinkedList<Integer>();
			String sql = "SELECT idComerc FROM comerc WHERE idComerc NOT IN (SELECT idComerc FROM contracte WHERE databaixa IS NULL)";
			ResultSet rs;
			Statement st = connexio.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				noContracte.add(rs.getInt("idComerc"));
			}
			return noContracte;
		} catch (Exception e) {
			throw new Exception("Error senseContracte - " + e.getMessage());
		}
	}

	public LinkedList<Integer> ambContracteActiu() throws Exception {
		try {
			LinkedList<Integer> ambContracte = new LinkedList<Integer>();
			String sql = "SELECT idComerc FROM contracte WHERE databaixa IS NULL";
			ResultSet rs;
			Statement st = connexio.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ambContracte.add(rs.getInt("idComerc"));
			}
			return ambContracte;
		} catch (Exception e) {
			throw new Exception("Error ambContracteActiu - " + e.getMessage());
		}
	}

	public Comerc recuperarComerc(int idComerc) throws Exception {
		try {
			PreparedStatement pst = connexio
					.prepareStatement("SELECT nomComerc, tipus, zona FROM Comerc WHERE idComerc = ?");
			pst.clearParameters();
			pst.setInt(1, idComerc);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Comerc(new Zona(rs.getString("zona")),rs.getString("nomComerc"), rs.getString("tipus"));
			}
			return null;
		} catch (Exception e) {
			throw new Exception("Error recuperarComerc - " + e.getMessage());
		}		
	}

}
