/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;
/**
 *
 * @author programacao
 */
public class Main {
     public static void main(String[] args) {
         
         /*ListaAdjacenciaLinked lista = new ListaAdjacenciaLinked(9);
         System.out.println("Número de vértices atual: " + lista.getNumeroDeVertices());
         lista.addAresta(1, 3);
         lista.addAresta(1, 5);
         lista.addAresta(0, 5);
         lista.addAresta(0, 4);
         lista.addAresta(0, 7);
         lista.addAresta(3, 2);
         lista.addAresta(3, 8);
         System.out.println(lista.imprimeLista());
         System.out.println("_____________________________________________________");
         System.out.println(lista.addVertice());
         lista.addAresta(6,9);
         System.out.println(lista.imprimeLista());
         System.out.println(lista.removeVertice(1));
         System.out.println("____________________________________________________");
         System.out.println(lista.imprimeLista());
         */
         long tempoInicial = 0;
         long tempoFinal = 0;
         int cont = 0;
         MatrizDeAdjacencia matriz = new MatrizDeAdjacencia(10000);
         //ListaAdjacenciaArray lista = new ListaAdjacenciaArray(5);
         
         matriz.acessarArestas();
     }
}
