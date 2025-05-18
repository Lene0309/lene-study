package RR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RRScheduling {
    private RR_PCB head;
    private RR_PCB tail;
    private static List<RR_PCB> pcbList;

    public RRScheduling() {
    }

    public void addPCB(RR_PCB p) {
        //进程所需运行时间为0时，该进程执行完毕，退出轮转队列
        if (p.getNeedTime() == 0) {
            p.setStatus("E");
            System.out.println("进程" + p.getName() + "退出轮转队列");
            return;
        }
        if (head == null) {
            head = tail = p; // 初始化头尾为同一节点
        } else {
            tail.setNext(p); // 添加到尾部
        }
        p.setNext(head); // 维持循环性
        tail = p; // 更新尾指针
    }

    public void runRR() {
        int cpuTime = 1;
        while (this.head .getNeedTime()!=0) {
            //在循环队列中选取一个优先级最高的进程
            RR_PCB pcb = this.head;
            this.head = this.head.getNext();//指向下一个进程
            tail.setNext(head);
            System.out.println("CPUTIME:" + cpuTime + "    " + "本次调度执行的进程"+pcb.getName());
            //pcb的 需要运行时间-1，已经运行时间+1
            pcb.setNeedTime(pcb.getNeedTime() - 1);
            pcb.setExecuteTime(pcb.getExecuteTime()+1);
            pcb.setRoundTime(cpuTime);
            pcb.setWaitingTime(cpuTime - pcb.getNeedTime() - pcb.getExecuteTime()-1);
            pcb.setCpuTime(pcb.getCpuTime() + 1);

            pcb.setStatus("W");//改变pcb状态
            addPCB(pcb);//执行后重新加入循环队列
            display();//打印结果
            if (pcb.getStatus().equals("W"))
                pcb.setStatus("R");//如果没有结束将状态重新设为就绪
            cpuTime++;
        }
        System.out.println("所有进程均执行完毕");
        System.out.println("NAME    RoundTime    WaitingTime");//输出运行结果
        for (RR_PCB pcb : pcbList) {
            System.out.printf("%-6s  %-11d  %-11d\n", pcb.getName(), pcb.getRoundTime(), pcb.getWaitingTime());
        }
}

        public void display() {
        System.out.println("NAME      CPUTIME   NEEDTIME   ALREADY   STATE");
        for (RR_PCB pcb : pcbList) {
            String state;
            int cpuTime = pcb.getCpuTime();
            int needTime = pcb.getNeedTime();
            int alreadyTime = pcb.getExecuteTime();

            if (pcb.getStatus().equals("R")) {
                state = "ready";
                cpuTime++; // 更新CPU时间
            } else if (pcb.getStatus().equals("E")) {
                state = "finish";
            } else {
                state = "working";
            }

            System.out.printf("%-10s%-10d%-11d%-10d%-10s\n",
                    pcb.getName(), cpuTime, needTime, alreadyTime, state);

            // 更新CPU时间，注意避免在循环中修改循环变量的属性
            if (pcb.getStatus().equals("R")) {
                pcb.setCpuTime(cpuTime);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 固定进程名称为 P1 至 P5，手动输入每个进程的所需时间
        String[] pNames = {"P1", "P2", "P3", "P4", "P5"};
        int numProcesses = pNames.length;
        RR_PCB[] processes = new RR_PCB[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("请输入进程 " + pNames[i] + " 的所需执行时间:");
            int needTime = scanner.nextInt();//输入进程所需运行时间
            processes[i] = new RR_PCB(pNames[i], needTime);
        }
        // 输出初始进程信息
        System.out.println("初始进程信息:");
        for (RR_PCB pcb : processes) {
            System.out.println("进程: " + pcb.getName() + ", 所需时间: " + pcb.getNeedTime());
        }
        pcbList = new ArrayList<>();
        for (int i = 0; i <numProcesses; i++) {
            pcbList.add(new RR_PCB("P" + i, processes[i].getNeedTime()));
        }
        RRScheduling rollScheduling = new RRScheduling();
        System.out.println("CPUTIME:0 ");
        for (RR_PCB p : pcbList) {//将列表中依次加入队列
            rollScheduling.addPCB(p);
        }
        rollScheduling.display();//初始状态展示
        rollScheduling.runRR();//运行
    }


}
