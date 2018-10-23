package geek.galaxy.cookieclicker;

import android.widget.ImageView;
import android.widget.TextView;

public class CookieMonster extends Eater {
    public CookieMonster(ImageView _icon, TextView _multiply) {
        super(_icon, _multiply);
        this.basicScore = 10;
        this.cost = 50;
    }
}
