import java.io.BufferedReader;
import java.io.FileReader;

public class firstChal {
    public static boolean contains2DeletableChar(String str){
        for(int i = 0; i < str.length()-1; i++){
            if(Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(str.charAt(i+1))) {
                if ((Character.isUpperCase(str.charAt(i)) && Character.isLowerCase(str.charAt(i+1))) || (Character.isLowerCase(str.charAt(i)) && Character.isUpperCase(str.charAt(i+1)))) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //read file and create one big string
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            String polymer ="";

            while ((line = br.readLine()) != null) {
                polymer = polymer.concat(line);
            }
            while(contains2DeletableChar(polymer)) {
                for (int i = 0; i < polymer.toString().length() - 1; i++) {
                    if (Character.toLowerCase(polymer.charAt(i)) == Character.toLowerCase(polymer.charAt(i+1))) {
                        if ((Character.isUpperCase(polymer.charAt(i)) && Character.isLowerCase(polymer.charAt(i+1))) || (Character.isLowerCase(polymer.charAt(i)) && Character.isUpperCase(polymer.charAt(i+1)))) {
                            polymer = polymer.substring(0, i) + polymer.substring(i + 2);
                        }
                    }
                }
            }
            System.out.println(polymer);
            System.out.println("result : " + polymer.length());


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
