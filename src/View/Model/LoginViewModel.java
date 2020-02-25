package View.Model;

import Model.Mediator.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {

    private Model model;
    private StringProperty errorPropety;
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;

    public LoginViewModel(Model model){
        this.model=model;
        this.errorPropety = new SimpleStringProperty("");
        this.usernameProperty= new SimpleStringProperty();
        this.passwordProperty= new SimpleStringProperty();
        }


    public StringProperty getErrorProperty(){
        return errorPropety;
    }

    public StringProperty getUsernameProperty(){
        return usernameProperty;
    }

    public StringProperty getPasswordProperty(){
        return passwordProperty;
    }

    public boolean validate(){
    boolean logged=model.logIn(usernameProperty.getValue(),passwordProperty.getValue());
    System.out.println(usernameProperty.getValue()+",,, "+ passwordProperty.getValue());
        if(logged){
            return true;
        }
        errorPropety.setValue("Incorrect username or password.");
        return false;
    }

    public void reset() {
        usernameProperty.setValue("");
        passwordProperty.setValue("");
    }
}
