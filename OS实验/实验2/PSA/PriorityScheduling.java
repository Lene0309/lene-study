package PSA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriorityScheduling {
    private PCB head;
    private static List<PCB> pcbList;

    public static int inputNeedTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("��������̵�����ʱ��: ");
        return scanner.nextInt();
    }

    public static int inputPriority() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("��������̵����ȼ�: ");
        return scanner.nextInt();
    }

    // �������ȼ���ֵ�� PCB ������в���
    public void addPCB(PCB node) {
        if (node.getNeedTime() == 0) {
            node.setStatus("E");// E ��ʾ finish ״̬
            System.out.println(node.getName() + " �˳���������");
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

    // ʵ�����ȼ����ȵĹ���
    public void runPS() {
        int cpuTime = 1;
        while (this.head != null) {
            // ִ��δ�����Ľ��̣�����ѡ���ȼ���ߵ�
            PCB pcb = this.head;
            this.head = this.head.getNext();// ָ����һ������
            System.out.println("CPUʱ��:" + cpuTime + "    " + "��Ҫ����ִ�еĽ���: " + pcb.getName());
            pcb.setPriority(pcb.getPriority() - 1);// PCB �����ȼ����� 1
            pcb.setNeedTime(pcb.getNeedTime() - 1); // PCB ������ʱ��� 1
            pcb.setRoundTime(cpuTime);// ��תʱ����Ϊ��ǰ CPU �Ѿ���ת��ʱ��
            pcb.setWaitingTime(pcb.getWaitingTime() + cpuTime - pcb.getLastTime() - 1);
            pcb.setLastTime(cpuTime);
            pcb.setCpuTime(pcb.getCpuTime() + 1);
            // W ��ʾ working ״̬
            pcb.setStatus("W");// �� PCB ״̬ת��Ϊ working
            addPCB(pcb);// ִ�к�δ������Ľ������²��뵽����
            display();// ��ӡ���
            if (pcb.getStatus().equals("W"))
                // R ��ʾ ready ״̬
                pcb.setStatus("R");// ���û�н�����״̬������Ϊ ready
            cpuTime++;
        }
        System.out.println("���н���ִ�����!");
        System.out.println("������    ��תʱ��    �ȴ�ʱ��");// �������ִ�н��
        for (PCB pcb : pcbList) {
            System.out.printf("%-8s %-12d %-12d\n", pcb.getName(), pcb.getRoundTime(), pcb.getWaitingTime());
        }

    }

    // ��ʾÿ��ִ�н���ĺ���
    public void display() {
        System.out.println("������     CPUʱ��   ����ʱ��   ���ȼ�   ״̬");
        for (PCB pcb : pcbList) {
            String state;
            if (pcb.getStatus().equals("R")) {
                if (pcb.getCpuTime() != 0)
                    pcb.setCpuTime(pcb.getCpuTime() + 1);
                state = "����";
            } else if (pcb.getStatus().equals("E")) {
                state = "���";
            } else {
                state = "������";
            }
            // ��ʽ����������ֺ��ʵļ��
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
        System.out.println("����   ������    ����ʱ��   ���ȼ� ");
        for (PCB pcb : pcbList) {// ��ʼ�����
            System.out.printf("        %-7s %-10d %-9d\n", pcb.getName(), pcb.getNeedTime(), pcb.getPriority());
        }
        System.out.println("CPUʱ��:0");
        processScheduling.display();// ��ʼ״̬��ʾ
        for (PCB p : pcbList) {// ���б���Ԫ�����β������
            processScheduling.addPCB(p);
        }
        processScheduling.runPS();// ִ��
    }
}