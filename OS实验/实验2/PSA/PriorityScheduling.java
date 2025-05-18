package PSA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriorityScheduling {
    private PCB head;
    private static List<PCB> pcbList;

    public static int inputNeedTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入进程的所需时间: ");
        return scanner.nextInt();
    }

    public static int inputPriority() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入进程的优先级: ");
        return scanner.nextInt();
    }

    // 根据优先级数值对 PCB 对象进行插入
    public void addPCB(PCB node) {
        if (node.getNeedTime() == 0) {
            node.setStatus("E");// E 表示 finish 状态
            System.out.println(node.getName() + " 退出就绪队列");
            return;
        }
        if ((this.head == null) || (node.getPriority() > this.head.getPriority())) {
            node.setNext(this.head);
            this.head = node;
            return;
        }
        PCB cur = this.head.getNext();
        PCB parent = this.head;
        while (cur != null) {
            if (node.getPriority() > cur.getPriority()) {
                node.setNext(parent.getNext());
                parent.setNext(node);
                return;
            }
            parent = cur;
            cur = cur.getNext();
        }
        parent.setNext(node);
        node.setNext(null);
    }

    // 实现优先级调度的功能
    public void runPS() {
        int cpuTime = 1;
        while (this.head != null) {
            // 执行未结束的进程，首先选优先级最高的
            PCB pcb = this.head;
            this.head = this.head.getNext();// 指向下一个进程
            System.out.println("CPU时间:" + cpuTime + "    " + "需要优先执行的进程: " + pcb.getName());
            pcb.setPriority(pcb.getPriority() - 1);// PCB 的优先级数减 1
            pcb.setNeedTime(pcb.getNeedTime() - 1); // PCB 的运行时间减 1
            pcb.setRoundTime(cpuTime);// 周转时间设为当前 CPU 已经周转的时间
            pcb.setWaitingTime(pcb.getWaitingTime() + cpuTime - pcb.getLastTime() - 1);
            pcb.setLastTime(cpuTime);
            pcb.setCpuTime(pcb.getCpuTime() + 1);
            // W 表示 working 状态
            pcb.setStatus("W");// 将 PCB 状态转变为 working
            addPCB(pcb);// 执行后将未工作完的进程重新插入到队列
            display();// 打印结果
            if (pcb.getStatus().equals("W"))
                // R 表示 ready 状态
                pcb.setStatus("R");// 如果没有结束则将状态重新设为 ready
            cpuTime++;
        }
        System.out.println("所有进程执行完毕!");
        System.out.println("进程名    周转时间    等待时间");// 输出最终执行结果
        for (PCB pcb : pcbList) {
            System.out.printf("%-8s %-12d %-12d\n", pcb.getName(), pcb.getRoundTime(), pcb.getWaitingTime());
        }

    }

    // 显示每次执行结果的函数
    public void display() {
        System.out.println("进程名     CPU时间   所需时间   优先级   状态");
        for (PCB pcb : pcbList) {
            String state;
            if (pcb.getStatus().equals("R")) {
                if (pcb.getCpuTime() != 0)
                    pcb.setCpuTime(pcb.getCpuTime() + 1);
                state = "就绪";
            } else if (pcb.getStatus().equals("E")) {
                state = "完成";
            } else {
                state = "运行中";
            }
            // 格式化输出，保持合适的间距
            System.out.printf("%-8s %-9d %-10d %-10d %-8s\n",
                    pcb.getName(), pcb.getCpuTime(),
                    pcb.getNeedTime(), pcb.getPriority(), state);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        pcbList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            int needTime = inputNeedTime();
            int priority = inputPriority();
            pcbList.add(new PCB("P" + i, priority, needTime));
        }

        PriorityScheduling processScheduling = new PriorityScheduling();
        System.out.println("输入   进程名    所需时间   优先级 ");
        for (PCB pcb : pcbList) {// 初始化输出
            System.out.printf("        %-7s %-10d %-9d\n", pcb.getName(), pcb.getNeedTime(), pcb.getPriority());
        }
        System.out.println("CPU时间:0");
        processScheduling.display();// 初始状态显示
        for (PCB p : pcbList) {// 将列表中元素依次插入队列
            processScheduling.addPCB(p);
        }
        processScheduling.runPS();// 执行
    }
}