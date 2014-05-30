package Domini;

import java.util.Date;

public class Reparacio {
	
	private Date data;
	private String estat;
	private int idReparacio;
	private Tecnic tecnic;
	private Maquina maquina;
	private final String perReperar="PER REPARAR";
	private final String finalitzada="FINALITZADA";
	
	public Reparacio (Maquina maquina){		
		this.data = new Date();
		this.estat = this.perReperar;		
		this.maquina = maquina;		
	}
	public Reparacio(Date data,String estat, int idReparacio, Tecnic tecnic, Maquina maquina){
		this(maquina);
		this.idReparacio = idReparacio;	
		this.data = data;
		this.tecnic = tecnic;
	}

	public Date getData() {
		return data;
	}

	public String getEstat() {
		return estat;
	}

	public void setEstatFinalitzada() {
		this.estat = this.finalitzada;
	}
	
	public int getIdReparacio() {
		return idReparacio;
	}

	public Tecnic getTecnic() {
		return this.tecnic;
	}

	public Maquina getMaquina() {
		return maquina;
	}
	public void setIdReparacio(int id) {
		this.idReparacio=id;
	}

}
