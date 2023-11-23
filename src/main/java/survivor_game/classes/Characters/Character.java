package survivor_game.classes.Characters;

import survivor_game.classes.Sprites;

public abstract class Character extends Sprites {
    protected int life;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
