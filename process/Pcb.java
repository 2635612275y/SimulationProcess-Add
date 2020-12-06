package systemoperator.testone.process;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author xfb
 * @create 2020--11--21  8:27
 * @date 2020/11/21 - 8:27
 */
public class Pcb {
    private String name;//进程名
    private int ID;//进程ID
    private int number;//优先级数
    private int arriveTime;//到达时间
    private int waitTime;//等待时间
    private int runTime;//需要运行时间
    private int usedCPUTime;//已用时间
    private int repose;//响应比
    private boolean flag;
    private String state;//进程状态
    private String cause;//阻塞原因
    private TextArea textArea;
//    private Zone zone = new Zone();
    //内存大小
    private int size;
    //最小剩余分区大小
    private static final int MIN_SIZE = 2;
    //内存分区
    public static LinkedList<Pcb.Zone> zones = new LinkedList<>();
    //上次分配的空闲区位置
    private int pointer;
    public Pcb() {
    }

    public Pcb(String name, int ID, int number, int arriveTime,
               int runTime, int usedCPUTime, String state, String cause) {
        this.name = name;
        this.ID = ID;
        this.number = number;
        this.arriveTime = arriveTime;
        this.runTime = runTime;
        this.usedCPUTime = usedCPUTime;
        this.state = state;
        this.cause = cause;
    }
    //默认内存大小512K
    public Pcb(TextArea textArea){
        this.size = 64;
        this.pointer = 0;
        this.textArea = textArea;
        this.zones = new LinkedList<>();
        zones.add(new Zone(0, size));
    }
    //内存分配
    // 分区节点类
    class Zone{
        // 分区大小
        private int size;
        //分区始址
        private int head;
        //空闲状态
        private boolean isFree;

        public Zone() {
        }

