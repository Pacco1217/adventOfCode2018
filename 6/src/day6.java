import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class day6 {


    public static int manhattan_distance(Point p1, Point p2){
        int val = Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
        return val;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            ArrayList<Point> pointList = new ArrayList<>();
            int nbElem = 0;
            while ((line = br.readLine()) != null) {
                String[] coord = line.split(",");
                pointList.add(new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1].substring(1)), Integer.toString(nbElem)));
                nbElem++;
            }


            int bigX = 0;
            int bigY = 0;
            for(Point p : pointList){
                if(p.getX() > bigX)
                    bigX = p.getX();
                if(p.getY() > bigY)
                    bigY = p.getY();
            }
            System.out.println("max x = "+bigX+ " max y = " + bigY);
            Point[][] grid = new Point[bigX + 1][bigY + 1];
            //Put all points from the list in the grid
            for (int x = 0; x < bigX + 1; x++){
                for(int y = 0; y < bigY + 1; y++){
                    grid[x][y] = new Point(x,y, ".");
                    for(Point p : pointList){
                        if(grid[x][y].equals(p)){
                            grid[x][y].setValue(p.getValue());
                        }
                    }
                }
            }

            for (int x = 0; x < bigX + 1; x++) {
                for (int y = 0; y < bigY + 1; y++) {
                    //if(grid[x][y].getValue() != ".")
                        //break;
                    int minDist = 500;
                    String val = "";
                    for(Point p : pointList){
                        int dist = manhattan_distance(p, grid[x][y]);
                        if(dist < minDist){
                            minDist = dist;
                            if(dist != 0)
                                val = p.getValue().toLowerCase();
                            else
                                val = p.getValue();
                        }else if(dist == minDist){
                            minDist = dist;
                            val = ".";
                        }
                    }
                    grid[x][y].setValue(val);
                }
            }
            Boolean[] isFinite = new Boolean[nbElem];
            for(int i = 0; i < isFinite.length; i++){
                isFinite[i] = true;
            }
            //check borders

            for(int x = 0; x < bigX + 1; x++){
                if(grid[x][0].getValue() != ".")
                    isFinite[Integer.parseInt(grid[x][0].getValue())] = false;
                if(grid[x][bigY].getValue() != ".")
                    isFinite[Integer.parseInt(grid[x][bigY].getValue())] = false;
            }
            for(int y = 0; y < bigY + 1; y++){
                if(grid[0][y].getValue() != ".")
                    isFinite[Integer.parseInt(grid[0][y].getValue())] = false;
                if(grid[bigX][y].getValue() != ".")
                    isFinite[Integer.parseInt(grid[bigX][y].getValue())] = false;
            }
            int max = 0;
            for(int i = 0; i < nbElem; i++) {
                if(isFinite[i]) {
                    int current = 0;
                    for (int x = 0; x < bigX + 1; x++) {
                        for (int y = 0; y < bigY + 1; y++) {
                            if(grid[x][y].getValue() != ".") {
                                if (Integer.parseInt(grid[x][y].getValue()) == i) {
                                    current++;
                                    //System.out.println(grid[x][y].getValue());
                                }
                            }
                        }
                    }
                    if (current > max) {
                        max = current;
                    }
                }
            }
            System.out.println(max);

            //part2
            int count = 0;
            for (int x = 0; x < bigX + 1; x++) {
                for (int y = 0; y < bigY + 1; y++) {
                    int totalDist = 0;

                    for(Point p : pointList){

                        totalDist += manhattan_distance(p, grid[x][y]);
                    }
                    if(totalDist < 10000)
                        count++;
                }
            }

            System.out.println(count);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
