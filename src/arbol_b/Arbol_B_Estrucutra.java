/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_b;

/**
 *
 * @author Victor
 */
class Arbol_B_Estrucutra {
    
    int n_claves; //numero de claves que va almacenar nuestro nodo
    int key_claves[]; //arreglo para almacenar las claves en el nodo
    boolean hoja; //si el nodo es hoja (nodo hoja = true; nodo interno = falso)
    Arbol_B_Estrucutra hijos[]; //Arreglo para almacenar los hijos

    //Constructor
    public Arbol_B_Estrucutra(int Grado_min) { //Recibimos en el objeto el grado minimo y le damos valores a los atributos del objeto
        n_claves = 0; //primero se queda vacio
        hoja = true; //este siempre va corrresponder a tener una hoja
        key_claves = new int[((2 * Grado_min) - 1)]; //Nuestro arreglo de claves realizara
        //2 * los grados minimos y le restara 1 la cualidad del arbol B
        hijos = new Arbol_B_Estrucutra[(2 * Grado_min)]; //determinara cuantos hijos tendra nuestro arbol
        //usando la cualidad del arbol tipo B

    }

    
    //Forma de imprimir el arbol
    public void imprimir() {
        // Imprime el inicio del nodo
        System.out.print("[");
        // Recorre las claves del nodo
        for (int i = 0; i < n_claves; i++) {
            // Imprime cada clave
            if (i < n_claves - 1) {
                System.out.print(key_claves[i] + " | "); // Imprime con separador si no es la última clave
            } else {
                System.out.print(key_claves[i]); // Imprime la última clave sin separador
            }
        }
        // Imprime el final del nodo
        System.out.print("]");
    }

    
    
    //Metodo para buscar y encontrar
    public int find(int k) {
       // Recorre las claves del nodo actual
       for (int i = 0; i < n_claves; i++) {
           // Compara cada clave con la clave buscada
           if (key_claves[i] == k) {
               // Si encuentra la clave, devuelve su posición
               return i;
           }
       }
       // Si la clave no se encuentra, devuelve -1
       return -1;
   }
}
