package geek.galaxy.cookieclicker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class Eater implements Eatable {
    Integer cost = 0;
    Integer basicScore = 1;
    Integer multiply = 1;

    private ImageView iconView;
    private TextView multiplyView;
    private boolean isBought = false;


    public Eater(ImageView _icon, TextView _multiply){
       this.iconView = _icon;
       this.multiplyView = _multiply;
    }

    @Override
    public Integer getEatedCookies() {
        if (isBought){
            return this.basicScore * this.multiply;
        }
        return 0;
    }

    @Override
    public Integer buyUpgrade(Integer money) {
        if(money >= cost){
            isBought = true;
            multiplyView.setVisibility(View.VISIBLE);
            iconView.setVisibility(View.VISIBLE);
            addMultipler();
            return money - cost;
        }
        return money;
    }

    @Override
    public void reset() {
        this.multiply = 1;
        multiplyView.setVisibility(View.INVISIBLE);
        iconView.setVisibility(View.INVISIBLE);
        updateText();
    }

    @Override
    public void addMultipler(){
        this.multiply += 1;
        updateText();
    }

    protected void updateText(){
        multiplyView.setText("x " + multiply.toString());
    }
}
