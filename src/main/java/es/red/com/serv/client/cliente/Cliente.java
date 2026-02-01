package es.red.com.serv.client.cliente;

import java.io.IOException;
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
        }
    }
}
