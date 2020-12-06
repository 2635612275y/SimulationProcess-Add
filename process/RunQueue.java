package systemoperator.testone.process;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xfb
 * @create 2020--11--21  8:49
 * @date 2020/11/21 - 8:49
 */
public class RunQueue implements queue {
    private  Queue<Pcb> runqueue=new LinkedList<Pcb>();
    private  static final RunQueue instance=new RunQueue();

    @Override
    public void addProcess(Pcb pcb) {
        pcb.setState("Run");
        runqueue.offer(pcb);
    }

    @Override
    public void removeProcess() {
        runqueue.poll();
    }

    @Override
    public void showQueue(TextArea textArea) {
        int count = 0;
        textArea.append("  进程名" + "      " + "进程ID" + "      " + "优先级"+ "      " +"到达时间"+
                "      " + "等待时间" + "      " + "需求运行时间" + "      " + "已用CPU时间" + "      " + "响应比" + "      " +
                "进程状态" + "      " + "阻塞原因" +  "      " + "所需主存大小" + "      " + "主存始址" + "\n");
        for (Pcb pcb : runqueue) {
            textArea.append(pcb.toString(count++)+'\n');
        }

    }

    @Override
    public Pcb peekProcess() {
        return runqueue.peek();
    }

    public Queue<Pcb> getRunQueue() {
        return runqueue;
    }

    public static RunQueue getInstance(){
        return instance;
    }

}
