public class Guard {
    private int id;
    private int minutesSleeped;
    private int[] minutes;

    public Guard(int id, int minutesSleeped) {
        this.id = id;
        this.minutesSleeped = minutesSleeped;
        this.minutes = new int[60];
        for(int i = 0; i< 60; i++){
            minutes[i] = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinutesSleeped() {
        return minutesSleeped;
    }

    public void setMinutesSleeped(int minutesSleeped) {
        this.minutesSleeped = minutesSleeped;
    }

    public int[] getMinutes() {
        return minutes;
    }

    public void setMinutes(int minuteFallAsleep, int minuteWakeUp) {
        for(int i = minuteFallAsleep; i < minuteWakeUp; i++){
            minutes[i] += 1;
        }
    }
}
