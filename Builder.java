package bankRuns;

import java.util.ArrayList;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.RandomCartesianAdder;

public class Builder implements ContextBuilder<Object>{
	@Override
	public Context build(Context<Object> context) {
		
		NetworkBuilder <Object> netBuilder = new NetworkBuilder <Object>
		("socialNetwork", context, true);
		netBuilder.buildNetwork();
		/**
		 *El setting para el modelo sera en un espacio continuo de 75*75 denominado miMundo
		 */
		context.setId("modeloBankRuns");
		
		int dimensionY = 75;
		int dimensionX = 75;  
		Parameters params = RunEnvironment.getInstance().getParameters();
		
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("miMundo", context, new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.StrictBorders(), dimensionX,dimensionY);
		Bancos bancoA = new Bancos ("A");
		context.add(bancoA);
		
		Bancos bancoB = new Bancos ("B");
		context.add(bancoB);
		
		Bancos bancoC = new Bancos ("C");
		context.add(bancoC);
		
		Bancos bancoD = new Bancos ("D");
		context.add(bancoD);
		
		
		/**
		 * por el momento estableceremos el numero e usuarios en miMundo a 100 pero se planea que pueda ser un parametro ajustable por el el usuario 
		 */
		int usuarioCount = 100;
		ArrayList<Usuario> miPoblacion = new ArrayList<Usuario>();
		
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
		
		//Creemos la red social
		for (Usuario finder: miPoblacion) {
			double propConect = RandomHelper.nextDoubleFromTo(.01, .3);
			for (Usuario candidato: miPoblacion){
				if (propConect > RandomHelper.nextDoubleFromTo(0, 1)) {
					if (finder != candidato & finder.miRed.contains(candidato) == false){
						finder.miRed.add(candidato);
						candidato.miRed.add(finder);									// busca si no existe dentro de la red para que no se duplique
					}
				}
			}
		}
		
		/**
		 * Agregamos 4 tipos de banco, posteriormente, el tamaño de cada uno se va a 
		 * determinar por el número de usuarios que depositen en el banco.
		 * FALTA AGREGAR CONDICION DE NUMERO DE USUARIOS.
		  */
		 
	return context;
	}
	
	
	
	

}
