import java.util.HashSet;
import java.util.List;

public class Const {
    public static String OR = "ИЛИ";
    public static String AND = "И";
    public static List<String> OPS = List.of(OR, AND);
    public static String START="@startmindmap";
    public static String STOP="@endmindmap";
    public static List<String> PUML = List.of(START,STOP);

    public static HashSet<Integer> LEVELS = new HashSet<>();
}
