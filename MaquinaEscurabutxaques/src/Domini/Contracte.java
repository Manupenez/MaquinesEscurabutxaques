package Domini;

import java.util.Date;

public class Contracte {

	private String infoContracte;
	private Date dataAlta, dataBaixa;
	private int id;

	public Contracte(String infoContracte, Date dataA, int id) {
		this.infoContracte = infoContracte;
		this.dataAlta = dataA;
		
		this.id = id;

	}

	public Contracte(String infoContracte, Date dataA) {
		this.infoContracte = infoContracte;
		this.dataAlta = dataA;

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

}
