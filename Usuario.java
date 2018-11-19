package bankRuns;

import cern.jet.random.ChiSquare;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;

public class Usuario {
	int idUsuario;
	int fondos;
	int umbral;
	int conectividad;
	boolean retiro;
	boolean alarm;
	boolean especular;
	boolean propio;
	String miBanco;
	
	public ContinuousSpace<Object> space;
	
	public Usuario(int idU, int fon, int umb, int conect, boolean ret, boolean ala, boolean espe, boolean prop, String bank) {
		this.idUsuario =idU;
		this.fondos = fon;
		this.umbral = umb;
		this.conectividad = conect;
		this.retiro = ret;
		this.alarm = ala;
		this.especular = espe;
		this.propio = prop;
		this.miBanco = bank;
		
		
	ChiSquare(double) r = RandomHelper.createChiSquare(4);
	if (r<1) {
		
	}
	

	
		//defineTipoUsuario();	
	}
	
	//public Usuario(String bank) {
		//this.miBanco = bank;
		
		//double r = RandomHelper.nextIntFromTo(0, 1);
		
		
		
	}


//Usuario
	
	/**
	 * ArrayList<Usuario> paciencia = new ArrayList<Usuario>();   
	 * public void get.Paciencia(){
	 * for(double p:paciencia){
	 * nivel += p;}
	 * return nivel;
	 * }
	 */

	


