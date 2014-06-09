package Aplicacio;

import java.util.LinkedList;

import Domini.Carcassa;
import Domini.Maquina;
import Domini.Placa;
import Domini.Tecnic;
import Persistencia.*;
//he afegit un metode obtenirMaquines()
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

	/**MËtode que genera una llista de les id dels tËcnics que poden muntar una m‡quina.
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirTecnics() throws Exception {
		try {
			return this.tecnicBBDD.obtenirTecnicsPerMuntar();
		} catch (Exception e) {
			throw new Exception("Error obtenirTecnics - " + e.getMessage());
		}
	}

	/**MËtode que genera una llista de les id de les plaques que no estan ocupades i es poden utilitzar
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirLListaPlaques() throws Exception {
		try {
			return this.placaBBDD.obtenirPlaques();
		} catch (Exception e) {
			throw new Exception("Error obtenirLListaPlaques - "
					+ e.getMessage());
		}
	}

	/**	MËtode que genera una llista de les id de les carcasses que no estan ocupades i es poden utilitzar
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirLListaCarcasses() throws Exception {
		try {
			return this.carcassaBBDD.obtenirCarcasses();
		} catch (Exception e) {
			throw new Exception("Error obtenirLListaCarcasses - "
					+ e.getMessage());
		}
	}

	/**MËtode que recupera de la base de dades la informaciÛ necess‡ria per a crear una m‡quina
	 * @param idTecnic
	 * @param idPlaca
	 * @param idCarcassa
	 * @return
	 * @throws Exception
	 */
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

	//M√®tode que retorna una llista amb els id de les m√†quines que han de ser revisades
	/**
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirMaquinesRevisar() throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines("REVISAR");
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesRevisar - "
					+ e.getMessage());
		}
	}

	//M√®tode que retorna una llista amb els id de les m√†quines que shan de tornar a muntar 
	/**
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirMaquinesReMuntar() throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines("TORNAR A MUNTAR");
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesReMuntar - "
					+ e.getMessage());
		}
	}

	//M√®tode que rebent l'id d'una m√†quina escull un t√®cnic que la pugui revisar
	/**
	 * @param idMaquina
	 * @return
	 * @throws Exception
	 */
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

	/**MËtode que rebent l'id de la m‡quina que es vol tornar a muntar la torna a muntar i deixa pendent de revisar
	 * @param idMaquina
	 * @throws Exception
	 */
	public void tornarMuntar(int idMaquina) throws Exception {
		try {

			Maquina maquina = maquinaBBDD.recuperarMaquina(idMaquina);
			maquina.setEstatRevisar();
			maquinaBBDD.modificarEstat(maquina);
		} catch (Exception e) {
			throw new Exception("Error tornarMuntar - " + e.getMessage());
		}

	}


	/**MËtode que, segons el que ha decidit el tËcnic amb l'id passat com a parametre, canvia l'estat de la m‡quina
	 * (idMaquina) a pendent de tornar a ser muntada(false) o llesta (true)
	 * @param idTecnic
	 * @param idMaquina
	 * @param ok
	 * @throws Exception
	 */
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
//obtenim totes les idmaquines de la BBDD
	public LinkedList<Integer> obtenirMaquines() throws Exception{
		try {
			return this.maquinaBBDD.obtenirMaquines();
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquines - " + e.getMessage());
		}
	}

}
