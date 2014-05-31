package Domini;

import java.util.Date;

public class Contracte {

	private String infoContracte;
	private Date dataAlta, dataBaixa;
	private int id;
	private double percentatge, pagament;

	public Contracte(String infoContracte, Date dataA, int id) {
		this.infoContracte = infoContracte;
		this.dataAlta = dataA;
		this.id = id;

	}

	public Contracte(String infoContracte, Date dataA, double percentatge,
			double pagament) {
		this.infoContracte = infoContracte;
		this.dataAlta = dataA;
		this.percentatge = percentatge;
		this.pagament = pagament;

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

}
