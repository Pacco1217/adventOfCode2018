public class Point implements Comparable{
    private int x;
    private int y;
    private String  value;

    public Point(int x, int y, String value){
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Point() {
        this.x = 999;
        this.y = 999;
        this.value = " ";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Point p = (Point)o;
        if (x < p.x) {
            return -1;
        }
        if (x > p.x) {
            return 1;
        }
        if (y < p.y) {
            return -1;
        }
        if (y > p.y) {
            return 1;
        }
        if (x < p.x) {
            return -1;
        }

        return 0;
    }
}
