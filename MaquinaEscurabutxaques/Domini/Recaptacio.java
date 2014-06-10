package Domini;

import java.util.Date;

public class Recaptacio {
	private Maquina maquina;
	private Contracte contracte;
	private int dinersMaquina;
	private Date data;
	
	public Recaptacio(Maquina maquina, Contracte contracte, int dinersMaquina){
		this.maquina=maquina;
		this.contracte=contracte;
		this.dinersMaquina=dinersMaquina;
		this.data= new Date();
}
	
	public Recaptacio(Maquina maquina, Contracte contracte, int dinersMaquina,Date data){
		this.maquina=maquina;
		this.contracte=contracte;
		this.dinersMaquina=dinersMaquina;
		this.data= data;
}
	
	public String generarInforme(){
		String informe;		
		double pagamentClient=this.contracte.getPercentatge();
		String client= this.contracte.getComerc().getTipus();
		if(client == "MINORISTA"){
			informe= ("El contracte: "+this.contracte.getId() + "�s d'un minorista. Li hem pagat "+pagamentClient
					+"hi la m�quina ha recullit: "+dinersMaquina);
		}
		else{
			pagamentClient= pagamentClient*dinersMaquina/100;
			informe= ("El contracte: "+this.contracte.getId() + "�s d'un majorista. Ha recollit "+pagamentClient
					+"hi la m�quina ens ha donat de benefici: "+(dinersMaquina-pagamentClient));
		}
		return informe;
	}

	
	public int getIdMaquina() {
		return maquina.getId();
	}

	public int getIdContracte() {
		return contracte.getId();
	}

	public int getDinersMaquina() {
		return dinersMaquina;
	}

	public Date getData() {
		return data;
	}
	
}
