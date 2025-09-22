import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pila principal: guarda todo lo que el usuario va escribiendo
        Pila<String> pilaPrincipal = new Pila<>();
        // Pila secundaria: guarda lo que se deshizo para poder rehacerlo
        Pila<String> pilaSecundaria = new Pila<>();

        int opcion = 0; // opción del menú
        do {
            // Mostrar el menú de opciones
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Escribir texto");
            System.out.println("2. Deshacer");
            System.out.println("3. Rehacer");
            System.out.println("4. Mostrar texto actual");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            // Siempre leemos la entrada como texto
            String entrada = sc.nextLine();
            try {
                // Intentamos convertir lo que escribió el usuario a número
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Si el usuario escribe letras u otro carácter, mostramos error
                System.out.println("Opción incorrecta, intenta nuevamente");
                continue; // volvemos al menú
            }

            // Según la opción ingresada, hacemos una acción
            switch (opcion) {
                case 1:
                    // Escribir texto nuevo
                    System.out.print("Escribe el texto: ");
                    String texto = sc.nextLine();

                    // Guardamos el texto en la pila principal
                    pilaPrincipal.push(texto);

                    // Reiniciamos la pila secundaria, porque ya no tiene sentido rehacer lo anterior
                    pilaSecundaria = new Pila<>();
                    System.out.println("Texto agregado.");
                    break;

                case 2:
                    // Deshacer = sacar lo último escrito
                    String deshecho = pilaPrincipal.pop();
                    if (deshecho != null) {
                        // Guardamos lo deshecho en la pila secundaria
                        pilaSecundaria.push(deshecho);
                        System.out.println("Se deshizo: " + deshecho);
                    } else {
                        System.out.println("No hay nada que deshacer.");
                    }
                    break;

                case 3:
                    // Rehacer = recuperar lo último deshecho
                    String rehecho = pilaSecundaria.pop();
                    if (rehecho != null) {
                        // Lo volvemos a poner en la pila principal
                        pilaPrincipal.push(rehecho);
                        System.out.println("Se rehizo: " + rehecho);
                    } else {
                        System.out.println("No hay nada que rehacer.");
                    }
                    break;

                case 4:
                    // Ver el texto actual = lo último que está en la cima de la pila principal
                    String actual = pilaPrincipal.peek();
                    if (actual != null) {
                        System.out.println("Texto actual en la cima: " + actual);
                    } else {
                        System.out.println("No hay texto en la pila.");
                    }
                    break;

                case 5:
                    // Salida del programa
                    System.out.println("Saliendo...");
                    break;

                default:
                    // Si el número no corresponde a una opción válida
                    System.out.println("Opción incorrecta, intenta nuevamente");
            }
        } while (opcion != 5);

        sc.close();
    }
}

// Clase que representa una Pila genérica (sirve para cualquier tipo de dato)
class Pila<T> {
    private Nodo<T> cima; // el último elemento que se agregó (arriba de la pila)

    // Clase interna que representa un nodo de la pila
    private static class Nodo<T> {
        T dato;          // el dato que guardamos
        Nodo<T> siguiente; // referencia al siguiente nodo de la pila

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Agregar un dato a la pila (encima de todo)
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima; // el nuevo apunta a lo que antes estaba arriba
        cima = nuevo;           // ahora la cima es el nuevo dato
    }

    // Sacar el dato de arriba de la pila
    public T pop() {
        if (isEmpty()) return null; // si está vacía, no hay nada que sacar
        T dato = cima.dato;         // guardamos el dato que estaba en la cima
        cima = cima.siguiente;      // la cima pasa a ser el siguiente
        return dato;
    }

    // Ver el dato que está arriba de la pila, sin sacarlo
    public T peek() {
        if (isEmpty()) return null;
        return cima.dato;
    }

    // Comprobar si la pila está vacía
    public boolean isEmpty() {
        return cima == null;
    }
}
