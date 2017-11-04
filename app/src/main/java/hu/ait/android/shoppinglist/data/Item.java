package hu.ait.android.shoppinglist.data;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public class Item {
    private String name;
    private double price;
    private String note;
    private boolean done;

    public Item(String name, double price, String note, boolean done){
        this.name = name;
        this.price = price;
        this.note = note;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


}
