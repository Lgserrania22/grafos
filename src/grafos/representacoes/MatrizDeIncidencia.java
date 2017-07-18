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
public class MatrizDeIncidencia{
    
    int numeroVertices;
    int numeroArestas;
    private double[][] matriz;
    
    public MatrizDeIncidencia(int numeroVertices, int numeroArestas){
        this.numeroVertices = numeroVertices;
        this.numeroArestas = numeroArestas;
        matriz = new double[this.numeroVertices][this.numeroArestas];
    }
    
 
    public int getNumeroDeVertices(){
        return this.numeroVertices;
    }

    public void addAresta(int origem, int destino, int aresta){
        if(origem != destino){
            this.matriz[origem][aresta] = 1;
            this.matriz[destino][aresta] = -1;
        }else{
            this.matriz[origem][aresta] = 1;
        }
    }

    public void addAresta(int origem, int destino,int aresta, double peso){
        if(origem != destino){
            this.matriz[origem][aresta] = peso;
            this.matriz[destino][aresta] = peso * (-1);
        }else{
            this.matriz[origem][aresta] = peso;
        }
    }

    public void setAresta(int origem, int destino, int aresta){
        this.addAresta(origem, destino, aresta);
    }

    public void setAresta(int origem, int destino,int aresta, double peso){
        this.addAresta(origem, destino,aresta, peso);
    }

    public void removeAresta(int aresta){
        for(int i = 0; i < this.numeroVertices; i++){
            if(this.matriz[i][aresta] != 0){
                this.matriz[i][aresta] = 0;
            }
        }
    }

    public boolean isAdjacente(int origem, int destino){
        for(int i = 0; i < this.numeroArestas; i++){
            if((this.matriz[origem][i] != 0) && (this.matriz[destino][i] != 0)){
                return true;
            }
        }
        return false;
    }

    public List getAdjacentes(int vertice){
        int contadora = 0;
        List<Integer> adj = new LinkedList<>();
        for(int arestas = 0; arestas < this.numeroArestas; arestas++){
            if(this.matriz[vertice][arestas] != 0){
                for(int vertices = 0; vertices < this.numeroVertices; vertices++ ){
                    if((this.matriz[vertices][arestas] != 0) && (vertices != vertice)){
                        adj.add(vertices);
                        contadora++;
                    }
                }
                if((contadora == 0) && (!adj.contains(vertice))){
                    adj.add(vertice);
                }
                contadora = 0;
            }
        }
        return adj;
    }

    public double getPeso(int aresta){
        for(int vertice = 0; vertice < this.numeroVertices; vertice++){
            if(this.matriz[vertice][aresta] != 0){
                return this.matriz[vertice][aresta];
            }
        }
        return 0;
    }

 
    
}
