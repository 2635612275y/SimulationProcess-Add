package systemoperator.testone.thread;

import systemoperator.testone.process.Pcb;
import systemoperator.testone.process.ReadyQueue;
import systemoperator.testone.display.FCFSDisplay;

import java.awt.*;
import java.io.IOException;

import static java.lang.StrictMath.random;

/**
 * @description:
 * @author: xfb
 * @time: 2020/11/18 22:25
 */
public class AddProcessThread implements Runnable{
    private TextArea textArea;
    private TextField text1;
    private int sum;
    public AddProcessThread(TextArea textArea, TextField text1, int sum){
        this.textArea = textArea;
        this.text1 = text1;
        this.sum = sum;
    }
    @Override
    public void run() {
            int count = Integer.parseInt(text1.getText());
            int number = count;
        for(int i = 0; i < sum - number; i++) {
            if(sum > 9)
                break;
//            System.out.println(i + "," + (sum - number));
            ReadyQueue readqueue = ReadyQueue.getInstance();
            Pcb process = readqueue.peekProcess();
            Pcb pcb = new Pcb();
            if (process == null) {
                pcb.setName("process" + (count + 1));
                pcb.setArriveTime(0);
                pcb.setID(count);
                pcb.setNumber((int) Math.abs((random() * 8 + 1)));
                pcb.setRuntime((int) Math.abs((random() * 4 + 1)));
                pcb.setUsedCPUTime((int) Math.abs((random() * pcb.getRuntime() + 1)));
                pcb.setState("Read");
                readqueue.addProcess(pcb);
                count++;
                text1.setText("" + sum);
            } else {
                pcb.setName("process" + (count + 1));
                pcb.setArriveTime(readqueue.peekProcess().getArriveTime() + pcb.getRuntime());
                pcb.setID(count);
                pcb.setNumber((int) Math.abs((random() * 8 + 1)));
                pcb.setRuntime((int) Math.abs((random() * 4 + 1)));
                pcb.setUsedCPUTime((int) Math.abs((random() * pcb.getRuntime() + 1)));
                pcb.setState("Read");
                readqueue.addProcess(pcb);
                count++;
                text1.setText("" + sum);
            }
        }
        try {
            FCFSDisplay.firstArrive(this.textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}