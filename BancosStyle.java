package bankRuns;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class BancosStyle extends DefaultStyleOGL2D {
	@Override
	public Color getColor(Object o) {
		Bancos p = (Bancos) o;
		if (p.iliquido == true) {
			return Color.RED;
		
		}
		else {
			return Color.BLACK;
		}
	}

}
