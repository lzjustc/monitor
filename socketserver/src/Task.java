import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:23
 * @Description :
 */
public class Task implements Runnable{
    private Socket socket;
    AbstractMech abstractMech;

    public Task(Socket socket) {
        this.socket = socket;
    }

    private void add(){
        abstractMech =new AbstractMech();
        abstractMech.setSocket(socket);
        Controller.getInstance().addAbstractMech(abstractMech);
    }

    @Override
    public void run() {
        add();
        try {
            recieve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recieve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String temp;
        String info;
        while ((temp=br.readLine()) != null) {
            info= abstractMech.getId()+"#"+temp;
            System.out.println(info);
            MessageHandler.getInstance().handle(Message.handInfo(info));

        }

    }
}
