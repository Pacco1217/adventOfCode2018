import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class firstChal {
    private static final String format = "yyyy-MM-DD HH:mm";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(format);
    private static Date getDatefromString(String str) throws ParseException {
        return sdf.parse(str.substring(1,17));
    }
    private static String[] getGuardInfos(String str){
        return str.substring(19).split(" ");
    }
    private static boolean guardIsExisting(ArrayList<Guard> list, int id){
        if(list.size() == 0){
            return false;
        }else{
            for(Guard g : list){
                if(g.getId() == id)
                    return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            int nbElements = 0;
            String line;
            ArrayList<String> datas = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                nbElements++;
                datas.add(line);
            }
            Collections.sort(datas);
            /*for(String data : datas){
                System.out.println(data);
            }*/

            ArrayList<Guard> guards = new ArrayList<>();
            for(int i = 0; i < datas.size(); i++){
                if (datas.get(i).contains("Guard")){
                    //TODO parse String to get info
                    String[] infos = getGuardInfos(datas.get(i));
                    int id = Integer.parseInt(infos[1].substring(1));
                    System.out.println("guard id : "+id);
                    //Get info about the guard
                    int index = i + 1;
                    int totalAsleep = 0;
                    if(index < (datas.size() - 1)) {
                        while (!datas.get(index).contains("Guard")) {
                            if(datas.get(index).contains("asleep")){
                                int asleep = 0;
                                Date dateAsleep = getDatefromString(datas.get(index));
                                Date dateWake = getDatefromString(datas.get(index + 1));

                                asleep = (int)(dateWake.getTime() - dateAsleep.getTime())/(60 * 1000);
                                int minuteFallAsleep = Integer.parseInt(datas.get(index).substring(15, 17));
                                int minuteWakeUp = Integer.parseInt(datas.get(index + 1).substring(15, 17));
                                if(guardIsExisting(guards, id)){
                                    System.out.println("guard exists");
                                    for(Guard g : guards){
                                        if(g.getId() == id){
                                            g.setMinutesSleeped(g.getMinutesSleeped() + asleep);
                                            g.setMinutes(minuteFallAsleep, minuteWakeUp);
                                        }
                                    }
                                }
                                else{
                                    guards.add(new Guard(id, asleep));
                                }

                            }
                            index++;
                            if(index >= datas.size()) {
                                break;
                            }
                        }
                        System.out.println(totalAsleep);
                        System.out.println("new Guard found");
                    }

                }
            }
            int idMax = 0;
            int minMax = 0;
            int[] minutesMax = new int[60];
            for(Guard g : guards){
                if(g.getMinutesSleeped() > minMax){
                    idMax = g.getId();
                    minMax = g.getMinutesSleeped();
                    minutesMax = g.getMinutes();
                }
            }
            int max = 0;
            int Imax = 0;
            for(int i = 0; i < 60; i++){
                if(minutesMax[i] > max){
                    max = minutesMax[i];
                    Imax = i;
                }
            }
            System.out.println("idMax : "+ idMax + " min : " + minMax);
            int result = idMax * Imax;
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
