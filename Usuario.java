package bankRun;
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
	/** Varibale */
	boolean alarm;
	/**  */
	int t ;
	/** Variable que le permite al usuario del modelo determinar el total de agentes tipo Usuario en el espacio */
	//int maxUsuarios;
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
		int maxUmbral=params.getInteger("umbralMaximo");
		int maxUsuarios=params.getInteger("totalClientes");
		int nEspecular=params.getInteger("numeroEspeculador");

		this.umbral = RandomHelper.nextIntFromTo(1, maxUmbral);
		this.retiro = false;
		
		this.especular = false;
		if(this.idUsuario<nEspecular) {
			this.especular=true;
		}
		
		this.fondos = misFondos();  //ventaja, si queremos cambiar la distribucion lo hacemos desde aqui
		bank.misUsuarios.add(this);
		bank.capital += this.fondos; // cambiamos bank.resTot por bank.capital 
		// TODO corroborar que este metodo nos genera efectivamente el suma de los fondos de todos los clientes de un banco genera su capital
		
	}
	 /** Metodo para asignar las dotaciones iniciales a cada Usuario de acuerdo con  */
	public double misFondos() {                                      //lo pusimos dos veces, quitar linea 51
		return RandomHelper.createChiSquare(3).nextDouble() + 1;   //creamos distribución chi, así arroja el número                                            //aqui no va este metodo, solo el return, lo demas va en el constructor
	}
	
	
	/** Método para determinar que usuario será el/la que siembre la semilla de especulacion en el modelo */
	
	public void especular(){ // TODO: lograr que la masa de especuladores sea mayor a uno y de preferencia un parametro que pueda modificarse en el GUI
		/** Por medio de esta variable aletoria de distrubucion unforme se seleccionara el id del Usuario que iniciara la especulacion */
		int e = RandomHelper.nextIntFromTo(1, maxUsuarios); //TODO: conseguir que funcione el intervalos con el parametro ingresado por el usuario del modelo
		if (this.idUsuario == e) {
			this.especular = true; // le asignamos a este usuario la obligacion de implantar la semilla de especulacion
			this.alarm = true; // artificialmente el individuo no recibe sus fondos por tanto se "alarma" y transmite esto en la señal
			this.retiro = true;
			miBanco.resTot -= this.fondos; // el usuario retira sus fondos a pesar de que se alarma artificialmente, es como si mintiera sobre el estado de los fundamentos del banco
		}
	}
	
	public double getReservas() { // tal vez innecesario
		return miBanco.resTot;
	}
	
	public void getAlarm() {  //TODO: revisar si en este metodo necesitamos un if adicional para especificar que antes de evaluar los fondos debe efectivamente retirar (retiro = true)
		if (miBanco.resTot < this.fondos) {
			this.alarm = true;
		}
	}
	
	
	public void setSignal() {
		for(Usuario amigo:miRed ) {
			if(amigo.retiro == true) {
				if(this.miBanco == amigo.miBanco) {
					if (amigo.alarm == true) {
						this.paciencia += (0.2 * maxUmbral); //TODO fijar una proporcion del maximo de umbral como valor de cada tipo de señal
					}
					else { this.paciencia -= (0.2 * maxUmbral);
				} 
					}
					else  { // ya no es es el mismo banco
						if (amigo.alarm == true) {
						this.paciencia += (0.1 * maxUmbral); //TODO fijar una proporcion del maximo de umbral como valor de cada tipo de señal
					}
					else { this.paciencia -= (0.1 * maxUmbral);
				        }
						
					}
				}
				
		}
	}
	

	
	public double setReservas() {
		miBanco.resTot = miBanco.resTot - this.fondos; // tal vez innecesario
		return miBanco.resTot;
	}
	

@ScheduledMethod(start=1, interval=1, shuffle=true,priority=1000)
	public void interactuar() {
	if(this.t==1) { // aca realizamos esto unicamente en el primer tick debido a que es el inicio de la especulacion
	 	especular();
	 	setSignal();
	}
	this.t++;
	if (this.paciencia > this.umbral) {
		this.retiro = true;
		getReservas();
			if(miBanco.resTot < this.fondos) {
				this.alarm = true;
				miBanco.resTot = 0;
			}
			else {
				miBanco.resTot -= this.fondos;
			}
	}
	setSignal();
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

/**
 * En el scheduled methos si quiero una condicion que un amigo no sea alguien de mi red social,
 * para captar su banco va a ser así
 * 
 * searcher.miBanco != candidato.miBanco
 * for (Usuario amigo:this.miRed)
 * 
 */
