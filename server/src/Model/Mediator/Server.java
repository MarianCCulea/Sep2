package Model.Mediator;

import Model.Domain.Booking;
import Model.Domain.Guest;
import Model.Domain.Room;
import Model.Domain.RoomList;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;


public class Server implements RemoteSv {

    private PropertyChangeProxy<Room, Boolean> property;
    private Model model;


    public Server(Model m) throws RemoteException, MalformedURLException {
        this.model = m;
        // UnicastRemoteObject.exportObject(this, 0);
        RemoteSv stub = (RemoteSv) UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("BookingSv", stub);
        this.property = new PropertyChangeProxy<>(this);

    }

    @Override
    public RoomList getRooms() throws RemoteException {
        return model.getRooms();
    }

    @Override
    public ArrayList<Room> getFreeRooms(LocalDate d1, LocalDate d2) throws RemoteException {

        return model.getFreeRooms(d1, d2);
    }

    @Override
    public ArrayList<Guest> getGuests() throws RemoteException {
        return model.getGuests();
    }

    @Override
    public ArrayList<Booking> getBookings() throws RemoteException {
        return model.getBookings();
    }

    @Override
    public void updateBookings() throws RemoteException {
        model.updateBookings();
    }

    @Override
    public Boolean createBooking(Booking book) throws RemoteException {
        model.createBooking(book);

       property.firePropertyChange("Update", null, true);
return false;
    }



    @Override
    public boolean addListener(GeneralListener<Room, Boolean> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Room, Boolean> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }
}