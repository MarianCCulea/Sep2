package View.Model;


import Model.Mediator.Model;
import Model.Mediator.ModelManager;
import View.View.AdminFirstView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener {
    private FirstViewModel model1;
    private LoginViewModel model3;
    private AdminFirstViewModel model4;
    private SecondViewModel model2;
    private Model modelManager;
    private BookingListViewModel model5;

    public MainViewModel(ModelManager model){
        this.modelManager=model;
        this.model1=new FirstViewModel(model);
        this.model2=new SecondViewModel(model);
        this.model3=new LoginViewModel(model);
        this.model4=new AdminFirstViewModel(model);
        this.model5= new BookingListViewModel(model);
        model.addListener(this);
    }

    public FirstViewModel getFirstViewModel(){
    return model1;
    }

    public BookingListViewModel getBookingViewModel(){return model5;}

    public SecondViewModel getSecondViewModel(){return model2;}

    public LoginViewModel getLoginViewModel() {
        return model3;
    }

    public AdminFirstViewModel getAdminFirstViewModel() {
        return model4;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
if(modelManager.isLoggedIn()){
    model4.update();
    model5.update();
}else{
    model1.update();
}
    }
}
