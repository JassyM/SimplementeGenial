/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.TransferHandler;
/**
 *
 * @author Natalia
 */
public class tabla extends javax.swing.JFrame  {
    jugadorMaquina jugadorMaq;
    int arrayAuxiliar[];
    boolean juego =true;
    JLabel matrizJuego[][]; //Matriz del labels de la tabla del juego
    JLabel fichasJuego[][]; //Matriz de labels de las fichas robadas
    JLabel matrizMover[][]; //Matriz de labels del movimiento
    JLabel matrizPuntajeHumano[][];//Panel de los puntajes
    JLabel matrizPuntajeMaquina[][];
    int fichaRotada;
    int matrizAux[][]; //Matriz de enteros de la tabla del juego, cada posicion sin ficha es un cero
    int matrizMovAux[][]; //Matriz de enteros del movimiento, ayuda a rotar la imagen
    int fichaCentroMovimiento = 0; //numero de la ficha que va en el centro de la matriz del movimiento
    int auxiliar[][]; //Matriz de enteros del movimiento, ayuda a rotar la imagen(Se utilizaria para las validaciones)
    int matrizAuxFichas[][]; //Matriz de enteros de las fichas robadas
    int setFichas[][];//Matriz que guarda las 120 fichas totales del juego
    Icon ficha_Roja;
    Icon ficha_Azul;
    Icon ficha_Verde;
    Icon ficha_Amarilla;
    Icon ficha_Naranja;
    Icon ficha_Morada;
    int puntajeFichaRoja;
    int puntajeFichaAzul;
    int puntajeFichaVerde;
    int puntajeFichaMorada;
    int puntajeFichaAmarilla;
    int puntajeFichaNaranja;
    int puntajeFichaRojaMaquina;
    int puntajeFichaAzulMaquina;
    int puntajeFichaVerdeMaquina;
    int puntajeFichaMoradaMaquina;
    int puntajeFichaAmarillaMaquina;
    int puntajeFichaNaranjaMaquina;
    int p;
    int[] fi, co;
    boolean jugadorHumano;
    JLabel numPuntaje[];
    boolean puntje;
    ArrayList<Integer> fichasRobadas; //Lista que guarda las fichas que se han robado
    boolean turno1Humano;
    boolean turno1Maquina;
    int fichaCentro;
    int fichaDos;
    int posFichaDos;
    boolean valido; // Se usa para saber si es válido  colocar una ficha en un lugar del tablero
    int numLabel; // Número del label de la ficha seleccionada de las fichas robadas. Se actualizan cada que seleccione
     // una ficha (en cada uno de los eventos).
    boolean seleccion; //Cuando en el label de rotacion hay alguna ficha su valor es true, indica que hay una ficha seleccionada.
    boolean robar; // Variable para saber si el jugador ha robado o no una ficha.
    boolean validacionMaquina;
    int maquinaI; // Posición i de la ficha2 que va a colocar la máquina en el tablero.
    int maquinaJ; // Posición j de la ficha2 que va a colocar la máquina en el tablero.
    int fichas21[][];
    ArrayList<Jugada> listaJugadas; // Lista que guarda todas las posibles jugadas que puede hacer la máquina.
    boolean fichaPeor; // Variable que se usa en colocarFichaMaquina(). True si el jugador máquina tiene una ficha con el color del
                        // peor puntaje. False si no tiene una ficha disponible con el peor puntaje.
    int posicionMenor;
    int[] arregloColorMaquina, filaMaquina, columnaMaquina;
    
    /**
     * Creates new form tabla
     */
    public tabla() {
        
        initComponents();
        fi=new int[2];
        co=new int[2];
        botonDerecho.setVisible(false);
        botonIzquierdo.setVisible(false);
        jugadorMaq = new jugadorMaquina();
        puntje = true;
        validacionMaquina = false;
        robar = true;
        turno1Humano = false;
        turno1Maquina = false;
        jugadorHumano = true;
        puntajeFichaRoja = 0;
        puntajeFichaAzul = 0;
        puntajeFichaVerde = 0;
        puntajeFichaMorada = 0;
        puntajeFichaAmarilla = 0;
        puntajeFichaNaranja = 0;
        puntajeFichaRojaMaquina = 0;
        puntajeFichaAzulMaquina = 0;
        puntajeFichaVerdeMaquina = 0;
        puntajeFichaMoradaMaquina = 0;
        puntajeFichaAmarillaMaquina = 0;
        puntajeFichaNaranjaMaquina = 0;
        
        arrayAuxiliar = new int[2];
        numPuntaje = new JLabel[19];
        fichasRobadas = new ArrayList<>();
        matrizPuntajeHumano = new JLabel[6][18];
        matrizPuntajeMaquina = new JLabel[6][18];
        setFichas = new int[120][2];
        matrizJuego = new JLabel[11][11];
        matrizMover = new JLabel[3][3];
        matrizMovAux = new int[3][3];
        auxiliar = new int[3][3];
        fichasJuego = new JLabel[6][2];
        matrizAuxFichas = new int[6][2];
        matrizAux = new int[11][11];
        fichaRotada = 0;
        fichaCentro = 1;
        fichaDos = 1;
        posFichaDos = 0;
        valido = true;
        numLabel = 0;
        seleccion = false;
        fichas21 = new int[21][2];
        maquinaI = 0;
        maquinaJ = 0;
        listaJugadas = new ArrayList<>();
        arregloColorMaquina = new int[2];
        filaMaquina = new int[2];
        columnaMaquina = new int[2];
        fichaPeor = false;
        posicionMenor = 0;
        
        for (int i = 0; i < auxiliar.length; i++) {
            for (int j = 0; j < auxiliar[i].length; j++) {
                auxiliar[i][j] = 0;
            }
        }
        botonRobar.setEnabled(false);
        fichas();
        llenarMatrizAux();
        tablero();
        fichasConstantes();
        llenarSetFichas();
        fichasRobadas();
        fichasJugar();
        movimiento();
        panelPuntjHumano();
        panelPuntjMaquina();
    }
    
    public void puntNum(){
        int x=26;
        for (int i = 0; i < numPuntaje.length; i++) {
            numPuntaje[i] = new JLabel();
            numPuntaje[i].setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
       //     numPuntaje[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       numPuntaje[i].setForeground(java.awt.Color.white);     
       numPuntaje[i].setText(i+"");
            
            if(puntje){
            panelJugadorHumano.add(numPuntaje[i]);
            }
            else {
                panelJugadorMaquina.add(numPuntaje[i]);
            }
            numPuntaje[i].setBounds(x,80, 13, 13);
            x+=23.7;
        }
    }

      
    
    public void panelPuntjHumano(){
        puntNum();
        puntje =false;
        int x=45;
        int y=100;
        for (int i = 0; i < matrizPuntajeHumano.length; i++) {
            for (int j = 0; j < matrizPuntajeHumano[i].length; j++) {
                matrizPuntajeHumano[i][j] = new JLabel();
                matrizPuntajeHumano[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/punto.png"))); 
                panelJugadorHumano.add(matrizPuntajeHumano[i][j]);
                matrizPuntajeHumano[i][j].setBounds(x, y, 13, 13);
                x+=23.7;
            }
            x=45;
       y+=25;
    }
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/puntaje.png"))); // NOI18N
        panelJugadorHumano.add(jLabel1);
        jLabel1.setBounds(15, 90, 450, 160);
    }
    
    public void panelPuntjMaquina(){
        puntNum();
        int x=45;
        int y=100;
        for (int i = 0; i < matrizPuntajeMaquina.length; i++) {
            for (int j = 0; j < matrizPuntajeMaquina[i].length; j++) {
                matrizPuntajeMaquina[i][j] = new JLabel();
                matrizPuntajeMaquina[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/punto.png"))); 
                panelJugadorMaquina.add(matrizPuntajeMaquina[i][j]);
                matrizPuntajeMaquina[i][j].setBounds(x, y, 13, 13);
                x+=23.7;
            }
            x=45;
       y+=25;
    }
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/puntaje.png"))); // NOI18N
        panelJugadorMaquina.add(jLabel1);
        jLabel1.setBounds(15, 90, 450, 160);
    }
    
    
    
    //Llena la matriz de las 120 fichas del juego
    public void llenarSetFichas() {
        int fichas[] = {2, 3, 4, 5, 6, 7};
        int i;
        int x = 0;
        int n = 0;
        while (x < 120) {
            for (i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    setFichas[x][0] = fichas[i];
                    setFichas[x][1] = fichas[i];
                    x++;
                }
                fichas21[i][0]=fichas[i];
                fichas21[i][1]=fichas[i];
            }
            // x = 30 Valor con el que termina x
            int f = 6; // Valor con el que termina i
            for (int z = 0; z < 5; z++) {
                for (i = z; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        setFichas[x][0] = fichas[n];
                        setFichas[x][1] = fichas[i + 1];
                        x++;
                    }
                    fichas21[f][0] = fichas[n];
                    fichas21[f][1] = fichas[i+1];
                    f += 1;
                }
                n++;
            }
        }
    }
    
    //Solo la utilizo cuando necesito hacer pruebas por consola .-.
    public void n() {
        /*        for(int d=0; d<fichasRobadas.size();d++){
               System.out.println(fichasRobadas.get(d));
            }*/
        System.out.println("pR: " + puntajeFichaRoja);
        System.out.println("pA: " + puntajeFichaAzul);
        System.out.println("pAn: " + puntajeFichaAmarilla);
        System.out.println("pV: " + puntajeFichaVerde);
        System.out.println("pN: " + puntajeFichaNaranja);
        System.out.println("pM: " + puntajeFichaMorada);
        System.out.println("p: " + p);
        
    }
    
    //Valida si el ramdom que salio ya habia sido antes
    public boolean validarFicha(int n) {
        boolean ficha = false;
        for (int i = 0; i < fichasRobadas.size(); i++) {
            if (n == fichasRobadas.get(i)) {
                ficha = true;
                i= fichasRobadas.size();
            }
        }
        return ficha;
    }
    
