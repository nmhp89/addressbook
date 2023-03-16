import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        AddressBook agenda = new AddressBook();
        agenda.load();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Address Book Menu");
            System.out.println("1. Listar contactos");
            System.out.println("2. Añadir contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar cambios y Salir");
            System.out.print("Ejecutar opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n- Lista de Contactos -");
                    agenda.list();
                    break;
                case 2:
                    System.out.println("\n- Añadir Contacto -");
                    System.out.print("Teléfono: ");
                    String numero = scanner.nextLine();
                    if (numero.isBlank()) {
                        System.out.println("Teléfono no puede ir vacío\n");
                        break;
                    }
                    if (agenda.exist(numero)) {
                        System.out.println("Teléfono ya existente\n");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    if (nombre.isBlank()) {
                        System.out.println("Nombre no puede ir vacío\n");
                        break;
                    }
                    agenda.create(numero, nombre);
                    System.out.println("Creado!\n");
                    break;
                case 3:
                    System.out.println("\n- Eliminar Contacto -");
                    System.out.print("Teléfono: ");
                    numero = scanner.nextLine();
                    if (numero.isBlank()) {
                        System.out.println("Teléfono no puede ir vacío\n");
                        break;
                    }
                    if (!agenda.exist(numero)) {
                        System.out.println("Teléfono no existente\n");
                        break;
                    }
                    agenda.delete(numero);
                    System.out.println("Eliminado!\n");
                    break;
                case 4:
                    agenda.save();
                    running = false;
                    break;
                default:
                    System.out.println("\nOpcion no válida\n");
                    break;
            }
        }
        scanner.close();
    }
}
