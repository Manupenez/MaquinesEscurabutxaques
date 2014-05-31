package Domini;

public class Comerc {
	
	private Zona zona;
	private String nomComerc;
	private int idComerc;
	private String tipus; 
	
	
	public Comerc(Zona zona, String nomComerc, String tipus){
		this.zona=zona;
		this.nomComerc=nomComerc;
		this.tipus= tipus;
	}
	
	public Zona getZona(){
		return this.zona;
	}

	public String getTipus() {
		return tipus;
	}
}
