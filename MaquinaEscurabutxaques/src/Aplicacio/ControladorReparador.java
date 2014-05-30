package Aplicacio;

import java.util.LinkedList;
import Domini.Maquina;
import Domini.Reparacio;
import Persistencia.*;

public class ControladorReparador {

	private MaquinaBBDD maquinaBBDD;
	private ReparacioBBDD reparacioBBDD;
	// Mètode que retorna maquines que estan per reperar
	public LinkedList<Integer> obtenirMaquinesPerReparar() throws Exception {
		try {
			return maquinaBBDD.obtenirMaquines("ESPATLLADA");
		} catch (Exception e) {
			throw new Exception("Error maquinesPerReparar - " + e.getMessage());
		}
	}
	public Reparacio recuperarReparacio(int idReparacio) throws Exception{
		try {
			return this.reparacioBBDD.recuperarReparacio(idReparacio);
		} catch (Exception e) {
			throw new Exception ("Error recuperarReparacio: "+e.getMessage());
		}
	}
	public void inserirReparacio(int idMaquina) throws Exception{
		try{
		TecnicBBDD tecnicRecuperat = new TecnicBBDD();
		Maquina maquina= maquinaBBDD.recuperarMaquina(idMaquina);
		/*
		LineaContracteBBDD LC = new LineaContracteBBDD();
		Contracte contracte = LC.getContracte(idMaquina);
		Zona zona= contracte.getComerc().getZona();
		Tecnic tecnic = tecnicRecuperat.getTecnicMenysReparacionsZona(zona);
		*/
		Reparacio reparacio = new Reparacio(maquina);
		this.reparacioBBDD.inserirReparacio(reparacio);
		}catch(Exception e){
			throw new Exception ("Error inserirReparacio: "+e.getMessage());
		}
	}
	public void canviarEstatFinalitzat (Reparacio reparacio) throws Exception{
		try{
			reparacio.setEstatFinalitzada();		
			this.reparacioBBDD.modificarReparacio(reparacio);
		}catch(Exception e){
			throw new Exception ("Error canviar estatFinalitzar "+e.getMessage());
		}
	}
	
}
