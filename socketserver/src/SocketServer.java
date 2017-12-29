import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:15
 * @Description :
 */
public class SocketServer {
    public void serve() throws IOException
    {
        int port = 5555;
        ServerSocket serverSocket = new ServerSocket(port);
        new Thread(new ConditionHandler()).start();
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new Task(socket)).start();
        }
    }

    public static void main(String[] args) throws IOException {
        SocketServer socketServer = new SocketServer();
        socketServer.serve();

    }
}
