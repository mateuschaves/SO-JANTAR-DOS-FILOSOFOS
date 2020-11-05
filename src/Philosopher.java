import java.util.ArrayList;
import java.util.Random;

public class Philosopher extends Thread {

    String name;
    int positionInTable;
    int minThinkEatTime;
    int maxThinkEatTime;
    ArrayList<Fork> forks;

    Philosopher(ArrayList<Fork> forks, int positionInTable, int minThinkEatTime, int maxThinkEatTime, String name) {
        this.forks = forks;
        this.positionInTable = positionInTable;
        this.minThinkEatTime = minThinkEatTime;
        this.maxThinkEatTime = maxThinkEatTime;
        this.name = name;
    }

    public void run() {
        while (true) {
            try {
                this.eat(this.forks, this.positionInTable, this.minThinkEatTime, this.maxThinkEatTime, this.name);
                this.thinking(minThinkEatTime, maxThinkEatTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void eat(ArrayList<Fork> forks, int positionInTable, int minThinkEatTime, int maxThinkEatTime, String name)
            throws InterruptedException {
        if (this.getLeftFork(forks, positionInTable).getInUse()
                && this.getRightFork(forks, positionInTable).getInUse()) {
            forks.wait();
        } else {

            this.getLeftFork(forks, positionInTable).setInUse(true);
            this.getRightFork(forks, positionInTable).setInUse(true);

            final int eatingTime = this.generateRandomTime(minThinkEatTime, maxThinkEatTime);
            System.out.println(name + " will be eating for " + eatingTime / 1000 + " seconds.");
            Thread.sleep(eatingTime);
            System.out.println(name + " finished eating and is dropping his two forks.");

            this.getLeftFork(forks, positionInTable).setInUse(false);
            this.getRightFork(forks, positionInTable).setInUse(false);

            forks.notify();
        }

    }

    void thinking(int minThinkEatTime, int maxThinkEatTime) throws InterruptedException {

        final int thinkingTime = this.generateRandomTime(minThinkEatTime, maxThinkEatTime);
        System.out.println(name + " will be thinking for " + thinkingTime / 1000 + " seconds.");
        Thread.sleep(thinkingTime);
    }

    Fork getLeftFork(ArrayList<Fork> forks, int positionInTable) {
        return forks.get(positionInTable);
    }

    Fork getRightFork(ArrayList<Fork> forks, int positionInTable) {
        return forks.get((positionInTable + 1) > forks.size() ? 0 : positionInTable + 1);
    }

    int generateRandomTime(int minThinkEatTime, int maxThinkEatTime) {
        return new Random().nextInt(maxThinkEatTime - minThinkEatTime) + minThinkEatTime;
    }
}
