package PSA;

public class PCB {
    private String name;//PCB名称
    private PCB next;//指针
    private int priority;//进程当前优先数
    private int needTime;//进程要求运行时间
    private String status; //进程状态
    private int roundTime;//周转时间
    private int waitingTime;//等待时间
    private int lastTime;//上一次CPU执行时间
    private int cpuTime;//保存cpuTime

    public PCB(String name, int priority, int needTime) {
        this.name = name;
        this.priority = priority;
        this.needTime = needTime;
        this.status = "R";
        this.roundTime = 0;
        this.waitingTime=0;
        this.lastTime=0;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PCB getNext() {
        return next;
    }

    public void setNext(PCB next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
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

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }
}
