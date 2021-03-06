package com.timetracker.model;

import java.util.ArrayList;


public class Perfil {
	
	public static final byte padawanId = 'p';
	private static final float padawanTarifa = 25;
	private static final String padawanName = "Padawan";
	
	public static final byte jediId = 'j';
	private static final float jediTarifa = 33;
	private static final String jediName = "Jedi";

	public static final byte maestroJediId = 'm';
	private static final float maestroJediTarifa = 45;
	private static final String maestroJediName = "Maestro Jedi";
	
	private byte id;
	private int numPerfiles;
	private float tarifa;
	
	/*public Perfil (){
		
	}*/
	
	public Perfil(byte id, float tarifa) {
		this.id = id;
		this.numPerfiles = 0;
		this.tarifa = tarifa;
	}

	/**
	 * @return the id
	 */
	public byte getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	/*public void setId(byte id) {
		this.id = id;
	}*/

	/**
	 * @return the tarifa
	 */
	public float getTarifa() {
		return tarifa;
	}
	/**
	 * @param tarifa the tarifa to set
	 */
	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
	}
	
	public int getNumPerfiles() {
		return numPerfiles;
	}

	public void setNumPerfiles(int numPerfiles) {
		this.numPerfiles = numPerfiles;
	}

	public static float getTarifa(byte idTarifa){
		switch (idTarifa) {
		case padawanId:
			return padawanTarifa;
		case jediId:
			return jediTarifa;
		case maestroJediId:
			return maestroJediTarifa;
		default:
			return -1;
		}
	}
	
	public static String getTarifaName(byte idTarifa){
		switch (idTarifa) {
		case padawanId:
			return padawanName;
		case jediId:
			return jediName;
		case maestroJediId:
			return maestroJediName;
		default:
			return "Perfil no encontrado";
		}
	}
	
	public static ArrayList<Byte> getListaIDPerfiles (){
		ArrayList<Byte> listaPerfiles = new ArrayList<Byte>();
		
		listaPerfiles.add(padawanId);
		listaPerfiles.add(jediId);
		listaPerfiles.add(maestroJediId);
		
		return listaPerfiles;
	}
}
