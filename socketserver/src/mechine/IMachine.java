package mechine;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午2:28
 * @Description :
 */
public interface IMachine {
    void runCommand(String command);
    Thread getThread();
    void setThread(Thread thread);
    String getCondition();
}