    //llena la lista de las fichas que se han sacado, son 12 por los dos jugadores
    public void fichasRobadas() {
        int ficha = fichaRandom();
        fichasRobadas.add(ficha);
        while (fichasRobadas.size() != 12) {
            ficha = fichaRandom();
            if (!validarFicha(ficha)) {
                fichasRobadas.add(ficha);
            }
        }
    }

    //Crea el tablero del juego con la matriz de labels y llena la matriz auxiliar con ceros
    public void tablero() {
        int x0 = 130;
        int x = 130;
        int xf = 40;
        int y = 48;
        int f0 = 6;
        int f = 6;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < f; j++) {
                matrizJuego[i][j] = new JLabel();
                matrizJuego[i][j].setBounds(x, y, 38, 41);
                matrizJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.png")));
                matrizJuego[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        labelTableroMouseClicked(evt);
                    }
                });
                panelTablero.add(matrizJuego[i][j]);
                matrizAux[i][j] = 0;
                x += 35;
            }
            y += 30;
            if (f0 < 11) {
                x = x0 - 18;
                x0 = x;
                f++;
                f0++;
            } else {
                x = xf + 18;
                xf = x;
                f--;
            }
        }
    }
    
    //Crea los labels y LLena la matriz que permite rotar la ficha
    public void movimiento() {
        int x0 = 32;
        int x = 32;
        int xf = 32;
        int y = 10;
        int f0 = 2;
        int f = 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < f; j++) {
                matrizMover[i][j] = new JLabel();
                matrizMover[i][j].setBounds(x, y, 40, 41);
                panelMoverFicha.add(matrizMover[i][j]);
                x += 35;
            }
            y += 30;
            if (f0 < 3) {
                x = x0 - 17;
                x0 = x;
                f++;
                f0++;
            } else {
                x = xf;
                f--;
            }
        }
    }
    
    //solo llena la matriz auxiliar del juego con 1, esta se llama antes de la funcion tablero, con la funcion tablero, esta 
    //matriz se llena de cero los campos que se van a utilizar para el juego
    public void llenarMatrizAux() {
        matrizAux = new int[11][11];
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                matrizAux[i][j] = 1;
            }
        }
    }
    
    //Solo da la imagen de la ficha a cada variable
    public void fichas(){
        ficha_Roja = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaRoja.png"));
        ficha_Azul = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaAzul.png"));
        ficha_Verde = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaVerde.png"));
        ficha_Amarilla = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaAmarilla.png"));
        ficha_Naranja = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaNaranja.png"));
        ficha_Morada = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaMorada.png"));
    }
      
    public void fichaRotacion() {
     if(auxiliar[2][0]!=0){
                    fichaRotada =1;
                    fichaDos=auxiliar[2][0];
                    seleccion =  true;
                }
                else if(auxiliar[1][0]!=0){
                    fichaRotada =2;
                    fichaDos=auxiliar[1][0];
                    seleccion =  true;
                }
                else if(auxiliar[0][0]!=0){
                    fichaRotada =3;
                    fichaDos=auxiliar[0][0];
                    seleccion =  true;
                }
                else if(auxiliar[0][1]!=0){
                    fichaRotada =4;
                    fichaDos=auxiliar[0][1];
                    seleccion =  true;
                }
                else if(auxiliar[1][2]!=0){
                    fichaRotada =5;
                    fichaDos=auxiliar[1][2];
                    seleccion =  true;
                }
                else if(auxiliar[2][1]!=0){
                    fichaRotada =6;
                    fichaDos=auxiliar[2][1];
                    seleccion =  true;
                }
                else{
                    seleccion = false;
                }
    }
    
    //Crea las 6 fichas constantes del juego y cada ficha tiene un color y se añade a la matriz auxiliar del tablero
    public void fichasConstantes() {
        matrizJuego[0][0].setIcon(ficha_Roja);
        matrizAux[0][0] = 2; //Roja

        matrizJuego[5][10].setIcon(ficha_Azul);
        matrizAux[5][10] = 3; //Azul

        matrizJuego[0][5].setIcon(ficha_Verde);
        matrizAux[0][5] = 4; //Verde

        matrizJuego[10][0].setIcon(ficha_Amarilla);
        matrizAux[10][0] = 5; //Amarilla

        matrizJuego[10][5].setIcon(ficha_Naranja);
        matrizAux[10][5] = 6; //Naranja

        matrizJuego[5][0].setIcon(ficha_Morada);
        matrizAux[5][0] = 7;// Morada

        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                System.out.print(matrizAux[i][j]);
            }
            System.out.println();
        }
    }
    
    //Se obtiene un numero random del 0-119
    public int fichaRandom(){
        int numero = (int) (Math.random() * 119);
        return numero;
    }
    
    //añade las fichas que se robaron al juego
    public void fichasJugar() {
        JPanel[] panelFichas = {panelFicha1, panelFicha2, panelFicha3, panelFicha4, panelFicha5, panelFicha6};
        int ficha;
        int y;
        for (int i = 0; i < fichasJuego.length; i++) {
            y = 9;
            for (int j = 0; j < fichasJuego[i].length; j++) {
                fichasJuego[i][j] = new JLabel();
                fichasJuego[i][j].setBounds(8, y, 41, 41);
                ficha = setFichas[fichasRobadas.get(i)][j];
                matrizAuxFichas[i][j] = ficha;
                if (ficha == 2) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaR.png")));
                } else if (ficha == 3) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaAz.png")));
                } else if (ficha == 4) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaV.png")));
                } else if (ficha == 5) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaA.png")));
                } else if (ficha == 6) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaN.png")));
                } else if (ficha == 7) {
                    fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaM.png")));
                }
                panelFichas[i].add(fichasJuego[i][j]);
                y = 44;
            }
        }
    }
    
    //Funcion que ayuda cuando se da clic en una de las fichas
    public Icon fichaUtilizar(int i, int j) {
        Icon icono = new javax.swing.ImageIcon(getClass().getResource("/imagenes/ficha.png"));
        if (matrizAuxFichas[i][j] == 2) {
            icono = ficha_Roja;
        } else if (matrizAuxFichas[i][j] == 3) {
            icono = ficha_Azul;
        } else if (matrizAuxFichas[i][j] == 4) {
            icono = ficha_Verde;
        } else if (matrizAuxFichas[i][j] == 5) {
            icono = ficha_Amarilla;
        } else if (matrizAuxFichas[i][j] == 6) {
            icono = ficha_Naranja;
        } else if (matrizAuxFichas[i][j] == 7) {
            icono = ficha_Morada;
        }
        return icono;

    }
    
    //Limpia cuando se cambia la ficha que se selecciono
    public void limpiarMov() {
        for (int i = 0; i < matrizMovAux.length; i++) {
            for (int j = 0; j < matrizMovAux[i].length; j++) {
                if (matrizMovAux[i][j] == 2 || matrizMovAux[i][j] == 3 || matrizMovAux[i][j] == 4 || matrizMovAux[i][j] == 5
                        || matrizMovAux[i][j] == 6 || matrizMovAux[i][j] == 7) {
                    matrizMovAux[i][j] = 0;
                    if (i != 1 && j != 1) {
                        auxiliar[i][j] = 0;
                    }
                    matrizMover[i][j].setIcon(null);
                }
            }
        }
    }
    
    
    public void pintarFicha(int i, int j, int numFicha, int posicion) { // Función que pinta la ficha seleccionada en el tablero
        
        if (posicion == 0) {                // de juego. Dentro de la función se hacen las validaciones de cuando no es válido
            if (numFicha == 2) {            // alguna selección para colocar la ficha. Se llama en el evento del tablero.
                matrizJuego[i][j].setIcon(ficha_Roja);
                matrizAux[i][j] = 2; //Roja
                arrayAuxiliar[0] = 2;
                fi[0]=i;
                co[0]=j;       
           //     puntajeFichaRoja += setPuntaje(fi[0], co[0], 2);    
            } else if (numFicha == 3) {
                matrizJuego[i][j].setIcon(ficha_Azul);
                matrizAux[i][j] = 3; //Azul
                arrayAuxiliar[0] = 3;                
                fi[0]=i;
                co[0]=j;
           //     puntajeFichaAzul += setPuntaje(fi[0], co[0], 3);    
            } else if (numFicha == 4) {
                matrizJuego[i][j].setIcon(ficha_Verde);
                matrizAux[i][j] = 4; //Verde
                arrayAuxiliar[0] = 4;
                fi[0]=i;
                co[0]=j;
             //   puntajeFichaVerde += setPuntaje(fi[0], co[0], 4);    
            } else if (numFicha == 5) {
                matrizJuego[i][j].setIcon(ficha_Amarilla);
                matrizAux[i][j] = 5; //Amarilla
                arrayAuxiliar[0]= 5;
                fi[0]=i;
                co[0]=j;
               // puntajeFichaAmarilla += setPuntaje(fi[0], co[0], 5);    
            } else if (numFicha == 6) {
                matrizJuego[i][j].setIcon(ficha_Naranja);
                matrizAux[i][j] = 6; //Naranja
                arrayAuxiliar[0] = 6;
                fi[0]=i;
                co[0]=j;
               // puntajeFichaNaranja += setPuntaje(fi[0], co[0], 6);    
            } else if (numFicha == 7) {
                matrizJuego[i][j].setIcon(ficha_Morada);
                matrizAux[i][j] = 7; // Morada
                arrayAuxiliar[0] = 7;
                fi[0]=i;
                co[0]=j;
                //puntajeFichaMorada += setPuntaje(fi[0], co[0], 7);    
            }
           
        } else if (posicion == 1) {

            if ((j == 0 && i > 5) || i == 10) { // Válida las orillas del tablero.
                
                    JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                            "Mensaje de Advertencia", //Título
                            JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                valido = false;
            } else {
                if (i > 4) {
                    j -= 1;
                }
                i += 1;
                fi[1] = i;
                co[1] = j;
                arrayAuxiliar[0] = 0;
                if (matrizAux[i][j] == 0) {
                    if (numFicha == 2) {
                        matrizJuego[i][j].setIcon(ficha_Roja);
                        matrizAux[i][j] = 2; //Roja
                        arrayAuxiliar[1] = 2;
                        puntajeFichaRoja += setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);  
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }

                    } else if (numFicha == 3) {
                        matrizJuego[i][j].setIcon(ficha_Azul);
                        matrizAux[i][j] = 3; //Azul
                        arrayAuxiliar[1] = 3;
                        puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);  
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }
                    } else if (numFicha == 4) {
                        matrizJuego[i][j].setIcon(ficha_Verde);
                        matrizAux[i][j] = 4; //Verde
                        arrayAuxiliar[1] = 4;
                        puntajeFichaVerde += setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co); 
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }
                    } else if (numFicha == 5) {
                        matrizJuego[i][j].setIcon(ficha_Amarilla);
                        matrizAux[i][j] = 5; //Amarilla
                        arrayAuxiliar[1] = 5;
                        puntajeFichaAmarilla += setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co); 
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }
                        
                    } else if (numFicha == 6) {
                        matrizJuego[i][j].setIcon(ficha_Naranja);
                        matrizAux[i][j] = 6; //Naranja
                        arrayAuxiliar[1] = 6;
                        puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);  
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }
                        
                    } else if (numFicha == 7) {
                        matrizJuego[i][j].setIcon(ficha_Morada);
                        matrizAux[i][j] = 7; // Morada
                        arrayAuxiliar[1] = 7;
                        puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                         for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);
        }
                    }
                    valido = true;
                } else {
                    if (jugadorHumano) {
                        JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                                "Mensaje de Advertencia", //Título
                                JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                    }
                    valido = false;
                }
            }
            
        } else if (posicion == 2) {
            if (j == 0 && (i > 5 || j < 5)) { // Válida las orillas del tablero.
                
                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                        "Mensaje de Advertencia", //Título
                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                
                valido = false;
            } else {
                j -= 1;
                fi[1] = i;
                co[1] = j;
                arrayAuxiliar[0] = 0;
                System.out.println("Natalia");
  
                if (matrizAux[i][j] == 0) {
                    if (numFicha == 2) {
                        matrizJuego[i][j].setIcon(ficha_Roja);
                        matrizAux[i][j] = 2; //Roja
                        arrayAuxiliar[1] = 2;
                        puntajeFichaRoja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 3) {
                        matrizJuego[i][j].setIcon(ficha_Azul);
                        matrizAux[i][j] = 3; //Azul
                        arrayAuxiliar[1] = 3;
                        puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 4) {
                        matrizJuego[i][j].setIcon(ficha_Verde);
                        matrizAux[i][j] = 4; //Verde
                        arrayAuxiliar[1] = 4;
                        puntajeFichaVerde+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 5) {
                        matrizJuego[i][j].setIcon(ficha_Amarilla);
                        matrizAux[i][j] = 5; //Amarilla
                        arrayAuxiliar[1] = 5;
                        puntajeFichaAmarilla+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
 
                    } else if (numFicha == 6) {
                        matrizJuego[i][j].setIcon(ficha_Naranja);
                        matrizAux[i][j] = 6; //Naranja
                        arrayAuxiliar[1] = 6;
                        puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
 
                    } else if (numFicha == 7) {
                        matrizJuego[i][j].setIcon(ficha_Morada);
                        matrizAux[i][j] = 7; // Morada
                        arrayAuxiliar[1] = 7;
                        puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                                  for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);}
     
                    }
                    valido = true;
                } else {
                    
                        JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                                "Mensaje de Advertencia", //Título
                                JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                    
                    valido = false;
                }
            }
        } else if (posicion == 3) {
            if ((j == 0 && i < 5) || i == 0) { // Válida las orillas del tablero.
                
                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                        "Mensaje de Advertencia", //Título
                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                
                
                valido = false;
            } else {
                if (i <= 5) {
                    j -= 1;
                }
                i -= 1;
                fi[1] = i;
                co[1] = j;
                for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);}
                arrayAuxiliar[0] = 0;
                if (matrizAux[i][j] == 0) {
                    if (numFicha == 2) {
                        matrizJuego[i][j].setIcon(ficha_Roja);
                        matrizAux[i][j] = 2; //Roja
                        arrayAuxiliar[1] = 2;
                        puntajeFichaRoja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 3) {
                        matrizJuego[i][j].setIcon(ficha_Azul);
                        matrizAux[i][j] = 3; //Azul
                        arrayAuxiliar[1] = 3;
                        puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                        
                    } else if (numFicha == 4) {
                        matrizJuego[i][j].setIcon(ficha_Verde);
                        matrizAux[i][j] = 4; //Verde
                        arrayAuxiliar[1] = 4;
                        puntajeFichaVerde+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 5) {
                        matrizJuego[i][j].setIcon(ficha_Amarilla);
                        matrizAux[i][j] = 5; //Amarilla
                        arrayAuxiliar[1] = 5;
                        puntajeFichaAmarilla+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 6) {
                        matrizJuego[i][j].setIcon(ficha_Naranja);
                        matrizAux[i][j] = 6; //Naranja
                        arrayAuxiliar[1] = 6;
                        puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 7) {
                        matrizJuego[i][j].setIcon(ficha_Morada);
                        matrizAux[i][j] = 7; // Morada
                        arrayAuxiliar[1] = 7;
                        puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                        
                    }
                    valido = true;
                } else {
                    
                        JOptionPane.showMessageDialog(null, "El movimiento no es válido,", //Mensaje
                                "Mensaje de Advertencia", //Título
                                JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                    
                    valido = false;
                }
            }
        } else if (posicion == 4) {
            if (i == 0) { // Válida las orillas del tablero.
                
                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                        "Mensaje de Advertencia", //Título
                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                
                valido = false;
            } else {
                if (i > 5) {
                    j += 1;
                }
                i -= 1;
                fi[1] = i;
                co[1] = j;
                arrayAuxiliar[0] = 0;
            for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);}
                if (matrizAux[i][j] == 0) {
                    if (numFicha == 2) {
                        matrizJuego[i][j].setIcon(ficha_Roja);
                        matrizAux[i][j] = 2; //Roja
                        arrayAuxiliar[1] = 2;
                        puntajeFichaRoja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                    } else if (numFicha == 3) {
                        matrizJuego[i][j].setIcon(ficha_Azul);
                        matrizAux[i][j] = 3; //Azul
                        arrayAuxiliar[1] = 3;
                        puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 4) {
                        matrizJuego[i][j].setIcon(ficha_Verde);
                        matrizAux[i][j] = 4; //Verde
                        arrayAuxiliar[1] = 4;
                        puntajeFichaVerde+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 5) {
                        matrizJuego[i][j].setIcon(ficha_Amarilla);
                        matrizAux[i][j] = 5; //Amarilla
                        arrayAuxiliar[1] = 5;
                        puntajeFichaAmarilla+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 6) {
                        matrizJuego[i][j].setIcon(ficha_Naranja);
                        matrizAux[i][j] = 6; //Naranja
                        arrayAuxiliar[1] = 6;
                        puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 7) {
                        matrizJuego[i][j].setIcon(ficha_Morada);
                        matrizAux[i][j] = 7; // Morada
                        arrayAuxiliar[1] = 7;
                        puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                    }
                    valido = true;
                } else {
                    
                        JOptionPane.showMessageDialog(null, "El movimiento no es válido,", //Mensaje
                                "Mensaje de Advertencia", //Título
                                JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                    
                    valido = false;
                }
            }
        } else if (posicion == 5) {
            j += 1;
            fi[1] = i;
            co[1] = j;
            for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);}
            arrayAuxiliar[0] = 0;
            if (matrizAux[i][j] == 0) {
                if (numFicha == 2) {
                    matrizJuego[i][j].setIcon(ficha_Roja);
                    matrizAux[i][j] = 2; //Roja
                    arrayAuxiliar[1] = 2;
                    puntajeFichaRoja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                } else if (numFicha == 3) {
                    matrizJuego[i][j].setIcon(ficha_Azul);
                    matrizAux[i][j] = 3; //Azul
                    arrayAuxiliar[1] = 3;
                    puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
 
                } else if (numFicha == 4) {
                    matrizJuego[i][j].setIcon(ficha_Verde);
                    matrizAux[i][j] = 4; //Verde
                    arrayAuxiliar[1] = 4;
                    puntajeFichaVerde+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                } else if (numFicha == 5) {
                    matrizJuego[i][j].setIcon(ficha_Amarilla);
                    matrizAux[i][j] = 5; //Amarilla
                    arrayAuxiliar[1] = 5;
                    puntajeFichaAmarilla+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                } else if (numFicha == 6) {
                    matrizJuego[i][j].setIcon(ficha_Naranja);
                    matrizAux[i][j] = 6; //Naranja
                    arrayAuxiliar[1] = 6;
                    puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                } else if (numFicha == 7) {
                    matrizJuego[i][j].setIcon(ficha_Morada);
                    matrizAux[i][j] = 7; // Morada
                    arrayAuxiliar[1] = 7;
                    puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                }
                valido = true;
            } else {
                
                    JOptionPane.showMessageDialog(null, "El movimiento no es válido,", //Mensaje
                            "Mensaje de Advertencia", //Título
                            JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                
                valido = false;
            }
        } else if (posicion == 6) {
            if (i == 10) { // Válida las orillas del tablero.
                
                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                        "Mensaje de Advertencia", //Título
                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                
                valido = false;
            } else {
                if (i <= 4) {
                    j += 1;
                }
                i += 1;
                fi[1] = i;
                co[1] = j;
                for(int d=0; d<2; d++){
            System.out.println("Fi "+fi[d]);
            System.out.println("Co "+co[d]);
             System.out.println("Color "+arrayAuxiliar[d]);}
                arrayAuxiliar[0] = 0;
                if (matrizAux[i][j] == 0) {
                    if (numFicha == 2) {
                        matrizJuego[i][j].setIcon(ficha_Roja);
                        matrizAux[i][j] = 2; //Roja
                        arrayAuxiliar[1] = 2;
                        puntajeFichaRoja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 3) {
                        matrizJuego[i][j].setIcon(ficha_Azul);
                        matrizAux[i][j] = 3; //Azul
                        arrayAuxiliar[1] = 3;
                        puntajeFichaAzul+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 4) {
                        matrizJuego[i][j].setIcon(ficha_Verde);
                        matrizAux[i][j] = 4; //Verde
                        arrayAuxiliar[1] = 4;
                        puntajeFichaVerde+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);

                    } else if (numFicha == 5) {
                        matrizJuego[i][j].setIcon(ficha_Amarilla);
                        matrizAux[i][j] = 5; //Amarilla
                        arrayAuxiliar[1] = 5;
                        puntajeFichaAmarilla+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                        
                    } else if (numFicha == 6) {
                        matrizJuego[i][j].setIcon(ficha_Naranja);
                        matrizAux[i][j] = 6; //Naranja
                        arrayAuxiliar[1] = 6;
                        puntajeFichaNaranja+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co);
                        
                    } else if (numFicha == 7) {
                        matrizJuego[i][j].setIcon(ficha_Morada);
                        matrizAux[i][j] = 7; // Morada
                        arrayAuxiliar[1] = 7;
                        puntajeFichaMorada+= setPuntaje(fi[1], co[1], arrayAuxiliar[1], arrayAuxiliar, fi, co );
                        
                    }
                    valido = true;
                } else {
                    
                        JOptionPane.showMessageDialog(null, "El movimiento no es válido,", //Mensaje
                                "Mensaje de Advertencia", //Título
                                JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                    
                    valido = false;
                }
            }
        }
    }
    
    public void puntaje(){
        for(int d=0; d<2; d++){
            System.out.println("Fi2 "+fi[d]);
            System.out.println("Co2 "+co[d]);
             System.out.println("Color2 "+arrayAuxiliar[d]);
        }
        
        if (arrayAuxiliar[0]==2) {
            puntajeFichaRoja += setPuntaje(fi[0], co[0], 2, arrayAuxiliar, fi, co);               
        } 
        
        if (arrayAuxiliar[0]==3) {
            puntajeFichaAzul += setPuntaje(fi[0], co[0], 3, arrayAuxiliar, fi, co);               
        } 
        
        if (arrayAuxiliar[0]==4) {
            puntajeFichaVerde += setPuntaje(fi[0], co[0], 4, arrayAuxiliar, fi, co);               
        } 
        
        if (arrayAuxiliar[0]==5) {
            puntajeFichaAmarilla += setPuntaje(fi[0], co[0], 5, arrayAuxiliar, fi, co);               
        } 
        

        if (arrayAuxiliar[0]==6) {
            puntajeFichaNaranja += setPuntaje(fi[0], co[0], 6, arrayAuxiliar, fi, co);               
        } 
        

        if (arrayAuxiliar[0]==7) {
            puntajeFichaMorada += setPuntaje(fi[0], co[0], 7, arrayAuxiliar, fi, co);               
        } 
        
        
            for (int s = 0; s < puntajeFichaRoja; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[0][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pr.png")));
                }
            }
            for (int s = 0; s < puntajeFichaVerde; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[1][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pv.png")));
                }
            }
            for (int s = 0; s < puntajeFichaAzul; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[2][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paz.png")));
                }
            }
            for (int s = 0; s < puntajeFichaNaranja; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[3][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pn.png")));
                }
            }
            for (int s = 0; s < puntajeFichaAmarilla; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[4][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pa.png")));
                }
            }

            for (int s = 0; s < puntajeFichaMorada; s++) {
                if (s <= 17) {
                    matrizPuntajeHumano[5][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pm.png")));
                }
            }
        
    }

    public int setPuntaje(int i, int j, int ficha, int[] arregloColor, int[]posI, int[]posJ) {
        int n;
        int b;
        p = 0;
        //-------------------Valida color ficha izquierda-----------------------
        n = i;
        b = j - 1;
        if (arregloColor[0] == arregloColor[1]) {
            if ((posI[0] == n && posJ[0] == b)||(posI[1] == n && posJ[1] == b)) {
                p += 0;
            } else {
                while (b >= 0) {
                    if (matrizAux[n][b] == ficha) {
                        p++;
                    } else {
                        b = 0;
                    }
                    b--;
                }
            }
        } else {
            while (b >= 0) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                    b = 0;
                }
                b--;
            }
        }
        //----------------------------------------------------------------------

        //Valida color ficha diagonal izquierda abajo
        n = i;
        b = j;
        int a;
        
        if (arregloColor[0] == arregloColor[1]) {
                    
        if (n <= 5) {
            n = i + 1;
            b = j;
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
            while (n <= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                    
                } else {
                    n=6;
                }
                n++;
            }
            if (n == 6) {
                n = 5;
            }

            if (matrizAux[n][b] == ficha && b != 0 && n==5) {

                if (b < 5) {
                    b = j - 1;

                    while (b >= 0) {
                        if (matrizAux[n][b] == ficha) {
                            
                            p++;
                        } else {
                           b=0;
                        }
                        n++;
                        b--;
                    }
                } else {
                    b = j - 1;

                    while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                           
                            p++;
                        } else {
                            n=10;
                        }
                         n++;
                            b--;
                    }
                }
            }
        } }else {
            n = i;
            b = j;
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
            if (n == 10) {
                a = n;
            } else if ((n==5&&b<=4)||(n==6&&b<=3)||(n==7&&b<=2)||(n==8&&b<=1)||(n==9&&b<=0)) {
                a = n + b;
            } else {
                a = 10;
            }
            n++;
            b--;
            while (n <= a) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                   n=a;
                }
                n++;
                b--;
            }

        }}}
        
        else {
        
        if (n < 5) {
            n = i + 1;
            b = j;
            while (n <= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                    
                } else {
                    n=6;
                }
                n++;
            }
            if (n == 6) {
                n = 5;
            }

            if (matrizAux[n][b] == ficha && b != 0 && n==5) {

                if (b < 5) {
                    b = j - 1;

                    while (b >= 0) {
                        if (matrizAux[n][b] == ficha) {
                            
                            p++;
                        } else {
                            b=0;
                        }
                        n++;
                            b--;
                    }
                } else {
                    b = j - 1;

                    while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                            
                            p++;
                        } else {
                           n=10;
                        }
                        n++;
                            b--;
                    }
                }
            }
        } else {
            n = i;
            b = j;
            if (n == 10) {
                a = n;
            } else if ((n==5&&b<=4)||(n==6&&b<=3)||(n==7&&b<=2)||(n==8&&b<=1)||(n==9&&b<=0)) {
                a = n + b;
            } else {
                a = 10;
            }
            n++;
            b--;
            while (n <= a) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                    n=a;
                }
                n++;
                b--;
            }

        }}
    //---------------------------------------------------------------------------    
        
       //------------------------Valida color ficha diagonal derecha abajo
        n = i;
        b = j;
        int m=0;
        if (arregloColor[0] == arregloColor[1]) {
            
                if (n < 5) {
                    
                    n = i + 1;
                    b = j + 1;
                    if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
                    while (n <= 5) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=6;
                        }
                        n++;
                        b++;
                    }
                    if(n==6){
                        n=5;
                    }
                    if(b<10 && matrizAux[n][b] == ficha){
                        n = i + 1;
                        b = j;
                        while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=10;
                        }
                        n++;

                       }
                    }}
                }
                else{
                    
                    n = i + 1;
                        b = j;
                        if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
                        while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=10;
                        }
                        n++;

                       }
                }}
            }  
            
        else {
            n=i;
            b=j;
                if (n < 5) {
                    n = i + 1;
                    b = j + 1;
                    while (n <= 5) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=6;
                        }
                        n++;
                        b++;
                    }
                    if(n==6){
                        n=5;
                    }
                    if(b<10 && matrizAux[n][b] == ficha){
                        n = i + 1;
                        b = j;
                        while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=10;
                        }
                        n++;

                       }
                    }
                }
                else{
                    n = i + 1;
                        b = j;
                        while (n <= 10) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                        } else {
                            n=10;
                        }
                        n++;

                       }
                }}
        
        //----------------------------------------------------------------------
        
        //-------------------------Valida color ficha derecha 
        n = i;
        b = j + 1;
        if (arregloColor[0] == arregloColor[1]) {
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
                while (b <= 10) {
                    if (matrizAux[n][b] == ficha) {
                        p++;
                    } else {
                        b = 10;
                    }
                    b++;
                }
            }
        } else {
            while (b <= 10) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                    b=10;
                }
                b++;
            }
        }
        //----------------------------------------------------------------------

        //---------------------------Valida color ficha derecha arriba
        n = i;
        b = j;
        int c = 0;
        if (arregloColor[0] == arregloColor[1]) {
        if (n <= 5) {
            n = i - 1;
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
            while (n >= 0) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                    
                } else {
                    n=0;
                }
                n--;
            }
        } }else {

            n = i - 1;
            b = j + 1;
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
            while (n >= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                    n=4;
                }
                 n--;
                 b++;
            }
            if(n==4){
                n=5;
            }


            if (b<10 && matrizAux[n][b] == ficha && n==5) {
                n = j - 1;
                    while (n >= 0) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                            n=0;
                        } else {
                            break;
                        }
                        n--;
                    }
                }
            }}
        
        }else {
            n=i;
            b=j;
        if (n <= 5) {
            n = i - 1;
            while (n >= 0) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                    
                } else {
                    n=0;
                }
                n--;
            }
        } else {

            n = i - 1;
            b = j + 1;
            while (n >= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;
                } else {
                    n=5;
                }
                 n--;
                 b++;
            }
            if(n==4){
                n=5;
            }
            if (b<10 &&matrizAux[n][b] == ficha && n==5) {
                n = j - 1;
                    while (n >= 0) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                            n=0;
                        } else {
                            break;
                        }
                        n--;
                    }
                }
        }}
        
                
       
     //------------------------------------------------------------------------   
        //Valida ficha color izquierdo arriba
        n = i;
        b = j;
        int f;
        if (arregloColor[0] == arregloColor[1]) {
            
                if (n <= 5) {
                    if (b == 5||(n==4 && b>=5)||(n==3 && b>=4)||(n==2 && b>=3)||(n==1 && b>=2)||(n==0 && b>=1)) {
                        f = 0;
                    } 
                    else if(n==5){
                        f = 4;
                    }
                    else {
                        f = n - b;
                    }
                    n = i - 1;
                    b = j - 1;
                    if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                         p+=0;
            } else {
                        while (n >= f) {
                            if (matrizAux[n][b] == ficha) {
                                p++;

                            } else {
                                n = f;
                            }
                            n--;
                            b--;
                        }
                    }
                }
        else {
            n = i - 1;
            b = j;
            if ((posI[0] == n && posJ[0] == b) || (posI[1] == n && posJ[1] == b)) {
                p+=0;
            } else {
            while (n >= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;     
                } else {
                    n=3;
                }
                n--;
            }
            if (n == 4) {
                n = 5;
            }
            if (matrizAux[n][b] == ficha && b>0) {
                if (b == 5||(n==4 && b>=5)||(n==3 && b>=4)||(n==2 && b>=3)||(n==1 && b>=2)||(n==0 && b>=1)) {
                        f = 0;
                    } 
                    else if(n==5){
                        f = 4;
                    }
                    else {
                        f = n - b;
                    }
                n = i - 1;
                b = j - 1;
                
                    while (n >= f) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                            
                        } else {
                            n=f;
                        }
                        n--;
                        b--;
                    
                }
            }

        }}
            }
         else {
            n = i;
        b = j;
               if (n <= 5) {
                    if (b == 5||(n==4 && b>=5)||(n==3 && b>=4)||(n==2 && b>=3)||(n==1 && b>=2)||(n==0 && b>=1)) {
                        f = 0;
                    } 
                    else if(n==5){
                        f = 4;
                    }
                    else {
                        f = n - b;
                    }
                    n = i - 1;
                    b = j - 1;
                    
                    while (n >= f) {
                            if (matrizAux[n][b] == ficha) {
                                p++;

                            } else {
                                n = f;
                            }
                            n--;
                            b--;
                        }
                    
                }
        else {
            n = i - 1;
            b = j;
            while (n >= 5) {
                if (matrizAux[n][b] == ficha) {
                    p++;     
                } else {
                    n=3;
                }
                n--;
            }
            if (n == 4) {
                n = 5;
            }
            if (matrizAux[n][b] == ficha && b>0) {
                if (b==5||(n==4 && b>=5)||(n==3 && b>=4)||(n==2 && b>=3)||(n==1 && b>=2)||(n==0 && b>=1)) {
                        f = b-n;
                        
                    }else {
                    f = 0;
                }
                n = i - 1;
                b = j - 1;
                if(n!=5){
                    while (n >= f) {
                        if (matrizAux[n][b] == ficha) {
                            p++;
                            
                        } else {
                            n=f;
                        }
                        n--;
                        b--;
                    
                }}
            }

        }
            }
     //   n();
        return p;

    }

    
    public boolean validarFinJuegoPosicion1() {
        boolean vacio = false;
        int a,b;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                a=i;
                b=j;
                if (matrizAux[i][j] == 0) {
                    if ((b == 0 && a> 5) || a == 10) {
                        vacio = false;
                    } else {
                        if (a > 4) {
                            b -= 1;
                        }
                        a += 1;
                        if (matrizAux[a][b] == 0) {
                            vacio = true;
                            i = 12;
                            j = 12;
                        }
                    }
                }

            }    
        }
        return vacio;
    }
    
    public boolean validarFinJuegoPosicion2() {
        int a,b;
        boolean vacio = false;
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                a=i;
                b=j;
                if (matrizAux[i][j] == 0) {
                    if (b == 0 && (a > 5 || b < 5)) {
                        vacio = false;
                    }
                    else {
                        b -= 1;
                        if (matrizAux[a][b] == 0) {
                            vacio = true;
                            i = 12;
                            j = 12;
                        }
                    }
                }
            }
        }
        return vacio;
    }
    
    public boolean validarFinJuegoPosicion3() {
        int a,b;
        boolean vacio = false;
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                a=i;
                b=j;
                if (matrizAux[i][j] == 0) {
                    if ((b == 0 && a < 5) || a == 0) { // Válida las orillas del tablero.
                        vacio = false;
                    } else {
                        if (a <= 5) {
                            b -= 1;
                        }
                        a -= 1;
                        if (matrizAux[a][b] == 0) {
                            vacio = true;
                            i = 12;
                            j = 12;
                        }
                    }
                }
            }
        }
        return vacio;
    }

    public boolean validarFinJuegoPosicion4() {
        int a, b;
        boolean vacio = false;
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                a = i;
                b = j;
                if (matrizAux[i][j] == 0) {

                    if (a == 0) { // Válida las orillas del tablero.
                        vacio = false;
                    }
                 else {
                    if (a > 5) {
                        b += 1;
                    }
                    a -= 1;
                    if (matrizAux[a][b] == 0) {
                        vacio = true;
                        i = 12;
                        j = 12;
                    }
                }
            
        }}}
        return vacio;
    }
    
    public boolean validarFinJuegoPosicion5() {
        int a, b;
        boolean vacio = false;
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                a = i;
                b = j;
                if (matrizAux[i][j] == 0) {
                    b += 1;
                    if (matrizAux[a][b] == 0) {
                        vacio = true;
                        i = 12;
                        j = 12;
                    }
                }
            }
        }
        return vacio;
    }
    
        public boolean validarFinJuegoPosicion6() {
        int a, b;
        boolean vacio = false;
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                a = i;
                b = j;
                if (matrizAux[i][j] == 0) {
                    if (a == 0) { // Válida las orillas del tablero.
                        vacio = false;
                    } else {
                        if (a <= 4) {
                            b += 1;
                        }
                        a += 1;
                        if (matrizAux[a][b] == 0) {
                            vacio = true;
                            i = 12;
                            j = 12;
                        }
                    }
                }
            }
        }
        return vacio;
    }
        
    public boolean validacionFinJuego(){
        boolean vacio=false;
        if(validarFinJuegoPosicion1()||validarFinJuegoPosicion2()||validarFinJuegoPosicion3()||validarFinJuegoPosicion4()||
           validarFinJuegoPosicion5()||validarFinJuegoPosicion6()){
            vacio=true;
        }
        return vacio;
    }

    
    public void colocarFichaRobada(int i, int j,int numFicha){
        if (numFicha == 2) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaR.png")));
        } else if (numFicha == 3) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaAz.png")));
        } else if (numFicha == 4) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaV.png")));
        } else if (numFicha == 5) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaA.png")));
        } else if (numFicha == 6) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaN.png")));
        } else if (numFicha == 7) {
            fichasJuego[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fichaM.png")));
        }
        //panelFichas[i].add(fichasJuego[i][j]);
    }
    
    public void limpiarSeleccion(){  // Limpia las matrices que contienen información de la ficha seleccionada. Se llama 
                                    // en el evento del tablero, sólo después de realizar una jugada válida.
        System.out.println("Tamaño: matrizMovAux.length: " + matrizMovAux.length);
        for(int i = 0; i < matrizMovAux.length; i++){
            for(int j = 0; j < matrizMovAux[i].length; j++){
                System.out.println("matrizMovAux["+ i +"]" + "["+ i +"] = " + matrizMovAux[i][j]);
                if(matrizMovAux[i][j] == 2 || matrizMovAux[i][j] == 3 || matrizMovAux[i][j] == 4 || matrizMovAux[i][j] == 5
                        || matrizMovAux[i][j] == 6 || matrizMovAux[i][j] == 7){
                    matrizMover[i][j].setIcon(null);
                    matrizMovAux[i][j] = 0;
                    auxiliar[i][j] = 0;
                    fichaCentroMovimiento = 0;
                }
                matrizMover[1][1].setIcon(null);
            }
        }
        matrizAuxFichas[numLabel][0] = 0;
        matrizAuxFichas[numLabel][1] = 0;
        fichasJuego[numLabel][0].setIcon(null);
        fichasJuego[numLabel][1].setIcon(null);
    }
    
    
    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    //    Para el minimax
    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    public void construir() {
        jugadorMaq.seleccionFichasRobada(setFichas, fichasRobadas);
        for (int i = 0; i < jugadorMaq.matrizAuxFichas.length; i++) {
            for (int j = 0; j < jugadorMaq.matrizAuxFichas[i].length - 1; j++) {
                if (jugadorMaq.matrizAuxFichas[i][j] == jugadorMaq.matrizAuxFichas[i][j + 1]) {
                    recorrerTablero(jugadorMaq.matrizAuxFichas[i][j], jugadorMaq.matrizAuxFichas[i][j + 1], i);
                } else if (jugadorMaq.matrizAuxFichas[i][j] != jugadorMaq.matrizAuxFichas[i][j + 1]) {
                    recorrerTablero(jugadorMaq.matrizAuxFichas[i][j], jugadorMaq.matrizAuxFichas[i][j + 1], i);
                    recorrerTablero(jugadorMaq.matrizAuxFichas[i][j + 1], jugadorMaq.matrizAuxFichas[i][j], i);
                }
            }
        }
        leerListaJugadas();
        System.out.println("Al final de construir. ");
    }

    public void leerListaJugadas(){
        for (int i = 0; i < listaJugadas.size(); i++) {
            System.out.println("F1: ["+ listaJugadas.get(i).getPosFicha1_I()+"]["+listaJugadas.get(i).getPosFicha1_J()+"] = "+
                    listaJugadas.get(i).getFicha1() + "  Util1:"+listaJugadas.get(i).getUtilidad1() +
                 "  - F2: [" +listaJugadas.get(i).getPosFicha2_I()+"]["+listaJugadas.get(i).getPosFicha2_J()+"] = " +
                    listaJugadas.get(i).getFicha2() +"  Util2: "+listaJugadas.get(i).getUtilidad2()+" PosMatriz: "+ listaJugadas.get(i).getPosicionMatriz());
        }
    }
    
    public void recorrerTablero(int ficha1, int ficha2, int posMatriz) {
        Jugada jugadaMax;
        int posI;
        int posJ;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (matrizAux[i][j] == ficha1) {
                    // Validar posiciones de ficha1.
                    if (validarFicha(i, j, 1)) { // Valido la posición 1 para la ficha1 de acuerdo a la posición de la ficha de color.
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        // Validar posiciones de ficha2.
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;
                                
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }
                    }
                    if (validarFicha(i, j, 2)) { //  Ficha 1 posición 2
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;
                                
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }
                    }
                    if (validarFicha(i, j, 3)) { // Ficha 1 posición 3
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;
                                
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }
                    }
                    if (validarFicha(i, j, 4)) { //  Ficha 1 posición 4
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;
                                
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }
                    }
                    if (validarFicha(i, j, 5)) { //  Ficha 1 posición 5
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;
                                
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }

                    }
                    if (validarFicha(i, j, 6)) { //  Ficha 1 posición 6
                        int ii = maquinaI;
                        int jj = maquinaJ;
                        posI = maquinaI;
                        posJ = maquinaJ;
                        for (int d = 1; d < 7; d++) {
                            if (validarFicha(posI, posJ, d)) {// En cada validarFicha se actualiza el valor de maquinaI y maquinaJ
                                jugadaMax = new Jugada();
                                jugadaMax.setFicha1(ficha1);
                                jugadaMax.setFicha2(ficha2);
                                jugadaMax.setPosicionMatriz(posMatriz);
                                jugadaMax.setPosFicha1(ii, jj);
                                jugadaMax.setPosFicha2(maquinaI, maquinaJ);
                                arregloColorMaquina[0] = 0;
                                arregloColorMaquina[1] = ficha2;
                                filaMaquina[1] = maquinaI;
                                columnaMaquina[1] = maquinaJ;
                                jugadaMax.setUtilidad2(setPuntaje(filaMaquina[1], columnaMaquina[1], ficha2, arregloColorMaquina, filaMaquina, columnaMaquina));
                                arregloColorMaquina[0] = ficha1;
                                filaMaquina[0] = ii;
                                columnaMaquina[0] = jj;                             
                                jugadaMax.setUtilidad1(setPuntaje(filaMaquina[0], columnaMaquina[0], ficha1, arregloColorMaquina, filaMaquina, columnaMaquina));
                                listaJugadas.add(jugadaMax);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean validarFicha(int i, int j, int posicion) { 
        boolean val = false;
        if (posicion == 0) {                
            if (matrizAux[i][j] == 0) {            
                val = true;
                maquinaI = i;
                maquinaJ = j;
            }else{
                val = false;
            }
        } else if (posicion == 1) {
            if ((j == 0 && i >= 5) || i == 10) { // Válida las orillas del tablero.
                val = false;
            } else {
                if (i > 4) {
                    j -= 1;
                }
                i += 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        } else if (posicion == 2) {
            if (j == 0) { 
                val = false;
            } else {
                j -= 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        } else if (posicion == 3) {
            if ((j == 0 && i <= 5) || i == 0) {
                val = false;
            } else {
                if (i <= 5) {
                    j -= 1;
                }
                i -= 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        } else if (posicion == 4) {
            if (i == 0 || (i == 0 && j == 10)) {
                val = false;
            } else {
                if (i > 5) {
                    j += 1;
                }
                i -= 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        } else if (posicion == 5) {
            if (i == 5 && j == 10) {
                val = false;
            } else {
                j += 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        } else if (posicion == 6) {
            if (i == 10 || (i == 5 && j == 10)) {
                val = false;
            } else {
                if (i <= 4) {
                    j += 1;
                }
                i += 1;
                if (matrizAux[i][j] == 0) {
                    maquinaI = i;
                    maquinaJ = j;
                    val = true;
                }
            }
        }
        return val;
    }
    
    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------
    
        //-----------------------Jugador Maquina--------------------------
    public int menorPuntaje(ArrayList<Integer> puntaj, ArrayList<Integer> fich) {
        int menor = puntaj.get(0);
        int fichaMenor = fich.get(0);
        for (int i = 1; i < puntaj.size(); i++) {
            if (menor > puntaj.get(i)) {
                menor = puntaj.get(i);
                fichaMenor = fich.get(i);
                posicionMenor = i;
            }
        }
        System.out.println("J " + fichaMenor);
        System.out.println("Az: " + puntajeFichaAzulMaquina);
        System.out.println("V: " + puntajeFichaVerdeMaquina);
        System.out.println("Am: " + puntajeFichaAmarillaMaquina);
        System.out.println("N: " + puntajeFichaNaranjaMaquina);
        System.out.println("M: " + puntajeFichaMoradaMaquina);
        return fichaMenor;
    }
    
    public void colocarFichaMaquina(int color) {
        fichaPeor = false;
        int max = 0;
        int posMax = 0; // Posición en la listaJugadas del que hace mayor utilidad.
        for (int i = 0; i < listaJugadas.size(); i++) {
            if (listaJugadas.get(i).getFicha1() == color) {
                fichaPeor = true;
                if (listaJugadas.get(i).getUtilidad1() > max) {
                    max = listaJugadas.get(i).getUtilidad1();
                    posMax = i; // Posición en el listaJugadas.
                }
            }if (listaJugadas.get(i).getFicha2() == color) {
                fichaPeor = true;
                if (listaJugadas.get(i).getUtilidad2() > max) {
                    max = listaJugadas.get(i).getUtilidad2();
                    posMax = i; // Posición en el listaJugadas.
                } 
            }
        }
        
        if (fichaPeor) {
            System.out.println("Jugada Max " + listaJugadas.get(posMax).getFicha1());
            // Ficha 1
            matrizAux[listaJugadas.get(posMax).getPosFicha1_I()][listaJugadas.get(posMax).getPosFicha1_J()] = listaJugadas.get(posMax).getFicha1();
            matrizJuego[listaJugadas.get(posMax).getPosFicha1_I()][listaJugadas.get(posMax).getPosFicha1_J()].setIcon(getIconoFicha(listaJugadas.get(posMax).getFicha1()));

            // Ficha 2
            matrizAux[listaJugadas.get(posMax).getPosFicha2_I()][listaJugadas.get(posMax).getPosFicha2_J()] = listaJugadas.get(posMax).getFicha2();
            matrizJuego[listaJugadas.get(posMax).getPosFicha2_I()][listaJugadas.get(posMax).getPosFicha2_J()].setIcon(getIconoFicha(listaJugadas.get(posMax).getFicha2()));
            int l = 0;
            int ficha;
            while (l == 0) {
                ficha = fichaRandom();
                if (!validarFicha(ficha)) {
                    fichasRobadas.add(ficha);
                    jugadorMaq.matrizAuxFichas[listaJugadas.get(posMax).getPosicionMatriz()][0] = setFichas[ficha][0];
                    jugadorMaq.matrizAuxFichas[listaJugadas.get(posMax).getPosicionMatriz()][1] = setFichas[ficha][1];
                    l = 1;
                }
            }

            if (listaJugadas.get(posMax).getFicha1() == 2) {
                puntajeFichaRojaMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 2) {
                puntajeFichaRojaMaquina += listaJugadas.get(posMax).getUtilidad2();
            }
            if (listaJugadas.get(posMax).getFicha1() == 3) {
                puntajeFichaAzulMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 3) {
                puntajeFichaAzulMaquina += listaJugadas.get(posMax).getUtilidad2();
            }
            if (listaJugadas.get(posMax).getFicha1() == 4) {
                puntajeFichaVerdeMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 4) {
                puntajeFichaVerdeMaquina += listaJugadas.get(posMax).getUtilidad2();
            }
            if (listaJugadas.get(posMax).getFicha1() == 5) {
                puntajeFichaAmarillaMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 5) {
                puntajeFichaAmarillaMaquina += listaJugadas.get(posMax).getUtilidad2();
            }
            if (listaJugadas.get(posMax).getFicha1() == 6) {
                puntajeFichaNaranjaMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 6) {
                puntajeFichaNaranjaMaquina += listaJugadas.get(posMax).getUtilidad2();
            }
            if (listaJugadas.get(posMax).getFicha1() == 7) {
                puntajeFichaMoradaMaquina += listaJugadas.get(posMax).getUtilidad1();
            }
            if (listaJugadas.get(posMax).getFicha2() == 7) {
                puntajeFichaMoradaMaquina += listaJugadas.get(posMax).getUtilidad2();
            }

            for (int s = 0; s < puntajeFichaRojaMaquina; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[0][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pr.png")));
                }
            }
            for (int s = 0; s < puntajeFichaVerdeMaquina; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[1][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pv.png")));
                }
            }
            for (int s = 0; s < puntajeFichaAzulMaquina; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[2][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paz.png")));
                }
            }
            for (int s = 0; s < puntajeFichaNaranjaMaquina; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[3][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pn.png")));
                }
            }
            for (int s = 0; s < puntajeFichaAmarilla; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[4][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pa.png")));
                }
            }

            for (int s = 0; s < puntajeFichaMoradaMaquina; s++) {
                if (s <= 17) {
                    matrizPuntajeMaquina[5][s].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pm.png")));
                }
            }
        }
    }
    
    public Icon getIconoFicha(int f){
        Icon ficha = new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.png"));
        if(f == 2){
            ficha = ficha_Roja;
        } else if (f == 3){
            ficha = ficha_Azul;
        } else if (f == 4){
            ficha = ficha_Verde;
        } else if (f == 5){
            ficha = ficha_Amarilla;
        } else if (f == 6){
            ficha = ficha_Naranja;
        } else if (f == 7){
            ficha = ficha_Morada;
        }
        return ficha;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelFichasJugar = new javax.swing.JPanel();
        panelFicha1 = new javax.swing.JPanel();
        panelFicha2 = new javax.swing.JPanel();
        panelFicha3 = new javax.swing.JPanel();
        panelFicha4 = new javax.swing.JPanel();
        panelFicha5 = new javax.swing.JPanel();
        panelFicha6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelMovimiento = new javax.swing.JPanel();
        botonDerecho = new javax.swing.JButton();
        botonIzquierdo = new javax.swing.JButton();
        panelMoverFicha = new javax.swing.JPanel();
        panelJugadorHumano = new javax.swing.JPanel();
        panelJugadorHumano1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelJugadorHumano2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelJugadorHumano3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelTablero = new javax.swing.JPanel();
        botonRobar = new javax.swing.JButton();
        panelJugadorMaquina = new javax.swing.JPanel();
        panelJugadorHumano5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelJugadorHumano6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        panelJugadorHumano7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(850, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1410, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(221, 221, 221));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(221, 221, 221));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setLayout(null);

        panelFichasJugar.setBackground(new java.awt.Color(221, 221, 221));
        panelFichasJugar.setOpaque(false);
        panelFichasJugar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFicha1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha1MouseClicked(evt);
            }
        });
        panelFicha1.setLayout(null);
        panelFichasJugar.add(panelFicha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 60, 90));

        panelFicha2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha2MouseClicked(evt);
            }
        });
        panelFicha2.setLayout(null);
        panelFichasJugar.add(panelFicha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 60, 90));

        panelFicha3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha3MouseClicked(evt);
            }
        });
        panelFicha3.setLayout(null);
        panelFichasJugar.add(panelFicha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 60, 90));

        panelFicha4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha4MouseClicked(evt);
            }
        });
        panelFicha4.setLayout(null);
        panelFichasJugar.add(panelFicha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 60, 90));

        panelFicha5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha5MouseClicked(evt);
            }
        });
        panelFicha5.setLayout(null);
        panelFichasJugar.add(panelFicha5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 90));

        panelFicha6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFicha6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFicha6MouseClicked(evt);
            }
        });
        panelFicha6.setLayout(null);
        panelFichasJugar.add(panelFicha6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 60, 90));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/colocarFichas.png"))); // NOI18N
        panelFichasJugar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 490, 130));

        jPanel3.add(panelFichasJugar);
        panelFichasJugar.setBounds(450, 470, 530, 140);

        panelMovimiento.setBackground(new java.awt.Color(221, 221, 221));
        panelMovimiento.setOpaque(false);
        panelMovimiento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonDerecho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flechaDerecha.png"))); // NOI18N
        botonDerecho.setBorderPainted(false);
        botonDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDerechoActionPerformed(evt);
            }
        });
        panelMovimiento.add(botonDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        botonIzquierdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flechaIzquierda.png"))); // NOI18N
        botonIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIzquierdoActionPerformed(evt);
            }
        });
        panelMovimiento.add(botonIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        panelMoverFicha.setOpaque(false);
        panelMoverFicha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMoverFichaMousePressed(evt);
            }
        });
        panelMoverFicha.setLayout(null);
        panelMovimiento.add(panelMoverFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 120));

        jPanel3.add(panelMovimiento);
        panelMovimiento.setBounds(1020, 390, 150, 160);

        panelJugadorHumano.setBackground(new java.awt.Color(221, 221, 221));
        panelJugadorHumano.setOpaque(false);
        panelJugadorHumano.setLayout(null);

        panelJugadorHumano1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tu Puntaje");
        panelJugadorHumano1.add(jLabel3);
        jLabel3.setBounds(0, 20, 530, 40);

        panelJugadorHumano.add(panelJugadorHumano1);
        panelJugadorHumano1.setBounds(0, 0, 0, 0);

        panelJugadorHumano2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano2.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tu Puntaje");
        panelJugadorHumano2.add(jLabel4);
        jLabel4.setBounds(0, 20, 530, 40);

        panelJugadorHumano3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tu Puntaje");
        panelJugadorHumano3.add(jLabel5);
        jLabel5.setBounds(0, 20, 530, 40);

        panelJugadorHumano2.add(panelJugadorHumano3);
        panelJugadorHumano3.setBounds(0, 0, 0, 0);

        panelJugadorHumano.add(panelJugadorHumano2);
        panelJugadorHumano2.setBounds(0, 0, 0, 0);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/jdr.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        panelJugadorHumano.add(jLabel2);
        jLabel2.setBounds(120, 20, 240, 72);

        jPanel3.add(panelJugadorHumano);
        panelJugadorHumano.setBounds(30, 220, 470, 260);

        panelTablero.setOpaque(false);

        javax.swing.GroupLayout panelTableroLayout = new javax.swing.GroupLayout(panelTablero);
        panelTablero.setLayout(panelTableroLayout);
        panelTableroLayout.setHorizontalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        panelTableroLayout.setVerticalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jPanel3.add(panelTablero);
        panelTablero.setBounds(470, 50, 430, 440);

        botonRobar.setText("Robar Ficha");
        botonRobar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRobarMouseClicked(evt);
            }
        });
        jPanel3.add(botonRobar);
        botonRobar.setBounds(660, 600, 120, 30);

        panelJugadorMaquina.setBackground(new java.awt.Color(221, 221, 221));
        panelJugadorMaquina.setOpaque(false);
        panelJugadorMaquina.setLayout(null);

        panelJugadorHumano5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano5.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tu Puntaje");
        panelJugadorHumano5.add(jLabel7);
        jLabel7.setBounds(0, 20, 530, 40);

        panelJugadorMaquina.add(panelJugadorHumano5);
        panelJugadorHumano5.setBounds(0, 0, 0, 0);

        panelJugadorHumano6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano6.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tu Puntaje");
        panelJugadorHumano6.add(jLabel8);
        jLabel8.setBounds(0, 20, 530, 40);

        panelJugadorHumano7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJugadorHumano7.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tu Puntaje");
        panelJugadorHumano7.add(jLabel9);
        jLabel9.setBounds(0, 20, 530, 40);

        panelJugadorHumano6.add(panelJugadorHumano7);
        panelJugadorHumano7.setBounds(0, 0, 0, 0);

        panelJugadorMaquina.add(panelJugadorHumano6);
        panelJugadorHumano6.setBounds(0, 0, 0, 0);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/oponente.png"))); // NOI18N
        panelJugadorMaquina.add(jLabel6);
        jLabel6.setBounds(150, 0, 250, 72);

        jPanel3.add(panelJugadorMaquina);
        panelJugadorMaquina.setBounds(900, 40, 510, 280);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoPanel.png"))); // NOI18N
        jLabel10.setText("jLabel10");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(0, 0, 1410, 640);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 1410, 642));

        jPanel2.setBackground(new java.awt.Color(133, 209, 235));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(null);

        jLabel25.setFont(new java.awt.Font("Laksaman", 1, 36)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/titulo.png"))); // NOI18N
        jPanel2.add(jLabel25);
        jLabel25.setBounds(0, 0, 1410, 106);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Las seis fichas robadas están cada una dentro de un panel, por tal razón cada panel tiene un evento
    //Cada evento permite saber que ficha es la que se eligió
    
    private void panelFicha1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha1MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(0, 0);
            Icon f2 = fichaUtilizar(0, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[0][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[0][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 0;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha1MouseClicked

    private void panelFicha2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha2MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(1, 0);
            Icon f2 = fichaUtilizar(1, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[1][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[1][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 1;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha2MouseClicked

    private void panelFicha3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha3MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(2, 0);
            Icon f2 = fichaUtilizar(2, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[2][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[2][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 2;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha3MouseClicked

    private void panelFicha4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha4MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(3, 0);
            Icon f2 = fichaUtilizar(3, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[3][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[3][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 3;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha4MouseClicked

    private void panelFicha5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha5MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(4, 0);
            Icon f2 = fichaUtilizar(4, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[4][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[4][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 4;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha5MouseClicked

    private void panelFicha6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFicha6MouseClicked
        // TODO add your handling code here:
        if (robar) {
            limpiarMov();
            Icon f1 = fichaUtilizar(5, 0);
            Icon f2 = fichaUtilizar(5, 1);
            matrizMover[1][1].setIcon(f1);
            matrizMover[2][0].setIcon(f2);
            int numero = matrizAuxFichas[5][1];
            matrizMovAux[2][0] = numero;
            auxiliar[2][0] = numero;
            fichaCentroMovimiento = matrizAuxFichas[5][0];
            auxiliar[1][1] = fichaCentroMovimiento;
            numLabel = 5;
            botonDerecho.setVisible(true);
            botonIzquierdo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.", 
                                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }
    }//GEN-LAST:event_panelFicha6MouseClicked

    public void Hilo(){
 
        
    };
        
                
    
    private void labelTableroMouseClicked(java.awt.event.MouseEvent evt){
     //   if (jugadorHumano) {
            if(robar){
            fichaRotacion();
            if (seleccion) {
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (matrizJuego[i][j] == evt.getComponent()) {
                            if (matrizAux[i][j] == 0) {
                                if ((i == 0 && j == 1) || (i == 1 && j == 0) || (i == 1 && j == 1) || (i == 0 && j == 4) || (i == 1 && j == 5) || (i == 1 && j == 6)
                                        || (i == 4 && j == 0) || (i == 5 && j == 1) || (i == 6 && j == 0) || (i == 4 && j == 9) || (i == 5 && j == 9) || (i == 6 && j == 9)
                                        || (i == 9 && j == 0) || (i == 9 && j == 1) || (i == 10 && j == 1) || (i == 9 && j == 6) || (i == 9 && j == 5) || (i == 10 && j == 4)) {
                                    turno1Humano = true;
                                }
                                if (turno1Humano) {
                                    //matrizAux[i][j] = fichaCentroMovimiento;
                                    pintarFicha(i, j, fichaDos, fichaRotada);
                                    
                                    if (valido) {
                                        pintarFicha(i, j, fichaCentroMovimiento, 0);
                                        puntaje();
                                        limpiarSeleccion();
                                        seleccion = false;
                                        botonRobar.setEnabled(true);
                                        jugadorHumano = false;
                                        botonDerecho.setVisible(false);
                                        botonIzquierdo.setVisible(false);    
                                        robar = false;
                                        construir();
                                        SwingWorker<Void, Void> Worker = new SwingWorker<Void, Void>() {
                                            @Override
                                            protected Void doInBackground() throws Exception {

                                                Thread.sleep(2000);

                                                ArrayList<Integer> fich = new ArrayList<>();
                                                ArrayList<Integer> puntaj = new ArrayList<>();
                                                int conta = 2;
                                                for (int h = 0; h < 6; h++) {
                                                    fich.add(conta);
                                                    conta++;
                                                }
                                                puntaj.add(puntajeFichaRojaMaquina);
                                                puntaj.add(puntajeFichaAzulMaquina);
                                                puntaj.add(puntajeFichaVerdeMaquina);
                                                puntaj.add(puntajeFichaAmarillaMaquina);
                                                puntaj.add(puntajeFichaNaranjaMaquina);
                                                puntaj.add(puntajeFichaMoradaMaquina);
                                                colocarFichaMaquina(menorPuntaje(puntaj, fich));
                                                while (!fichaPeor) {
                                                    fich.remove(posicionMenor);
                                                    puntaj.remove(posicionMenor);
                                                    colocarFichaMaquina(menorPuntaje(puntaj, fich));
                                                }
                                                return null;
                                            }

                                        };
                                        Worker.execute();                                 
                                              
                                                                               
                                        for (int h = 0; h < listaJugadas.size(); h++) {
                                            listaJugadas.remove(h);
                                        }
                                    }
                                }
                            } else {
                                if (jugadorHumano) {
                                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                                        "Mensaje de Advertencia", //Título
                                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                                }
                            }
                            i = 11;
                            j = 11;
                        }
                    }
                }
                
            } 
            else {
                JOptionPane.showMessageDialog(null, "Seleccione una ficha.",
                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
            }}
            
            else {
            JOptionPane.showMessageDialog(null, "Debe robar una ficha.",
                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
        }
       validacionFinJuego();

       if(!validacionFinJuego()){
            JOptionPane.showMessageDialog(null, "Fin de la Partida",
                        "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE);
        }
            /*if (!turno1Humano) {
                JOptionPane.showMessageDialog(null, "El movimiento no es válido.", //Mensaje
                        "Mensaje de Advertencia", //Título
                        JOptionPane.WARNING_MESSAGE); //Tipo de mensaje

            }*/
            
       // }
        /*
        validacionMaquina = false;
        if (!jugadorHumano) {
            
            try {
                
                while(!validacionMaquina){
                    Thread.sleep(50);
                maquina();
                }
                jugadorHumano = true;
            } catch (Exception e) {
                    System.out.println("n ");
            }
        }*/
        

    }
    
    public void maquina() {
        jugadorMaq.seleccionFichasRobada(setFichas, fichasRobadas);
        jugadorMaq.ficha();
        int r = jugadorMaq.randomRotada();
        int a = jugadorMaq.fichaDos;
        int b = jugadorMaq.fichaCentro;
        jugadorMaq.ramdomFilaColumna(matrizAux);

        int n = jugadorMaq.fila;

        int m = jugadorMaq.columna;
        
        
        System.out.println("n " + n + " m " + m + " FD: " + a + " " + b + " r " + r);
        if (matrizAux[n][m] == 0) {
            if ((n == 0 && m == 1) || (n == 1 && m == 0) || (n == 1 && m == 1) || (n == 0 && m == 4) || (n == 1 && m == 5) || (n == 1 && m == 6)
                || (n == 4 && m == 0) || (n == 5 && m == 1) || (n == 6 && m == 0) || (n == 4 && m == 9) || (n == 5 && m == 9) || (n == 6 && m == 9)
                || (n == 9 && m == 0) || (n == 9 && m == 1) || (n == 10 && m == 1) || (n == 9 && m == 6) || (n == 9 && m == 5) || (n == 10 && m == 4)) {
            turno1Maquina = true;
        }
            if(turno1Maquina){
            pintarFicha(n, m, a, r);
            if (valido) {
                pintarFicha(n, m, b, 0);
                validacionMaquina = true;
                int ficha = fichaRandom();
                if (!validarFicha(ficha)) {
                    fichasRobadas.add(ficha);
                    jugadorMaq.matrizAuxFichas[jugadorMaq.indiceArreglo][0] = setFichas[ficha][0];
                    jugadorMaq.matrizAuxFichas[jugadorMaq.indiceArreglo][1] = setFichas[ficha][1];
                }
            }
            }
            
        }
        

    }
    
    private void botonDerechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDerechoActionPerformed
        // TODO add your handling code here:
        int x=0;
        int y=0;
        int n=0;
        Icon icono =null;
        for(int i=0; i<matrizMovAux.length;i++){
            for(int j=0; j<matrizMovAux[i].length;j++){
                if(matrizMovAux[i][j]==2||matrizMovAux[i][j]==3||matrizMovAux[i][j]==4||matrizMovAux[i][j]==5||
                    matrizMovAux[i][j]==6||matrizMovAux[i][j]==7){
                    n=matrizMovAux[i][j];
                    matrizMovAux[i][j]=0;
                    auxiliar[i][j] =0;
                    x=i;
                    y=j;
                    //    System.out.print("X: "+ x+" y: "+y);
                    icono = matrizMover[i][j].getIcon();
                    matrizMover[i][j].setIcon(null);
                }
            }
        }
        if(x==2&&y==0){
            y++;
            auxiliar[x][y] = n;
            matrizMovAux[x][y]=n;
            matrizMover[x][y].setIcon(icono);

        }
        else if(x==2&&y==1){
            x--;
            y++;
            auxiliar[x][y] = n;
            matrizMovAux[x][y]=n;
            matrizMover[x][y].setIcon(icono);

        }
        else if(x==1&&y==2){
            x--;
            y--;
            auxiliar[x][y] = n;
            matrizMovAux[x][y]=n;
            matrizMover[x][y].setIcon(icono);
        }
        else if(x==0&&y==1){
            y--;
            auxiliar[x][y] = n;
            matrizMovAux[x][y]=n;
            matrizMover[x][y].setIcon(icono);
        }
        else if(x>=0&&y==0){
            x++;
            auxiliar[x][y] = n;
            matrizMovAux[x][y]=n;
            matrizMover[x][y].setIcon(icono);
        }
        auxiliar[1][1] = fichaCentroMovimiento;
    }//GEN-LAST:event_botonDerechoActionPerformed

    //Eventos para rotar las fichas
    private void botonIzquierdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIzquierdoActionPerformed
        // TODO add your handling code here:
        //int contador;
        int x = 0;
        int y = 0;
        int n = 0;
        Icon icono = null;
        for (int i = 0; i < matrizMovAux.length; i++) {
            for (int j = 0; j < matrizMovAux[i].length; j++) {
                if (matrizMovAux[i][j] == 2 || matrizMovAux[i][j] == 3 || matrizMovAux[i][j] == 4 || matrizMovAux[i][j] == 5
                    || matrizMovAux[i][j] == 6 || matrizMovAux[i][j] == 7) {
                    n = matrizMovAux[i][j];
                    matrizMovAux[i][j] = 0;
                    auxiliar[i][j] = 0;

                    x = i;
                    y = j;
                    //        System.out.print("X: "+ x+" y: "+y);
                    icono = matrizMover[i][j].getIcon();
                    matrizMover[i][j].setIcon(null);
                }
            }
        }
        if (x > 0 && y == 0) {
            x--;
            auxiliar[x][y] = n;
            matrizMovAux[x][y] = n;
            matrizMover[x][y].setIcon(icono);

        } else if (x == 0 && y == 0) {
            y++;
            auxiliar[x][y] = n;
            matrizMovAux[x][y] = n;
            matrizMover[x][y].setIcon(icono);

        } else if (x == 0 && y == 1) {
            x++;
            y++;
            auxiliar[x][y] = n;
            matrizMovAux[x][y] = n;
            matrizMover[x][y].setIcon(icono);
        } else if (x == 1 && y == 2) {
            x++;
            y--;
            auxiliar[x][y] = n;
            matrizMovAux[x][y] = n;
            matrizMover[x][y].setIcon(icono);
        } else if (x == 2 && y == 1) {
            y--;
            auxiliar[x][y] = n;
            matrizMovAux[x][y] = n;
            matrizMover[x][y].setIcon(icono);
        }
        auxiliar[1][1] = fichaCentroMovimiento;
    }//GEN-LAST:event_botonIzquierdoActionPerformed

    private void botonRobarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRobarMouseClicked
        // TODO add your handling code here:
        if (!robar) {
            int ficha = fichaRandom();
            if (!validarFicha(ficha)) {
                fichasRobadas.add(ficha);
            }
            int ficha1;
            int ficha2;
            ficha1 = setFichas[fichasRobadas.get(fichasRobadas.size() - 1)][0];
            ficha2 = setFichas[fichasRobadas.get(fichasRobadas.size() - 1)][1];
            matrizAuxFichas[numLabel][0] = ficha1;
            matrizAuxFichas[numLabel][1] = ficha2;
            colocarFichaRobada(numLabel, 0, ficha1);
            colocarFichaRobada(numLabel, 1, ficha2);

            robar = true;

            botonRobar.setEnabled(false);
        }
    }//GEN-LAST:event_botonRobarMouseClicked

    private void panelMoverFichaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMoverFichaMousePressed

    }//GEN-LAST:event_panelMoverFichaMousePressed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDerecho;
    private javax.swing.JButton botonIzquierdo;
    private javax.swing.JButton botonRobar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelFicha1;
    private javax.swing.JPanel panelFicha2;
    private javax.swing.JPanel panelFicha3;
    private javax.swing.JPanel panelFicha4;
    private javax.swing.JPanel panelFicha5;
    private javax.swing.JPanel panelFicha6;
    private javax.swing.JPanel panelFichasJugar;
    private javax.swing.JPanel panelJugadorHumano;
    private javax.swing.JPanel panelJugadorHumano1;
    private javax.swing.JPanel panelJugadorHumano2;
    private javax.swing.JPanel panelJugadorHumano3;
    private javax.swing.JPanel panelJugadorHumano5;
    private javax.swing.JPanel panelJugadorHumano6;
    private javax.swing.JPanel panelJugadorHumano7;
    private javax.swing.JPanel panelJugadorMaquina;
    private javax.swing.JPanel panelMoverFicha;
    private javax.swing.JPanel panelMovimiento;
    private javax.swing.JPanel panelTablero;
    // End of variables declaration//GEN-END:variables
     private javax.swing.JLabel jLabel1;
  
}
