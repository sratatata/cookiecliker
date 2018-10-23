package geek.galaxy.cookieclicker;

import android.widget.ImageView;
import android.widget.TextView;

public class ExtraCookieMonster extends Eater {

    public ExtraCookieMonster(ImageView _icon, TextView _multiply) {
        super(_icon, _multiply);
        this.basicScore = 100;
        this.cost = 1000;
    }
}
