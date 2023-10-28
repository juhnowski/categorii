import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Parser {
public ArrayList<Pair> read(String fileName) throws Exception{
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    ArrayList<Pair> rows = new ArrayList<>();
    int cnt=1;
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        boolean start = false;
        while (line != null) {

            if (!Const.PUML.contains(line)) {
                if (start) {
                    rows.add(new Pair(line));
                }
            } else {
                if (line.trim().equals(Const.START)) start = true;
                if (line.trim().equals(Const.STOP)) break;
            }
            line = br.readLine();
            cnt++;
        }
        return rows;
    } catch (Exception e){
        throw new Exception("line:"+cnt+ " error:"+e.getMessage());
    } finally {
        br.close();
    }
}
}
