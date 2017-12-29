package mechine;

import java.awt.im.spi.InputMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 上午11:17
 * @Description :
 */
public class Machine implements IMachine,Runnable{
    private int cpuLoad;
    private int memoryUseage=128;
    private Thread thread;
    private int id;
    private ClientSocket clientSocket ;
    private CommandRunner commandRunner ;
    private HBHandler hbHandler;

    private class StatusSender implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    Machine.this.sendStatus(Machine.this.getCondition());
                    Thread.sleep(1000);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class CommandRunner implements Runnable {
        @Override
        public void run() {
            while (true){
                try {
                    readMsg();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void readMsg() throws IOException {
            Socket socket = clientSocket.getSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp;
            String msg;
            while ((temp=br.readLine()) != null) {
                msg=temp;
                runCommand(msg);
            }
        }
    }

    private class HBHandler implements Runnable{


        @Override
        public void run() {
            while (true) {
                try {
                    Machine.this.sendHB();
                    Thread.sleep(2000);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Machine() throws IOException {
        clientSocket = new ClientSocket();
        commandRunner = new CommandRunner();
        hbHandler = new HBHandler();
        new Thread( commandRunner).start();
        new Thread(hbHandler).start();
    }

    public void run(){
        while(true) {
            if (cpuLoad <= 95) {
                cpuLoad = cpuLoad + (int) (Math.random() * 10 - 4);
                if (cpuLoad < 0) {
                    cpuLoad = 0;
                }
            }
            if (memoryUseage <= 2040) {
                memoryUseage = memoryUseage + (int) (Math.random() * 200 - 80);
                if (memoryUseage < 128) {
                    memoryUseage = 128;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    private void sendHB() throws IOException {
        this.clientSocket.heartbeat();
    }
    private void sendStatus(String status) throws IOException{
        this.clientSocket.sendStatus(status);
    }
}
