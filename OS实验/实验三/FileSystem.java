import java.io.*;
import java.util.Scanner;

public class FileSystem {
    public static Scanner input;
    public static String address;
    public static File file;
    public static File[] filelist;
    public static int savenum = 0;
    public static int savemax = 10;
    public static String fileName;
    public static int opennum = 0;

    public static void main(String[] args) throws IOException {
        input = new Scanner(System.in);
        while (true) {
            System.out.print("请输入用户名：");
            String username = input.nextLine();
            System.out.print("请输入密码：");
            String password = input.nextLine();
            MFD mfd = Init_MFD.login(username, password);
            if (mfd == null) {
                System.out.println("请重新输入");
            } else {
                address = mfd.getAddress();
                file = new File(address);
                filelist = file.listFiles();
                while (true) {
                    displayChoose();
                    int choose = input.nextInt();
                    switch (choose) {
                        case 1:
                            create();
                            break;
                        case 2:
                            read();
                            break;

                        case 3:
                            write();
                            break;
                        case 4:
                            delete();
                            break;

                        case 5://查看文件目录，当前用户的文件目录
                            dic();
                            break;
                        case 6:
                            System.out.println("退出成功");
                            System.exit(0);
                        default:
                            System.out.println("输入指令异常，请重新输入！");
                            break;

                    }
                }
            }
        }


    }

    public static void write() {
        System.out.println("操作目录 :" + address);
        System.out.println("输入你要写入的文件名字（包括文件后缀名）：");
        String fileName = input.next();
        File fileToWrite = new File(address + "\\" + fileName);
        try {
            System.out.println("选择写入方式：add(追加)  cover(覆盖)");
            String how = input.next();
            System.out.println("输入你要写入的文本内容:");
            String content = input.nextLine(); // 读取整行文本
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileToWrite, how.equals("add"));
                writer.write(content);
                writer.flush();
                System.out.println("文件写入成功");
            } catch (IOException e) {
                System.out.println("写入文件时出现异常：" + e.getMessage());
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("关闭写入流时出现异常：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("输入格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("操作完毕，返回\n");
    }


    public static void delete() {
        System.out.println("输入你要删除的文件名字（包括文件后缀名）：");
        fileName = input.next();
        File f4 = new File(address + "\\" + fileName);
        if (f4.isFile()) {
            if (f4.exists()) {
                f4.delete();
                System.out.println("文件删除成功");
            }
        } else {

            System.out.println("文件不存在，删除失败");
        }
    }

    public static void read() {
        System.out.println("操作目录 :" + address);
        System.out.println("输入你要读取的文件名字（包括文件后缀名）：");
        String fileName = input.next();

        File fileToRead = new File(address + "\\" + fileName);
        if (!fileToRead.exists()) {
            System.out.println("不存在这样的文件");
            System.out.println("操作完毕，返回" + "\n");
            return;
        }
        if (opennum >= 5) {
            System.out.println("打开文件数已达到本次访问上限");
            System.out.println("操作完毕，返回" + "\n");
            return;
        }
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileToRead))) {
            System.out.println("读取的文件内容是：");
            String line;
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
            }
            opennum++;
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在，读取失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取文件时出现异常：" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("操作完毕，返回" + "\n");
    }


    public static void dic() {
        System.out.println("操作用户目录：" + address);
        File[] files = file.listFiles(); // 获取当前目录下的所有文件和目录
        if (files != null) {
            System.out.println("您的当前系统文件如下：\n");
            for (File f : files) {
                if (f.isFile()) { // 判断是否为文件
                    System.out.println("文件名：" + f.getName());
                    System.out.println("物理地址：" + f.getAbsolutePath());
                    System.out.println("文件大小：" + f.length() + " bytes");
                    System.out.println("文件权限：" + (f.canRead() && f.canWrite() ? "可读写" : "不可读写"));
                    System.out.println();
                }
            }
        } else {
            System.out.println("目录为空或无法访问");
        }
        System.out.println("操作完毕，返回\n");
    }


    public static void displayChoose() {
        System.out.println("***************************");
        System.out.println("#      --1:创建文件--      *");
        System.out.println("#      --2:读取文件--      *");
        System.out.println("#      --3:写入文件--      *");
        System.out.println("#      --4:删除文件--      *");
        System.out.println("#      --5:查看目录--      *");
        System.out.println("#      --6:退出登录--      *");
//        System.out.println("#      --7:打开文件--      *");
//        System.out.println("#      --8:关闭文件--      *");
        System.out.println("***************************");
        System.out.println("请输入要进行的操作");
    }

    public static void create() {
        System.out.println("操作目录 :" + address);
        System.out.println("输入你要创建的文件名字（包括文件后缀名）：");
        String fileName = input.next(); // 获取用户输入的文件名
        File newFile = new File(address + "\\" + fileName);
        // 检查文件是否已存在
        if (newFile.exists()) {
            System.out.println("该文件已经存在，无法创建！");
            return; // 提前结束方法，避免执行后续无意义的代码
        }
        // 检查文件保存数量是否达到上限
        if (savenum >= savemax) {
            System.out.println("保存文件数已达到本次访问上限！");
            return; // 提前结束方法
        }
        try {
            newFile.createNewFile();
            System.out.println("文件创建成功");
            savenum++;
        } catch (IOException e) {
            System.out.println("创建文件时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("操作完毕，返回" + "\n");
    }

}
