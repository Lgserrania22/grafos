/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.representacoes;

/**
 *
 * @author programacao
 */
public interface InterfaceAresta {
    
    public void setOrigem(int origem);
    public int getOrigem();
    public void setDestino(int destino);
    public int getDestino();
    public void setPeso(int peso);
    public double getPeso();
    
}
