package Aplicacio;

import java.util.LinkedList;

import Domini.Carcassa;
import Domini.Maquina;
import Domini.Placa;
import Domini.Tecnic;
import Persistencia.*;

public class ControladorMaquina {

	private Maquina maquina;
	private MaquinaBBDD maquinaBBDD;
	private TecnicBBDD tecnicBBDD;
	private PlacaBBDD placaBBDD;
	private CarcassaBBDD carcassaBBDD;

	public ControladorMaquina() throws Exception {
		maquinaBBDD = new MaquinaBBDD();
		tecnicBBDD = new TecnicBBDD();
		placaBBDD = new PlacaBBDD();
		carcassaBBDD = new CarcassaBBDD();
	}

	//Mètode que genera una llista de les id dels tècnics que poden muntar una màquina.
	public LinkedList<Integer> obtenirTecnics() throws Exception {
		try {
			return this.tecnicBBDD.obtenirTecnicsPerMuntar();
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnics - " + e.getMessage());
		}
	}

	//Mètode que genera una llista de les id de les plaques que no estan ocupades i es poden utilitzar
	public LinkedList<Integer> obtenirLListaPlaques() throws Exception {
		try {
			return this.placaBBDD.obtenirPlaques();
		} catch (Exception e) {
			throw new Exception("Error obtenirLListaPlaques - "
					+ e.getMessage());
		}
	}

	//Mètode que genera una llista de les id de les carcasses que no estan ocupades i es poden utilitzar
	public LinkedList<Integer> obtenirLListaCarcasses() throws Exception {
		try {
			return this.carcassaBBDD.obtenirCarcasses();
		} catch (Exception e) {
			throw new Exception("Error obtenirLListaCarcasses - "
					+ e.getMessage());
		}
	}

	//Mètode que recupera de la base de dades la informació necessària per a crear una màquina
	public int muntarMaquina(int idTecnic, int idPlaca, int idCarcassa)
			throws Exception {
		try {
			Tecnic tecnic = tecnicBBDD.recuperarTecnic(idTecnic);
			Placa placa = placaBBDD.recuperarPlaca(idPlaca);
			Carcassa carcassa = carcassaBBDD.recuperarCarcassa(idCarcassa);
			maquina = new Maquina(carcassa, placa, tecnic);
			carcassa.setOcupada();
			placa.setOcupada();
			placaBBDD.ocuparPlaca(placa);
			carcassaBBDD.ocuparCarcassa(carcassa);
			return maquinaBBDD.inserirMaquina(maquina);
		} catch (Exception e) {
			throw new Exception("Error muntarMaquina - " + e.getMessage());
		}
	}

	//Mètode que retorna una llista amb els id de les màquines que han de ser revisades
	public LinkedList<Integer> obtenirMaquinesRevisar() throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines("REVISAR");
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesRevisar - "
					+ e.getMessage());
		}
	}

	//Mètode que retorna una llista amb els id de les màquines que shan de tornar a muntar 
	public LinkedList<Integer> obtenirMaquinesReMuntar() throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines("TORNAR A MUNTAR");
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesReMuntar - "
					+ e.getMessage());
		}
	}

	//Mètode que rebent l'id d'una màquina escull un tècnic que la pugui revisar
	public LinkedList<Integer> obtenirTecnicsRevisar(int idMaquina)
			throws Exception {
		try {
			return this.tecnicBBDD.obtenirTecnicsPerRevisar(maquinaBBDD
					.recuperarMaquina(idMaquina));
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnicsRevisar - "
					+ e.getMessage());
		}
	}

	//Mètode que rebent l'id de la màquina que es vol tornar a muntar la torna a muntar i deixa pendent de revisar
	public void tornarMuntar(int idMaquina) throws Exception {
		try {

			Maquina maquina = maquinaBBDD.recuperarMaquina(idMaquina);
			maquina.setEstatRevisar();
			maquinaBBDD.modificarEstat(maquina);
		} catch (Exception e) {
			throw new Exception("Error tornarMuntar - " + e.getMessage());
		}

	}

	//Mètode que, segons el que ha decidit el tècnic amb l'id passat com a parametre, canvia l'estat de la màquina
	//(idMaquina) a pendent de tornar a ser muntada(false) o llesta (true)
	public void canviarEstatOK(int idTecnic, int idMaquina, boolean ok)
			throws Exception {
		try {
			Maquina maquina = maquinaBBDD.recuperarMaquina(idMaquina);
			maquina.setTecnicRevisar(tecnicBBDD.recuperarTecnic(idTecnic));
			if (ok)
				maquina.setEstatLLesta();
			else
				maquina.setEstatTornarAMuntar();
			maquinaBBDD.setTecnicRevisar(maquina);
			} catch (Exception e) {
			throw new Exception("Error canviarEstatOK - " + e.getMessage());
		}
	}

}
