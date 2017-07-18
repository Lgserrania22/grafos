package grafos.representacoes;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Willian Soares
 */
public class MatrizDeAdjacencia implements Grafo{

    private int[][] matriz;
    int numeroDeVertices;

    public MatrizDeAdjacencia(int numeroDeVertices) {
        this.matriz = new int[numeroDeVertices][numeroDeVertices];
        this.numeroDeVertices = numeroDeVertices;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    @Override
    public int getNumeroDeVertices() {
        return this.matriz.length;
    }

    @Override
    public void addAresta(int origem, int destino) {
        if (origem != destino) {
            this.matriz[origem][destino] = 1;
            this.matriz[destino][origem] = 1;
        }
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        if (origem != destino) {
            this.matriz[origem][destino] = (int)peso;
            this.matriz[destino][origem] = (int)peso;
        }
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
        this.setAresta(origem, destino, 0);
    }

    @Override
    public boolean isAdjacente(int origem, int destino) {
        return this.matriz[origem][destino] != 0
                || this.matriz[destino][origem] != 0;
    }

    @Override
    public List getAdjacentes(int vertice) {
        List<Integer> adj = new LinkedList<>();
        for (int coluna = 0; coluna < this.matriz[vertice].length; coluna++) {
            if (isAdjacente(vertice, coluna)) {
                adj.add(coluna);
            }
        }
        return adj;
    }

    @Override
    public double getPeso(int origem, int destino) {
        return this.matriz[origem][destino];
    }
    
    public void addVertice(){
        int[][] matriz2 = new int[this.numeroDeVertices + 1][this.numeroDeVertices + 1];
        for(int i = 0; i < this.numeroDeVertices; i++){
            for(int j = 0; j < this.numeroDeVertices; j++){
                matriz2[i][j] = this.matriz[i][j];
            }
        }
        this.matriz = matriz2;
        this.numeroDeVertices++;
    }
    
    public void imprimeMatriz(){
        for(int i = 0; i < this.numeroDeVertices; i++){
            for(int j = 0; j < this.numeroDeVertices; j++){
                System.out.print("[" + this.matriz[i][j] + "]");
            }
            System.out.println("");
        }
    }
    
    public void preencheMatriz(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroDeVertices; i++){
            for(int j = 0; j < this.numeroDeVertices; j++){
                tempoInicial = System.nanoTime();
                cont++;
                this.addAresta(i, j);
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
    
    public void acessarArestas(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        int teste;
        for(int i = 0; i < this.numeroDeVertices; i++){
            for(int j = 0; j < this.numeroDeVertices; j++){
                tempoInicial = System.nanoTime();
                cont++;
                teste = this.matriz[i][j];
                if(cont == 1000){
                 tempoFinal = tempoFinal/1000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
            }
        }
    }

}
