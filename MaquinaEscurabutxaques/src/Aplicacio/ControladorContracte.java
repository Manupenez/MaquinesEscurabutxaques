package Aplicacio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Domini.Comerc;
import Domini.Contracte;
import Domini.Maquina;
import Persistencia.ContracteBBDD;
import Persistencia.ComercBBDD;
import Persistencia.LineaContracteBBDD;
import Persistencia.MaquinaBBDD;

public class ControladorContracte {

	private ContracteBBDD contracteBBDD;
	private ComercBBDD comercBBDD;
	private MaquinaBBDD maquinaBBDD;
	private LineaContracteBBDD lineaContracteBBDD;

	public ControladorContracte() throws Exception {
		contracteBBDD = new ContracteBBDD();
		comercBBDD = new ComercBBDD();
		maquinaBBDD = new MaquinaBBDD();
		lineaContracteBBDD = new LineaContracteBBDD();
	}

	/**
	 * Retorna el contracte del comerç passat per paràmetre
	 * 
	 * @param idComerc
	 * @return Contracte
	 * @throws Exception
	 */
	public Contracte aconseguirContracte(int idComerc) throws Exception {
		// canviat el return void a return int
		try {
			return this.contracteBBDD.recuperarContracteActual(idComerc);
		} catch (Exception e) {
			throw new Exception("Error aconseguirContracte - " + e.getMessage());
		}
	}

	// clonar el contracte amb idnou(assignat per bbdd), donar de baixa
	// LiniaContracte i ferles de nou
	// afegir a LC maquines,etc.
	/**
	 * Posa data baixa al contracte anterior i en crea un de nou amb la
	 * informació modificada.
	 * 
	 * @param idComerc
	 * @param info
	 * @param idmaquines
	 * @param percentatge
	 * @param pagament
	 * @throws Exception
	 */
	public void modificarContracte(int idComerc, String info,
			LinkedList<Integer> idmaquines, double percentatge, double pagament)
			throws Exception {
		try {
			this.baixaContracte(this.aconseguirContracte(idComerc).getId());
			this.nouContracte(idComerc, info, idmaquines, percentatge, pagament);
		} catch (Exception e) {
			throw new Exception("Error modificarContracte - " + e.getMessage());
		}
	}

	/**
	 * Posa la data de baixa d'un contracte com a la data actual, elimina les
	 * lineas de contracte i posa l'estat de les màquines a lliure.
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void baixaContracte(int id) throws Exception {
		try {
			Contracte contracte = this.contracteBBDD
					.recuperarContracteActual(id);
			contracte.setDataBaixa(new Date());
			contracteBBDD.posarDataBaixa(contracte);
			LinkedList<Integer> idmaquines = lineaContracteBBDD
					.eliminarlinies(contracte);
			for (Integer idmaquina : idmaquines) {
				Maquina maquina = maquinaBBDD.recuperarMaquina(idmaquina);
				maquina.setEstatLLesta();
				maquinaBBDD.modificarEstat(maquina);
			}
		} catch (Exception e) {
			throw new Exception("Error baixaContracte - " + e.getMessage());
		}
	}

	/** Retorna els comerços que no tenen cap contracte vigent.
	 * @return LinkedList<Integer>
	 * @throws Exception
	 */
	public LinkedList<Integer> comercSenseContracte() throws Exception {
		try {
			return comercBBDD.senseContracte();
		} catch (Exception e) {
			throw new Exception("Error comercSenseContracte - "
					+ e.getMessage());
		}
	}

	/** Retorna les màquines que com a estat el que li passen per paràmetre. 
	 * @param estat
	 * @return LinkedList<Integer>
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirMaquinesLlestes(String estat)
			throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines(estat);
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesLlestes - "
					+ e.getMessage());
		}
	}

	/** Crea un contracte amb la informació passada per paràmetres, retorna la ID del contracte.
	 * @param idComerc
	 * @param info
	 * @param idmaquines
	 * @param percentatge
	 * @param pagament
	 * @return id del contracte creat
	 * @throws Exception
	 */
	public int nouContracte(int idComerc, String info,
			LinkedList<Integer> idmaquines, double percentatge, double pagament)
			throws Exception {
		try {
			Contracte contracte = new Contracte(info, new Date(), percentatge,
					pagament, this.comercBBDD.recuperarComerc(idComerc));
			contracteBBDD.inserirContracte(idComerc, contracte);
			for (Integer m : idmaquines) {
				Maquina maquina = maquinaBBDD.recuperarMaquina(m);
				maquina.setEstatEnUnComerc();
				maquinaBBDD.modificarEstat(maquina);
				lineaContracteBBDD.inserirlineaContracte(contracte, maquina);
			}
			return contracte.getId();
		} catch (Exception e) {
			throw new Exception("Error nouContracte - " + e.getMessage());
		}
	}

	/** Retorna els comerços que tenen contractes vigents.
	 * @return LinkedList<Integer>
	 * @throws Exception
	 */
	public LinkedList<Integer> comercAmbContracte() throws Exception {
		try {
			return comercBBDD.ambContracteActiu();
		} catch (Exception e) {
			throw new Exception("Error comercAmbContracte - " + e.getMessage());
		}
	}

	/** Retorna el tipus del comerç que se li ha passat per paràmetre.
	 * @param idComerc
	 * @return Tipus del comerç
	 * @throws Exception
	 */
	public String mirarTipusComerc(int idComerc) throws Exception {
		Comerc comerc;
		try {
			comerc = comercBBDD.recuperarComerc(idComerc);
			return comerc.getTipus();
		} catch (Exception e) {
			throw new Exception("Error mirarTipusComerc - " + e.getMessage());
		}

	}

}
