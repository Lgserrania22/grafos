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
public class ListaAdjacenciaArray implements Grafo2{
    
    private int numeroVertices;
    LinkedList<ArestaSimples>[] lista;
    int cont = 0;
    
    public ListaAdjacenciaArray(int numeroVertices){
        this.numeroVertices = numeroVertices;
        this.lista = new LinkedList[numeroVertices];
        for(int i = 0; i < numeroVertices; i++){
            LinkedList<ArestaSimples> arestas = new LinkedList();
            this.lista[i] = arestas;
        }
    }

    @Override
    public int getNumeroDeVertices() {
        return this.lista.length;
    }

    @Override
    public void addAresta(int origem, int destino) {
        ArestaSimples aresta = new ArestaSimples(origem, destino);
        this.lista[origem].add(aresta);
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        ArestaSimples aresta = new ArestaSimples(origem, destino,peso);
        this.lista[origem].add(aresta);
    }

    @Override
    public void setAresta(int origem, int destino) {
        this.addAresta(origem, destino);
    }

    @Override
    public void setAresta(int origem, int destino, double peso) {
        this.addAresta(origem, destino, peso);
    }

    @Override
    public void removeAresta(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                this.lista[origem].remove(aresta);
            }
        }
    }

    @Override
    public boolean isAdjacente(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List getAdjacentes(int origem) {
        LinkedList<Integer> adjacentes = new LinkedList();
        for(ArestaSimples aresta : this.lista[origem]){
            adjacentes.add(aresta.getDestino());
        }    
        return adjacentes;
    }

    @Override
    public double getPeso(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                return aresta.getPeso();
            }
        }
        
        return 0;
    }

    @Override
    public int addVertice() {
        LinkedList<ArestaSimples>[] novaLista = new LinkedList[numeroVertices + 1];
        for(int i = 0; i < this.numeroVertices; i++){
            novaLista[i] = this.lista[i];
        }
        novaLista[this.numeroVertices] = new LinkedList();
        this.lista = novaLista;
        this.numeroVertices++;
  
        return this.numeroVertices;
    }
    
    public String imprimeLista(){
        String msg = "";
        
        for(int i = 0; i < this.numeroVertices; i++){
            msg += "[" + i + "] - " + this.getAdjacentes(i);
            msg += "\n";
        }
        
        return msg;
    }
    
    public void preencheLista(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroVertices; i++){
                tempoInicial = System.nanoTime();
                cont++;
                ArestaSimples aresta = new ArestaSimples(0,i);
                this.lista[0].add(aresta);
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
    public void acessarVertices(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroVertices; i++){
                tempoInicial = System.nanoTime();
                cont++;
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
    public void preencheVertice(){
        for (int i = 0; i < 300000; i++){
            ArestaSimples aresta = new ArestaSimples(0,0);
            this.lista[0].add(aresta);
        }
    }
    
    public void acessarArestas(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        this.preencheVertice();
        for(int i = 0; i < 300000; i++){
                tempoInicial = System.nanoTime();
                cont++;
                this.lista[0].get(i);
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
}
