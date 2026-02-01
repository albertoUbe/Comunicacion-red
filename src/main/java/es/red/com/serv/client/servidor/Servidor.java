package es.red.com.serv.client.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import es.red.com.serv.client.compartido.Conexion;
import es.red.com.serv.client.compartido.Constantes;

public class Servidor extends Conexion implements Constantes {
    public static void main(String[] args) throws IOException {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println(String.format(FORMAT, SERVIDOR_ESCUCHANDO, PUERTO));

            try (Socket cliente = servidor.accept()) {
                enviar(cliente, MSG_SERVIDOR);
                
                String respuesta = recibir(cliente);
                System.out.printf(FORMAT, SALTO_LINEA, respuesta);
            }
        }
    }
}
