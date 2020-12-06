package systemoperator.testone.thread;

import systemoperator.testone.process.Pcb;
import systemoperator.testone.process.FinishQueue;
import systemoperator.testone.process.RunQueue;
import systemoperator.testone.process.WaitQueue;

import java.awt.*;

/**
 * @description:
 * @author: xfb
 * @time: 2020/11/25 8:30
 */
public class WaitProcessThread implements Runnable{
    private TextArea textArea;

    public WaitProcessThread(TextArea textArea) {
        this.textArea = textArea;
    }
    public void run(){
        RunQueue runQueue = RunQueue.getInstance();
        FinishQueue finishQueue = FinishQueue.getInstance();
        if(runQueue.getRunQueue().size() == 0){
            System.out.println("该运行中没有运行队列，无法阻塞！！！");
        }else{
            WaitQueue waitQueue = WaitQueue.getInstance();
            Pcb pcb = runQueue.peekProcess();
            pcb.setState("Wait");
            waitQueue.addProcess(pcb);
            runQueue.removeProcess();
        }
    }
}
