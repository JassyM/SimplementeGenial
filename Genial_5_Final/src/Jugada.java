/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jassy
 */
public class Jugada {
    
    private int ficha1;
    private int ficha2;
    private int utilidad1;
    private int utilidad2;
    private int posicionMatriz; // La posición en la que está en la matriz de 6x2
    private int color1; // Color de cada ficha.
    private int color2;
    private int posFicha1_I;  // Posiciones de las fichas en el tablero.
    private int posFicha1_J;
    private int posFicha2_I;
    private int posFicha2_J;
    
    public Jugada(){
        ficha1 = 0;
        ficha2 = 0;
        utilidad1 = 0;
        utilidad2 = 0;
        posicionMatriz = 0;
        color1 = 0;
        color2 = 0;
        posFicha1_I = 0;
        posFicha1_J = 0;
        posFicha2_I = 0;
        posFicha2_J = 0;
    }
    
    public void setFicha1(int f1){
        ficha1 = f1;
    }
    
    // Valores de la primera ficha.
    public int getFicha1(){
        return ficha1;
    }
    
    
    public void setFicha2(int f2){
        ficha2 = f2;
    }
    
    // Valores de la segunda ficha.
    public int getFicha2(){
        return ficha2;
    }
    
    public void setPosicionMatriz(int pos){
        posicionMatriz = pos;
    }
    
    public int getPosicionMatriz(){
        return posicionMatriz;
    }
    
    public void setUtilidad1(int util){
        utilidad1 = util;
    }
    
    public int getUtilidad1(){
       return utilidad1;
    }
    
    public void setUtilidad2(int util){
        utilidad2 = util;
    }
    
    public int getUtilidad2(){
       return utilidad2;
    }
    
    public void setPosFicha1 (int i, int j){
        posFicha1_I = i;
        posFicha1_J = j;
    }
    
    public int getPosFicha1_I(){
        return posFicha1_I;
    }
    
    public int getPosFicha1_J(){
        return posFicha1_J;
    }
    
    
    public void setPosFicha2 (int i, int j){
        posFicha2_I = i;
        posFicha2_J = j;
    }
    
    public int getPosFicha2_I(){
        return posFicha2_I;
    }
    
    public int getPosFicha2_J(){
        return posFicha2_J;
    }
    
}
