package View.Model;

import Model.Domain.Booking;
import Model.Domain.Room;
import Model.Mediator.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingListViewModel {
    private StringProperty checkin;
    private StringProperty checkout;
    private StringProperty rooms;
    private StringProperty number;
    private StringProperty errorLabel;
    private StringProperty name;
    private StringProperty phone;
    private Model model;
    private boolean needsupdate;

    public BookingListViewModel(Model model){
        this.model=model;
        this.errorLabel = new SimpleStringProperty();
        errorLabel.setValue("");
        this.needsupdate=false;
    }
    public BookingListViewModel(int a , LocalDate d1, LocalDate d2, String b, String c, ArrayList<Room> rooms){
        this.number= new SimpleStringProperty(a+"");
        this.checkin=new SimpleStringProperty(d1.toString());
        this.checkout=new SimpleStringProperty(d2.toString());
        this.name=new SimpleStringProperty(b);
        this.phone=new SimpleStringProperty(c);
        String s="";
        for(int i=0;i<rooms.size();i++){
            s=s+rooms.get(i).getNr();
            if(i==rooms.size()-1){
                s=s+".";
            }else s=s+", ";
        }
        this.rooms=new SimpleStringProperty(s);
    }

    public StringProperty getCheckinProperty() {
        return checkin;
    }
    public StringProperty getCheckoutProperty() {
        return checkout;
    }
    public StringProperty getNameProperty() {
        return name;
    }
    public StringProperty getRoomsProperty() {
        return rooms;
    }
    public StringProperty getNumberProperty(){
        return number;
    }
    public StringProperty getPhoneProperty(){
        return phone;
    }
    public StringProperty errorProperty(){
        return errorLabel;
    }
    public void logOut(){
        model.logOut();
    }
    public void update(){
        this.needsupdate=true;
        errorLabel.setValue("Please update list in order to continue.");
    }
    public ArrayList<Booking> getBookings() {
        this.needsupdate=false;
        return model.getBookings();
    }
    public void error(String s) {
        errorLabel.setValue(s);
    }
}
