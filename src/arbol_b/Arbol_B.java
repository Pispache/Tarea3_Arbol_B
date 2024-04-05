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

}
