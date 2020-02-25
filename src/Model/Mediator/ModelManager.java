package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Domain.RoomList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModelManager implements Model {
    private RoomList list;
    private PropertyChangeSupport property;
    private ArrayList<Booking> bookings;
    private boolean loggedIn;
    private Client client;


    public ModelManager(Client client) {
        this.loggedIn = false;
        this.client = client;
        this.property = new PropertyChangeSupport(this);
        try {
            this.bookings = client.getBookings();
        } catch (Exception e) {
            e.printStackTrace();
            bookings = new ArrayList<>();
        }
        this.client.get(this);

        this.list = getRoomss();
      /*  ArrayList<Room> ar = new ArrayList<>();
        ar.add(list.getList().get(6));
        ar.add(list.getList().get(7));

        Guest guest = new Guest("MArtya dasd", 32141255);
        Booking b = new Booking(guest, list.getList(), LocalDate.now(), LocalDate.now().plusDays(1), 1);
*/
    }

    @Override
    public int roomnr() {
        return 0;
    }

    @Override
    public void logOut() {
        loggedIn = false;
    }

    @Override
    public boolean validateLogin(String user, String pw) {
        if (!user.equals("admin"))
            return false;
        if (!pw.equals("safepassword"))
            return false;
        return true;
    }

    @Override
    public boolean logIn(String user, String pw) {
        if (validateLogin(user, pw) == true) {
            this.loggedIn = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn;
    }

    @Override
    public ArrayList<Room> getAvRooms(LocalDate d1, LocalDate d2) {
        try {
            ArrayList<Room> thislist=client.getFreeRooms(d1, d2);
            if (thislist == null|| thislist.isEmpty()) {
                ArrayList<Room> empty = new ArrayList<>();
                return empty;
            } else
                return thislist;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Room> getRooms(int[] rooms) {
        ArrayList<Room> r = new ArrayList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < list.getList().size(); j++) {
                if (rooms[i] == list.getList().get(j).getNr()) {
                    r.add(list.getList().get(j));
                }
            }
        }
        return r;
    }

    @Override
    public int getLastBooking() {
        int max = 0;
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getNo() > max)
                max = bookings.get(i).getNo();
        }
        return max + 5;
    }

    @Override
    public Boolean createBooking(Booking book) {
        try {
            return client.createBooking(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    @Override
    public void needsUpdate() {
        property.firePropertyChange("update", false, true);
        try {
            this.bookings = client.getBookings();
            list = client.getRooms();
        } catch (Exception e) {
        }
    }

    @Override
    public RoomList getRoomss() {
        try{
        return client.getRooms();}
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
