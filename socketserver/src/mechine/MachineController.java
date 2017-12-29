package mechine;

import mechine.IMachine;
import mechine.Machine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 上午11:33
 * @Description :
 */
public class MachineController {
    private List<IMachine> machines = new ArrayList<>();

    private void runCommand(int index, String command){
        if(index < machines.size()-1){
            System.out.println("Running Commons Machine"+index);
            machines.get(index).runCommand(command);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void runCommand(String command){
        int index = Integer.parseInt(command.substring(0,4));
        String newCommand = command.substring(4,command.length());
        runCommand(index,command);
    }

    public boolean addMechine() throws IOException {
        Machine machine = new Machine();
        machine.setThread(new Thread(machine));
        machine.getThread().start();
        machines.add(machine);
        return true;
    }

    public boolean deleteMechine(int index){
        if(index >= machines.size()){
            return false;
        }
        IMachine machine = machines.get(index);
        machine.getThread().stop();
        machines.remove(index);
        return true;
    }

    public int getSize(){
        return  machines.size();
    }
    public String getCondition(){
        String condition="";
        for (IMachine machine: machines) {
            condition += "#"+machines.indexOf(machine)+machine.getCondition();
        }
        return condition;
    }
}
