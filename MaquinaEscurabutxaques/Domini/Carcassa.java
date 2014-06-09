package Domini;

public class Carcassa {

	private int id;
	private String estat;
	private int preu;
	private static final String ocupada ="OCUPADA";
	private static final String lliure ="LLIURE";
	private static final String noOperativa ="NO OPERATIVA";
	
	//Construcor de carcassa 
	public Carcassa(int id, String estat, int preu){
		this.id = id;
		this.estat = estat;
		this.preu = preu;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getEstat(){
		return this.estat;
	}

	public void setLLiure() {
		this.estat = this.lliure;
	}

	public void setOcupada(){
		this.estat = this.ocupada;
	}

	public void setNoOperativa(){
		this.estat=this.noOperativa;
	}
	
	
}
