import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:51
 * @Description :
 */
public class Controller {
    private static Controller instance = new Controller();
    private List<AbstractMech> abstractMeches = new ArrayList();

    private Controller(){}

    public static Controller getInstance() {
        return instance;
    }

    public void addAbstractMech(AbstractMech abstractMech){
        abstractMech.setId(abstractMeches.size());
        abstractMeches.add(abstractMech);
    }

    public List<AbstractMech> getAbstractMeches() {
        return abstractMeches;
    }

    public void showConditions() {
        for (AbstractMech abstractMech : abstractMeches) {
            System.out.printf("id=%d  Condition=%s \n",abstractMech.getId(),abstractMech.getCondition());
        }
    }

    public void runCommand(int id, String command) throws IOException {
        /*
        command!para1!para2
        e.g.
        sum!123!456
         */
        AbstractMech abstractMech = abstractMeches.get(id);
        Socket socket = abstractMech.getSocket();
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.flush();

    }
}
