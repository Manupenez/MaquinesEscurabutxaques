package Domini;

public class Comerc {
	
	private Zona zona;
	private String nomComerc;
	private int idComerc;
	
	public Comerc(Zona zona, String nomComerc){
		this.zona=zona;
		this.nomComerc=nomComerc;
	}
	
	public Zona getZona(){
		return this.zona;
	}
}
