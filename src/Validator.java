import java.util.ArrayList;
import java.util.Collections;

public class Validator {
    public void validate(ArrayList<Pair> list) throws Exception{
        int minLevel =Collections.min(Const.LEVELS);
        long vertices = list.stream().filter(p->p.getLevel()==minLevel).count();
        if (vertices>1) throw new Exception("tree has " + vertices + " vertices");
        ArrayList<Integer> parent = new ArrayList<>();
        parent.add(list.get(0).getLevel());
        Pair vertice = list.get(0);
        if (!vertice.isOperation() || vertice.getLevel() != minLevel) {
            throw new Exception("wrong vertice");
        }

        Pair prev = null;
        for (int i=1; i<list.size(); i++){
            Pair p = list.get(i);
            int level = p.getLevel();
            if (level<=parent.get(parent.size()-1)) throw new Exception("line:"+i+" wrong level="+level);
        }
    }

}
