package survivor_game.classes.Collisions;

public class ResultatCollision {
    private boolean collisionDetected;
    private Collisions collision;

    public ResultatCollision(boolean collisionDetected, Collisions collision) {
        this.collisionDetected = collisionDetected;
        this.collision = collision;
    }

    public boolean isCollisionDetected() {
        return collisionDetected;
    }

    public Collisions getCollision() {
        return collision;
    }
}
