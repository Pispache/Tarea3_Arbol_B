package arbol_b;

import java.util.Scanner;

public class Main_Console {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el grado del árbol B: ");
        int grado = scanner.nextInt();
        Arbol_B arbolB = new Arbol_B(grado);
        char seguir = 'S';
        int opcion = 0;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Buscar clave");
            System.out.println("4. Mostrar árbol B");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        while (seguir == 'S' || seguir == 's') {
                            scanner.nextLine(); // Consumir el salto de línea pendiente
                            System.out.print("Ingrese la lista de claves separadas por comas: ");
                            String listaClaves = scanner.nextLine().replaceAll("\\s+", "");
                            String[] clavesArray = listaClaves.split(",");
                            for (String claveStr : clavesArray) {
                                int claveInsertar = Integer.parseInt(claveStr);
                                arbolB.insertar(claveInsertar);
                            }
                            System.out.println("Claves insertadas correctamente.");
                            System.out.println("¿Desea insertar más claves? (S/N)");
                            seguir = scanner.next().charAt(0);
                        }
                        break;

                    case 2:
                        System.out.print("\nIngrese la clave a eliminar: ");
                        int claveEliminar = scanner.nextInt();
                        arbolB.eliminar(claveEliminar);
                        System.out.println("Clave eliminada correctamente.");
                        break;
                    case 3:
                        System.out.print("\nIngrese la clave a buscar: ");
                        int claveBuscar = scanner.nextInt();
                        arbolB.buscarNodoPorClave(claveBuscar);
                        break;
                    case 4:
                        System.out.println("\nÁrbol B:");
                        arbolB.showBTree();
                        break;
                    case 5:
                        System.out.println("\nSaliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Entrada no válida. Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }

        } while (opcion != 5);

        scanner.close();
    }
}