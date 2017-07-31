package grafos.representacoes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Willian Soares
 */
public class MatrizDeAdjacencia extends Grafo{

    private int[][] matriz;
    int numeroVertices;
    //Variaveis da DFS
    private int tempo = 0;       
    private String[] cor; //Serve também para a bfs
    private int[] descoberta;
    private int[] finalizacao;
    private boolean ciclo = false;
    private int verticesFinalizados;
    private Collection<ArestaSimples> arestasArvore = new ArrayList<ArestaSimples>();
    private Collection<ArestaSimples> arestasRetorno = new ArrayList<ArestaSimples>();
    private Collection<ArestaSimples> arestasAvanco = new ArrayList<ArestaSimples>();
    private Collection<ArestaSimples> arestasCross = new ArrayList<ArestaSimples>();
    //##############################################################################
    //Variáveis da BFS
    private int[] distancia;
    private int[] pai;
    LinkedList<Integer> fila = new LinkedList();
    //#############################################################################

    public MatrizDeAdjacencia(int numeroDeVertices) {
        this.matriz = new int[numeroDeVertices][numeroDeVertices];
        this.numeroVertices = numeroDeVertices;
        this.cor = new String[this.numeroVertices];
        this.descoberta = new int[this.numeroVertices];
        this.pai = new int[this.numeroVertices];
        this.finalizacao = new int[this.numeroVertices];
        this.distancia = new int[this.numeroVertices];
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
    
    public void addArestaOrientada(int origem, int destino) {
        if (origem != destino) {
            this.matriz[origem][destino] = 1;
        }
    }
    
    public void addArestaOrientada(int origem, int destino, double peso) {
        if (origem != destino) {
            this.matriz[origem][destino] = (int)peso;
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
        return this.matriz[origem][destino] != 0;
    }

    @Override
    public List getAdjacentes(int vertice) {
        List<ArestaSimples> adj = new LinkedList<>();
        for (int coluna = 0; coluna < this.matriz[vertice].length; coluna++) {
            if (isAdjacente(vertice, coluna)) {
                adj.add(new ArestaSimples(vertice, coluna, this.matriz[vertice][coluna]));
            }
        }
        return adj;
    }

    @Override
    public double getPeso(int origem, int destino) {
        return this.matriz[origem][destino];
    }
    
    @Override
    public int addVertice(){
        int[][] matriz2 = new int[this.numeroVertices + 1][this.numeroVertices + 1];
        for(int i = 0; i < this.numeroVertices; i++){
            for(int j = 0; j < this.numeroVertices; j++){
                matriz2[i][j] = this.matriz[i][j];
            }
        }
        this.matriz = matriz2;
        this.numeroVertices++;
        return this.numeroVertices;
    }
    
    private void imprimeMatriz(){
        for(int i = 0; i < this.numeroVertices; i++){
            for(int j = 0; j < this.numeroVertices; j++){
                System.out.print("[" + this.matriz[i][j] + "]");
            }
            System.out.println("");
        }
    }
    
    public void imprime(){
        imprimeMatriz();
    }
    
    public void preencheMatriz(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroVertices; i++){
            for(int j = 0; j < this.numeroVertices; j++){
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
        for(int i = 0; i < this.numeroVertices; i++){
            for(int j = 0; j < this.numeroVertices; j++){
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
    
    public void buscaEmLargura(int raiz){
        int u;
        for(int i = 0; i < this.numeroVertices; i++){
            this.cor[i] = "branco";
            this.distancia[i] = -1;
            this.pai[i] = -1;
        }
        this.cor[raiz] = "cinza";
        this.distancia[raiz] = 0;
        System.out.println("Visitou o vértice: " + raiz + "\nCor: " + this.cor[raiz] +"\nDistância: " + this.distancia[raiz] + "\nSeu pai: " + this.pai[raiz]);
        enfileira(raiz);
        while(this.fila.size() > 0){
            u = desenfileira();
            for(int j = 0; j < this.numeroVertices; j++){
                if(this.matriz[u][j] == 1){
                    int vertice = j;
                    if("branco".equals(this.cor[vertice])){
                        this.cor[vertice] = "cinza";
                        this.distancia[vertice] = this.distancia[u] + 1;
                        this.pai[vertice] = u;
                        System.out.println("Visitou o vértice: " + vertice + "\nCor: " + this.cor[vertice] +"\nDistância: " + this.distancia[vertice] + "\nSeu Pai: " + this.pai[vertice]);
                        enfileira(vertice);
                    }
                }
            }
            System.out.println("Vértice finalizado: " + u);
            this.cor[u] = "preto";
        }
    }
    
    private void enfileira(int u){
        this.fila.add(u);
    }
    
    private int desenfileira(){
        int u = this.fila.get(0);
        this.fila.remove(0);
        return u;
    }

    public String paisEDistancias(){
        String texto = "";
        for(int i = 0; i < this.numeroVertices; i++){
            texto += "Vértice: " + i + " | Pai: " + this.pai[i] + " | Distância: " + this.distancia[i] + "\n";
        }
        return texto;
    }
    
}
