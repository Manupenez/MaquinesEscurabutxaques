package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	
}
