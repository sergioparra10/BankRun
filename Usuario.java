package bankRuns;
/** Modelo Bankruns especulativos en una red social aleatoria*/
/**Autores: Pedro Guerrero, Luis Miguel Orozco, Andrea Parra & Sergio Parra */
/** Proyecto Final */
/** Introduction to Programming and Agent-based modelling*/
/** Profesor Florian Chavez-Juarez*/
/** Invierno 2018 */
/** CIDE */
import java.util.ArrayList;
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
	/** Variable (double) que captura los niveles actuales de toleracia/paciencia que tiene un agente tipo usuario a las eñales que recibe sobre el estado del sistema bancario */
	double paciencia = 0;
	/** Variable (boolean) que se activa cuando el cleinte de un banco acude al banco a retirar sus fondos pero el banco no puede cubrir el total de sus depositos */
	boolean alarm;
	/** Variable (int) de conteo del numero de ticks en el modelo */
	int t ;
	/** ArrayList de la clase Usuario en la cual cada agente alamacenara a aquellos agentes que forman parte de su "red social" */
	ArrayList<Usuario> miRed = new ArrayList<Usuario>();  
	/** ArrayList de la clase Bancos que cada agente tipo Banco utiliza para identificar a los agentes tipo Usuario con los que esta relacionado*/
	ArrayList<Bancos> misUsuarios;
	
	public ContinuousSpace<Object> space;
	
	/**Constructor que asigna a cada usuario sus caracteristicas (parametros) */
	public Usuario(Bancos bank) {
		this.miBanco = bank;
		this.idUsuario = classID;  
		classID ++;
		/** Parametros del modelo*/
		Parameters params = RunEnvironment.getInstance().getParameters();
		/** Umbral que define si el usuario retira o no con base en las señales que ya recibio) */
		int maxUmbral=params.getInteger("maxUmbral");
		/** Numero de Usuarios especuldores que hay en el modelo*/
		int nEspecular=params.getInteger("nEspecular");
		/** Numero aleatorio que define el numero maximo de señales malaas que lo hacen retirar*/
		this.umbral = RandomHelper.nextIntFromTo(0, maxUmbral);
		this.retiro = false;
		
		/** Indicar que solamente en el primer tick se definen especuladores */ 
		this. t =1;
		this.especular = false;
		if(this.idUsuario<nEspecular+1) {
			this.especular=true;
			System.out.printf("el usuario %s es especulador en el periodo %s\n ", this.idUsuario, this.t);
			System.out.println("*******************************************************************************************");
			}
		this.fondos = misFondos();  //ventaja, si queremos cambiar la distribucion lo hacemos desde aqui
		bank.misUsuarios.add(this);
		bank.capital += this.fondos; // cambiamos bank.resTot por bank.capital 
		}
	
	 /** Metodo para asignar las dotaciones iniciales a cada Usuario de acuerdo con  */
	public double misFondos() {                                    
		return RandomHelper.createChiSquare(3).nextDouble() + 1;   //creamos distribución chi, así arroja el número                                            //aqui no va este metodo, solo el return, lo demas va en el constructor
	}
	
	/** Metodo de conteo para el numero de retiros dentro del sistema (para serie de tiempo en GUI)*/
	public int wCount() {
		int wc = 0;
		if (this.retiro == true) {
			wc++;
		}
		return wc;
	}
	
	/**Metodo de conteo para el numero de agentes que no pudieron retirar sus fondos del banco (pra serie de tiempo en GUI) */
	public int aCount() {
		int ac =0;
		if(this.alarm == true) {
			ac++;
		}
		return ac;
	}
	
 //TODO: encontrar una forma de contar las veces que se envia la señal y de preferncia vincularla con el receptor y emisor
	
	/** Método para determinar que usuario será el que siembre la semilla de especulacion en el modelo */
	public void especular(){ 
		if (this.especular == true) {
			this.alarm = true; // artificialmente el individuo no recibe sus fondos por tanto se "alarma" y transmite esto en la señal
			this.retiro = true;
			miBanco.resTot -= this.fondos; // el usuario retira sus fondos a pesar de que se alarma artificialmente, es como si mintiera sobre el estado de los fundamentos del banco
			System.out.printf("El especulador numero %s retiro %s del banco %s en el periodo %s\n", this.idUsuario, this.fondos, miBanco.idBanco, (this.t)-1);
			System.out.printf("Su banco %s tiene %s de reservas totales actualmente\n", miBanco.idBanco, miBanco.resTot);
			System.out.println("==========================================================================================");
			this.fondos=0;
		}
		
	}
	public double getReservas() { 
		return miBanco.resTot;
	}
	public void getAlarm() {  
		if (miBanco.resTot < this.fondos) {
			this.alarm = true;
		}
	}
	
	/** Metodo en el cual el Usuario recibe señales con base en si las personas de su red pudieron retirar o no. */
	public void getSignal() {
		Parameters params = RunEnvironment.getInstance().getParameters();
		double maxUmbral = params.getInteger("maxUmbral");
		for(Usuario amigo:miRed ) {
			if(amigo.retiro == true) {
				if(this.miBanco == amigo.miBanco) {
					if (amigo.alarm == true) {
						this.paciencia += (0.3 * maxUmbral); //TODO fijar una proporcion del maximo de umbral como valor de cada tipo de señal
					}
					
					else { this.paciencia -= (0.2 * maxUmbral);
					
				} 
					}
					else  { // ya no es es el mismo banco
						if (amigo.alarm == true) {
						this.paciencia += (0.15 * maxUmbral); //TODO fijar una proporcion del maximo de umbral como valor de cada tipo de señal
						}
					else { this.paciencia -= (0.1 * maxUmbral);
					}					
					}
				}	
		}
	}
	
	
	/** Metodo que regresa el ID de cada agente tipo Usuario que permite mostrarlo como label de cada agente en el GUI */
	public int getId() {
		return this.idUsuario;
	}
