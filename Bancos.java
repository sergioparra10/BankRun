package bankRuns;

/** Modelo Bankruns especulativos en una red social aleatoria*/
/**Autores: Pedro Guerrero, Luis Miguel Orozco, Andrea Parra & Sergio Parra */
/** Proyecto Final */
/** Introduction to Programming and Agent-based modelling*/
/** Profesor Florian Chavez-Juarez*/
/** Invierno 2018 */
/** CIDE */

/**  Clase en la cual se definen las acciones y caracteristicas de los bancos. */

import java.util.ArrayList;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;

public class Bancos {
	/** Variable (String) para tener un identificador de cada uno de los bancos*/
	String idBanco;
	/** Variable (double) la tasa de reservas requeridas de los bancos*/
	double resReq;
	/** Variable (double) para el exedente de reservas */
	double resExe;
	/** Variable (double) es la suma de las reservas requeridas y el exedente */
	double resTot ;
	/** Variable (double) para el capital de cada banco (depositos que hacen los usuarios) */
	double capital;
	/** Variable (boolean) que indica si el banco tiene o no dinero */
	boolean iliquido;
	/** Variable (int) de conteo del numero de tick en el modelo */
	int k =1; 
	/** ArraYList de la clase Bancos que almacena al conjunto de agentes tipo Usuario que estan vinculados a un banco en especifico, similar a una lista de clientes de la cual los bancos obtienen el capital con base en el total de los fondos de sus "clientes" */
	ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
	
	
	public ContinuousSpace<Object> space;
	
	public Bancos (String idB) {
		this.idBanco = idB;
		this.resTot = (this.capital *(this.resExe + this.resReq));
		Parameters params = RunEnvironment.getInstance().getParameters();
		this.resExe = params.getDouble("resExe");
		this.resReq = params.getDouble("resReq");

	}

	
	  /** Metodo para que los agentes se informen de que su banco no tiene reservas suficientes para honrar su depositos y además cierra el banco para  */
	  public void liquidar() { //ambos dentro de un schedule method
			  if(this.resTot <= 0) {
				  this.iliquido = true;
				  for (Usuario miAgente: misUsuarios){
					  miAgente.alarm = true;
					  miAgente.fondos =0;
					  miAgente.retiro = true;
		  }
			  }
		  //TODO: resolver como hacer una señalizacion de que el banco ha quebrado y ya no opera para ninguno de sus usuarios
	 }
 public double getReservas() {
	 return this.resTot;
 }
 
 /** Metodo que regresa el id unico de cada banco que permite identificar a cada banco en el GUI */
 
 public String getName() {
	 return this.idBanco;
 }
 
 //TODO: pasar el metodo abrir banco al builder
 
 /** Schedule method para que el banco determine su stock de capital con el cual operara */
 
 @ScheduledMethod (start=0, interval=0, shuffle=true,priority=100)
 	public void abrirBanco() {
	 	 this.resTot = (this.capital *(this.resExe + this.resReq));
		//System.out.printf("El banco %s tiene %s en sus reservas totales inicialemnte\n", this.idBanco, this.resTot);
	
 }
 
/** public void cerrar() {
	 if(this.resTot = 0) {
		 this.iliquido = true;
	 }
 } */
 
 }

