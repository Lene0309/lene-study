import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Run_Translation {
    public static void main(String[] args) {
        PageTable[] page = new PageTable[7];
        //7页作业 用数组存储
        page[0] = new PageTable(0, 1, '5', 11, 0);
        page[1] = new PageTable(1, 1, '8', 12, 0);
        page[2] = new PageTable(2, 1, '9', 13, 0);
        page[3] = new PageTable(3, 1, '1', 21, 0);
        page[4] = new PageTable(4, 0, '×', 22, 0);
        page[5] = new PageTable(5, 0, '×', 23, 0);
        page[6] = new PageTable(6, 0, '×', 121, 0);
        MemoryManagement mm = new MemoryManagement();
        //内存初始化
        mm.init(page);
//        //12条指令 数组存储
        List<PTInstruction> job = new ArrayList<>();
        job.add(new PTInstruction( 1,0,70,1));
        job.add(new PTInstruction( 2,1,50,1));
        job.add(new PTInstruction( 3,2,15,1));
        job.add(new PTInstruction( 4,3,21,1));
        job.add(new PTInstruction( 5,0,56,0));
        job.add(new PTInstruction( 6,6,40,1));
        job.add(new PTInstruction( 7,4,53,1));
        job.add(new PTInstruction( 8,5,23,1));
        job.add(new PTInstruction( 9,1,37,1));
        job.add(new PTInstruction(10,2,78,0));
        job.add(new PTInstruction(11,4, 1,1));
        job.add(new PTInstruction(12,6,84,0));
////
////        //随机生成测试数据进行先进先出检验
//        for (int i = 1; i <= 12; i++) {
//            job.add(new PTInstruction(i, new Random().nextInt(7),
//                    new Random().nextInt(128),new Random().nextInt(2)));
//        }
        //输出初始化页表
        for (int p = 0; p <= 6; p++) {
            System.out.println(page[p]);
        }
        //主要执行语句代码
        for (int n = 0; n <= 11; n++) {
            PTInstruction cur=job.get(n);
//            System.out.println("===>" + cur);
            //cur.runIntroduction(mm.list);
            if (cur.getIsInMemory()) {
                cur.runIntroduction(mm.list);//再次调用执行指令计算绝对地址
            }
        }
    }
}
