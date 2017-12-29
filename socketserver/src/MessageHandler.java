/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午3:49
 * @Description :
 */
public class MessageHandler {
    private static MessageHandler instance = new MessageHandler();

    public static MessageHandler getInstance() {
        return instance;
    }

    private MessageHandler(){}

    public void handle(Message message){
        if(message.getContent().equals("HB")){
            handleHB(message);
        }
    }

    private void handleHB(Message message){
        int id = message.getId();
        long time = message.getTime();
        AbstractMech abstractMech = Controller.getInstance().getAbstractMeches().get(id);
        abstractMech.setLastHB(time);
        abstractMech.setCondition(Condition.ONLINE);
    }
}
