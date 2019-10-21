
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natalia
 */
public class jugadorMaquina {
    int matrizAuxFichas[][];
    int fila;
    int columna;
    boolean valido;
    int fichaCentro;
    int fichaDos;       
    int indiceArreglo;
    
    public jugadorMaquina() {
        matrizAuxFichas = new int[6][2];
        fila =0;
        columna=0;
        
        fichaCentro =0;
        fichaDos =0;
    }
    
    public int randomFichaSeleccionada(){
        int numero = (int) (Math.random() * 5);
        return numero;
    }
    
    public void ramdomFilaColumna(int[][] arreglo) {
        int i = (int) (Math.random() * 10);
        int j = (int) (Math.random() * 10);
        valido = false;
        while (!valido) {
            if (((i == 0 && j == 0) || (i == 0 && j == 5) || (i == 5 && j == 10) ||
                (i == 10 && j == 0) || (i == 10 && j == 5) || (i == 5 && j == 0))&&
                arreglo[i][j]!=0) {
                i = (int) (Math.random() * 10);
                j = (int) (Math.random() * 10);
                
            } else {
                fila = i;
                columna = j;
                valido = true;
            }
        }
    }
    
    public int randomRotada(){
        int numero = (int) (Math.random() * 6)+1;
        return numero;
    }

    
    public void seleccionFichasRobada(int[][] arreglo, ArrayList<Integer> fichaR) {
        int ficha;
        int x = 6;
        for (int i = 0; i < matrizAuxFichas.length; i++) {
            for (int j = 0; j < matrizAuxFichas[i].length; j++) {
                ficha = arreglo[fichaR.get(x)][j];
                matrizAuxFichas[i][j] = ficha;
            }
            x++;
        }
    }
        
        public void ficha(){
            int n= randomFichaSeleccionada();
            indiceArreglo = n;
            fichaCentro = matrizAuxFichas[n][0];
            fichaDos = matrizAuxFichas[n][1];        
        }
        
    
                
    
    
    
}
