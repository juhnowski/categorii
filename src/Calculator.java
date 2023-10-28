import java.util.ArrayList;

public class Calculator {
    private ArrayList<Pair> ilist;
    public Calculator(ArrayList<Pair> list) {
        ilist = list;
    }

    public ArrayList<Pair> reduce(ArrayList<Pair> list, String op, String prefix) {
        ArrayList<Pair> res = new ArrayList<>();
        int l = list.get(0).getLevel();
        for (int i=0; i<list.size(); i++) {
            Pair p = list.get(i);
            int plen = p.getName().length();
            String prfx = p.getName().charAt(plen-1) == '-' ?p.getName():"";
           // System.out.println("char="+p.getName().charAt(plen-1));

            /*
                    String nm ="PR1-";
        int plen=nm.length();
        String prfx = nm.charAt(plen-1) == '-' ?nm:"";
        System.out.println("prfx="+prfx);
             */
            if (p.getLevel()==l) {
                p.addPrefix(prefix);
                if (op.equals(Const.OR)) {
                    if (p.isOperation()) {
                        ArrayList<Pair> op_res = reduce(getSublist(list,p), p.getOperation(),prfx);
                        res.addAll(op_res);
                    } else {
                        res.add(p);
                    }
                }

                if (op.equals(Const.AND)) {
                    if (p.isOperation()) {
                        ArrayList<Pair> op_res = reduce(getSublist(list,p), p.getOperation(),prfx);
                        if (res.isEmpty()) {
                            res.addAll(op_res);
                        } else {
                            ArrayList<Pair> res_before = new ArrayList<>(res);
                            res.clear();
                            op_res.forEach(op_res_p->{
                                res_before.forEach(r->{
                                    Pair nr = (Pair) r.clone();
                                    nr.and(op_res_p);
                                    res.add(nr);
                                });
                            });
                        }
                    } else {
                        if (res.isEmpty()) {
                            res.add(p);
                        } else {
                            res.forEach(pair -> {
                                pair.and(p);
                            });
                        }
                    }
                }
            }
        }
        return res;
    }

    private ArrayList<Pair> getSublist(ArrayList<Pair> list, Pair p) {
        ArrayList<Pair> res = new ArrayList<Pair>();
        int i=list.indexOf(p)+1;
        int l = p.getLevel();
        while (i<list.size()){
            if (list.get(i).getLevel()>l) {
                res.add(list.get(i));
                i++;
            } else {
                break;
            }
        }
        return res;
    }
}

