package Model.Domain;

import java.io.Serializable;

public class Room implements Serializable {

    private int nr;
    private String type;
    private Double price;

    public Room(int nr,String type, Double price){
        this.nr=nr;
        this.type=type;
        this.price=price;
    }

    public int getNr() {
        return nr;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }
}
