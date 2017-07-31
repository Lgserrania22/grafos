/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author Gustavo
 */
public class CaminhoMinimoAciclico {
    
    private double[] distancia;
    private int[] pai;
    private Grafo grafo;
    private int numeroVertices;
    private Stack pilha = new Stack();
    
    public CaminhoMinimoAciclico(Grafo grafo, int raiz){
        this.grafo = grafo;
        this.numeroVertices = this.grafo.getNumeroDeVertices();
        this.distancia = new double[this.numeroVertices];
        this.pai = new int[this.numeroVertices];
        BuscaProfundidade busca = new BuscaProfundidade(this.grafo, raiz);
        this.pilha = busca.ordenacaoTopologica();
        inicializa(raiz);
        while(!this.pilha.empty()){
            int v = (int)this.pilha.pop();
            List<ArestaSimples> arestas = this.grafo.getAdjacentes(v);
            for(ArestaSimples aresta : arestas){
                relaxa(aresta);
            }
        }
    }
    
    private void inicializa(int raiz){
        for(int i = 0; i < this.numeroVertices; i++){
            this.distancia[i] = Double.POSITIVE_INFINITY;
            this.pai[i] = -1;
        }
        this.distancia[raiz] = 0.0;
    }
    
    private void relaxa(ArestaSimples aresta){
        if(this.distancia[aresta.getDestino()] > this.distancia[aresta.getOrigem()] + aresta.getPeso()){
            this.distancia[aresta.getDestino()] = this.distancia[aresta.getOrigem()] + aresta.getPeso();
            this.pai[aresta.getDestino()] = aresta.getOrigem();
        }
    }
    
    public void imprime(){
        for(int i = 0; i < this.numeroVertices; i++){
            System.out.println("\nVertice: " + i);
            System.out.println("Distância da raiz: " + this.distancia[i]);
            System.out.println("Pai: " + this.pai[i]);
        }
    }
}
