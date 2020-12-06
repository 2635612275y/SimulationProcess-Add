package systemoperator.testone.process;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author xfb
 * @create 2020--11--21  8:49
 * @date 2020/11/21 - 8:49
 */
public class WaitQueue implements queue {
    private  java.util.Queue<Pcb> waitQueue=new LinkedList<Pcb>();
    private  static final WaitQueue instance=new WaitQueue();

    @Override
    public void addProcess(Pcb pcb) {
        waitQueue.offer(pcb);
    }

    @Override
    public void removeProcess() {
        waitQueue.poll();
    }

    @Override
    public void showQueue(TextArea textArea) {
        int count = 0;
        textArea.append("  进程名" + "      " + "进程ID" + "      " + "优先级"+ "      " +"到达时间"+
                "      " + "等待时间" + "      " + "需求运行时间" + "      " + "已用CPU时间" + "      " + "响应比" + "      " +
                "进程状态" + "      " + "阻塞原因" +  "      " + "所需主存大小" + "      " + "主存始址" + "\n");
        for (Pcb pcb : waitQueue) {
            textArea.append(pcb.toString(count++)+'\n');
        }
    }

    @Override
    public Pcb peekProcess() {
        return waitQueue.peek();
    }

    public java.util.Queue<Pcb> getWaitQueue() {
        return waitQueue;
    }

    public static WaitQueue getInstance(){
        return instance;
    }

}