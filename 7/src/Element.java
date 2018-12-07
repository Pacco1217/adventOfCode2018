
import java.util.ArrayList;


public class Element implements Comparable{
    private String element;
    private ArrayList<String> prerequisites;

    public Element(String element) {
        this.element = element;
        prerequisites = new ArrayList<>();
    }

    public Element() {
        this.element = "";
        prerequisites = new ArrayList<>();
    }
    public Element(Element e){
        this.element = e.element;
        this.prerequisites = new ArrayList<>(e.getPrerequisites().size());
        for(String s : e.getPrerequisites()){
            this.prerequisites.add(s);
        }
    }

    @Override
    public String toString() {
        return "Element{" +
                "element='" + element + '\'' +
                ", prerequisites=" + prerequisites +
                '}';
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public int compareTo(Object o) {
        Element e = (Element)o;
        return element.compareTo(e.element);
    }

    public int getIntValue(){
        return element.charAt(0) - 'A' + 1;
    }
}
