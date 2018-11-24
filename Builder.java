package bankRuns;

import java.util.ArrayList;

/**
 * La clase Builder nos permite definir las caracteristicas, el entorno y los agentes que utilizamos en el modelo.
 */

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.RandomCartesianAdder;

public class Builder implements ContextBuilder<Object>{
	@Override
	public Context build(Context<Object> context) { //TODO: hay un warning sobre la necesidad de parametrizar el context
		
		/**
		 *El setting para el modelo sera en un espacio continuo de 75*75 denominado miMundo
		 */
		context.setId("bankRuns");
		
		int dimensionY = 75;
		int dimensionX = 75;  
		Parameters params = RunEnvironment.getInstance().getParameters();
		
		/**
		 * Estos comandos son los que crean un mundo en donde el modelo basado en agentes se va a desarrollar, para nuestro
		 * modelo elegimos un mundo continuo y acotado.
		 */
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("miMundo", context, new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.StrictBorders(), dimensionX,dimensionY);
		
		/**
		 * Aqui definimos los bancos. En nuestro modelo decidimos crear cuatro bancos los cuales tendrán diferentes
		 * caracteristicas en cuanto al numero de fondos que poseen.
		 */
		
		Bancos bancoA = new Bancos ("A");
		context.add(bancoA);
		
		Bancos bancoB = new Bancos ("B");
		context.add(bancoB);
		
		Bancos bancoC = new Bancos ("C");
		context.add(bancoC);
		
		Bancos bancoD = new Bancos ("D");
		context.add(bancoD);
		
		
		/**
		 * Mediante este comando declaramos el numero de usuarios que viviran en el modelo.
		 * Por el momento estableceremos el numero de usuarios en miMundo a 100,
		 *  pero es un parametro ajustable por el el usuario en el modelo.
		 */
		int usuarioCount = 100;
		/**
		 * Mediante este ArrayList definimos a la poblacion que vive en el modelo, es decir, es una lista
		 * que guarda a todos los usuarios.
		 */
		ArrayList<Usuario> miPoblacion = new ArrayList<Usuario>();
		
		/**
		 * Con este loop le asignamos aleatoriamente un banco a los usuarios.
		 *  De manera que la poblacion tiene la misma probabilidad de pertenecer a uno de los bancos.
		 */
		for (int i=0; i<usuarioCount; i++) {
			double r = RandomHelper.nextDoubleFromTo(0,1);    //cómo asignar el banco a los usuarios 
			if (r<0.25) {
				Usuario miAgente = new Usuario (bancoA);
				context.add(miAgente);
				miPoblacion.add(miAgente);
					}
			else if(r<0.5) {
				Usuario miAgente = new Usuario (bancoB);
						context.add(miAgente);
						miPoblacion.add(miAgente);
							}
			
			else if (r<0.75) {
				Usuario miAgente = new Usuario (bancoC);
						context.add(miAgente);
						miPoblacion.add(miAgente);

			}
			
			else {
				Usuario miAgente = new Usuario (bancoD);
				context.add(miAgente);
				miPoblacion.add(miAgente);

	}
		
			
		}
		
		/**
		 * Creamos la red social, los individuos estan conectados entre ellos.
		 * Esta red es la base para esparcir o no algun rumor sobre el estatus de los bancos.
		 * 
		 */
		for (Usuario finder: miPoblacion) {
			double propConect = RandomHelper.nextDoubleFromTo(.01, .3);
			for (Usuario candidato: miPoblacion){
				if (propConect > RandomHelper.nextDoubleFromTo(0, 1)) {
					/**
					 * Esta condicion elimina el escenario en donde un agente se repita dentro de una misma red social.
					 */
					if (finder != candidato & finder.miRed.contains(candidato) == false){
						finder.miRed.add(candidato);
						candidato.miRed.add(finder);									
					}
				}
			}
		}
		
	
		 
	return context;
	

	
	
	
	

}}