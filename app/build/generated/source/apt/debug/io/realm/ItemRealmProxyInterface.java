package io.realm;


public interface ItemRealmProxyInterface {
    public String realmGet$itemId();
    public void realmSet$itemId(String value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public String realmGet$category();
    public void realmSet$category(String value);
    public double realmGet$price();
    public void realmSet$price(double value);
    public String realmGet$note();
    public void realmSet$note(String value);
    public boolean realmGet$isPurchased();
    public void realmSet$isPurchased(boolean value);
}
