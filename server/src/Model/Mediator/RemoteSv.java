package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Domain.RoomList;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteSv extends RemoteSubject<Room, Boolean>
{
    RoomList getRooms()throws RemoteException;
    ArrayList<Room> getFreeRooms(LocalDate d1, LocalDate d2)throws RemoteException;
    ArrayList<Guest> getGuests()throws RemoteException;
    ArrayList<Booking> getBookings()throws RemoteException;
    void updateBookings()throws RemoteException;
    Boolean createBooking(Booking book)throws RemoteException;



}
