import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pila<String> pilaPrincipal = new Pila<>();
        Pila<String> pilaSecundaria = new Pila<>();

        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Escribir texto");
            System.out.println("2. Deshacer");
            System.out.println("3. Rehacer");
            System.out.println("4. Mostrar texto actual");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            String entrada = sc.nextLine(); // leer siempre como String
            try {
                opcion = Integer.parseInt(entrada); // intentar convertir a número
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta, intenta nuevamente");
                continue; // volver al menú
            }

            switch (opcion) {
                case 1:
                    System.out.print("Escribe el texto: ");
                    String texto = sc.nextLine();
                    pilaPrincipal.push(texto);
                    pilaSecundaria = new Pila<>(); // limpiar pila secundaria
                    System.out.println("Texto agregado.");
                    break;

                case 2:
                    String deshecho = pilaPrincipal.pop();
                    if (deshecho != null) {
                        pilaSecundaria.push(deshecho);
                        System.out.println("Se deshizo: " + deshecho);
                    } else {
                        System.out.println("No hay nada que deshacer.");
                    }
                    break;

                case 3:
                    String rehecho = pilaSecundaria.pop();
                    if (rehecho != null) {
                        pilaPrincipal.push(rehecho);
                        System.out.println("Se rehizo: " + rehecho);
                    } else {
                        System.out.println("No hay nada que rehacer.");
                    }
                    break;

                case 4:
                    String actual = pilaPrincipal.peek();
                    if (actual != null) {
                        System.out.println("Texto actual en la cima: " + actual);
                    } else {
                        System.out.println("No hay texto en la pila.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción incorrecta, intenta nuevamente");
            }
        } while (opcion != 5);

        sc.close();
    }
}

// Clase Pila genérica
class Pila<T> {
    private Nodo<T> cima;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T pop() {
        if (isEmpty()) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public T peek() {
        if (isEmpty()) return null;
        return cima.dato;
    }

    public boolean isEmpty() {
        return cima == null;
    }
}
