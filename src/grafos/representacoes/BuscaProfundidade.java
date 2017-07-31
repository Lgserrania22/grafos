/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Gustavo
 */
public class BuscaProfundidade {
    
    //Variaveis da dfs
    private int tempo = 0;       
    private String[] cor; //Também usado na bfs
    private int[] descoberta;
    private int[] finalizacao;
    private boolean ciclo = false;
    private int verticesFinalizados = 0;
    private HashSet<ArestaSimples> arestasArvore = new HashSet<ArestaSimples>();
    private HashSet<ArestaSimples> arestasRetorno = new HashSet<ArestaSimples>();
    private HashSet<ArestaSimples> arestasAvanco = new HashSet<ArestaSimples>();
    private HashSet<ArestaSimples> arestasCross = new HashSet<ArestaSimples>();
    private int numeroVertices;
    private Grafo grafo;
    //#############################################################################
    
    public BuscaProfundidade(Grafo grafo, int raiz){
        this.grafo = grafo;
        this.numeroVertices = grafo.getNumeroDeVertices();
        this.cor = new String[this.numeroVertices];
        this.descoberta = new int[this.numeroVertices];
        this.finalizacao = new int[this.numeroVertices];
        for(int x = 0; x < this.numeroVertices; x++){
            this.cor[x] = "branco";
        }
        while(this.verticesFinalizados < this.numeroVertices){
            if("branco".equals(this.cor[raiz])){
                dfsVisit(raiz);
            }else{
                for(int i = 0; i < this.numeroVertices; i++){
                    if("branco".equals(this.cor[i])){
                        raiz = i;
                        break;
                    }
                }
            }
        }
    }
    
    public void dfsVisit(int u){
        double peso;
        this.cor[u] = "cinza";
        this.descoberta[u] = this.tempo;
        this.tempo++;
        List<ArestaSimples> arestas = (List<ArestaSimples>)this.grafo.getAdjacentes(u);
        //System.out.println("Vértice visitado:" + u + "\nTempo de descoberta: " + this.descoberta[u] + "\nCor: " + this.cor[u]);
            for(ArestaSimples aresta : arestas){
                if("branco".equals(this.cor[aresta.getDestino()])){
                    dfsVisit(aresta.getDestino());
                    aresta.setTipo("arvore");
                    this.arestasArvore.add(aresta);
                }else if("cinza".equals(this.cor[aresta.getDestino()])){
                    aresta.setTipo("retorno");
                    this.arestasRetorno.add(aresta);
                    this.ciclo = true;
                }else{
                    if(this.descoberta[u] < this.finalizacao[aresta.getDestino()]){
                        aresta.setTipo("avanço");
                        this.arestasAvanco.add(aresta);
                    }else{
                        aresta.setTipo("cross");
                        this.arestasCross.add(aresta);
                    }
                }
            }
        this.cor[u] = "preto";
        this.finalizacao[u] = this.tempo;
        this.tempo++;
        this.verticesFinalizados++;
        //System.out.println("Vértice finalizado: " + u + "\nTempo de finalização: "+ this.finalizacao[u] +"\nCor: " + this.cor[u]);  
    }
    
    public HashSet<ArestaSimples> arestasDeRetorno(){     
        return this.arestasRetorno;
    }

    public HashSet<ArestaSimples> arestasDeAvanco(){        
        return this.arestasAvanco;
    }

    public HashSet<ArestaSimples> arestasDeCruzamento(){       
        return this.arestasCross;
    }

    public HashSet<ArestaSimples> arestasDeArvore(){
       return this.arestasArvore;
    }

    public boolean existeCiclo(){
        return this.ciclo;
    }
    
    /*DA PARA MELHORAR !*/
    public Stack ordenacaoTopologica(){
        Stack pilha = new Stack();
        int aux;
        int maior = 0;
        int j;
        for(int i = 0; i < this.numeroVertices; i++){
            aux = 0;
            for(j = 0; j < this.numeroVertices; j++){
                if(this.finalizacao[j] < this.finalizacao[aux]){
                    aux = j;
                }
            }
            this.finalizacao[aux] = Integer.MAX_VALUE;
            System.out.println("Adicionou " + aux);
            pilha.push(aux);
        }
        return pilha;
    }
}
