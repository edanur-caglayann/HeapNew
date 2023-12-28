package HeapUygulamalari;

public class Element {
    public int contents; // Önceliği belirten indis
    public Element(int contents) {
        this.contents = contents;
    }
    @Override
    public String toString(){
        return String.valueOf(contents);
    }
}
