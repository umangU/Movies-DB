import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class  movie_operations
{
    public static void main(String [] argv) throws Exception
    {
        Connection conn = null;
        // Create a new InputStreamReader and connecting to STDIN
        InputStreamReader istream = new InputStreamReader(System.in);
        // Create a new BufferedReader and connect it to the InputStreamReader
        BufferedReader bufRead = new BufferedReader(istream);
        //Create a new Scanner to take in the inputs
        Scanner scanner = new Scanner(System.in);
        //Creating a SimpleDateFormat instance and assigning a pattern to parse the user-input
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Creating a Java.Sql.Date object to take date input
        java.sql.Date sqlDate;
        System.out.println("******************************************************************");
        System.out.println("\n\n\t\t\t Movie Direct\n\n");
        System.out.println("******************************************************************");
        //Declaring and initializing variables with empty values
        String database = "";
        String user = "";
        String pass = "";
        int password = -1;
        try
        {
          //Asking user for the database details and required username and password to connect
          System.out.print("\n");
          System.out.print("Database: ");
          database = bufRead.readLine();
          System.out.print("User: ");
          user = bufRead.readLine();
          System.out.print("Password: ");
          pass = bufRead.readLine();
          password = Integer.parseInt(pass);
        }
        //Catch block to handle Input/Output and Formatting Exceptions
        catch (IOException err)
        {
          System.out.println(err);
        }
        catch(NumberFormatException err)
        {
          System.out.println(err);
        }

        try
        {
          //Connecting the database using the username and password provided by the user
          Class.forName("org.postgresql.Driver");
          String url = "jdbc:postgresql://localhost/"+database;
          conn = DriverManager.getConnection(url,user,pass);
        }
        //Catch block to handle ClassNotFoundException and SQLExceptions
        catch (ClassNotFoundException e)
        {
          e.printStackTrace();
          System.exit(1);
        }
        catch (SQLException e)
        {
          e.printStackTrace();
          System.exit(2);
        }

        //Global variables declared and initialized
        String mid;
        Integer mid_int = -1;
        String mtitle= "";
        String dfname= "";
        String dlname= "";
        String genre = "";
        String mediatype = "";
        String release_date;
        Date date2=null;
        String studio = "";
        String retail_price;
        Double retail_flo = 0.0;
        String copies;
        Integer copies_int = -1;
        boolean var = false;
        boolean check = false;

        //While Loop to prompt user to re-enter the information in case SQLException
        while(var == false)
        {
          try
          {
            //Asking for user inputs and prompting to re-enter the information in case of empty string
            System.out.print("\n");
            System.out.print("Please enter the id for the new movie: ");
            mid = bufRead.readLine();
            mid_int = Integer.parseInt(mid);
            while(mid == null)
            {
              System.out.print("Please enter the id for the new movie: ");
              mid = bufRead.readLine();
              mid_int = Integer.parseInt(mid);
            }
            System.out.print("Please enter the title for the new movie: ");
            mtitle = bufRead.readLine();
            while(mtitle.isEmpty()){
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the title for the new movie: ");
              mtitle = bufRead.readLine();
            }
            System.out.print("Please enter the director's first name: ");
            dfname = bufRead.readLine();
            while(dfname.isEmpty()){
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the director's first name: ");
              dfname = bufRead.readLine();
            }
            System.out.print("Please enter the director's last name: ");
            dlname = bufRead.readLine();
            while(dlname.isEmpty()){
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the director's last name: ");
              dlname = bufRead.readLine();
            }
            System.out.print("Please enter the genre of the movie: ");
            genre = bufRead.readLine();
            while(genre.isEmpty()){
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the genre of the movie: ");
              genre = bufRead.readLine();
            }
            System.out.print("Please enter the media type: ");
            mediatype = bufRead.readLine();
            while(mediatype.isEmpty()){
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the media type: ");
              mediatype = bufRead.readLine();
            }
            System.out.print("Please enter the movies's release date: ");
            release_date = scanner.next();
            date2 = java.sql.Date.valueOf(release_date);
            check = "".equals(release_date);
            while(check == true)
            {
              System.out.print("Please enter the movies's release date: ");
              release_date = scanner.next();
              date2 = java.sql.Date.valueOf(release_date);
            }
            System.out.print("Please enter the movies's studio: ");
            studio = bufRead.readLine();
            while(studio.isEmpty())
            {
              System.out.println("Please enter a value!!!");
              System.out.print("Please enter the movies's studio: ");
              studio = bufRead.readLine();
            }
            System.out.print("Please enter the retail price of the Movie: ");
            retail_price = bufRead.readLine();
            retail_flo = Double.parseDouble(retail_price);
            while(retail_price == null)
            {
              System.out.print("Please enter the retail price of the Movie: ");
              retail_price = bufRead.readLine();
              retail_flo = Double.parseDouble(retail_price);
            }
            System.out.print("Please enter the number of copies in stock: ");
            copies = bufRead.readLine();
            copies_int = Integer.parseInt(copies);
            while(copies == null)
            {
              System.out.print("Please enter the number of copies in stock: ");
              copies = bufRead.readLine();
              copies_int = Integer.parseInt(copies);
            }
         }
         //Catch Block to handle Input/Output and Number Formatting Exceptions
         catch (IOException err)
         {
           System.out.println(err);
         }
         catch(NumberFormatException err)
         {
           System.out.println(err);
         }

        Statement stmt = null;
        try
        {
         //Creating a new statement object with the additional arguments for inserting
         stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
         //Get all record in the movies table
         ResultSet uprs = stmt.executeQuery("SELECT * FROM Movies");
         //Creating a new row in the ResultSet object
         uprs.moveToInsertRow();
         //Adding new employee's information to the new row of data
         uprs.updateInt("movie_id",mid_int);
         uprs.updateString("movie_title", mtitle);
         uprs.updateString("director_last_name", dlname);
         uprs.updateString("director_first_name", dfname);
         uprs.updateString("genre", genre);
         uprs.updateString("media_type", mediatype);
         uprs.updateDate("release_date", date2);
         uprs.updateString("studio_name", studio);
         uprs.updateDouble("retail_price", retail_flo);
         uprs.updateInt("current_stock", copies_int);
         //Insert the new row of data to the database
         uprs.insertRow();
         //Move the cursor back to the start of the ResultSet object
         uprs.beforeFirst();
         var=true;
         System.out.println("\nSuccess! A new entry for "+mtitle+" has been entered into the database.");
         System.out.println();
       }
       catch (SQLException e )
       {
         System.out.println(e);
         var=false;
       }
      }
      conn.close();
      //Closing the database connection
      System.exit(1);
     }
}

