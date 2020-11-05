import java.util.ArrayList;

class Main {

    static final int forksAmount = 5;
    static final int philosopherAmount = 5;
    static final String[] philosopherNames = { "Mateus", "Lívia", "Daniel", "Duda", "Mateus da barbona", };
    static final int minThinkEatTime = 2000;
    static final int maxThinkEatTime = 5000;

    public static void main(String[] args) {

        ArrayList<Fork> forks = new ArrayList<Fork>();
        for (int i = 0; i < forksAmount; i++) {
            forks.add(new Fork());
        }

        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
        for (int i = 0; i < philosopherAmount; i++) {
            philosophers.add(new Philosopher(forks, i, minThinkEatTime, maxThinkEatTime, philosopherNames[i]));
            philosophers.get(i).start();
        }

    }
}