package arbol_b;

/**
 *
 * @author cesar
 */
public class Arbol_B {

    //Realizamos nuestra raiz Root
    Arbol_B_Estrucutra root;
    int Grado_min; //Inicializa el grado minimo

    //Constructor
    public Arbol_B(int Grado_min) { //Envia el grado minimo al objeto
        this.Grado_min = Grado_min; //lo asigno al objeto
        root = new Arbol_B_Estrucutra(Grado_min); //Inicializo nuestro atributo Nodo arbol
    }

    //Buscar las claves y devolver la clave
    public int buscarClaveMayor() {
        // Llama al método getClaveMayor para buscar la clave más grande en el árbol
        int claveMax = getClaveMayor(this.root);

        // Retorna la clave más grande encontrada
        return claveMax;
    }

    private int getClaveMayor(Arbol_B_Estrucutra current) {
        // Si el nodo actual es nulo, no hay clave mayor, retorna 0
        if (current == null) {
            return 0;
        }

        // Mientras el nodo actual no sea una hoja
        while (!current.hoja) {
            // Se accede al hijo más a la derecha
            current = current.hijos[current.n_claves];
        }

        // Retorna la clave más grande del nodo hoja actual
        return claveMayorPorNodo(current);
    }

    private int claveMayorPorNodo(Arbol_B_Estrucutra current) {
        // Devuelve el valor mayor dentro del nodo, que corresponde al valor más a la derecha en el arreglo de claves
        return current.key_claves[current.n_claves - 1];
    }

    //Mostar las claves de nodo minimo
    public void mostrarClavesNodoMinimo() {
        // Busca el nodo mínimo en el árbol
        Arbol_B_Estrucutra temp = buscarNodoMinimo(root);

        // Si no se encontró un nodo mínimo, imprime un mensaje indicando que no hay mínimo
        if (temp == null) {
            System.out.println("Sin mínimo");
        } else {
            // Si se encontró un nodo mínimo, imprime las claves del nodo
            temp.imprimir();
        }
    }

    //Localizar el nodo minimo
    public Arbol_B_Estrucutra buscarNodoMinimo(Arbol_B_Estrucutra nodoActual) {
        // Si el nodo raíz es nulo, el árbol está vacío, devuelve null
        if (root == null) {
            return null;
        }

        // Inicializa un nodo auxiliar con el nodo raíz
        Arbol_B_Estrucutra aux = root;

        // Mientras el nodo auxiliar no sea una hoja
        while (!aux.hoja) {
            // Se accede al primer hijo
            aux = aux.hijos[0];
        }

        // Retorna el nodo mínimo, que corresponde al nodo más a la izquierda
        return aux;
    }

