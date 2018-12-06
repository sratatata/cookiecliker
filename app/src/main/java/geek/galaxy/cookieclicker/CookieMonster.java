package geek.galaxy.cookieclicker;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

class CookieMonster implements Upgrade {
    private static final int INITIAL_PRICE = 10;
    private static final int POINTS = 1;
    private static int currentPrice = INITIAL_PRICE;

    public CookieMonster() {
        currentPrice = (int)abs(ceil(currentPrice*Upgrade.PRICE_MULTIPLIER));
    }

    public static Integer getCrrentPrice() {
        return currentPrice;
    }

    @Override
    public void onCookieClick(GameState gameState) {
        gameState.incrementScore(POINTS);
    }
}
