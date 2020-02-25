package Model.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomList implements Serializable {

    private ArrayList<Room> rooms;

    public RoomList(){
        rooms=new ArrayList<>();
    }

    public ArrayList<Room> getList(){
        return rooms;
    }
    public void setList(ArrayList<Room> roomlist){
        this.rooms=roomlist;
    }

    public ArrayList<Room> getFreeRooms(int[] room){
        ArrayList<Room> free=new ArrayList<>();
        int i=0;
        while (room[i]!=0){
            for(int j=0;j<rooms.size();j++){
                if(room[i]==rooms.get(j).getNr()){
                    free.add(rooms.get(j));
                }
            }
            i++;
        }



        return free;
    }


}
