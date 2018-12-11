package bankRuns;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class UsuarioStyle extends DefaultStyleOGL2D {
	@Override
	public Color getColor(Object a) {
		Usuario b = (Usuario) a;
		if (b.alarm == true) {
			return Color.RED;
		}
		else if (b.retiro == true && b.fondos >=0) {
			return Color.YELLOW;
			
		}
		else {
			return Color.GREEN;
		}
		
	}

}
