package mechine;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:40
 * @Description :
 */
public class ClientSocket {
    private String host = "127.0.0.1";
    private int port = 5555;
    private Socket socket ;
    private Writer writer;

    public ClientSocket() throws IOException {
        socket = new Socket(host,port);
        writer = new OutputStreamWriter(socket.getOutputStream());
    }

    public void heartbeat() throws IOException {
        writer.write("HB-"+ new Date().getTime()+"\n");
        System.out.println("send HB");
        writer.flush();
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendStatus(String status)throws IOException{
        writer.write("st"+status+"-"+ new Date().getTime()+"\n");
        System.out.println("send HB");
        writer.flush();
    }
}
