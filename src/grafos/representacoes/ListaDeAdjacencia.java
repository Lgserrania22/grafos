/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.List;

/**
 *
 * @author Gustavo
 */
public class ListaDeAdjacencia implements Grafo {
    
    private Vertice[] vertices;
    
    public ListaDeAdjacencia(int numeroVertices){
        this.vertices = new Vertice[numeroVertices];
        for(int i = 0; i < numeroVertices; i++){
            this.vertices[i] = new Vertice(i);
        }
    }
    
    @Override
    public int getNumeroDeVertices(){
        return this.vertices.length;
    }

    @Override
    public void addAresta(int origem, int destino){
        if(!this.vertices[origem].verificaAdjacencia(destino)){
            this.vertices[origem].setAdjacente(destino);
            System.out.println("Aresta adicionada!");
        }else{
            System.out.println("Essa adjacência já está definida");
        }
    }

    @Override
    public void addAresta(int origem, int destino, double peso){
        if(!this.vertices[origem].verificaAdjacencia(destino)){
            this.vertices[origem].setAdjacente(destino, peso);
        }else{
            System.out.println("Essa adjacência já está definida");
        }
    }

    @Override
    public void setAresta(int origem, int destino){
        this.addAresta(origem, destino);
    }

    @Override
    public void setAresta(int origem, int destino, double peso){
        this.addAresta(origem, destino, peso);
    }

    @Override
    public void removeAresta(int origem, int destino){
        this.vertices[origem].removeAdjacente(destino);
    }

    @Override
    public boolean isAdjacente(int origem, int destino){
        return this.vertices[origem].verificaAdjacencia(destino);
    }

    @Override
    public List getAdjacentes(int vertice){
        return this.vertices[vertice].getAjacentes();
    }

    @Override
    public double getPeso(int origem, int destino){
        if(this.vertices[origem].verificaAdjacencia(destino)){
            return this.vertices[origem].getPeso(destino);
        }else{
            return 0;
        }    
    }
    
    public String imprimeLista(){
        String msg = "";
        for(int i = 0; i < this.vertices.length; i++){
            msg += "[" + i +"] -> ";
            for(int j = 0; j < this.vertices[i].getNumeroAdjacentes(); j++){
                msg += "[" + this.vertices[i].getAdjacente(j) + "]["+ this.vertices[i].getPeso(this.vertices[i].getAdjacente(j))+"] -> ";
            }
            msg += "\n";
        }
        return msg;
    }
}
