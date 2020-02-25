package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Room;
import Model.Domain.RoomList;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends PropertyChangeSubject {

    public int roomnr();
    void logOut();
    boolean validateLogin(String user, String pw);
    boolean logIn(String user, String pw);
    boolean isLoggedIn();
    ArrayList<Room> getAvRooms(LocalDate d1, LocalDate d2);
    ArrayList<Room> getRooms(int[] rooms);
    int getLastBooking();
    Boolean createBooking(Booking book);
    ArrayList<Booking> getBookings();
    void needsUpdate();

    RoomList getRoomss();
}
