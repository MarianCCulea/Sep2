package Model.Domain;

import java.io.Serializable;

public class Guest implements Serializable {


    private String name;
    private int phone;

    public Guest(String name, int phone){
        this.name=name;
        this.phone=phone;
    }

    public String getName(){
        return name;
    }

    public int getPhone(){
        return phone;
    }

}
