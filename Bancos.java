package bankRuns;

import java.util.ArrayList;

import repast.simphony.space.continuous.ContinuousSpace;

public class Bancos {
	String idBanco;
	double resReq;
	double resExe;
	double resTot;
	double capital;
	boolean iliquido;
	public Usuario miBanco;
	public Usuario fondos;
	double reservasTotales = (this.capital *(this.resExe + this.resReq));
	
	
	public ContinuousSpace<Object> space;
	

	
	public Bancos (String idB, double capital) {
		this.idBanco = idB;
		this.capital = capital;
		
	}
	
	ArrayList<Bancos> misUsuarios = new ArrayList<Bancos>();
	  public void set.capital(){
		  if (this.idBanco == this.miBanco){
			  misUsuarios.add(this.fondos);} // como extarer los fondos de la clase suuario para usar con la clase bancos
		  for(double c:misUsuarios){
		      capital += c;}
	
	
	  }
	 
	
	//Banco establecer el nexo entre usuarios y bancos
	  
	  public void setReservas(double retiro){
	  		this.capital = this.capital - retiro;
	  	if (this.idBanco = this.miBanco) {
	  		if(this.reservasTotales < this.fondos){
	  			iliquido=true;
	  			}
	  		else {this.resTot = this.resTot - this.fondos;}
	  	
	// Método para cerrar el banco
	
	/**
	 * public void () {
	 * 
	 * 	for (iliquido = true){
	 * 		Cambiar el color del banco una vez que haya quebrado}}
	 */
	
	
}
