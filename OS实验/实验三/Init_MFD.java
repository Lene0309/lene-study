import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Init_MFD {
    private static List<MFD> mfds;
    public static final String root = "./root";

    public static void init() throws IOException {
        if (mfds != null)
            return;
        mfds = new ArrayList<>();
        File file = new File(root);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("根目录不存在或不是一个文件夹。");
            return;
        }
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("根目录下没有文件。");
            return;
        }
        for (File f : files) {
            String fname = f.getName();
            mfds.add(new MFD(fname,fname,root+"/"+fname));
        }
    }

    public static MFD login(String username, String password) throws IOException {
        init();
        for (MFD mfd : mfds) {
            if (mfd.getUsername().equals(username)) {
                if (mfd.getPassword().equals(password)) {
                    System.out.println("登录成功");
                    return mfd;
                }
                System.out.println("密码输入错误");
            }
        }
        System.out.println("该用户不存在");
        return null;
    }
}
