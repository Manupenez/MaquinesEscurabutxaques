package Domini;

import java.util.Date;

public class LineaContracte {

	
	private Date dataalta;
	private Date databaixa;
	private Maquina maquina;
	private Contracte contracte;
	
	
	public LineaContracte (Date dataalta, Date databaixa, Maquina maquina, Contracte contrcate){
		this.dataalta=dataalta;
		this.databaixa=databaixa;
		this.maquina=maquina;
		this.contracte=contrcate;		
	}
	
	public Date getDataBaixa(){
		return this.databaixa;
	}
	
	public int getIdContracte(){
		return this.contracte.getId();
	}

	public void setDataBaixa(Date dataBaixa) {
		this.databaixa=dataBaixa;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}
}
