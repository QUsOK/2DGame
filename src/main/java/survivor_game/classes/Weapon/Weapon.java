package survivor_game.classes.Weapon;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Weapon {

    protected String nom;
    protected int degat;
    protected int distance;
    protected double recharge;
    protected boolean isRecharge = true;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public int getDegat() {
        return degat;
    }

    public int getDistance() {
        return distance;
    }

    public double getRecharge() {
        return recharge;
    }

    public String getNom() {
        return nom;
    }

    public boolean isRecharge() {
        return isRecharge;
    }

    public void setRecharge(boolean recharge) {
        isRecharge = recharge;
    }

    public void recharge() {
        if (!isRecharge) {
            scheduler.shutdownNow();

            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                isRecharge = true;
            }, (long) recharge * 200, TimeUnit.MILLISECONDS);
        }
    }

}
