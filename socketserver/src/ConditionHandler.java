import java.util.Date;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午3:57
 * @Description :
 */
public class ConditionHandler implements Runnable{


    @Override
    public void run() {
        while(true) {
            Controller.getInstance().showConditions();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (AbstractMech abstractMech : Controller.getInstance().getAbstractMeches()) {
                long now = new Date().getTime();
                long lastHB = abstractMech.getLastHB();
                if(lastHB == 0)break;
                if (now - lastHB > 10000) {
                    abstractMech.setCondition(Condition.SUSPAND);
                } else if (now - lastHB > 30000) {
                    abstractMech.setCondition(Condition.OFFLINE);
                }
            }
        }
    }

}
