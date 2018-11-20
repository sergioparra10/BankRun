package bankRuns;

import java.util.ArrayList;

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
		
		
		
	
	}
	
	ArrayList<Usuario> miRed = new ArrayList<Usuario>();
	public void agregarContacto(Usuario nuevoContacto){
		if(! miRed.contains(nuevoContacto)) {	
		
		miRed.add(nuevoContacto);}
		
		nuevoContacto.agregarContacto(this);}
	
public Usuario buscarcontacto() {
		//buscar en el ArrayList miRed buscar aleatoriamente una persona diefreneta la que ya está buscando.
	}
	
	public void agregarContactos() {
		while (miRed.size()<conectividad) {
			Usuario nuevoContacto = buscarContacto();
			agregarContacto(nuevoContacto);
		}
		
	}
	
	ArrayList<Usuario> paciencia = new ArrayList<Usuario>();
	public void sendSignal(){
		if(alarm = true){
			if(this.miBanco = bank){
				paciencia.add(2);
						}
			else{
				paciencia.add(1);
						}
				}
		else{
			if(this.miBanco =! bank) { 
				paciencia.add(-1);
						}
			else{
				paciencia.add(-2);
						}
	}
	}
	
	/**
	 * Asignacion de fondos para cada individuo con base en una distribución sesgada ala derecha
	 */
//ChiSquare(double) r = RandomHelper.createChiSquare(4);
//if (r<1) {

	
	public void getPaciencia() {
		// TODO Auto-generated method stub
		
	}
	
		//defineTipoUsuario();	
	}
	
	//public Usuario(String bank) {
		//this.miBanco = bank;
		
		//double r = RandomHelper.createPoisson(3).nextDouble();

//Usuario
	
	/**
	 * ArrayList<Usuario> paciencia = new ArrayList<Usuario>();   
	 * public void get.Paciencia(){
	 * for(double p:paciencia){
	 * nivel += p;}
	 * return nivel;
	 * }
	 */

	

 /**
  * @SCHEDULED METHOD(start=1, interval=1, shuffle=true,priority=50)
  * if (fondos>0){
  * 	for (especulador = true ){
  * 		sendSignal();}
  * 	setPaciencia();
  * 	if (nivel>umbral){
  * 		getReservas(); 
  * 		if (reservasTotales<fondos){
  * 			alarm = true; }
  * 		else {alarm = false}
  * 		sendSignal();
  * 		setPaciencia();
  * 
  * 				
  * 			 
  * 
  */

