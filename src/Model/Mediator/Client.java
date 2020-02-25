package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Domain.RoomList;
import utility.observer.event.ObserverEvent;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Client implements RemoteSv {

    private RemoteSv server;
    private Model model;

    public Client(String host)
    {
        try
        {
            server = (RemoteSv) Naming.lookup("rmi://"+host+":1099/BookingSv");
            UnicastRemoteObject.exportObject(this, 0);
           //server.addListener(this);
            server.updateBookings();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void get(ModelManager a){
        this.model=a;
    }



    public RoomList getRooms() throws RemoteException {
        return server.getRooms();
    }


    public ArrayList<Room> getFreeRooms(LocalDate d1, LocalDate d2) throws RemoteException {
        ArrayList<Room> a=server.getFreeRooms(d1,d2);
        System.out.println(a);
        return a;
    }


    public ArrayList<Guest> getGuests() throws RemoteException {
        return server.getGuests();
    }


    public ArrayList<Booking> getBookings() throws RemoteException {
        return server.getBookings();
    }


    public void updateBookings() throws RemoteException {
server.updateBookings();
    }



    public Boolean createBooking(Booking book) throws RemoteException {
         System.out.println("Client");
        return server.createBooking(book);
    }

    @Override
    public void propertyChange(ObserverEvent event) throws RemoteException {
    model.needsUpdate();
    }
}