/**
 * Lo que pasa en cada tick 
 * (1) en el primer tick comienza la especulacion 
 * (2) si retiro o no depende de mi umbral de paciencia y de la cantitdad de señales que he recibido de las personas de mi red social
 * (3) si el banco se queda sin dinero quiebra y el Usuario manda una señal
 */
@ScheduledMethod(start=1, interval=1, shuffle=true,priority=1000)
	public void interactuar() {
	if(this.t==1) { 
		especular();
	 	getSignal();
	}
	this.t++;
	if (this.fondos>0) {	
	if (this.paciencia > this.umbral) {
		this.retiro = true;
			if(miBanco.resTot < this.fondos) {
				this.alarm = true;
				System.out.printf("El usuario %s intento retirar sus fondos del banco %s pero no habia reservas suficientes su retiro es de %s en el periodo %s\n", this.idUsuario, miBanco.idBanco, this.fondos, (this.t)-1);
				System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////");
				System.out.printf("Las reserva totales del banco %s al que pertence el usuario %s son %s  \n", miBanco.idBanco, this.idUsuario, this.fondos);
				System.out.println("----------------------------------------------------------------------------");
				miBanco.resTot = 0;
				this.fondos = 0;
				miBanco.liquidar();
				
			}
			else {
				miBanco.resTot -= this.fondos;
				System.out.printf("El usuario %s tiene %s fondos y pertenece al banco %s\n", this.idUsuario, this.fondos, miBanco.idBanco);
				System.out.printf("El usuario %s ha retirado sus fondos del banco %s y sus retiro fue de %s en el periodo %s \n", this.idUsuario, miBanco.idBanco, this.fondos, (this.t)-1);
				System.out.printf("Los fondos del banco %s son %s\n", miBanco.idBanco, miBanco.resTot);
				System.out.println("------------------------------------------------------------------------------");
				this.fondos = 0;
			}
	}
	
	getSignal();
	
		}
	}

//@ScheduledMethod (start=1, interval=1, shuffle=false, priority=1100)
	//public void tickCount() {
	//System.out.printf("Estamos en el tick %s\n", this.t);
//}
}
