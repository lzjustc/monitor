import java.net.Socket;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:52
 * @Description :
 */
public class AbstractMech {
    private int id;
    private Socket socket;
    private Condition condition = Condition.ONLINE;
    private long lastHB;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public long getLastHB() {
        return lastHB;
    }

    public void setLastHB(long lastHB) {
        this.lastHB = lastHB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
