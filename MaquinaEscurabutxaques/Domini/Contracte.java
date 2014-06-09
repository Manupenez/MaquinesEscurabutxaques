package Domini;

import java.util.Date;

public class Contracte {

	private String infoContracte;
	private Date dataAlta, dataBaixa;
	private int id;
	private double percentatge, pagament;
	private Comerc comerc;

	  public Contracte(String infoContracte, Date dataA, double percentatge,
				double pagament, Comerc comerc) {
	    	this.infoContracte = infoContracte;
			this.dataAlta = dataA;
			this.percentatge = percentatge;
			this.pagament = pagament;
			this.comerc= comerc;
	    }
		public Contracte(String infoContracte, Date dataA, Date dataB, double percentatge,
				double pagament, Comerc comerc, int id) {
			this(infoContracte,dataA,percentatge,pagament,comerc);
			this.id= id;
			this.dataBaixa=dataB;
		}

	public void setInformacio(String info) {
		this.infoContracte = info;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public int getId() {
		return id;
	}

	public String getInformacio() {
		return this.infoContracte;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	
	public double getPagament(){
		return this.pagament;
	}
	
	public double getPercentatge(){
		return this.percentatge;
	}
	
	public Comerc getComerc(){
		return this.comerc;
	}
	
	public String getTipusComerc() {
		return this.comerc.getTipus();
	}
}
