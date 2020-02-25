import Model.Mediator.Client;
import Model.Mediator.ModelManager;
import View.Model.MainViewModel;
import View.View.MainView;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String host = "localhost"; //change to ip in order to access the server from another computer
       // if (System.getSecurityManager() == null){ System.setSecurityManager(new SecurityManager());}
        Client client = new Client(host);
        ModelManager model=new ModelManager(client);
        MainViewModel mainViewModel=new MainViewModel(model);
        MainView main= new MainView(mainViewModel);
        main.start(primaryStage);

    }

    public static void main(String[] args) throws RemoteException {

        launch(args);
    }
}
