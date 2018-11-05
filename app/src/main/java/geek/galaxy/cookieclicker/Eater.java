package geek.galaxy.cookieclicker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class Eater {
    ImageView iconView = null;
    TextView multiplyView = null;

    Integer cost = 0;
    Integer basicScore = 1;
    Integer multiply = 1;
    boolean isBought = false;

    public Eater(ImageView _icon, TextView _multiply){
       this.iconView = _icon;
       this.multiplyView = _multiply;
    }

    public Integer getEatedCookies(){
        if (isBought){
            return this.basicScore * this.multiply;
        }
        return 0;
    }
    private void addMulitpler(){
        this.multiply += 1;
        updateText();
    }
    public Integer buyUpgrade(Integer money){
        if(money >= cost){
            isBought = true;
            multiplyView.setVisibility(View.VISIBLE);
            iconView.setVisibility(View.VISIBLE);
            addMulitpler();
            return money - cost;
        }
        return money;
    }
    public boolean canUpgrade(Integer money){
        return money >= cost;
    }

    public void reset(){
        this.multiply = 1;
        multiplyView.setVisibility(View.INVISIBLE);
        iconView.setVisibility(View.INVISIBLE);
        updateText();
    }

    private void updateText(){
        multiplyView.setText("x " + multiply.toString());
    }
}
