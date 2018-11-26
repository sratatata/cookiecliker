package geek.galaxy.cookieclicker;

public interface Eatable {
    Integer getEatedCookies();
    Integer buyUpgrade(Integer money);
    void reset();
    void addMultipler();
}
