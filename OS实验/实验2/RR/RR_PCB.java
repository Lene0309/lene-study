package RR;

public class RR_PCB {
    private String name;//进程名
    private int needTime;//进程需要的运行时间
    private int executeTime;//进程已运行时间
    private String status;//进程状态
    private RR_PCB next;//指针
    private int cpuTime;
    private int waitingTime;//等待时间
    private int roundTime;//周转时间

    public RR_PCB(String name, int needTime) {
        this.name = name;
        this.needTime = needTime;
        this.executeTime = 0;
        this.status = "R";
    }

    //方便了解对象的当前状态
    public String toString() {
        if (next != null) {
            return "RR_PCB{" +
                    "name='" + name + '\'' +
                    ", next=" + next.getName() +
                    ", needTime=" + needTime +
                    ", executeTime=" + executeTime +
                    ", status='" + status + '\'' +
                    ", cpuTime=" + cpuTime +
                    ", waitingTime=" + waitingTime +
                    '}';
        } else {
            return "RR_PCB{" +
                    "name='" + name + '\'' +
                    ", next=null" +
                    ", needTime=" + needTime +
                    ", executeTime=" + executeTime +
                    ", status='" + status + '\'' +
                    ", cpuTime=" + cpuTime +
                    ", waitingTime=" + waitingTime +
                    '}';

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public int getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(int executeTime) {
        this.executeTime = executeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public RR_PCB getNext() {
        return next;
    }

    public void setNext(RR_PCB next) {
        this.next = next;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
