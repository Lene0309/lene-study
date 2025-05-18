import java.util.List;

public class PTInstruction {
    public int instructionNum;//指令序号
    public int pageNum;//页号
    public int unitNum;//单元号
    public boolean isInMemory = false;//布尔型，所需页是否在主存中
    public int isModify;//页面是否被修改，1修改，0不修改

    //结构体
    public PTInstruction(int introductionNum, int pageNum, int unitNum, int isModify) {
        this.instructionNum = introductionNum;
        this.pageNum = pageNum;
        this.unitNum = unitNum;
        this.isModify = isModify;
    }

    public boolean getIsInMemory() {
        return !isInMemory;
    }

    @Override
    public String toString() {
        return "指令"+instructionNum+"\t页号："+pageNum+"\t单元号："+unitNum+"\t是否为修改指令："+isModify;
    }

    //页置换算法逻辑
    public void runIntroduction(List<PageTable> pT){
        //用于遍历传入的页面列表pT
        for (PageTable pageTable : pT) {
            //now_pNum当前页面的页号
            int now_pNum = pageTable.getPageNum();
            if (now_pNum == pageNum) {
                //charBlockNum为字符形式的块号
                char charBlockNum = pageTable.getBlockNum();
                //将字符形式的块号转换为数字表示
                int bNum = Character.getNumericValue(charBlockNum);
                //根据所给公式得到绝对地址
                int absoluteAddress = bNum * 128 + unitNum;
                System.out.println("\t第" + instructionNum + "条指令执行——绝对地址:" + absoluteAddress);
                if (isModify == 1) {
                    pageTable.setModifyBit(1);//设置修改位为1
                }
                isInMemory = true;
                return;
            }
            System.out.println("\t第" + instructionNum + "条指令执行——页错误*" + pageNum);
            return;

        }
    }

    //地址转换方法，根据给定的页号找到相应的页表项，并根据页表项的标志位进行地址转换
    public void addrTranslation(PageTable[] page){
        PageTable p = null;
        //遍历给定的页表数组，查找与给定页号相匹配的页表项
        for(PageTable pageTable : page){
            if(pageTable.getPageNum()==pageNum){
                p = pageTable;
                break;
            }
        }
        //一旦找到匹配的页表项，代码检查该页表项的标志位（flag）。
        // 如果标志位为1，则执行地址转换操作；否则，输出错误消息。
        if(p.flag==1){
            StringBuilder str1 = new StringBuilder(Integer.toBinaryString(pageNum));
            while(str1.length()<4){
                str1.insert(0, "0");
            }
            StringBuilder str2 = new StringBuilder(Integer.toBinaryString(pageNum));
            while (str2.length()<8){
                str2.insert(0, "0");
            }
            String realAddress = str1.toString() +str2;

            System.out.println("绝对地址为："+realAddress+"十进制为："+Integer.parseUnsignedInt(realAddress,2));
        }else{//若页表不在主存中，则缺页中断
            System.out.println("*"+ p.pageNum);
        }

    }


}
