package Aplicacio;

import java.util.Date;
import java.util.LinkedList;

import Domini.Contracte;
import Domini.Maquina;
import Domini.Recaptacio;
import Persistencia.ContracteBBDD;
import Persistencia.MaquinaBBDD;
import Persistencia.RecaptacioBBDD;

//he creat alguns metodes
public class ControladorRecaptacio {
	
	private RecaptacioBBDD recaptacioBBDD;
	private MaquinaBBDD maquinaBBDD;
	private ContracteBBDD contracteBBDD;
	
	public ControladorRecaptacio() throws Exception{
		this.recaptacioBBDD = new RecaptacioBBDD();
		this.maquinaBBDD = new MaquinaBBDD();
		this.contracteBBDD = new ContracteBBDD ();
	}
	
	/** M�tode que genera un informe i l'introdueix a BBDD.
	 * @param idmaquina
	 * @param idcontracte
	 * @param dinersMaquina
	 * @throws Exception
	 */
	public void crearInformeRecaptacio(int idmaquina, int idcontracte, int dinersMaquina) throws Exception{
		try{
		Maquina maquina = maquinaBBDD.recuperarMaquina(idmaquina);
		Contracte contracte = contracteBBDD.recuperarContracteComerc(idcontracte);		
		Recaptacio recaptacio = new Recaptacio(maquina, contracte, dinersMaquina);	
		this.recaptacioBBDD.inserirRecaptacio(recaptacio);
		}catch (Exception e) {
			throw new Exception ("Error crearInformeRecaptacio: "+e.getMessage());
		}
	}
	
	/** M�tode que retorna l'objecte Recaptaci� amb l'id que es passa per par�metre.
	 * @param idrecaptacio
	 * @return Recaptaci�
	 */
	public Recaptacio recuperarRecaptacio(int idRecaptacio)throws Exception{
		try{
		return this.recaptacioBBDD.recuperarRecaptacio(idRecaptacio);
		}catch(Exception e){
			throw new Exception("recuperarRecaptacio - "+e.getMessage());
		}
	}

	public double recuperarRecaptacio(Date data) throws Exception {
		if( data instanceof Date){
			try{
			return this.recaptacioBBDD.recuperarDinersRecaptacio(data);
			}catch(Exception e){
				throw new Exception("recuperarRecaptacioDATA "+e.getMessage());
			}
		}else{
			throw new Exception("El valor pasat per parametre data no �s tipus DATE");
		}
	}
	public LinkedList <Date> getDatesRecaptacioMaquina(int idMaquina){
		try{
			return this.recaptacioBBDD.recuperarDatesRecaptacio(idMaquina);
		}catch(Exception e){
			throw new Exception("getDatesRecaptacioMaquina "+e.getMessage());
		}
	}
	
}
