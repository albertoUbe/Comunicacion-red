package es.red.com.serv.client.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import es.red.com.serv.client.compartido.Conexion;
import es.red.com.serv.client.compartido.Constantes;

public class Servidor extends Conexion implements Constantes {
    public static void main(String[] args) throws IOException, URISyntaxException {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.printf(FORMAT_ESCUCHA, SERVIDOR_ESCUCHANDO, PUERTO);

            try (Socket cliente = servidor.accept()) {
                enviar(cliente, MSG_SERVIDOR);
                
                String respuesta = recibir(cliente);
                System.out.printf(FORMAT_RESPUESTA, SALTO_LINEA, respuesta);

                enviar(cliente, MSG_IMAGEN_SERVIDOR);

                URI uri = new URI(LINK_IMG);
                try (InputStream input = uri.toURL().openStream(); 
                OutputStream output = cliente.getOutputStream()) {
                    byte[] buffer = new byte[BYTES];
                    int bytes;

                    while ((bytes = input.read(buffer)) != -1) {
                        output.write(buffer, INICIO_ESCRITURA, bytes);
                    }
                    output.flush();                  
                }
            }
        }
    }
}
