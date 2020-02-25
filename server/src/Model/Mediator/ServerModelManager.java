package Model.Mediator;

import Model.Domain.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServerModelManager implements Model {

    private RoomList rooms;
    private ArrayList<Guest> guests;
    private ArrayList<Booking> bookings;
    private Database database;

    public ServerModelManager(Database db) {
        this.rooms = new RoomList();
        this.database = db;
        readRooms();
        readGuests();
        this.bookings = database.getBookings(guests, rooms.getList());
    }

    @Override
    public RoomList getRooms() {
        return rooms;
    }

    @Override
    public ArrayList<Guest> getGuests() {
        return guests;
    }

    @Override
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    @Override
    public void readRooms() {
        rooms.setList(database.getRooms());
    }

    @Override
    public void readGuests() {
        this.guests = database.getGuests();
    }

    @Override
    public synchronized void updateBookings() {
        database.update();
        this.bookings = database.getBookings(guests, rooms.getList());
        readGuests();
    }


    @Override
    public synchronized  ArrayList<Room>  getFreeRooms(LocalDate d1, LocalDate d2) {
        int[] a = database.getAvRooms(d1, d2);
        ArrayList<Room> ab=rooms.getFreeRooms(a);
        return ab;
    }

    @Override
    public synchronized void createBooking(Booking book) {
        bookings.add(book);
        database.insertBooking(book);
    }


}
