package Model.Domain;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
public Database(String password){

    try{
        Class.forName("org.postgresql.Driver");
    }
    catch (Exception e){
        System.out.println(e.getMessage());
    }
    // Establishing a connection
    try {
        String databaseUrl = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";

        this.connection = DriverManager.getConnection(databaseUrl, user, password);
        System.out.println("Connection established to: " + databaseUrl);
    }
    catch (Exception exception) {
        System.out.println("Connection failed");
        exception.printStackTrace();
    }
}

    public void insertBooking(Booking book){
    try{
        System.out.println(book.getGuest().getName()+" "+book.getNo());
    statement = connection.createStatement();
        String sql = "INSERT INTO sepdb.Guest (guestName, phone) VALUES ('"+book.getGuest().getName()+"',"+book.getGuest().getPhone()+")";
        statement.execute(sql);
        sql = "INSERT INTO sepdb.booking (bookingno,guestname, datefrom, dateto) VALUES ("+book.getNo()+",'"+book.getGuest().getName()+"', '"+book.getD1().toString()+"', '"+book.getD2().toString()+"')";
        statement.execute(sql);
        for(int i=0;i<book.getRooms().size();i++) {
            sql = "INSERT INTO sepdb.Roomispartofbooking(bookingno,roomno) Values ("+book.getNo()+","+book.getRooms().get(i).getNr()+")";
            statement.execute(sql);
        }
        System.out.println("A booking for "+book.getGuest().getName()+" was added with number "+book.getNo());
    }
    catch (Exception e){e.printStackTrace();
    System.out.println("this---->"+e.getMessage());
    }
    }

public ArrayList<Guest> getGuests(){
    ArrayList<Guest> list= new ArrayList<>();
    try {

        statement = connection.createStatement();
        String sql = "select * from sepdb.guest";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
        Guest g= new Guest(resultSet.getString(1),resultSet.getInt(2));
        list.add(g);
        }
    } catch (Exception e) {
    }
    return list;
    }

    public ArrayList<Room> getRooms(){
        ArrayList<Room> list= new ArrayList<>();
        try {

            statement = connection.createStatement();
            String sql = "select * from sepdb.room";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Room g= new Room(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3));
                list.add(g);
            }
        } catch (Exception e) {
        }
        return list;
    }


    public ArrayList<Booking> getBookings(ArrayList<Guest> guests,ArrayList<Room> rooms) {
        ArrayList<Booking> bookings= new ArrayList<>();
    if(!(guests.isEmpty())){
        try {
            statement = connection.createStatement();
            String sql = "select * from sepdb.booking";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                String[] token = resultSet.getObject(3).toString().split("-");
                int z= Integer.parseInt(token[0]);
                int c= Integer.parseInt(token[1]);
                int v= Integer.parseInt(token[2]);

                LocalDate d1= LocalDate.of(z,c,v);
                String[] token2 = resultSet.getObject(4).toString().split("-");
                int z2= Integer.parseInt(token2[0]);
                int c2= Integer.parseInt(token2[1]);
                int v2= Integer.parseInt(token2[2]);

                LocalDate d2= LocalDate.of(z2,c2,v2);
                int booknr=resultSet.getInt(1);

            Booking b= new Booking(booknr,d1,d2);

            for(int j=0;j<guests.size();j++){
                if(guests.get(j).getName().equals(resultSet.getString(2)))
                    b.setGuest(guests.get(j));
            }

            sql = "select * from sepdb.roomispartofbooking where bookingno=" +booknr;
             ResultSet resultSet2 = statement.executeQuery(sql);
             ArrayList<Room> roomlist= new ArrayList<>();
                while(resultSet2.next()){

                    for(int i=0 ; i<rooms.size();i++){
                        if(rooms.get(i).getNr()==resultSet2.getInt(2))
                            roomlist.add(rooms.get(i));
                    }
                }
                b.setRooms(roomlist);
            bookings.add(b);

            b=null;
            }
        } catch (Exception e) {
        }
    }
    return bookings;
    }


public void update(){
    ArrayList<String> names=new ArrayList<>();
    try{
        statement = connection.createStatement();
        String sql = "Select guestname from sepdb.booking where dateto=CURRENT_DATE";

        resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            sql = "DELETE FROM sepdb.guest WHERE guestname='" + resultSet.getString(1) + "'";
            resultSet = statement.executeQuery(sql);
        /*    resultSet = statement.executeQuery(sql);
                names.add(resultSet.getString(1));
                System.out.println(names);
            System.out.println();
        }
        sql = "delete from sepdb.booking where dateto=CURRENT_DATE";
        resultSet = statement.executeQuery(sql);
        System.out.println(resultSet);

        for (int i = 0; i <=names.size(); i++) {
            sql = "DELETE FROM sepdb.guest WHERE guestname='"+ names.get(i)+"'";
            resultSet = statement.executeQuery(sql);
        }

         */
        }  }
    catch (Exception e){System.out.println(e.getMessage());}
}


public int[] getAvRooms(LocalDate d1, LocalDate d2){
    int[] rooms=new int[50];
    int k=1;
    try{
    statement = connection.createStatement();
    String sql = "SELECT r.roomno FROM sepdb.Roomispartofbooking rb\n" +
            "            Full JOIN sepdb.Room r ON r.roomno=rb.roomno\n" +
            "            Full JOIN sepdb.Booking b ON b.bookingno=rb.bookingno\n" +
            "            WHERE (date '"+d1.toString()+"'> dateto\n" +
            "                       OR date '"+d2.toString()+"' < datefrom or datefrom is null)\n" +
            "   order by r.roomno";

    resultSet = statement.executeQuery(sql);
         ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    int columnCount = resultSetMetaData.getColumnCount();
    // Rows
        int p=0;
    while (resultSet.next()) {
           rooms[p]= resultSet.getInt(1);
           p++;
    }
}
		catch (SQLException exception) {
        exception.printStackTrace();
    }
    System.out.println("in databaseeeeeeeeeeee");
    System.out.println(" "+rooms[0]+" "+rooms[1]+" "+rooms[3]+" "+rooms.length);
    return rooms;
}
}
