public class Pair implements Cloneable{
    private String name;
    private String operation;
    private Integer value;
    private boolean isOperation;

    private Integer level;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isOperation() {
        return isOperation;
    }

    public Integer getLevel() {
        return level;
    }

    public void and(Pair p) {
        this.name = this.name + " " + p.getName();
        this.value *= p.getValue();
    }

    public void addPrefix(String prefix){
        this.name = prefix+this.name;
    }

    public Object clone() {
        try {
            return (Pair) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Pair(this.name, this.operation, this.value, this.isOperation, this.level);
        }
    }

    public Pair(String name, String operation, Integer value, boolean isOperation, Integer level){
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.isOperation = isOperation;
        this.level = level;
    }

    public Pair(String row) throws Exception{
        if (row.trim().isEmpty()) throw new Exception("null row");
        String[] s = row.split(":");
        if (s.length != 2) throw  new Exception("bad format :");
        String s2=s[1].trim();
        if (s2.isEmpty()) throw new Exception("null :_");
        String s1=s[0];
        if (s1.trim().isEmpty()) throw new Exception("null _:");

        try {
            value = Integer.parseInt(s2);
            isOperation = false;
        } catch (Exception ex){
            if (Const.OPS.contains(s2)) {
                operation = s2;
                isOperation = true;
            } else {
                throw new Exception("bad operation: " + s2);
            }
        }

        String[] sn =s1.split("\\*");
        if (sn.length !=2) throw new Exception("bad format *");

        level = getTabs(sn[0]);
        Const.LEVELS.add(level);
        name = sn[1].trim();

    }

    private int getTabs(String s) {
        {
            int counter = 0;
            if (s.length()>0) {
                for (char c : s.toCharArray()) {
                    if ("\t".equals("" + c)) {
                        counter = counter + 4;
                    } else {
                        counter++;
                    }
                }
            }
            return counter;
        }
    }

    public String toString() {
        if (isOperation) {
            return "name:" + name + "  operation:" + operation +  "  level:"+level;
        } else {
            return "name:" + name +  "  value:" + value + "  level:"+level;
        }

    }
}