    //Busca el valor ingresado y muestra el contenido del nodo que contiene el valor
    public void buscarNodoPorClave(int num) {
        // Busca un nodo en el árbol que contenga la clave especificada
        Arbol_B_Estrucutra temp = search(root, num);

        // Si no se encuentra el nodo, imprime un mensaje indicando que no se ha encontrado
        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            // Si se encuentra el nodo, imprime el nodo encontrado
            print(temp);
        }
    }

    //Busqueda
    private Arbol_B_Estrucutra search(Arbol_B_Estrucutra actual, int key) {
        int i = 0;//se empieza a buscar siempre en la primera posicion

        //Incrementa el indice mientras el valor de la clave del nodo sea menor
        while (i < actual.n_claves && key > actual.key_claves[i]) {
            i++;
        }

        //Si la clave es igual, entonces retornamos el nodo
        if (i < actual.n_claves && key == actual.key_claves[i]) {
            return actual;
        }

        //Si llegamos hasta aqui, entonces hay que buscar los hijos
        //Se revisa primero si tiene hijos
        if (actual.hoja) {
            return null;
        } else {
            //Si tiene hijos, hace una llamada recursiva
            return search(actual.hijos[i], key);
        }
    }
    
    //Metodo para insertar
    public void insertar(int key_claves) {
        Arbol_B_Estrucutra copiaArbol = root; //realizo una copia de la estructura

        //Realizamos una comprobacion para poder separar e insertar
        //Si copiaArbol con n_claves = 2 * Grado_min - 1 signifca que el nodo esta lleno
        if (copiaArbol.n_claves == ((2 * Grado_min) - 1)) {
            //Dividimos para insertar
            Arbol_B_Estrucutra Second = new Arbol_B_Estrucutra(Grado_min); //creamos un nodo temporal
            root = Second; //reemplazamos el valor de la raiz con nuestro nodo temporal
            Second.hoja = false; //nuestro temporal tambien va ser hoja
            Second.n_claves = 0; //cantidad de elementos 0
            Second.hijos[0] = copiaArbol; //cantidad de hijos de second corresponde a todo lo que existe en la raiz
            split(Second, 0, copiaArbol); // le pasamos Second la posicion y la raiz CopiaArbol
            nonFullInsert(Second, key_claves); //una vez dividio podemos hacer la insercion
        } else {
            nonFullInsert(copiaArbol, key_claves);
        }

    }

    // Caso cuando la raiz se divide
    // dato =          | | | | | |
    //             /
    //      |10|20|30|40|50|
    // i = 0
    // Nodo_Dividido = |10|20|30|40|50| 
    private void nonFullInsert(Arbol_B_Estrucutra dato, int key_claves) {
        //Comprobar si el dato recivido es una hoja
        if (dato.hoja) {
            int i = dato.n_claves; //recorre la cantidad de valores del nodo
            //y busca la posicion 1 donde asignar el valor

            /*ORDENAMIENTO DE BURBUJA EMPLEADO
            Con la variable i le da el valor a los datos con la clave del nodo actual
            luego busca la posicion para realizar la insercion por lo que iremos
            de atras hacia adelante  desplazando los valores que son mayores al valor que queremos insertar
            entonces si la clave es menor se realiza un desplazamiento y se decrementa la variable i hasta que
            i sea mayor o igual a 1
             */
            while (i >= 1 && key_claves < dato.key_claves[i - 1]) {
                dato.key_claves[i] = dato.key_claves[i - 1]; //Recorre los valores mayores a la clave
                i--;

            }  //fin para insertar y comprobar para ordenar

            //Nos queda insertar el valor en la posicion e incrementar
            dato.key_claves[i] = key_claves; //asigna el valor al nodo
            dato.n_claves++;//aumenta la cantidad de elemntos del nodo

            //Que pasa si queremos hacer la insercion y dato no es hoja
        } else {
            int comprobar = 0;
            //Buscamos la posicion del hijo
            while (comprobar < dato.n_claves && key_claves > dato.key_claves[comprobar]) { //si nuestra clava es > a la posicion comprobar
                //hasta encontrar la ultima o un dato que cumpla con la condicion.
                comprobar++;//empieza a incrementar su posicion hasta encontrar su hijo correcto
            }

            //Que pasa si el nodo hijo esta lleno
            if (dato.hijos[comprobar].n_claves == (2 * Grado_min - 1)) { //el hijo en la posicion comprobar de los datos
                //es igual a el maximo permitido significa que ya esta lleno
                split(dato, comprobar, dato.hijos[comprobar]); //realizamos una division

                if (key_claves > dato.key_claves[comprobar]) { //comprobamos que la clave sea mayor a la clave  en la posicion comprobar de los datos
                    comprobar++; //incrementamos la posicion de j 
                }
            }
            nonFullInsert(dato.hijos[comprobar], key_claves); //Hacemos una llamada recursiva para que se
            //repita el proceso pasandole el hijo correcto en la posicion comprobar de dato.
        }
    }

    //Split o dividir cadenas
    //Manera grafica
    //dato =  | |   | |   | |
    //          /
    //|1| |2| |3| |4| |5|
    // i = 0
    // Nodo_Dividido = |1| |2| |3| |4| |5|
    private void split(Arbol_B_Estrucutra dato, int i, Arbol_B_Estrucutra Nodo_Dividido) {
        //Nodo temporal que sera el hijo de i + 1 de dato y le pasamos nuestro grado minimo
        Arbol_B_Estrucutra temp = new Arbol_B_Estrucutra(Grado_min);
        temp.hoja = Nodo_Dividido.hoja; //si Nodo_Dividido (nuestra raiz) es tipo hoja tomar los mismos atributos de temp
        temp.n_claves = (Grado_min - 1);
        //por lo tanto temp tendra solo 2 valores

        //Copiamos los ultimos valores de Nodo_Dividio pero los tomara en el inicio del nodo temp = temp = |4| |5| 
        for (int comprobar = 0; comprobar < Grado_min - 1; comprobar++) {
            temp.key_claves[comprobar] = Nodo_Dividido.key_claves[(comprobar + Grado_min)];
        }

        //Si en caso no es una hoja debemos reasignar los nodos hijos
        if (!Nodo_Dividido.hoja) {
            for (int k = 0; k < Grado_min; k++) {
                temp.hijos[k] = Nodo_Dividido.hijos[(k + Grado_min)];
            }
        }

        //Nuevo tamaño de Nodo_Dividido                                         // dato = | |  | |  | |                      
        Nodo_Dividido.n_claves = (Grado_min - 1);                               //          /
        // |1| |2| | |  | |
        // Con un ciclo For necesito hacer un desplazamiento de los hijos de dato para otro nodo temp si en caso es necesario    
        for (int comprobar = dato.n_claves; comprobar > i; comprobar--) {
            dato.hijos[(comprobar + 1)] = dato.hijos[comprobar];
        }

        //Reasignamos el hijo (i+1) de dato
        dato.hijos[(i + 1)] = temp; //asignar nuestro nodo tem a la posicion i + 1         // dato = | |  | |  | |                      
        Nodo_Dividido.n_claves = (Grado_min - 1);                                         //          /         \
        // |1| |2| | |  | |     |4| |5| | |  | |        

        //Re acomodar las claves de dato ya que anterior mente reacomodamos los hijos
        for (int comprobar = dato.n_claves; comprobar > i; comprobar--) {
            dato.key_claves[(comprobar + 1)] = dato.key_claves[comprobar];
        }

        //Agregar el valor de el dato de intermedio de nuestro split            
        dato.key_claves[i] = Nodo_Dividido.key_claves[(Grado_min - 1)];
        dato.n_claves++;                                                               //dato =               |3| | | | |        
    }                                                                                 //                      /        \
    //             |1|2|||          |4|5|| |

    //Imprime el arbol
    public void showBTree() {
        //Llama al metodo imprimir el cual le pasa el nodo raiz
        print(root);
    }

    //Print en preorder
    private void print(Arbol_B_Estrucutra n_claves) {
        // Imprime las claves del nodo actual
        n_claves.imprimir();

        // Si el nodo actual no es una hoja
        if (!n_claves.hoja) {
            // Recorre los hijos del nodo actual para imprimir recursivamente sus claves
            for (int comprobar = 0; comprobar <= n_claves.n_claves; comprobar++) {
                // Verifica si el hijo actual no es nulo
                if (n_claves.hijos[comprobar] != null) {
                    System.out.println(); // Salto de línea para separar nodos
                    print(n_claves.hijos[comprobar]); // Llama recursivamente al método print con el hijo actual
                }
            }
        }
    }

}
