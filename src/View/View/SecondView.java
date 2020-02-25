package View.View;

import Model.Domain.Room;
import View.Model.FirstViewModel;
import View.Model.SecondViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class SecondView {

    @FXML
    private Label error;
    @FXML
    private Label label;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    private String title;
    private Scene scene;
    private SecondViewModel viewModel;
    private MainView mainView;



    public SecondView() {
    }

    public void init(MainView mainView, SecondViewModel viewModel, Scene scene2, String title2,int[] rooms,String label1,LocalDate d1,LocalDate d2) {

        this.title = title2;
        this.scene = scene2;
        this.mainView = mainView;
        this.viewModel = viewModel;
        viewModel.setDates(d1,d2);
        viewModel.setRooms(rooms);
        viewModel.setLabel(label1);

        error.textProperty().bindBidirectional(viewModel.errorProperty());
        label.textProperty().bind(viewModel.labelProperty());
    }

    public void cancelpressed(ActionEvent actionEvent) {
        if (viewModel.isLogged()==true){
            mainView.openWindow("AdminFirst");
        }else{
            mainView.openWindow("First");
        }
        name.clear();
        phone.clear();
        viewModel.error("");
    }


    public void bookpressed(ActionEvent actionEvent) {
        String a=name.getText();
        String b=phone.getText();
        if(a ==null || b==null){
            viewModel.error("Please fill out the fields.");
        }else if(b.matches("[0-9]+")==false && b.length() > 2) {
            viewModel.error("Please use only numbers for phone number");
        }else if(a.matches(".*\\d.*")==true && a.length() > 2){
            viewModel.error("Please use only letters for name.");
        }
        else{
            viewModel.error("");
            if (viewModel.isLogged()==true){
            viewModel.createBooking(a,b);
            name.clear();
            phone.clear();
            mainView.openWindow("AdminFirst");
        }
        else{
            viewModel.requestBooking(a,b);
            }
        }
    }


    public Scene getScene() {
        return scene;
    }

    public String getTitle(){
        return title;
    }
}
