package Aplicacio;

import java.util.Date;
import java.util.LinkedList;

import Domini.Comerc;
import Domini.Contracte;
import Domini.Maquina;
import Persistencia.ContracteBBDD;
import Persistencia.ComercBBDD;
import Persistencia.LineaContracteBBDD;
import Persistencia.MaquinaBBDD;

//pene
public class ControladorContracte {

	private Contracte contracte;
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

	public void aconseguirContracte(int idComerc) throws Exception {
		try {
			this.contracte = this.contracteBBDD
					.recuperarContracteActual(idComerc);
		} catch (Exception e) {
			throw new Exception("Error aconseguirContracte - " + e.getMessage());
		}
	}

	public void modificarContracte(int idComerc, String info) throws Exception {
		try {
			this.aconseguirContracte(idComerc);
			this.contracte.setInformacio(info);
			this.contracteBBDD.guardarContracteModificat(contracte, idComerc);
		} catch (Exception e) {
			throw new Exception("Error modificarContracte - " + e.getMessage());
		}
	}

	public void baixaContracte(int i) throws Exception {
		try {

			Contracte contracte = this.contracteBBDD
					.recuperarContracteActual(i);
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

	public LinkedList<Integer> comercSenseContracte() throws Exception {
		try {
			return comercBBDD.senseContracte();
		} catch (Exception e) {
			throw new Exception("Error comercSenseContracte - "
					+ e.getMessage());
		}
	}

	public LinkedList<Integer> obtenirMaquinesLlestes(String estat)
			throws Exception {
		try {
			return this.maquinaBBDD.obtenirMaquines(estat);
		} catch (Exception e) {
			throw new Exception("Error obtenirMaquinesLlestes - "
					+ e.getMessage());
		}
	}

	public int nouContracte(int idComerc, String info,
			LinkedList<Integer> idmaquines, double percentatge, double pagament)
			throws Exception {
		try {
			Contracte contracte = new Contracte(info, new Date(), percentatge,
					pagament);
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

	public LinkedList<Integer> comercAmbContracte() throws Exception {
		try {
			return comercBBDD.ambContracteActiu();
		} catch (Exception e) {
			throw new Exception("Error comercAmbContracte - " + e.getMessage());
		}
	}

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
