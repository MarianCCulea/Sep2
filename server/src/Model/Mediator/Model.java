package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Domain.RoomList;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Model {
    RoomList getRooms();
    ArrayList<Guest> getGuests();
    ArrayList<Booking> getBookings();
    void readRooms();
    void readGuests();
    void updateBookings();
    ArrayList<Room> getFreeRooms(LocalDate d1, LocalDate d2);
    void createBooking(Booking book);
}
