package View.View;

import Model.Domain.Room;
import View.Model.FirstViewModel;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.ArrayList;

public class FirstView {

    @FXML
    private TableColumn<FirstViewModel, String> number;
    @FXML
    private TableColumn<FirstViewModel, String> type;
    @FXML
    private TableColumn<FirstViewModel, String> adults;
    @FXML
    private TableColumn<FirstViewModel, String> price;
    @FXML
    private TableColumn<FirstViewModel, String> number1;
    @FXML
    private TableColumn<FirstViewModel, String> type1;
    @FXML
    private TableColumn<FirstViewModel, String> adults1;
    @FXML
    private TableColumn<FirstViewModel, String> price1;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<FirstViewModel> table;
    @FXML
    private TableView<FirstViewModel> table1;
    @FXML
    private Label priceLabel;
    @FXML
    private DatePicker p1;
    @FXML
    private DatePicker p2;
    private LocalDate selectedDate;
    private String title;
    private Scene scene;
    private FirstViewModel viewModel;
    private MainView mainView;



    public FirstView() {

    }


    public void init(MainView mainView, FirstViewModel viewModel, Scene scene2, String title2) {

        number1.setCellValueFactory(cellData -> cellData.getValue().getRoomNrProperty());
        type1.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        adults1.setCellValueFactory(cellData -> cellData.getValue().getAdultproperty());
        price1.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
        number.setCellValueFactory(cellData -> cellData.getValue().getRoomNrProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        adults.setCellValueFactory(cellData -> cellData.getValue().getAdultproperty());
        price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());

        this.title = title2;
        this.scene = scene2;
        this.mainView = mainView;
        this.viewModel = viewModel;
        this.selectedDate = LocalDate.now();
        LocalDate datenow=selectedDate;
        p1.setValue(datenow);
        for(int x=0;x<7;x++){
            datenow=datenow.plusDays(1);
        }
        p2.setValue(datenow);

        setTables();

        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
        priceLabel.textProperty().bind(viewModel.priceProperty());
        System.out.println("worked");
    }

    public void setTables(){
        LocalDate d1=selectedDate;
        LocalDate d2=selectedDate;
        for(int i=0;i<7;i++){
            d2=d2.plusDays(1);
        }
        ObservableList<FirstViewModel> tableData = FXCollections.observableArrayList();
        ArrayList<Room> rooms=viewModel.getAvRooms(d1,d2);
        if(rooms!=null) {
            for (int i = 0; i < rooms.size(); i++) {
                int nr = rooms.get(i).getNr();
                String typ = rooms.get(i).getType();
                Double price = rooms.get(i).getPrice();
                tableData.add(new FirstViewModel(nr, typ, price));
            }
            table.setItems(tableData);
        }
    }


    @FXML
    private void submitButton() {
        if(viewModel.getNeedsupdate()==false) {

            ObservableList<FirstViewModel> a = table1.getItems();
            if (a.isEmpty() == false) {
                int[] list = new int[50];
                for (int i = 0; i < a.size(); i++) {
                    list[i] = a.get(i).getRoomnumber();
                }
                viewModel.sumPrice(table1.getItems(), p1.getValue(), p2.getValue());
                String b = viewModel.getPricee();
                LocalDate d1 = p1.getValue();
                LocalDate d2 = p2.getValue();
                mainView.openSecond(list, b, d1, d2);
            } else {
                viewModel.error("Please select the rooms you want to book.");
            }
        }else{
            viewModel.error("Please update the list in order to continue.");
        }
    }

    public Scene getScene() {
        return scene;
    }

    @FXML
    private void priceButton() {
        viewModel.sumPrice(table1.getItems(),p1.getValue(),p2.getValue());
    }

    @FXML
    private void addButton(){
        if(viewModel.getNeedsupdate()==false) {
            viewModel.error("");
       FirstViewModel selected=table.getSelectionModel().getSelectedItem();
       if(selected!=null){
        ObservableList<FirstViewModel> tableData = table.getItems();
        ObservableList<FirstViewModel> tableData1 = table1.getItems();
        tableData1.add(selected);
       tableData.remove(selected);
       table.setItems(tableData);
       table1.setItems(tableData1);
       }
        }else{
            viewModel.error("Please update the list in order to continue.");
        }
    }

    @FXML
    private void removeButton(){
        if(viewModel.getNeedsupdate()==false) {
            viewModel.error("");
       FirstViewModel selected=table1.getSelectionModel().getSelectedItem();
        if(selected!=null){
        ObservableList<FirstViewModel> tableData = table.getItems();
        ObservableList<FirstViewModel> tableData1 = table1.getItems();
        tableData.add(selected);
        tableData1.remove(selected);
        table1.setItems(tableData1);
        table.setItems(tableData);
        }
        }else{
            viewModel.error("Please update the list in order to continue.");
        }
    }

    @FXML
    private void loginButton(){
mainView.openWindow("Login");
    }

    @FXML
    private void updateList(){
        Boolean check=viewModel.check(p1.getValue(),p2.getValue());
        ObservableList<FirstViewModel> tableData = FXCollections.observableArrayList();
        if(check==true)
            uppdate();
        else {
            table.setItems(tableData);
        }


    }

    public void uppdate(){
        ObservableList<FirstViewModel> tableData = FXCollections.observableArrayList();
        ArrayList<Room> rooms=viewModel.getAvRooms(p1.getValue(),p2.getValue());
        if(rooms!=null) {
            for (int i = 0; i < rooms.size(); i++) {
                int nr = rooms.get(i).getNr();
                String typ = rooms.get(i).getType();
                Double price = rooms.get(i).getPrice();
                tableData.add(new FirstViewModel(nr, typ, price));
            }
            table.setItems(tableData);
        }else{
            viewModel.error("There are no rooms.");
        }
    }

    public String getTitle() {
        return title;
    }


}
