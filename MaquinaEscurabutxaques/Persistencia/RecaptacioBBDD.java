package Persistencia;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Domini.Maquina;
import Domini.Recaptacio;

public class RecaptacioBBDD {

	private ConnexioBBDD connexio;
	
	public RecaptacioBBDD() throws Exception{
		this.connexio = this.connexio.getConnexioBBDD();
	}
	public void inserirRecaptacio(Recaptacio recaptacio) throws Exception {
		try{
		PreparedStatement pst =this.connexio.prepareStatement("INSERT INTO Recaptacio(recaptacio,data,idcontracte,idmaquina) VALUES (?,?,?,?)");
		pst.setInt(1, recaptacio.getDinersMaquina());
		long data = recaptacio.getData().getTime();
		pst.setDate(2, new Date(data));
		pst.setInt(3, recaptacio.getIdContracte());
		pst.setInt(4, recaptacio.getIdMaquina());
		}catch(Exception e){
			throw new Exception("Error al inserir recaptacio "+e.getMessage());
		}
		
	}
	//Recupera l'objecte recaptacio a partir del seu ID
	public Recaptacio recuperarRecaptacio(int idRecaptacio) throws Exception{
		try{
			PreparedStatement pst = this.connexio.prepareStatement("SELECT * FROM recaptacio WHERE idRecaptacio = ?");
			pst.clearParameters();
			pst.setInt(1, idRecaptacio);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				MaquinaBBDD maquinaBBDD = new MaquinaBBDD();
				ContracteBBDD contracteBBDD = new ContracteBBDD();
				return new Recaptacio(maquinaBBDD.recuperarMaquina(rs.getInt("idmaquina")),contracteBBDD.recuperarContracte(rs.getInt("idContracte")),rs.getInt("recaptacio"),rs.getDate("data"));
			}
			return null;
		}catch(Exception e){
			throw new Exception("recuperarRecaptacio - "+e.getMessage());
		}
	}
	//Retorna els diners que ha recollit una màquina en una data en concret
	public double recuperarDinersRecaptacio(Date data, int idMaquina) throws Exception {
		try{
			PreparedStatement pst = this.connexio.prepareStatement("SELECT recaptacio FROM recaptacio WHERE data = ? AND idmaquina = ? ");
			pst.clearParameters();
			pst.setDate(1, data);
			pst.setInt(2, idMaquina);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return rs.getInt("recaptacio");
			}
			return -1;
		}catch(Exception e){
				throw new Exception("recuperarDinersRecaptacio - "+e.getMessage());
			}
	}
	//Retorna un llistat de les dates en que s'ha fet recaptació en una màquina
	public LinkedList<Date> recuperarDatesRecaptacio(int idMaquina) throws Exception {
		try{
			PreparedStatement pst = this.connexio.prepareStatement("SELECT data FROM recaptacio WHERE idMaquina = ?");
			pst.clearParameters();
			pst.setInt(1, idMaquina);
			ResultSet rs = pst.executeQuery();
			LinkedList<Date> dates = new LinkedList<Date>();
			while(rs.next()){
				dates.add(rs.getDate("data"));				
			}
			return dates;			
		}catch(Exception e){
			throw new Exception("recuperar Recaptacio - "+e.getMessage());
			}
	}
	 
	
}
