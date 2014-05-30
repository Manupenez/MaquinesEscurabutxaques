package Domini;

import java.util.Date;

public class Reparacio {
	
	private Date dataIniciReparacio;
	private String estat;
	private Maquina maquina;
	private int idReparacio;
	private Tecnic tecnic;
	private final String perReperar="PER REPARAR";
	private final String finalitzada="FINALITZADA";
	
	public Reparacio (Maquina maquina, Tecnic tecnic, Date data){		
		this.dataIniciReparacio = data;
		this.estat = this.perReperar;		
		this.maquina = maquina;	
		this.tecnic=tecnic;
	}
	public Reparacio(Date data,String estat, int idReparacio, Tecnic tecnic, Maquina maquina){
		this(maquina,tecnic,data);
		this.idReparacio = idReparacio;	
	}

	public Date getData() {
		return dataIniciReparacio;
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
