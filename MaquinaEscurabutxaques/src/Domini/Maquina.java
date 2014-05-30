package Domini;

public class Maquina {
	
	private Carcassa carcassa;
	private Placa placa;
	private Tecnic tecnicMuntar;
	private Tecnic tecnicRevisar;
	private String estat;
	private int id;
	private static final String revisar= "REVISAR";
	private static final String llesta="LLESTA";
	private static final String tornarMuntar="TORNAR A MUNTAR";
	private static final String espatllada="ESPATLLADA";
	private static final String enUnComerc="EN UN COMER�";
	
	//Constructor bàsic de màquina
	public Maquina(Carcassa carcassa, Placa placa, Tecnic tecnic){
		this.carcassa=carcassa;
		this.placa=placa;
		this.tecnicMuntar=tecnic;
		this.estat=this.revisar;
	}
	
	//Constructor complert de màquina amb la informació sencera
	public Maquina(Carcassa carcassa, Placa placa, Tecnic tecnicMuntar,
			Tecnic tecnicRevisar, String estat, int id) {
		this.id = id;
		this.carcassa=carcassa;
		this.placa = placa;
		this.tecnicMuntar = tecnicMuntar;
		this.tecnicRevisar = tecnicRevisar;
		this.estat = estat;
	}
	public Carcassa getCarcassa() {
		return carcassa;
	}
	public void setCarcassa(Carcassa carcassa) {
		this.carcassa = carcassa;
	}
	public Placa getPlaca() {
		return placa;
	}
	public void setPlaca(Placa placa) {
		this.placa = placa;
	}
	public Tecnic getTecnicMuntar() {
		return tecnicMuntar;
	}
	public void setTecnicMuntar(Tecnic tecnicMuntar) {
		this.tecnicMuntar = tecnicMuntar;
	}
	public Tecnic getTecnicRevisar() {
		return tecnicRevisar;
	}
	public void setTecnicRevisar(Tecnic tecnicRevisar) {
		this.tecnicRevisar = tecnicRevisar;
	}
	public String getEstat() {
		return estat;
	}
	public void setEstatRevisar() {
		this.estat = this.revisar;
	}
	public int getId() {
		return this.id;
	}
	
	public int getIdCarcassa(){
		return this.carcassa.getId();
	}
	
	public int getIdPlaca(){
		return this.placa.getId();
	}
	
	public int getIdTecnicMuntar(){
		return this.tecnicMuntar.getId();
	}
	
	public int getIdTecnicRevisar(){
		return this.tecnicRevisar.getId();
	}

	public void setEstatLLesta() {
		this.estat = this.llesta;
	}

	public void setEstatTornarAMuntar() {
		this.estat=this.tornarMuntar;
	}
	
	public void setEstatEnUnComerc(){
		this.estat = this.enUnComerc;
	}
		
}
