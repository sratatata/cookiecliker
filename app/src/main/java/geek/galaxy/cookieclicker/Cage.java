package geek.galaxy.cookieclicker;

import java.util.ArrayList;

public class Cage<T extends Upgrade> extends ArrayList<T> {
    public void onCookieClick(GameState gameState){
        for (Upgrade monster : this){
            monster.onCookieClick(gameState);
        }
    }

    public void onTimeTick(GameState gameState) {
        //todo Notify all monsters in given cage, that time has ticking
    }
}
