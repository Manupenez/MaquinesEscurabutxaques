package Domini;

public class Tecnic {
	
	private int id;
	private String nom;
	private int sou;
	private Zona zona;
	
	//Construcor de tècnic
	public Tecnic(int id, String nom, int sou, Zona zona){
		this.id = id;
		this.nom = nom;
		this.sou = sou;
		this.zona = zona;
	}
	
	public int getId(){
		return this.id;
	}
}
