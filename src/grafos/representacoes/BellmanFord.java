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
public class BellmanFord {
    
    private Grafo grafo;
    private double[] distancia;
    private int[] pai;
    private int numeroVertices;
    private LinkedList<ArestaSimples> conjuntoArestas = new LinkedList();
    private LinkedList<ArestaSimples> ciclosNegativos = new LinkedList();
    
    public BellmanFord(Grafo grafo, int raiz){
        this.grafo = grafo;
        this.numeroVertices = grafo.getNumeroDeVertices();
        this.distancia = new double[this.numeroVertices];
        this.pai = new int[this.numeroVertices];
        inicializa(raiz);
        carregaArestas();
        for(int i = 1; i < this.numeroVertices - 1; i++){
            for(ArestaSimples aresta : this.conjuntoArestas){
                relaxa(aresta);
            }
        }
        for(ArestaSimples aresta : this.conjuntoArestas){
            if(this.distancia[aresta.getDestino()] > this.distancia[aresta.getOrigem()] + aresta.getPeso()){
                this.ciclosNegativos.add(aresta);
            }
        }
        if(this.ciclosNegativos.size() > 0){
            imprimeCiclos();
        }else{
            imprimeResultado();
        }
    }
    
    private void inicializa(int raiz){
        for(int i = 0; i < this.numeroVertices; i++){
            this.distancia[i] = Double.POSITIVE_INFINITY;
            this.pai[i] = -1;
        }
        this.distancia[raiz] = 0;
    }
    
    private void carregaArestas(){
        for(int i = 0; i < this.numeroVertices; i++){
            List<ArestaSimples> adjacentes = this.grafo.getAdjacentes(i);
            for(ArestaSimples aresta : adjacentes){
                this.conjuntoArestas.add(aresta);
            }
        }   
    }
    
    private void relaxa(ArestaSimples aresta){
        if(this.distancia[aresta.getDestino()] > this.distancia[aresta.getOrigem()] + aresta.getPeso()){
            this.distancia[aresta.getDestino()] = this.distancia[aresta.getOrigem()] + aresta.getPeso();
            this.pai[aresta.getDestino()] = aresta.getOrigem();
        }
    }
    
    private void imprimeResultado(){
        for(int i = 0; i < this.numeroVertices; i++){
            System.out.println("Vértice: " + i);
            System.out.println("Pai: " + this.pai[i]);
            System.out.println("Distância: " + this.distancia[i]);
        }
    }
    
    private void imprimeCiclos(){
        System.out.println("Ciclos:");
        for(ArestaSimples aresta : this.ciclosNegativos){
            System.out.println("Entre " + aresta.getOrigem() + " e " + aresta.getDestino());
        }
    }
    
}
