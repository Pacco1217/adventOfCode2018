import java.io.BufferedReader;
import java.io.FileReader;

public class firstChal {

    public static void main(String[] args){
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            int[][] tab = new int[1000][1000];
            for(int i = 0; i< 1000; i++){
                for(int j = 0; j < 1000; j++){
                    tab[i][j] = 0;
                }
            }
            int[][] tabClaims = new int[1000][1000];
            for(int i = 0; i< 1000; i++){
                for(int j = 0; j < 1000; j++){
                    tabClaims[i][j] = 0;
                }
            }
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] words = line.split(" ");
                int number = Integer.parseInt(words[0].substring(1));
                String[] coord = words[2].split(",");
                int beginCol = Integer.parseInt(coord[0]);
                int beginRow = Integer.parseInt(coord[1].substring(0, coord[1].length() -1));
                String[] sizes = words[3].split("x");
                int nbCol = Integer.parseInt(sizes[0]);
                int nbRow = Integer.parseInt(sizes[1]);

                for(int row = beginRow; row < beginRow + nbRow; row++){
                    for(int col = beginCol; col < beginCol + nbCol; col++){
                        if(tab[row][col] == 0){
                            tab[row][col] = number;
                        }else{
                            tabClaims[row][col] = 1;
                        }
                    }
                }
            }
            int result = 0;
            for(int i = 0; i< 1000; i++){
                for(int j = 0; j < 1000; j++){
                    result += tabClaims[i][j];
                }
            }
            System.out.println(result);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}
