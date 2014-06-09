package Aplicacio;

import java.util.Date;
import java.util.LinkedList;

import Domini.Carcassa;
import Domini.Comerc;
import Domini.Maquina;
import Domini.Placa;
import Domini.Reparacio;
import Domini.Tecnic;
import Persistencia.*;

public class ControladorReparador {

	private MaquinaBBDD maquinaBBDD;
	private ReparacioBBDD reparacioBBDD;
	
	public ControladorReparador() throws Exception{
		this.maquinaBBDD = new MaquinaBBDD();
		this.reparacioBBDD = new ReparacioBBDD ();
	}
	
	/** Retorna les màquines que tenen com a estat "ESPATLLADA"
	 * @return LinkedList<Integer>
	 * @throws Exception
	 */
	public LinkedList<Integer> obtenirMaquinesPerReparar() throws Exception {
		try {
			return maquinaBBDD.obtenirMaquines("ESPATLLADA");
		} catch (Exception e) {
			throw new Exception("Error maquinesPerReparar - " + e.getMessage());
		}
	}
	
	/** Posa a finalitzat l'estat de la reparació i ocupa la placa i la carcassa.
	 * @param idReparacio
	 * @param idCarcassa
	 * @param idPlaca
	 * @throws Exception
	 */
	public void canviarEstatFinalitzat(int idReparacio, int idCarcassa, int idPlaca) throws Exception{
		try {
		Reparacio reparacio = this.recuperarReparacio(idReparacio);	
		Maquina maquina = reparacio.getMaquina();
		if(idCarcassa < 0){
			Carcassa carcassa=maquina.getCarcassa();
			carcassa.setNoOperativa();
			
			CarcassaBBDD carcassaBBDD= new CarcassaBBDD();
			carcassaBBDD.ocuparCarcassa(carcassa);
			
			carcassa= carcassaBBDD.recuperarCarcassa(idCarcassa);	
			carcassa.setOcupada();
			carcassaBBDD.ocuparCarcassa(carcassa);
			maquina.setCarcassa(carcassa);
		}
		if(idPlaca < 0){
			Placa placa= maquina.getPlaca();
			placa.setNoOperativa();
			
			PlacaBBDD placaBBDD= new PlacaBBDD();
			placaBBDD.ocuparPlaca(placa);
			
			placa= placaBBDD.recuperarPlaca(idPlaca);
			placa.setOcupada();
			placaBBDD.ocuparPlaca(placa);
			maquina.setPlaca(placa);
		}		
		reparacio.setEstatFinalitzada();		
		this.reparacioBBDD.modificarReparacio(reparacio);
		} catch (Exception e) {
			throw new Exception ("Error modificarReparacio: "+e.getMessage());
		}
	}
	
	/** Recupera una reparació de la BBDd
	 * @param idReparacio
	 * @return Reparació
	 * @throws Exception
	 */
	private Reparacio recuperarReparacio(int idReparacio) throws Exception{
		try {
			return this.reparacioBBDD.recuperarReparacio(idReparacio);
		} catch (Exception e) {
			throw new Exception ("Error recuperarReparacio: "+e.getMessage());
		}
	}
	
	/** Insereix una reparació a la BBDD
	 * @param idMaquina
	 * @param comerc
	 * @throws Exception
	 */
	public void inserirReparacio(int idMaquina, Comerc comerc) throws Exception{
		try{
		Maquina maquina= maquinaBBDD.recuperarMaquina(idMaquina);	
		Date data= new Date();
		TecnicBBDD tecnicBBDD=  new TecnicBBDD();
		Tecnic tecnic = tecnicBBDD.getTecnicMenysReparacionsZona(comerc.getZona());

		Reparacio reparacio = new Reparacio(maquina,tecnic,data);
		this.reparacioBBDD.inserirReparacio(reparacio);
		}catch(Exception e){
			throw new Exception ("Error inserirReparacio: "+e.getMessage());
		}
	}

	/** Retorna les màquines que té un comerç
	 * @param idComerc
	 * @return LinkedList<Integer>
	 * @throws Exception 
	 */
	public LinkedList<Integer> obtenirMaquinesComerc(int idComerc) throws Exception {
		LinkedList<Integer> id = maquinaBBDD.obtenirMaquinesXComerc(idComerc);
		return id;
	}
	
}
