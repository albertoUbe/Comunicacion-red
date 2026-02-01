package es.red.com.serv.client.compartido;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class Conexion {
    
    public static void enviar(Socket socket, String mensaje) throws IOException {
        OutputStream aux = socket.getOutputStream();
        DataOutputStream output = new DataOutputStream(aux);

        output.writeUTF(mensaje);
    }


    public static String recibir(Socket socket) throws IOException {
        InputStream aux = socket.getInputStream();
        DataInputStream input = new DataInputStream(aux);

        return input.readUTF();
    }
}
