/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;
import grafos.representacoes.MatrizDeAdjacencia;
import grafos.representacoes.ListaDeAdjacencia;
import java.util.List;
import grafos.representacoes.MatrizDeIncidencia;

/**
 *
 * @author a14034
 */
public class Main {
    public static void main(String[] args) {
        /*MatrizDeAdjacencia g = new MatrizDeAdjacencia(6);
        g.addAresta(1, 3);
        g.addAresta(1, 5);
        System.out.println(g.getPeso(0, 1));
        System.out.println(g.getPeso(1, 3));
        System.out.println(g.isAdjacente(1, 5));
        System.out.println(g.isAdjacente(5, 1));
        List<Integer> adjs = g.getAdjacentes(1);
        System.out.println(adjs.size());
        System.out.println("---------------\nadjs de 1:");
        for (Integer adj : adjs) {
            System.out.println("\t"+adj);
        }
        */
        ListaDeAdjacencia lista = new ListaDeAdjacencia(6);
        
        lista.addAresta(1, 3, 20);
        lista.addAresta(2, 3, 10);
        lista.addAresta(1, 5, 20);
        lista.addAresta(1, 4, 30);
        lista.addAresta(2, 1, 10);
        lista.addAresta(4, 5, 50);
        lista.addAresta(5, 3, 20);
        System.out.println(lista.imprimeLista());
        
        /*MatrizDeIncidencia matriz = new MatrizDeIncidencia(5,6);
        matriz.addAresta(0, 2, 0);
        matriz.addAresta(0, 4, 1);
        matriz.addAresta(0, 0, 4);
        matriz.addAresta(2, 4, 2);
        matriz.addAresta(1, 3, 3);
        
        matriz.getAdjacentes(0);
        
        List<Integer> adjs = matriz.getAdjacentes(0);
        for(Integer adj : adjs){
            System.out.println("\t"+adj);
        }
        System.out.println(matriz.isAdjacente(0, 4));
        System.out.println(matriz.isAdjacente(0, 1));
        */
    }
}
