package tcpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public TCPClient() {
    }

    // ansluter till en server på hostname, port
    public byte[] askServer(String hostname, int port, byte[] bytesToServer) throws IOException {
        // return new byte[0];
        Socket socket = new Socket(hostname, port);// vi skpar en socket för att ansluta till servern

        /* Vi skapar en OutputStream för att skicka data */
        OutputStream out = socket.getOutputStream();
        out.write(bytesToServer);// Skicka data till servern
        out.flush(); // säkrställa att allt skickades

        /* Skapar en inputstream för att läsa svaret från servern */
        InputStream in = socket.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();//
        byte[] tempBuff = new byte[1024]; // tillfällig buffert
        int bytesRead;

        while ((bytesRead = in.read(tempBuff)) != -1) {
            buffer.write(tempBuff, 0, bytesRead);
        }

        /* stänger anslutningen och returnerar svaret */
        in.close();
        out.close();
        socket.close();

        return buffer.toByteArray();
    }

    // ansluter till servern som innan, men utan att skicka data till servern
    public byte[] askServer(String hostname, int port) throws IOException {
        return askServer(hostname, port, new byte[0]);

    }

}