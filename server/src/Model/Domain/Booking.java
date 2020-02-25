package Model.Domain;

import Model.Domain.Guest;
import Model.Domain.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking implements Serializable {


    private int no;
    private LocalDate d1;
    private LocalDate d2;
    private Guest guest;
    private ArrayList<Room> rooms;

    public Booking(Guest g, ArrayList<Room> rooms, LocalDate d1, LocalDate d2, int no) {
        this.guest = g;
        this.rooms = rooms;
        this.d1 = d1;
        this.d2 = d2;
        this.no = no;
    }

    public Booking(int no, LocalDate d1, LocalDate d2) {
        this.guest = null;
        this.d1 = d1;
        this.d2 = d2;
        this.no = no;
        this.rooms = new ArrayList<>();
    }

    public int getNo() {
        return no;
    }

    public LocalDate getD1() {
        return d1;
    }

    public LocalDate getD2() {
        return d2;
    }
    public void setRooms(ArrayList<Room> r){
        this.rooms=r;
    }
    public void setGuest(Guest g) {
        this.guest = g;
    }

    public Guest getGuest() {
        return guest;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }


}
