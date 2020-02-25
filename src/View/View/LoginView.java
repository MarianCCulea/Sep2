package View.View;


import View.Model.LoginViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginView
{
    private String title;
    private Scene scene;
    private LoginViewModel viewModel;
    private MainView mainView;
    @FXML
    private Label error;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    public LoginView()
    {

    }

    public void init(MainView mainView, LoginViewModel viewModel, Scene scene2, String title2) {

        this.title = title2;
        this.scene = scene2;
        this.mainView = mainView;
        this.viewModel = viewModel;

        error.textProperty().bindBidirectional(viewModel.getErrorProperty());
        username.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        password.textProperty().bindBidirectional(viewModel.getPasswordProperty());

    }
    /**
     * getter for the scene
     * @return the scene
     */
    public Scene getScene()
    {
        return scene;
    }
    /**
     * getter for the title
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Returns the user to the FirstView.
     */
    @FXML
    public void cancelpressed()
    {
    mainView.openWindow("First");
    }
    /**
     * Calls executeLogin method in the controller with the user name and the
     * password as parameters.
     */
    @FXML
    public void loginpressed()
    {
        if(viewModel.validate())
            mainView.openWindow("AdminFirst");
    }

    public String[] getInput(String type)
    {
        return new String[] { username.getText(), password.getText() };
    }

    public void reset() {
        viewModel.reset();
    }

    public void onEnter(ActionEvent actionEvent) {
        loginpressed();
    }
}