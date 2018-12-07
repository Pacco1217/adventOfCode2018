import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class day7 {
    public static boolean isFullOfZero(int[] tab, int size){
        for(int i = 0; i < size; i++){
            if(tab[i] != 0)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            ArrayList<String> data = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            ArrayList<Element> elements = new ArrayList<>();
            //generate list of elements with their prerequisites
            for(String s : data){
                String[] parsedData = s.split(" ");
                String prere = parsedData[1];
                String elem = parsedData[7];
                boolean addedData = false;
                boolean prereExists = false;
                for(int i = 0; i < elements.size(); i++){
                    if(elements.get(i).getElement().equals(elem)){
                        addedData = true;
                        elements.get(i).getPrerequisites().add(prere);
                    }
                    if(elements.get(i).getElement().equals(prere)){
                        prereExists = true;
                    }

                }
                if(!addedData){
                    Element tmp = new Element(elem);
                    tmp.getPrerequisites().add(prere);
                    elements.add(tmp);
                }
                if(!prereExists){
                    Element tmp = new Element(prere);
                    elements.add(tmp);
                }

            }
            //Sort by alphabetical order
            Collections.sort(elements);

            ArrayList<Element> elements_p2 = new ArrayList<>(elements.size());
            for(Element e : elements){
                System.out.println(e);
                elements_p2.add(new Element(e));
            }
            /*
            PART ONE
             */
            String result = "";
            while(elements.size() > 0){
                for(Element e : elements){
                    if(e.getPrerequisites().size() == 0){
                        result += e.getElement();
                        for(Element elem: elements){
                            if(elem.getPrerequisites().contains(e.getElement()))
                                elem.getPrerequisites().remove(e.getElement());
                        }
                        elements.remove(e);
                        break;
                    }
                }
            }
            System.out.println("Part 1 : "+result);


            /*
            PART TWO;
             */
            /*Data value*/
            int nbWorkers = 5;

            int timeBase = 60;
            int timeTaks[] = new int[26];
            for(int i = 0; i< 26; i++){
                timeTaks[i] = timeBase+i;
            }

            /*Example value*/
            /*int nbWorkers = 2;
            int timeBase = 0;
            int timeTaks[] = new int[6];
            for(int i = 0; i< 6; i++){
                timeTaks[i] = timeBase+i;
            }*/


            int result_p2 = 0;


            Set<Element> running = new HashSet<>();

            while (!isFullOfZero(timeTaks, timeTaks.length)){
                for(Element e : elements_p2){
                    if(e.getPrerequisites().size() == 0 && running.size() < nbWorkers && timeTaks[e.getIntValue()-1] >= 0){
                        running.add(e);
                    }
                }
                ArrayList<Element> delete = new ArrayList<>();
                for(Element e : running){
                    if(timeTaks[e.getIntValue() - 1] > 0){
                        timeTaks[e.getIntValue() -1]--;
                    }else{
                        System.out.println("Time : " + result_p2 + " Task : "+ e.getElement());
                        delete.add(e);
                        for(Element elem : elements_p2){
                            if(elem.getPrerequisites().contains(e.getElement()))
                                elem.getPrerequisites().remove(e.getElement());
                        }
                    }

                }
                running.removeAll(delete);
                elements_p2.removeAll(delete);
                result_p2++;
            }
            System.out.println("Part 2 : " + ++result_p2);


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
