import java.util.ArrayList;
import java.util.List;

public class Init_AFD {
    public static List<AFD> afds;
    public static void init(){
        if(afds!=null)
            return;
        afds=new ArrayList<>();
    }
}
