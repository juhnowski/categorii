import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.SortedSet;

public class Main {
    public static void main(String[] args) {

        Parser parser = new Parser();
        String fileName = "tree.puml";
        ArrayList<Sequence> list = new ArrayList<>();
        try {
            ArrayList<Pair> lst = parser.read(fileName);
           // lst.forEach(System.out::println);

            Validator validator = new Validator();
            validator.validate(lst);

            Calculator calculator = new Calculator(lst);
            String op = lst.get(0).getOperation();
            lst.remove(0);
            ArrayList<Pair> _list = calculator.reduce(lst, op,"");
            _list.forEach(s->{
                list.add(new Sequence(s.getName(),s.getValue()));
            });
        } catch (Exception e){
            System.out.println(fileName+" "+e.getMessage());
            e.printStackTrace();
        }


        java.util.Collections.sort(list);

        try {
            FileWriter fileWriter = new FileWriter("sequences.csv", StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            list.forEach(s -> {
                if (s.value>0) {
                    printWriter.println(s.value + " ; " + s.sequence);
                    System.out.println(s.value + " ; " + s.sequence);
                }
            });

            printWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}