        public Zone(int head, int size) {
            this.head = head;
            this.size = size;
            this.isFree = true;
        }
        public int getHead() {
            return head;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getRuntime() {
        return runTime;
    }

    public void setRuntime(int runTime) {
        this.runTime = runTime;
    }

    public int getUsedCPUTime() {
        return usedCPUTime;
    }

    public void setUsedCPUTime(int usedCPUTime) {
        this.usedCPUTime = usedCPUTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getRepose() {
        return repose;
    }

    public void setRepose(int repose) {
        this.repose = repose;
    }

    public void run(){
//        System.out.println(this.name + "正在运行...");
        try {
            Thread.sleep(this.runTime*2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String toString(int count) {
        return name + "\t " + ID + "\t     " + number + "\t\t " + arriveTime + "\t\t" +
                waitTime + "\t\t   " + runTime + "\t\t        " + usedCPUTime + "\t\t       " + repose + "\t\t" +
                state + "\t     " + cause + "\t\t\t " + "7" + "\t\t      " + zones.get(count).head;
    }



    public boolean allocation(int sum, TextField textField){
            System.out.println("1.FirstFit 2.NextFit 3.BestFit 4.WorstFit");
            System.out.print("请选择分配算法:");
            Scanner in = new Scanner(System.in);
            int xuanze = in.nextInt();
            switch (xuanze) {
                case 1:
                    for(int i = 0; i <  sum - Integer.parseInt(textField.getText());i++)
                    fristFit(7);
                    return true;
                case 2:
                    for(int i = 0; i <  sum - Integer.parseInt(textField.getText());i++)
                    nextFit(7);
                    return true;
                case 3:
                    for(int i = 0; i <  sum - Integer.parseInt(textField.getText());i++)
                    bestFit(7);
                    return true;
                case 4:
                    for(int i = 0; i <  sum - Integer.parseInt(textField.getText());i++)
                    worstFit(7);
                    return true;
                default:
                    System.out.println("请重新选择!");
                    return false;
            }
    }
    //添加进程时，进程的所需内存大小未进行判定，需优化
    public boolean allocation(int sum){
        System.out.println("1.FirstFit 2.NextFit 3.BestFit 4.WorstFit");
        System.out.print("请选择分配算法:");
        Scanner in = new Scanner(System.in);
        int xuanze = in.nextInt();
        switch (xuanze) {
            case 1:
                for(int i = 0; i <  sum;i++)
                    fristFit(7);
                return true;
            case 2:
                for(int i = 0; i <  sum;i++)
                    nextFit(7);
                return true;
            case 3:
                for(int i = 0; i <  sum;i++)
                    bestFit(7);
                return true;
            case 4:
                for(int i = 0; i <  sum;i++)
                    worstFit(7);
                return true;
            default:
                System.out.println("请重新选择!");
                return false;
        }
    }

    // FF
    private void fristFit(int size){
        //遍历分区链表
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            //找到可用分区（空闲且大小足够）
            if (tmp.isFree && (tmp.size > size)){
                doAllocation(size, pointer, tmp);
                return;
            }
        }
        //遍历结束后未找到可用分区, 则内存分配失败
        System.out.println("无可用内存空间!");
    }

    //NF
    private void nextFit(int size){
        Zone tmp = zones.get(pointer);
        if (tmp.isFree && (tmp.size > size)){
            doAllocation(size, pointer, tmp);
            return;
        }
        int len = zones.size();
        int i = (pointer + 1) % len;
        for (; i != pointer; i = (i+1) % len){
            tmp = zones.get(i);
            if (tmp.isFree && (tmp.size > size)){
                doAllocation(size, i, tmp);
                return;
            }
        }
        //全遍历后如果未分配则失败
        System.out.println("无可用内存空间!");
    }

    //BF
    private void bestFit(int size){
        int flag = -1;
        int min = this.size;
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            if (tmp.isFree && (tmp.size > size)){
                if (min > tmp.size - size){
                    min = tmp.size - size;
                    flag = pointer;
                }
            }
        }
        if (flag == -1){
            System.out.println("无可用内存空间!");
        }else {
            doAllocation(size, flag, zones.get(flag));
        }
    }

    //WF
    private void worstFit(int size){
        int flag = -1;
        int max = 0;
        for (pointer = 0; pointer < zones.size(); pointer++){
            Zone tmp = zones.get(pointer);
            if (tmp.isFree && (tmp.size > size)){
                if (max < tmp.size - size){
                    max = tmp.size - size;
                    flag = pointer;
                }
            }
        }
        if (flag == -1){
            System.out.println("无可用内存空间!");
        }else {
            doAllocation(size, flag, zones.get(flag));
        }
    }

    //开始分配
    private void doAllocation(int size, int location, Zone tmp) {
        //要是剩的比最小分区MIN_SIZE小，则把剩下那点给前一个进程
        if (tmp.size - size <= MIN_SIZE){
            tmp.isFree = false;
        } else {
            Zone split = new Zone(tmp.head + size, tmp.size - size);
            zones.add(location + 1, split);
            tmp.size = size;
            tmp.isFree = false;
        }
        System.out.println("成功分配 " + size + "KB 内存!");
    }


    //内存回收

    public void collection(int id){

        if (id >= zones.size()){
            System.out.println(zones.size());
            System.out.println("无此分区编号!");
            return;
        }
        Zone tmp = zones.get(id);
        int size = tmp.size;
        if (tmp.isFree) {
            System.out.println("指定分区未被分配, 无需回收");
            return;
        }
        //如果回收的分区后一个是空闲就和后一个合并
        if (id < zones.size() - 1 && zones.get(id + 1).isFree){
            Zone next = zones.get(id + 1);
            tmp.size += next.size;
            zones.remove(next);
        }
        //回收的分区要是前一个是空闲就和前分区合并
        if (id > 0 && zones.get(id - 1).isFree){
            Zone previous = zones.get(id - 1);
            previous.size += tmp.size;
            zones.remove(id);
            id--;
        }
        zones.get(id).isFree = true;
        System.out.println("内存回收成功!, 本次回收了 " + size + "KB 空间!");
    }

    //展示分区状况
    public void showZones(){
        this.textArea.append("\t\t\t\t分区编号\t\t\t分区始址\t\t\t分区大小\t\t\t空闲状态\n");
        this.textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < zones.size(); i++){
            Zone tmp = zones.get(i);
            this.textArea.append("\t\t\t\t     " + i + "\t\t\t\t      " + tmp.head + "\t\t\t\t    " +
                    tmp.size + "\t\t\t\t    " + tmp.isFree + "\n");
        }
        this.textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
    }
//    public void showZones(TextField textArea){
//        for (int i = 0; i < zones.size(); i++){
//            Zone tmp = zones.get(i);
//            this.textArea.append("\t\t\t\t     " + i + "\t\t\t\t      " + tmp.head + "\t\t\t\t    " +
//                    tmp.size + "\t\t\t\t    " + tmp.isFree + "\n");
//        }
//        this.textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
//    }
}
