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
	boolean retiro;
	boolean especular;
	Bancos miBanco;
	static int classID =1;
	public Bancos resTot;
	ArrayList<Usuario> miRed = new ArrayList<Usuario>();  //movamos la variable de instancia arriba

	
	
	
	public ContinuousSpace<Object> space;
	
	public Usuario(Bancos bank) {
		this.miBanco = bank;
		this.idUsuario = classID;   //conectar con otra clase
		classID ++;
		Parameters params = RunEnvironment.getInstance().getParameters();
		int maxUmbral=params.getInteger("Valor maximo del umbral");
		int maxConect=params.getInteger("Valor maximo para el indice de conectividad");
		this.umbral = RandomHelper.nextIntFromTo(1, maxUmbral);
		this.retiro = false;
		this.especular = false;
		this.fondos = misFondos();  //ventaja, si queremos cambiar la distribucion lo hacemosdesde aqui
		bank.misUsuarios.add(this);
		bank.resTot += this.fondos;
		
	}
	
	public double misFondos() {                                      //lo pusimos dos veces, quitar linea 51
		return RandomHelper.createChiSquare(3).nextDouble() + 1;   //creamos distribución chi, así arroja el número                                            //aqui no va este metodo, solo el return, lo demas va en el constructor
	}
	
	
	public void bankMatch() {
		if (this.miBanco = contacto.mibanco) {   //metodo para hilar los individuos con su banco
			this.propio = true;
		}
		else {
			this.propio = false;
		}
	}
	
	public void quiebra() {
		if (this.fondos < this.resTot) {    //cierra el banco
			this.alarm = true;
		}
	}

	
	
	public void sendSignal(){
		if(alarm == true){
			if(this.miBanco == this.idBanco){ //TODO: encontrar como extraer el banco al que pertenece el otro individuo o "Contacto"
				paciencia.add(highBadSignal); //como hacer que me acepte esta variable
						}
			else{
				paciencia.add(lowBadSignal);
						}
				}
		else{
			if(this.miBanco == this.idBanco) { 
				paciencia.add(lowGoodSignal);
						}
			else{
				paciencia.add(highGoodSignal);
						}
	}
	}
	
	/**
	 * Asignacion de fondos para cada individuo con base en una distribución sesgada ala derecha
	 */
	}
	
	//public Usuario(String bank) {
		//this.miBanco = bank;
		
		//double r = RandomHelper.createPoisson(3).nextDouble();

//Usuario
	
	
	  public void get.Paciencia(){
	  for(double p:paciencia){
	  nivel += p;}
	  return nivel;
	  }
	 

	

 /**
  * @SCHEDULED METHOD(start=1, interval=1, shuffle=true,priority=50)
  * 
  * public void rutina(){
  * if (this.fondos>0){
  * 	if (this.especulador = true ) // buscar un end para periodo 2 {
  * 		sendSignal();}
  * 	setPaciencia();
  * 	if (nivel>umbral){
  * 		if (this.miBanco==idBanco) 
  *  	{Bancos getReservas(); 
  * 		}
  * 		if (reservasTotales<fondos){
  * 			alarm = true; }
  * 		else {alarm = false}
  * 		sendSignal();
  * 		setPaciencia();
  * 
  * 				primero generar un agente y meterlo en el contexto y al mismo tiempo lo meto en un arraylist de usuario
  * 				ese arraylist va a tener a toda la poblacion, despues hacer un loop similar al tipo de sangre para que 
  *				no se repita el individuo en la red social
  *				simplificar: hashset en vez de arraylist y la red será fija 
  * 			 
  * 
  */
	  
	//Primero creamos bancos, luego clientes y los juntamos con los bancos y luego crear la red social
	  //Hacer un shuffle y elegir amigos
	  //Ver el ScheduledMethod del tipo de sangre para elegir parejas
