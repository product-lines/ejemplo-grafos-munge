package ejemplo.pruebas;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ejemplo.grafo.Grafo;
import ejemplo.grafo.Nodo;

public class GrafosTest {

	@Test
	public void pruebaGrafos() {
		
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

			if (camino != null) {
				System.out.println("Camino A -> C encontrado");
				for (Nodo nodo: camino) {
					System.out.println(nodo.getNombre());
				}				
			}
			
			// la prueba falla si no encuentra un camino
			if (camino == null) {
				System.out.println("Camino A -> C no encontrado");
				Assertions.fail("Error en la busqueda del camino");
			}
			
			
		} catch (Exception e) {
			// La prueba falla si ocurre algún error
			System.err.println(e.getMessage());
			e.printStackTrace();
			Assertions.fail("Error en la busqueda del camino");
		}
		
	}

}

