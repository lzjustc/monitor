import static java.lang.Thread.sleep;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : ${Date} 上午11:17
 * @Description :
 */
public class Machine implements Runnable{
    private int cpuLoad;
    private int memoryUseage;
    private Thread thread;

    public void run(){
        while(true) {
            if (cpuLoad <= 95) {
                cpuLoad = cpuLoad + (int) (Math.random() * 10 - 5);
                if (cpuLoad < 0) {
                    cpuLoad = 0;
                }
            }
            if (memoryUseage <= 2040) {
                memoryUseage = memoryUseage + (int) (Math.random() * 200 - 100);
                if (memoryUseage < 0) {
                    memoryUseage = 0;
                }
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public void runCommand(String command){
        System.out.println("Running command:  "+ command);
    }

    public int getMemoryUseage() {
        return memoryUseage;
    }

    public int getCpuLoad(){
        return cpuLoad;
    }

    public String getCondition(){
        return "^"+cpuLoad+"^"+memoryUseage;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
