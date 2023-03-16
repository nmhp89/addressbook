import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private Map<String, String> contactos;
    private String rutaArchivo;

    public AddressBook() {
        this.contactos = new HashMap<>();
        this.rutaArchivo = "agenda.txt";
    }

    public void load() throws IOException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.rutaArchivo))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] partes = row.split(":");
                this.contactos.put(partes[0], partes[1]);
            }
        }
        System.out.println("Contactos cargados.\n");
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.rutaArchivo))) {
            for (Map.Entry<String, String> entry : this.contactos.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
        System.out.println("Cambios guardados.");
    }

    public void list() {
        if (this.contactos.isEmpty()) {
            System.out.println("Lista de contactos vacía");
        }
        for (Map.Entry<String, String> entry : this.contactos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.print("\n");
    }

    public void create(String numero, String nombre) {
        this.contactos.put(numero, nombre);
    }

    public void delete(String numero) {
        this.contactos.remove(numero);
    }

    public boolean exist(String numero) {
        return this.contactos.containsKey(numero);
    }
}
