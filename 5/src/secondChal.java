import java.io.BufferedReader;
import java.io.FileReader;

public class secondChal {
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
            int minor = polymer.length();
            for(int j = 0; j < 26; j++) {
                System.out.println("iteration "+ j);
                char c = (char)('a' + j);

                String polymerTester = polymer.replaceAll(String.valueOf(c),"");
                polymerTester = polymerTester.replaceAll(String.valueOf(Character.toUpperCase(c)), "");

                while (contains2DeletableChar(polymerTester)) {
                    for (int i = 0; i < polymerTester.length() - 1; i++) {
                        if (Character.toLowerCase(polymerTester.charAt(i)) == Character.toLowerCase(polymerTester.charAt(i + 1))) {
                            if ((Character.isUpperCase(polymerTester.charAt(i)) && Character.isLowerCase(polymerTester.charAt(i + 1))) || (Character.isLowerCase(polymerTester.charAt(i)) && Character.isUpperCase(polymerTester.charAt(i + 1)))) {
                                polymerTester = polymerTester.substring(0, i) + polymerTester.substring(i + 2);
                            }
                        }
                    }
                }
                if(polymerTester.length() < minor){
                    minor = polymerTester.length();
                }
            }
            System.out.println("result : " + minor);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
