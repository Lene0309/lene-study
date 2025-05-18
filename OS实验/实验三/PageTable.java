public class PageTable {
    public int pageNum;//页号
    public int flag;//标记是否放入主存
    public char blockNum;//主存块号
    public int diskLocation;//在磁盘上的位置
    public int modifyBit;//修改位
    //构造方法
    public PageTable(int pageNo,int flag,char blockNo,int diskLocation,int modifyBit) {
        this.pageNum=pageNo;
        this.flag=flag;
        this.blockNum=blockNo;
        this.diskLocation=diskLocation;
        this.modifyBit=modifyBit;
    }
    public void setModifyBit(int modifyBit) {
        this.modifyBit=modifyBit;
    }
    public void setBlockNo(char blockNo) {
        this.blockNum=blockNo;
    }
    public void setFlag(int flag) {
        this.flag=flag;
    }
    public char getBlockNum() {
        return blockNum;
    }
    public int getPageNum() {
        return pageNum;
    }
    @Override
    //改写父类方法,输出页表
    public String toString() {
        return "页号：" + pageNum +  "   是否放入主存：" +flag+ "   主存块号：" +blockNum+ "   修改位："+modifyBit+"   在磁盘上的位置：" +diskLocation;
    }
}
