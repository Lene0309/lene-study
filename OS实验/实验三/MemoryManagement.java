import java.util.Arrays;
import java.util.LinkedList;

public class MemoryManagement {
    //链表存储调入主存的页
    public LinkedList<PageTable> list= new LinkedList<>();

    //初始化
    public void init(PageTable[] page) {
        list.addAll(Arrays.asList(page).subList(0, 4));
    }

    //缺页中断处理 先进先出
    public void FIFO(PTInstruction job, PageTable[] page) {
        //去掉链表首元素
        char alter=list.get(0).getBlockNum();//获取块号
        list.get(0).setFlag(0); //标志置为0
        list.get(0).setBlockNo('×'); //将主存块号置为*
        if(list.get(0).modifyBit==1)
            System.out.println("\t\tout "+list.get(0).pageNum);
        list.remove(0);

        //链表尾部插入新页
        int needPage=job.pageNum;//获取所需页的页号
        for(int i=0;i<=6;i++) {
            if(page[i].getPageNum()==needPage) {
                page[i].setBlockNo(alter);//分配块号
                page[i].setFlag(1);//标志置为1
                list.addLast(page[i]);
                System.out.println("\t\tin "+job.pageNum);
                return;
            }
        }
        System.out.println("未找到页号！");
    }
}
