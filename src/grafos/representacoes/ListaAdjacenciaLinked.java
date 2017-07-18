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
 * @author programacao
 */
public class ListaAdjacenciaLinked implements Grafo2{
    
    LinkedList<LinkedList<ArestaSimples>> vertices = new LinkedList<> ();
    
    
    private int numeroVertices;
    
    public ListaAdjacenciaLinked(int numeroVertices){
        this.numeroVertices = numeroVertices;
        for(int i = 0; i < numeroVertices; i++){
            LinkedList<ArestaSimples> aresta = new LinkedList<>();
            vertices.add(aresta);
        }
    }
    
    @Override
    public int getNumeroDeVertices(){
        return this.vertices.size();
    }

    @Override
    public void addAresta(int origem, int destino) {
        
        ArestaSimples aresta = new ArestaSimples(origem, destino);
        this.vertices.get(origem).add(aresta);
        
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        ArestaSimples aresta = new ArestaSimples(origem, destino,peso);
        this.vertices.get(origem).add(aresta);
    }

    @Override
    public void setAresta(int origem, int destino) {
        this.addAresta(origem,destino);
    }

    @Override
    public void setAresta(int origem, int destino, double peso) {
        this.addAresta(origem, destino, peso);
    }

    @Override
    public void removeAresta(int origem, int destino) {
        for(int i = 0; i < this.vertices.get(origem).size(); i++){
            if(this.vertices.get(origem).get(i).getDestino() == destino){
                this.vertices.get(origem).remove(i);
            }
        }
    }

    @Override
    public boolean isAdjacente(int origem, int destino) {
        for(int i = 0; i < this.vertices.get(origem).size(); i++){
            if(this.vertices.get(origem).get(i).getDestino() == destino){
                return true;
            }
        }
        return false;
    }

    @Override
    public List getAdjacentes(int vertice) {
        List<Integer> adjacentes = new LinkedList();
        
        for(int i = 0; i < this.vertices.get(vertice).size(); i++){
            adjacentes.add(this.vertices.get(vertice).get(i).getDestino());
        }
        
        return adjacentes;
    }

    @Override
    public double getPeso(int origem, int destino) {
        for(int i = 0; i < this.vertices.get(origem).size(); i++){
            if(this.vertices.get(origem).get(i).getDestino() == destino){
                return this.vertices.get(origem).get(i).getPeso();
            }
        }
        return 0;
    }
    
    @Override
    public int addVertice(){
        LinkedList<ArestaSimples> arestas = new LinkedList();
        this.vertices.add(arestas);
        return this.vertices.size();
    }
    
    public int removeVertice(int vertice){
        this.vertices.remove(vertice);
        return this.vertices.size();
    }
    
    public String imprimeLista(){
        String msg = "";
        
        for(int i = 0; i < this.vertices.size(); i++){
            msg += "[" + i +"] - ";
            for(int j = 0; j < this.vertices.get(i).size(); j++){
                msg += "[" + this.vertices.get(i).get(j).getDestino() + "] ->";
            }
            msg += "\n";
        }
        
        return msg;
    }
    
    
}
