package bankRuns;

import java.util.ArrayList;

import cern.jet.random.ChiSquare;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;

public class Usuario {
	int idUsuario;
	double fondos;
	int umbral;
	int conectividad;
	boolean retiro;
	boolean alarm;
	boolean especular;
	boolean propio;
	String miBanco;
	int highBadSignal = 2;
	int lowBadSignal = 1;
	int lowGoodSignal = -1;
	int highGoodSignal = -2;
	static int classID =1;
	
	Parameters params = RunEnvironment.getInstance().getParameters();
	int maxUmbral=params.getInteger("Valor maximo del umbral");
	int maxConect=params.getInteger("Valor maximo para el indice de conectividad");
	
	
	public ContinuousSpace<Object> space;
	
	public Usuario(int conect, boolean ret, boolean ala, boolean espe, boolean prop, String bank) {
		this.miBanco = bank;
		this.idUsuario = classID;
		classID ++;
		this.umbral = RandomHelper.nextIntFromTo(1, maxUmbral);
		this.conectividad = RandomHelper.nextIntFromTo(1, maxConect);
		this.retiro = ret;
		this.alarm = ala;
		this.especular = espe;
		this.propio = prop;
		
		
		
	
	}
	
	public double misFondos() {
		this.fondos = RandomHelper.createChiSquare(3).nextDouble();
		return this.fondos;
	}
	
	@ScheduledMethod (start=1, interval=1, priority = 1000)
	public void setValorInicial () {
		
		
	}
	
	
	/**Propuesta para el Metodo de fondos, o c�mo adquieren sus fondos inciales los usarios
	 * Queremos simular una distribuci�n ChiSquare
	 * 
	 * public double fondos() {
	 * 		return RandomHelper.createChiSquare(double freedom)                               //c�mo hacer que sea chiSquare??
	 * 		}
	 * 
	 */
	
	ArrayList<Usuario> miRed = new ArrayList<Usuario>();
	public void agregarContacto(Usuario nuevoContacto){
		if(! miRed.contains(nuevoContacto)) {	
		
		miRed.add(nuevoContacto);}
		
		nuevoContacto.agregarContacto(this);}
	
	public void buscarContacto () {
		Usuario nuevoContacto = RandomHelper.nextInt(this.idUsuario);
		
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
			if(this.miBanco = bank){ //TODO: encontrar como extraer el banco al que pertenece el otro individuo o "Contacto"
				paciencia.add(highBadSignal);
						}
			else{
				paciencia.add(lowBadSignal);
						}
				}
		else{
			if(this.miBanco =! this.id) { 
				paciencia.add(lowGoodSignal);
						}
			else{
				paciencia.add(highGoodSignal);
						}
	}
	}
	
	/**
	 * Asignacion de fondos para cada individuo con base en una distribuci�n sesgada ala derecha
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
  * 
  * public void rutina(){
  * if (this.fondos>0){
  * 	if (this.especulador = true ) // buscar un end para periodo 2 {
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

