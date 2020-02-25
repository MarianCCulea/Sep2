import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Model.Domain.Database;
import Model.Mediator.Model;
import Model.Mediator.Server;
import Model.Mediator.ServerModelManager;

public class RmiServer
{
    public static void main(String[] args)
            throws RemoteException, MalformedURLException
    {
        Database database= new Database("Simpluhhh1");
        Model model=new ServerModelManager(database);
        startRegistry();
        Server server = new Server(model);
        System.out.println("Server started..."+model.getRooms().getList().toString());
        model.updateBookings();
    }

    public static void startRegistry() throws RemoteException{
    try{
        Registry reg= LocateRegistry.createRegistry(1099);

        System.out.println("Registry started...");
    }catch (java.rmi.server.ExportException e)
    {
        System.out.println("Registry already started? " + e.getMessage());
    }
}
}