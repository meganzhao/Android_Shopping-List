package hu.ait.android.shoppinglist.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public class Item extends RealmObject {
    @PrimaryKey
    @Required
    private String itemId;

    private String name;




    private String category;
    private double price;
    private String note;
    private boolean isPurchased;


    public Item(){}

    public Item(String name, double price, String note, boolean isPurchased){
        this.name = name;
        this.price = price;
        this.note = note;
        this.isPurchased = isPurchased;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public void setItemId(String updatedItemId) {
        itemId = updatedItemId;
    }

    public String getItemId() {
        return itemId;
    }
}
