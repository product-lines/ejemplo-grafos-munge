package ejemplo.grafo;

import java.util.List;

public class Ejemplo {

	public static void main(String[] args) {
		
		try {
			
			Grafo g = new Grafo();
			
			g.addNodo("A");
			g.addNodo("B");
			g.addNodo("C");

			/*if_not[Peso]*/
			g.addArco("A", "B");
			g.addArco("B", "C");
			g.addArco("A", "C");
			/*else[Peso]*/
			g.addArco("A", "B", 1);
			g.addArco("B", "C", 1);
			g.addArco("A", "C", 2);			
			/*end[Peso]*/

			List<Nodo> camino = g.buscarRuta("A", "C");

			if (camino == null) {
				System.out.println("Camino A -> C no encontrado");
			} else {
				System.out.println("Camino A -> C encontrado");
				for (Nodo nodo: camino) {
					System.out.println(nodo.getNombre());
				}				
			}
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			
			Grafo g = new Grafo();
			
			g.addNodo("A");
			g.addNodo("B");
			g.addNodo("C");
			g.addNodo("D");
			g.addNodo("E");

			/*if_not[Peso]*/
			g.addArco("A", "B");
			g.addArco("B", "C");
			g.addArco("C", "D");
			g.addArco("E", "D");
			g.addArco("A", "C");
			/*else[Peso]*/
			g.addArco("A", "B", 1);
			g.addArco("B", "C", 1);
			g.addArco("C", "D", 1);
			g.addArco("E", "D", 2);
			g.addArco("A", "C", 3);		
			/*end[Peso]*/
			
			List<Nodo> camino = g.buscarRuta("A", "E");
			
			if (camino == null) {
				System.out.println("Camino A -> E no encontrado");
			} else {
				System.out.println("Camino A -> E encontrado");
				for (Nodo nodo: camino) {
					System.out.println(nodo.getNombre());
				}				
			}
			System.out.println();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
