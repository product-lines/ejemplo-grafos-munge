package ejemplo.grafo;

import java.util.List;
import java.util.ArrayList;
/*if[DFS]*/
import java.util.Stack;
/*end[DFS]*/
/*if[BFS]*/
import java.util.LinkedList;
import java.util.Queue;
/*end[BFS]*/

public class Grafo {

	List<Nodo> nodos = new ArrayList<>();
	List<Arco> arcos = new ArrayList<>();
	
	public Grafo() { }
	
	public void addNodo(String nombre)
	{
		Nodo nodo = new Nodo();
		nodo.setNombre(nombre);
		nodos.add(nodo);
	}
	
	/*if_not[Peso]*/
	public void addArco(String origen, String destino) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		
		addArco(nodoOrigen, nodoDestino);
	}
	
	public void addArco(Nodo nodoOrigen, Nodo nodoDestino)
	{
		Arco arco = new Arco();
		arco.setOrigen(nodoOrigen);
		arco.setDestino(nodoDestino);
		arcos.add(arco);
		nodoOrigen.agregarArco(arco);
		
		/*if_not[Dirigido]*/
		arco = new Arco();
		arco.setOrigen(nodoDestino);
		arco.setDestino(nodoOrigen);
		arcos.add(arco);
		nodoDestino.agregarArco(arco);
		/*end[Dirigido]*/
	}
	/*end[Peso]*/
	
	/*if[Peso]*/
	public void addArco(String origen, String destino, int peso) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		
		addArco(nodoOrigen, nodoDestino, peso);
	}
	
	public void addArco(Nodo nodoOrigen, Nodo nodoDestino, int peso)
	{
		Arco arco = new Arco();
		arco.setOrigen(nodoOrigen);
		arco.setDestino(nodoDestino);
		arco.setPeso(peso);
		arcos.add(arco);
		nodoOrigen.agregarArco(arco);

		/*if_not[Dirigido]*/
		arco = new Arco();
		arco.setOrigen(nodoDestino);
		arco.setDestino(nodoOrigen);
		arcos.add(arco);
		nodoDestino.agregarArco(arco);
		/*end[Dirigido]*/	
	}	
	/*end[Peso]*/
	
	public Nodo buscarNodo(String nombre) 
	{
		for (Nodo nodo: nodos) {
			if (nodo.getNombre().equals(nombre)) {
				return nodo;
			}
		}
		return null;
	}
	
	public boolean existeRuta(String origen, String destino) throws Exception {
		if (buscarRuta(origen, destino) != null) {
			return true;
		}
		return false;
	}
	
	public List<Nodo> buscarRuta(String origen, String destino) throws Exception {
		
		Nodo nodoOrigen = buscarNodo(origen);
		Nodo nodoDestino = buscarNodo(destino);
		List<Nodo> nodosRuta = new ArrayList<>();
		
		// Origen o destino no encontrado
		if (nodoOrigen == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		if (nodoDestino == null) {
			throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
		}
		
		/*if[DFS]*/
		if (buscarRutaDFS(nodosRuta, nodoOrigen, nodoDestino)) {
			return nodosRuta;
		} else {
			return null;
		}
		/*end[DFS]*/
		
		/*if[BFS]*/
		if (buscarRutaBFS(nodosRuta, nodoOrigen, nodoDestino)) {
			return nodosRuta;
		} else {
			return null;
		}
		/*end[BFS]*/
	}
	
	/*if[DFS]*/
	private boolean buscarRutaDFS(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino) {
		
		// agrega el origen
	    nodosRuta.add(nodoOrigen);
		
		// origen y destino son el mismo ?
		if(nodoOrigen.getNombre().equals(nodoDestino.getNombre())){
            return true;
        }
		
		// si no son el mismo, revise las rutas usando una pila
        Stack<Nodo> pilaDeNodos = new Stack<>();
        ArrayList<Nodo> nodosVisitados = new ArrayList<>();

        pilaDeNodos.add(nodoOrigen);

        while(!pilaDeNodos.isEmpty()){
            Nodo actual = pilaDeNodos.pop();

            // ignore los nodos ya visitados
            if (nodosVisitados.contains(actual))
            	continue;
            
            // es el nodo que estamos buscando ?
            if (actual.equals(nodoDestino)) {
            	nodosRuta.addAll(pilaDeNodos);
            	nodosRuta.add(nodoDestino);
                return true;
            }
            else {
                // siga buscando en las rutas no visitadas
            	nodosVisitados.add(actual);
            	for(Nodo nodo: actual.getNodosAdyacentes()) {
            		if (!pilaDeNodos.contains(nodo))
            			pilaDeNodos.add(nodo);
            	}
            }
        }
        return false;
        
	}	
	/*end[DFS]*/
	
	/*if[BFS]*/
	private boolean buscarRutaBFS(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino) {
		
		// origen y destino son el mismo ?
		if(nodoOrigen.getNombre().equals(nodoDestino.getNombre())){
			System.err.println("Nodo destino encontrado en el mismo nodo origen");
		    nodosRuta.add(nodoOrigen);
            return true;
        }
		
		// si no son el mismo, revise las rutas usando una cola
        Queue<Nodo> queue = new LinkedList<>();
		ArrayList<Nodo> nodosVisitados = new ArrayList<>();
        
        queue.add(nodoOrigen);
        nodosVisitados.add(nodoOrigen);

        while(!queue.isEmpty()){
        	
            Nodo actual = queue.remove();
            if(actual.equals(nodoDestino)) {
            	nodosRuta.add(actual);
                return true;
            }
            else{
                if(actual.getNodosAdyacentes().isEmpty())
                    return false;
                else {
                	for (Nodo nodo: actual.getNodosAdyacentes()) {
                		if (!nodosVisitados.contains(nodo))
                			queue.add(nodo);
                	}
                }
                    
            }
            if (!nodosRuta.contains(actual))
            	nodosRuta.add(actual);
        }

        return false;        
	}
	/*end[BFS]*/
	
}
