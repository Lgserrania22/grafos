/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Djikstra {
 
    private int[] pai;
    private double[] distancia;
    private LinkedList<Integer> provisoria = new LinkedList();
    private LinkedList<Integer> definitiva = new LinkedList();
    private Grafo grafo;
    private int numeroVertices;
    
    public Djikstra(Grafo grafo, int raiz){
        this.grafo = grafo;
        this.numeroVertices = this.grafo.getNumeroDeVertices();
        this.distancia = new double[this.numeroVertices];
        this.pai = new int[this.numeroVertices];
        inicializa(raiz);
        while(this.provisoria.size() > 0){
            int u = extrairMinimo();
            this.definitiva.add(u);
            List<ArestaSimples> arestas = this.grafo.getAdjacentes(u);
            for(ArestaSimples aresta : arestas){
                relaxa(aresta);
            }
        }
    }
    
    private void inicializa(int raiz){
        for(int i = 0; i < this.numeroVertices; i++){
            this.distancia[i] = Double.POSITIVE_INFINITY;
            this.pai[i] = -1;
            this.provisoria.add(i);
        }
        this.distancia[raiz] = 0.0;
    }
    
    private void relaxa(ArestaSimples aresta){
        if(this.distancia[aresta.getDestino()] > this.distancia[aresta.getOrigem()] + aresta.getPeso()){
            this.distancia[aresta.getDestino()] = this.distancia[aresta.getOrigem()] + aresta.getPeso();
            this.pai[aresta.getDestino()] = aresta.getOrigem();
        }
    }
    
    private int extrairMinimo(){
        double peso = this.distancia[this.provisoria.get(0)];
        int u = this.provisoria.get(0);
        for(int i = 1; i < this.provisoria.size(); i++){
            if(this.distancia[this.provisoria.get(i)] <= peso){
                u = this.provisoria.get(i);
                peso = this.distancia[u];
            }else if(peso < 0 && this.distancia[this.provisoria.get(i)] >= 0){
                u = this.provisoria.get(i);
            }
        }
        this.provisoria.remove(this.provisoria.indexOf(u));
        return u;
    }
    
    public void imprime(){
        for(int i = 0; i < this.definitiva.size(); i++){
            System.out.println("\nVertice: " + this.definitiva.get(i));
            System.out.println("Distância: " + this.distancia[this.definitiva.get(i)]);
            System.out.println("Pai: " + this.pai[this.definitiva.get(i)]);
        }
    }
    
}
