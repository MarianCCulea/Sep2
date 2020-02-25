package View.View;

import View.Model.FirstViewModel;
import View.Model.MainViewModel;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainView extends Application {
    private Stage primaryStage;
    private FirstView firstView;
    private AdminFirstView adminfirstView;
    private LoginView loginView;
    private MainViewModel mainViewModel;
    private SecondView secondView;
    private BookingListView bookingListView;
    private int currentViewID;

    public MainView(MainViewModel m){
        this.mainViewModel=m;
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        openWindow("First");

    }


    public void openWindow(String id)
    {
        switch (id) {
            case "First":
                loadFirstWindow("Booking Tool", "FirstView.fxml", 1280, 720);
                break;
            case "Login":
                loadLoginWindow("Login", "Login.fxml", 600, 300);
                break;
            case "AdminFirst":
                loadAdminFirstWindow("Admin", "AdminFirstView.fxml", 1280, 720);
                break;
            case "BookingList":
                loadBookingListWindow("BookingList", "BookingList.fxml", 1080, 645);
                break;
        }

    }


    private void loadFirstWindow(String title, String fxmlFile, double width, double height)
    {
        if (firstView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                firstView = loader.getController();
                if (primaryStage.getScene() != null)
                {
                    primaryStage.getScene().getWindow().hide();
                }
                primaryStage.setScene(scene);
                primaryStage.setTitle(title);
                primaryStage.show();
                firstView.init(this,mainViewModel.getFirstViewModel() ,scene,title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(firstView.getScene());
        primaryStage.setTitle(firstView.getTitle());
        primaryStage.show();
        firstView.setTables();
    }


    private void loadBookingListWindow(String title, String fxmlFile, double width, double height)
    {
        if (bookingListView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                bookingListView = loader.getController();
                if (primaryStage.getScene() != null)
                {
                    primaryStage.getScene().getWindow().hide();
                }
                primaryStage.setScene(scene);
                primaryStage.setTitle(title);
                primaryStage.show();
                bookingListView.init(this,mainViewModel.getBookingViewModel() ,scene,title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(bookingListView.getScene());
        primaryStage.setTitle(bookingListView.getTitle());
        primaryStage.show();
        bookingListView.setTable();
    }

    private void loadAdminFirstWindow(String title, String fxmlFile, double width, double height)
    {
        if (adminfirstView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                adminfirstView = loader.getController();
                if (primaryStage.getScene() != null)
                {
                    primaryStage.getScene().getWindow().hide();
                }
                primaryStage.setScene(scene);
                primaryStage.setTitle(title);
                primaryStage.show();
                adminfirstView.init(this,mainViewModel.getAdminFirstViewModel() ,scene,title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(adminfirstView.getScene());
        primaryStage.setTitle(adminfirstView.getTitle());
        primaryStage.show();
        adminfirstView.setTables();

    }


    private void loadLoginWindow(String title, String fxmlFile, double width, double height)
    {
        if (loginView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root, width, height);
                loginView = loader.getController();
                if (primaryStage.getScene() != null)
                {
                    primaryStage.getScene().getWindow().hide();
                }
                primaryStage.setScene(scene);
                primaryStage.setTitle(title);
                primaryStage.show();
                loginView.init(this,mainViewModel.getLoginViewModel() ,scene,title);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(loginView.getScene());
        primaryStage.setTitle(loginView.getTitle());
        primaryStage.show();
        loginView.reset();

    }

    public void openSecond(int[] a, String b, LocalDate d1,LocalDate d2) {
        if (secondView == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("SecondView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1280, 720);
                secondView = loader.getController();
                if (primaryStage.getScene() != null)
                {
                    primaryStage.getScene().getWindow().hide();
                }
                primaryStage.setScene(scene);
                primaryStage.setTitle("Finish Booking");
                primaryStage.show();
                secondView.init(this,mainViewModel.getSecondViewModel() ,scene,"Finish Booking",a,b,d1,d2);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(secondView.getScene());
        primaryStage.setTitle(secondView.getTitle());
        primaryStage.show();

    }
}
