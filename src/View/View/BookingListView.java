package View.View;

import Model.Domain.Booking;
import Model.Domain.Room;
import View.Model.BookingListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingListView {

    @FXML
    private TableColumn<BookingListViewModel, String> number;
    @FXML
    private TableColumn<BookingListViewModel, String> checkin;
    @FXML
    private TableColumn<BookingListViewModel, String> checkout;
    @FXML
    private TableColumn<BookingListViewModel, String> name;
    @FXML
    private TableColumn<BookingListViewModel, String> phone;
    @FXML
    private TableColumn<BookingListViewModel, String> rooms;
    @FXML
    private TableView<BookingListViewModel> table;
    @FXML
    private Label errorLabel;
    private String title;
    private Scene scene;
    private BookingListViewModel viewModel;
    private MainView mainView;


    public BookingListView() {

    }

    public void init(MainView mainView, BookingListViewModel viewModel, Scene scene2, String title2) {
        checkin.setCellValueFactory(cellData -> cellData.getValue().getCheckinProperty());
        checkout.setCellValueFactory(cellData -> cellData.getValue().getCheckoutProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        rooms.setCellValueFactory(cellData -> cellData.getValue().getRoomsProperty());
        number.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        phone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());

        this.title = title2;
        this.scene = scene2;
        this.mainView = mainView;
        this.viewModel = viewModel;

        setTable();

        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());

    }

    public void setTable(){
        viewModel.error("");
        ObservableList<BookingListViewModel> tableData = FXCollections.observableArrayList();
        ArrayList<Booking> bookings=viewModel.getBookings();
        if(bookings!=null) {
            for (int i = 0; i < bookings.size(); i++) {
                int nr = bookings.get(i).getNo();
                LocalDate d1 = bookings.get(i).getD1();
                LocalDate d2= bookings.get(i).getD2();
                String name=bookings.get(i).getGuest().getName();
                String phone=bookings.get(i).getGuest().getPhone()+"";
                ArrayList<Room> rooms=bookings.get(i).getRooms();
                tableData.add(new BookingListViewModel(nr,d1,d2,name,phone,rooms));
            }
            table.setItems(tableData);
        }
    }

    @FXML
    private void editpressed(){
        viewModel.error("Feature missing.");
    }
    @FXML
    private void roompressed(){
        viewModel.error("");
        mainView.openWindow("AdminFirst");
    }
    @FXML
    private void loginButton(){
        viewModel.error("");
        mainView.openWindow("First");
        viewModel.logOut();
    }

    public String getTitle() {
        return title;
    }
    public Scene getScene() {
        return scene;
    }

    public void updatepressed() {
        setTable();
    }

}
