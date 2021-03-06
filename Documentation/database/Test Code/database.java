import java.sql.*;

class database { 
  
  public static Connection conn = null;
  public static int validation = 0;
  public static int cat;
  public static String users;
  public static String queries;
  public static String qcode;
  
  //**********************************************************************************************************
  public static void main(String[] args)
  {
    
  }
  //**********************************************************************************************************
  
  
  //***********************************************************************************************************
    //CONNECT TO THE DATABASE
 public static void connect() { 
        try { 
  

   String userName = "dish";
   String password = "fundop";
   String url = "jdbc:mysql://db4free.net:3306/dish";
   Class.forName ("com.mysql.jdbc.Driver").newInstance ();
   conn = DriverManager.getConnection (url, userName, password);


   //PRINT OUT CONNECTION CONFIRMATION
   //System.out.println ("Database connection established");

        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
    } 
 //END OF DATABASE CONNECTION
 //************************************************************************************************************
 
 
 //***********************************************************************************************************
 //INSERT QUERY INTO DATABASE
 public static void insert (String q){
   String query = q;
   
   //CONNECT TO THE DATABASE
   connect();
   
   try { 
       Statement stmt = conn.createStatement(); 
       stmt.executeUpdate(query);
       System.out.println(query + "Executed Successfully");
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
   
 }
 //END OF INSERT QUERY
 //*************************************************************************************************************
 
 //*************************************************************************************************************
 //GET LAST INSERTED ID
 public static int getLastId(String tn)
 {
   String tablename = tn;
     
   //CONNECT TO THE DATABASE
   connect();
   
   //RETRIEVE THE DATA
   try { 
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT * FROM " + tablename); 
       while ( rs.next() ) { 
           String id = rs.getString("id");
           cat = Integer.parseInt(id);
           
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 

   return cat;
 }
 //*************************************************************************************************************
 
 

 
  //***********************************************************************************************************
 //RETRIEVE USER INFO FROM DATABASE
 public static String getUsers(){
   
   //CONNECT TO THE DATABASE
   connect();
   
   //CLEAR THE USERS
   users = "";
   
   //RETRIEVE THE DATA
   try { 
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT * FROM investigator"); 
       
       while ( rs.next() ) { 
           String id = rs.getString("id");
           String username = rs.getString("username"); 
           String password = rs.getString("password"); 
           String first_name = rs.getString("first_name"); 
           String last_name = rs.getString("last_name"); 
           String department = rs.getString("department"); 
           String privilege = rs.getString("privilege"); 
           
           users = users + username +  ",";
           
           
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
           users = users.substring(0,users.length()-1);

           return users;
   
 }
 //END OF RETRIEVE USER INFO
 //*************************************************************************************************************
 
 
   //***********************************************************************************************************
 //RETRIEVE SELECTED USER INFO FROM DATABASE
 public static String getSelectedUser(String u){
   
   String suser = u;
   
   //CONNECT TO THE DATABASE
   connect();
   
   //CLEAR THE USERS
   users = "";
   
   //RETRIEVE THE DATA
   try { 
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT * FROM investigator WHERE username = '" + suser + "'"); 
       
       while ( rs.next() ) { 
           String id = rs.getString("id");
           String username = rs.getString("username"); 
           String password = rs.getString("password"); 
           String first_name = rs.getString("first_name"); 
           String last_name = rs.getString("last_name"); 
           String department = rs.getString("department"); 
           String privilege = rs.getString("privilege");            
           
           users = id + "," + username + "," + password + "," + first_name + "," + last_name + "," + department + "," + privilege;
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 

   System.out.println(users);
           return users;
   
 }
 //END OF RETRIEVE SELECTED USER INFO
 //*************************************************************************************************************
 
 //***********************************************************************************************************
 //RETRIEVE QUERY INFO FROM DATABASE
 public static String getQueries(){
   
   //CONNECT TO THE DATABASE
   connect();
   
   //CLEAR THE USERS
   queries = "";
   
   //RETRIEVE THE DATA
   try { 
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT * FROM querylibrary"); 
       
       while ( rs.next() ) { 
           String id = rs.getString("id");
           String queryname = rs.getString("queryname"); 
           String querycode = rs.getString("querycode"); 
           
           queries = queries + queryname +  ",";
           
           
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
           queries = queries.substring(0,queries.length()-1);

           return queries;
   
 }
 //END OF RETRIEVE QUERY INFO
 //*************************************************************************************************************
 
 
  //***********************************************************************************************************
 //RETRIEVE SELECTED QUERY INFO FROM DATABASE
 public static String getSelectedQuery(String q){
   
   String queryn = q;
   
   //CONNECT TO THE DATABASE
   connect();
   
   //CLEAR THE USERS
   queries = "";
   
   //RETRIEVE THE DATA
   try { 
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT * FROM querylibrary WHERE queryname = '" + queryn + "'"); 
       
       while ( rs.next() ) { 
           String id = rs.getString("id");
           String queryname = rs.getString("queryname"); 
           String querycode = rs.getString("querycode");  
           
           qcode = querycode;
           System.out.println(qcode);
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
           
           return qcode;
   
 }
 //END OF RETRIEVE SELECTED QUERY INFO
 //*************************************************************************************************************
 
 
 //***********************************************************************************************************
 //VERIFY USER LOGIN & STATUS
 public static int verifyLogin(String u, String p){
   String user = u;
   String pass = p;
   
   //CONNECT TO THE DATABASE
   connect();
   
   //RETRIEVE THE DATA
   try { 
     
     validation = 0;
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT username, password, privilege FROM investigator WHERE username = '" + user + "'"); 
       while ( rs.next() ) { 
           String username = rs.getString("username"); 
           String password = rs.getString("password"); 
           String privilege = rs.getString("privilege"); 
           
           if (user.equals(username)){
             if (pass.equals(password)){
             if(privilege.equals("1")){
             validation = 1;
             }
             if(privilege.equals("2")){
               validation = 2;
             }
             }
           }
           
       } 
       
   } catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
   
   return validation;

 }
 //END OF VERIFY USER LOGIN & STATUS
 //*************************************************************************************************************
 
 
   // Convert password to string*********************************************************
        public static String convertPassword(char[] cPassword)
        {
                // Declare variables
                String strRet = new String("");
                
                // Go through each character
                for (int i = 0; i < cPassword.length; i++)
                {
                        strRet += cPassword[i];
                }
                
                return strRet;
        }
        //********************************************************************************
}