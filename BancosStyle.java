package bankRuns;

/** Modelo Bankruns especulativos en una red social aleatoria*/
/**Autores: Pedro Guerrero, Luis Miguel Orozco, Andrea Parra & Sergio Parra */
/** Proyecto Final */
/** Introduction to Programming and Agent-based modelling*/
/** Profesor Florian Chavez-Juarez*/
/** Invierno 2018 */
/** CIDE */

/** clase para definir el estilo de los agentes tipo Bancos */

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;
/** Metodo para modificar el color de los agentes tipo Banco: negro en el estado inicial y mientras tenga reservas totales positivas y rojo cuando el banco quiebra */
public class BancosStyle extends DefaultStyleOGL2D {
	@Override
	public Color getColor(Object o) {
		Bancos p = (Bancos) o;
		if (p.iliquido == true) {
			return Color.ORANGE;
		
		}
		else {
			return Color.BLACK;
		}
	}

}
