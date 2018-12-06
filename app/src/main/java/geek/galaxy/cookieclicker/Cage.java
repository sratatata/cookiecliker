package geek.galaxy.cookieclicker;

import java.util.ArrayList;

public class Cage<T extends Upgrade> extends ArrayList<T> {
    public void onCookieClick(GameState gameState){
        for (Upgrade monster : this){
            monster.onCookieClick(gameState);
        }
    }

}
