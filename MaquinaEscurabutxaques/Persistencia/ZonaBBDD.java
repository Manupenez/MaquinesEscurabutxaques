package Persistencia;

public class ZonaBBDD {

	private ConnexioBBDD connexio;

	public ZonaBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
	}

	public void tancarConnexio() throws Exception {
		connexio.close();
	}	
	
	// El cas d'ús gestió zona no ha estat implementat, aquests mètodes de
		// moment no es fan servir en el nostre programa.
}
