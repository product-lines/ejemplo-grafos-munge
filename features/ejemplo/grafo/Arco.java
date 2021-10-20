package ejemplo.grafo;

public class Arco {

	private Nodo origen;
	
	private Nodo destino;
	
	/*if[Peso]*/
	private int		peso;
	/*end[Peso]*/
	
	public Arco() {	}
	
	public Nodo getOrigen() {
		return origen;
	}
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	
	public Nodo getDestino() {
		return destino;
	}
	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	/*if[Peso]*/
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	/*end[Peso]*/
	
}
