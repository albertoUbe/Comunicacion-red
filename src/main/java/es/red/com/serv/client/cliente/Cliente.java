package es.red.com.serv.client.cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import es.red.com.serv.client.compartido.Conexion;
import es.red.com.serv.client.compartido.Constantes;

public class Cliente extends Conexion implements Constantes {
    public static void main(String[] args) throws UnknownHostException, IOException {
        try (Socket cliente = new Socket(HOST, PUERTO)) {
            
            String recibir = recibir(cliente);
            System.out.println(recibir);

            enviar(cliente, MSG_CLIENTE);

            String recibirImagen = recibir(cliente);
            System.out.println(recibirImagen);

            File carpeta = new File(CARPETA);
            if (!carpeta.exists()) {
                carpeta.mkdirs(); 
            }

            File archivo = new File(CARPETA, NOMBRE_IMAGEN);
            try (InputStream input = cliente.getInputStream();
                FileOutputStream output = new FileOutputStream(archivo)) {
                byte[] buffer = new byte[1024];
                int bytes;

                while ((bytes = input.read(buffer)) != -1) {
                    output.write(buffer, INICIO_ESCRITURA, bytes);
                }
                output.flush();
            }

            
        }
    }
}
