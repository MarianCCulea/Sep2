package View.Model;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Mediator.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class SecondViewModel {
    private Model model;
    private StringProperty error;
    private StringProperty label;
    private LocalDate d1;
    private LocalDate d2;
    private int[] rooms;

    public SecondViewModel(Model model) {
        this.model = model;
        this.error = new SimpleStringProperty();
        this.label = new SimpleStringProperty();
        error.setValue("");
    }

    public StringProperty errorProperty(){
        return error;
    }

    public StringProperty labelProperty(){
        return label;
    }

    public void error(String t){
        error.setValue(t);
    }

    public boolean isLogged() {
        return model.isLoggedIn();
    }

    public void setRooms(int[] rooms) {
        this.rooms=rooms;
    }

    public void setLabel(String label1) {
        label.setValue(label1+" Checking in on "+d1.toString()+" and out on "+d2.toString());
    }

    public void setDates(LocalDate d1, LocalDate d2) {
        this.d1=d1;
        this.d2=d2;
    }

    public void createBooking(String a, String b) {
        int phone = Integer.parseInt(b);
        Guest guest= new Guest(a,phone);
        ArrayList<Room> list=model.getRooms(rooms);
        int nr=model.getLastBooking();

        Booking book=new Booking(guest,list,d1,d2,nr);
        model.createBooking(book);
    }

    public void requestBooking(String a, String b) {

    }

    public void clear() {
    }
}
