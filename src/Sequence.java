public class Sequence implements Comparable<Sequence>{
    public String sequence;
    public Integer value;
    public Sequence(String sequence, Integer value){
        this.sequence = sequence;
        this.value = value;
    }

    @Override
    public int compareTo(Sequence o) {
        return o.value.compareTo(this.value);
    }
}
