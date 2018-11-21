package bankRuns;

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
		
		//NetworkBuilder <Object> netBuilder = new NetworkBuilder <Object>
		//("socialNetwork", context, true);
		//netBuilder.buildNetwork();
		
		/** El setting para el modelo sera en un espacio continuo de 75*75 denominado miMundo */
		context.setId("modeloBankRuns");
		
		int dimensionY = 75;
		int dimensionX = 75;  
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("miMundo", context, new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.StrictBorders(), dimensionX,dimensionY);
		/**
		 * por el momento estableceremos el numero de usuarios en miMundo a 100 pero se planea que pueda ser un parametro ajustable por el el usuario 
		 */
		int usuarioCount = 100;
		for (int i=0; i<usuarioCount; i++) {
			double r = RandomHelper.nextDoubleFromTo(0,1);
			if (r<0.25) {
				context.add(new Usuario ("A"));
					}
			else if(r<0.5) {
				context.add(new Usuario("B"));
			}
			else if (r<0.75) {
				context.add(new Usuario ("C"));
			}
			
			else {
				context.add(new Usuario("D"));
			}
		
			
		}
			context.add(new Bancos ("A"));
			context.add(new Bancos ("B"));
			context.add(new Bancos ("C"));
			context.add(new Bancos ("D"));
		
		/**
		 * Agregamos 4 tipos de banco, posteriormente, el tamaño de cada uno se va a 
		 * determinar por el número de usuarios que depositen en el banco.
		 * FALTA AGREGAR CONDICION DE NUMERO DE USUARIOS.
		  */
		 
	return context;
	}
	
	
	
	

}
