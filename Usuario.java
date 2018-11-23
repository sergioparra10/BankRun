package bankRuns;
import java.util.ArrayList;

import cern.jet.random.ChiSquare;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;

public class Usuario {
	/** Variable (int) que asiga un identificador a cada agente en el modelo diferente al default de JAVA */
	int idUsuario;
	/** Variable (double) para la dotación inicial del agente que posteriormente depositara en su banco */
	double fondos;
	/** Variable (int) integer que determina el nivel de paciencia que tiene un agente a las señales que recibe de su red (aversión a la perdida de su dotacion) */
	int umbral;
	/** Variable (boolean) que indica si el agente ya ha retirado sus depositos del banco (retiro = true)  y que posteriormente determinara la señal que tranamitida a su red */
	boolean retiro;
	/**  Variable (boolean) que indica si este agente es el espculador que sembrara la señal (semilla) especulativa al sistema */
	boolean especular;
	/** Variable de la clase Bancos que recupera el identificador individual de cada agente tipo Banco para verifica la existencia de la relacion entre un banco y el usuario (si es el banco al que pertene el usuario o no)*/
	Bancos miBanco;
	/** Variable (static int) que genera un identificador individual para cada agente tipo usuario */
	static int classID =1;
	/** Variable de la clase Bancos que contiene las reserva totales con las que deipone el banco en ese moemento y que se actuliza cada vez que hay un retiro */
	public Bancos resTot;
	/** Variable (double) que caprura los niveles actuales de toleracia/paciencia que tiene un agente tipo usuario a las eñales que recibe sobre el estado del sistema bancario */
	double paciencia = 0;
	/**  */
	int poblacion;
	/** Variable que le permite al usuario del modelo determinar el total de agentes tipo Usuario en el espacio */
	int maxUsuarios;
	/** ArrayList de la clase Usuario en la cual cada agente alamacernra a aquellos agentes que forman parte de su "red social" */
	ArrayList<Usuario> miRed = new ArrayList<Usuario>();  //movamos la variable de instancia arriba
	/** ArrayList de la clase Bancos en la cual cada banco almacena a los agentes tipo Usuario que forman parte de su red de "clientes" que han depositado sus fondos en el y que pueden realizar retiros */
	ArrayList<Bancos> misUsuarios;
	
	
	public ContinuousSpace<Object> space;
	
	public Usuario(Bancos bank) {
		this.miBanco = bank;
		this.idUsuario = classID;   //conectar con otra clase
		classID ++;
		Parameters params = RunEnvironment.getInstance().getParameters();
		int maxUmbral=params.getInteger("Valor maximo del umbral");
		int maxUsuarios=params.getInteger("Total de clientes en el modelo");
		int nEspecular=params.getInteger("numeroEspeculador");

		this.umbral = RandomHelper.nextIntFromTo(1, maxUmbral);
		this.retiro = false;
		
		this.especular = false;
		if(this.idUsuario<nEspecular) {
			this.especular=true;
		}
		
		this.fondos = misFondos();  //ventaja, si queremos cambiar la distribucion lo hacemosdesde aqui
		bank.misUsuarios.add(this);
		bank.resTot += this.fondos;
		
		
	}
	 /** Metodo para asignar las dotaciones iniciales a cada Usuario de acuerdo con  */
	public double misFondos() {                                      //lo pusimos dos veces, quitar linea 51
		return RandomHelper.createChiSquare(3).nextDouble() + 1;   //creamos distribución chi, así arroja el número                                            //aqui no va este metodo, solo el return, lo demas va en el constructor
	}
	
	
	/** Método para determinar que usuario será el/la que siembre la semilla de especulacion en el modelo */
	
	public void especular(){ 
		/** Por medio de esta variable aletoria de distrubucion unforme se seleccionara el id del Usuario que iniciara la especulacion */
		int e = RandomHelper.nextIntFromTo(1, maxUsuarios); 
		if (this.idUsuario == e) {
			this.especular = true;
		}
	}
	
	public double getReservas() {
		return miBanco.resTot;
	}
	
	@ScheduledMethod (start = 1, interval = 1, shuffle = true, priority = 900)
	public void getsignal() {
		
	}
	
	
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