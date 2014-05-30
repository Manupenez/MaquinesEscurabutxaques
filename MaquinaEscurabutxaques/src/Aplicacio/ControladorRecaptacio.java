package Aplicacio;

import Domini.Contracte;
import Domini.Maquina;
import Domini.Recaptacio;
import Persistencia.ContracteBBDD;
import Persistencia.MaquinaBBDD;
import Persistencia.RecaptacioBBDD;
import Persistencia.ReparacioBBDD;

public class ControladorRecaptacio {
	
	private RecaptacioBBDD recaptacioBBDD;
	private MaquinaBBDD maquinaBBDD;
	private ContracteBBDD contracteBBDD;
	
	public ControladorRecaptacio() throws Exception{
		this.recaptacioBBDD = new RecaptacioBBDD();
		this.maquinaBBDD = new MaquinaBBDD();
		this.contracteBBDD = new ContracteBBDD ();
	}
	
	public void crearInformeRecaptacio(int idmaquina, int idcontracte, int dinersMaquina) throws Exception{
		try{
		Maquina maquina = maquinaBBDD.recuperarMaquina(idmaquina);
		Contracte contracte = contracteBBDD.recuperarContracteActual(idcontracte);		
		Recaptacio recaptacio = new Recaptacio(maquina, contracte, dinersMaquina);	
		this.recaptacioBBDD.inserirRecaptacio(recaptacio);
		}catch (Exception e) {
			throw new Exception ("Error crearInformeRecaptacio: "+e.getMessage());
		}
	}
	public Recaptacio recuperarRecaptacio(int idrecaptacio){
		return this.recaptacioBBDD.recuperarRecaptacio(idrecaptacio);
	}
	
}
