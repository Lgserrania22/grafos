/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author programacao
 */
public class Main {
     public static void main(String[] args) {
        
         Grafo lista = new MatrizDeAdjacencia(6);
         
         lista.addArestaOrientada(0, 1, 5);
         lista.addArestaOrientada(0, 2, 3);
         lista.addArestaOrientada(1, 2, 2);
         lista.addArestaOrientada(1, 3, 6);
         lista.addArestaOrientada(2, 3, 7);
         lista.addArestaOrientada(2, 4, 4);
         lista.addArestaOrientada(2, 5, 2);
         lista.addArestaOrientada(3, 4, 1);
         lista.addArestaOrientada(3, 5, 1);
         lista.addArestaOrientada(4, 5, 2);
         
         Djikstra d = new Djikstra(lista, 0);
         d.imprime();
     }
}
