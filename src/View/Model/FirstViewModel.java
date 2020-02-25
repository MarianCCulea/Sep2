package View.Model;

import Model.Domain.Room;
import Model.Mediator.Model;
import Model.Mediator.ModelManager;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class FirstViewModel {
    private Model model;
    private StringProperty errorLabel;
    private StringProperty price;
    private StringProperty priceLabel;
    private StringProperty type;
    private StringProperty roomnr;
    private StringProperty adultproperty;
    private boolean needsupdate;
    private Double roomPrice;
    private Double adult;
    private int roomnumber;
    private String pricee;


    public FirstViewModel(ModelManager model) {
        this.model = model;
        this.errorLabel = new SimpleStringProperty();
        this.priceLabel = new SimpleStringProperty();
        this.needsupdate= false;
        errorLabel.setValue("");
    }

    public FirstViewModel(int nr,String typ,Double price) {
        this.price=new SimpleStringProperty(price.toString());
        this.roomnr=new SimpleStringProperty(nr+"");
        this.type=new SimpleStringProperty(typ);
        Double adult = toAdult(typ);
        this.adultproperty=new SimpleStringProperty(isAdult(adult));
        this.adult=adult;
        this.roomPrice=price;
        this.roomnumber=nr;
    }

    public String getPricee(){return pricee;}
    public boolean getNeedsupdate(){return needsupdate;}
    public StringProperty errorProperty() {
        return errorLabel;
    }
    public StringProperty priceProperty() {
        return priceLabel;
    }
    public int getRoomnumber() {
        return roomnumber;
    }
    public double getPrice(){
        return roomPrice;
    }

    public StringProperty getAdultproperty(){
        return adultproperty;
    }
    public StringProperty getPriceProperty(){
        return price;
    }
    public StringProperty getTypeProperty() {
        return type;
    }
    public StringProperty getRoomNrProperty() {
        return roomnr;
    }

    private Double toAdult(String type){
        switch(type){
            case "Single":return 1.0;
            case "Double":return 2.0;
            case "Family":return 2.01;
            case"Suite":return 4.0;
        }
        return null;
    }

    private String isAdult(Double a){
        switch(a.toString()){
            case "2.0":return "2";
            case "1.0":return "1";
            case "4.0":return "4";
            case "2.01":return "2 + 1 kid";
        }
        return null;
    }

    public Double getAdult() {
        return adult;
    }

    public void sumPrice(ObservableList<FirstViewModel> obs, LocalDate in, LocalDate out) {
        errorLabel.setValue("");
        Double sum=0.0;
        Double adults=0.00;
        for(int i=0;i<obs.size();i++){
            sum+=obs.get(i).getPrice();
            adults+=obs.get(i).getAdult();
        }
        if(out.getDayOfYear()>=in.getDayOfYear())
        sum=sum*(out.getDayOfYear()-in.getDayOfYear());
        else{


            int o=0;
            LocalDate asdf=LocalDate.of(in.getYear(),in.getMonthValue(),in.getDayOfMonth());
            while (asdf.isBefore(out)){
                asdf=asdf.plusDays(1);
                o++;
            }
            sum=sum*o;
        }
        Double b=adults*100%100;
        String a=sum.floatValue()+" for "+adults.intValue()+" adults and "+(int)b.floatValue()+" kids.";
        this.pricee=a;
        priceLabel.setValue(a);
        System.out.println(a);


    }

    public boolean check(LocalDate value, LocalDate value1){
        if(value.isBefore(value1)){
            errorLabel.setValue("");
            return true;
        }else if(value.isBefore(LocalDate.now())){
            errorLabel.setValue("Make sure the dates are not in the past.");
            return false;
        }else if(value.isBefore(LocalDate.now())){
            errorLabel.setValue("Make sure the dates are not in the past.");
            return false;
        }else{errorLabel.setValue("Make sure check out-date is after check-in date.");
            return false;}
    }

    public ArrayList<Room> getAvRooms(LocalDate value, LocalDate value1) {
      if(value.isBefore(value1)){
            errorLabel.setValue("");
            needsupdate=false;
           return model.getAvRooms(value,value1);

        }else if(value.isBefore(LocalDate.now())){
            errorLabel.setValue("Make sure the dates are not in the past.");
            return null;
        }else if(value1.isBefore(LocalDate.now())){
          errorLabel.setValue("Make sure the dates are not in the past.");
          return null;
      }else{errorLabel.setValue("Make sure check out-date is before check-in date.");
            return null;}

    }

    public void update(){
        errorLabel.setValue("Please update the list in order to continue.");
        needsupdate=true;
    }
    public void error(String s) {
        errorLabel.setValue(s);
    }
}
