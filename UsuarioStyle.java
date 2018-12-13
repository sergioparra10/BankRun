package bankRuns;

/** Modelo Bankruns especulativos en una red social aleatoria*/
/**Autores: Pedro Guerrero, Luis Miguel Orozco, Andrea Parra & Sergio Parra */
/** Proyecto Final */
/** Introduction to Programming and Agent-based modelling*/
/** Profesor Florian Chavez-Juarez*/
/** Invierno 2018 */
/** CIDE */

/** clase para definir el estilo de los agentes tipo Usuario */

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

/** Metodo para modificar el color de los agentes tipo Banco: verde en el estado inicial y mientras no retire, amarrillo cuando acude al banco y puede retiar todos sus fondos y rojo cuando el usuario no pudo recuperar sus fondos del banco */
public class UsuarioStyle extends DefaultStyleOGL2D {
	@Override
	public Color getColor(Object a) {
		Usuario b = (Usuario) a;
		if (b.alarm == true) {
			return Color.RED;
		}
		else if (b.retiro == true && b.fondos >=0) {
			return Color.BLUE;
			
		}
		else {
			return Color.GREEN;
		}
		
	}

}
