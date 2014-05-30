package Domini;

import java.util.Date;

public class Recaptacio {
	private Maquina maquina;
	private Contracte contracte;
	private int recaptacio;
	public Date data;
	
	public Recaptacio(Maquina maquina, Contracte contracte, int recaptacio, Date data){
		this.maquina=maquina;
		this.contracte=contracte;
		this.recaptacio=recaptacio;
		this.data=data;
	}
	
	public String generarInforme(int dinersRecullits){
		String informe;
		int dinersClient=this.contracte.getPercentatge();
		String client= this.contracte.getTipusComerc();
		if(client == "minorista"){
			informe= ("El contracte: "+this.contracte.getId() + "d'un minorista. Ha rebut "+dinersClient
					+"hi la màquina ha recullit: "+dinersRecullits);
		}
		else{
			dinersClient= dinersClient*dinersRecullits/100;
			informe= ("El contracte: "+this.contracte.getId() + "d'un majorista. Ha rebut "+dinersClient
					+"hi la màquina ens ha donat de benefici: "+(dinersRecullits-dinersClient));
		}
		return informe;
	}
}
