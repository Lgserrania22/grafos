/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

import java.util.List;

/**
 *
 * @author a14034
 */
public interface Grafo {

    public int getNumeroDeVertices();

    public void addAresta(int origem, int destino);

    public void addAresta(int origem, int destino, double peso);

    public void setAresta(int origem, int destino);

    public void setAresta(int origem, int destino, double peso);

    public void removeAresta(int origem, int destino);

    public boolean isAdjacente(int origem, int destino);

    public List getAdjacentes(int vertice);

    public double getPeso(int origem, int destino);
}
