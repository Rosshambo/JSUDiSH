package edu.jsu.dish;
import java.sql.*;
import java.io.*;

public class database { 
  
  public Connection conn = null;
  public int getesc, validation = 0;
  public static int iid;
  public int cat;
  public String dbhost, dbuser, dbpass, dbname, users, queries, qcode;
  private Experiment experiment;
  
  
    //CONNECT TO THE DATABASE
 public void connect() { 
        try { 
			 
			  //Tony's DB information.
			/*
			   String userName = "root";
			   String password = "123a456s";
			   String url = "jdbc:mysql://localhost/dish";
			*/
			   //Use this if db4free is up!
			   
			   //READ DATABASE SETTINGS FROM THE DBCONFIG.INI FILE
				try{
				FileInputStream fstream = new FileInputStream("c:/dbconfig.ini");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String[] strLine;
				String line;
				
				//GET THE DBHOST
				strLine = br.readLine().split("=");
				dbhost = strLine[1];

				//GET THE DBUSERNAME
				strLine = br.readLine().split("=");
				dbuser = strLine[1];

				//GET THE DBPASS
				strLine = br.readLine().split("=");
				dbpass = strLine[1];

				//GET THE DBNAME
				strLine = br.readLine().split("=");
				dbname = strLine[1];				
			
			
				in.close();
				}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
				}
				//----------------------------------------------------------------------------------------

			   String userName = dbuser;
			   String password = dbpass;
			   String url = "jdbc:mysql://" + dbhost + "/" + dbname;
			 
			   Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			   conn = DriverManager.getConnection (url, userName, password);


			   //PRINT OUT CONNECTION CONFIRMATION
			   //System.out.println ("Database connection established");

        }
		catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
    } 
 //END OF DATABASE CONNECTION
 //************************************************************************************************************
 
 
 //***********************************************************************************************************
 //INSERT QUERY INTO DATABASE
 public void insert (String q){
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
 public int getLastId(String tn)
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
 public String getUsers(){
   
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
 public String getSelectedUser(String u){
   
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
 public String getQueries(){
   
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
 public String getSelectedQuery(String q){
   
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
       
   } 
   catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
           
           return qcode;
   
 }
 //END OF RETRIEVE SELECTED QUERY INFO
 //*************************************************************************************************************
 
 
 //***********************************************************************************************************
 //VERIFY USER LOGIN & STATUS
 public int verifyLogin(String u, String p){
   String user = u;
   String pass = p;
   
   
   //CONNECT TO THE DATABASE
   connect();
   
   //RETRIEVE THE DATA
   try { 
     
     validation = -1;
     
       Statement stmt = conn.createStatement(); 
       ResultSet rs; 
       rs = stmt.executeQuery("SELECT id, username, password, privilege FROM investigator WHERE username = '" + user + "'"); 
       while ( rs.next() ) { 
			String idd = rs.getString("id");
			String username = rs.getString("username"); 
			String password = rs.getString("password"); 
			String privilege = rs.getString("privilege"); 
			
           if (user.equals(username) && pass.equals(password)){
				if(privilege.equals("1")){
					validation = 1;
					iid = Integer.parseInt(idd);
		        }
		        else if(privilege.equals("2")){
					validation = 2;
					iid = Integer.parseInt(idd);
		        }
				else if (privilege.equals("0")) {
					validation = 0;
				}
           }
       } 
   }
   catch (Exception e) { 
     System.err.println("Got an exception! "); 
     System.err.println(e.getMessage()); 
   } 
   
   return validation;

 }
 //END OF VERIFY USER LOGIN & STATUS
 //*************************************************************************************************************
 
 public void insertIntoDB(Experiment exp) {
	experiment = exp;
	
	//EXPERIMENT ID
	int expid = getLastId("experiment")+1;
	
	//PARTICIPANT ID
	int participantid = getLastId("participant")+1;
	
	//Logan this is how you need to get the information
	//experiment.get'CLASS IT'S LOCATED IN'().get'WHATEVER INFO YOU NEED'();
	
	//INSERT EXPERIMENT
	insert("INSERT INTO experiment VALUES('" + expid + "','" + 
	experiment.getTitle() + "')");
	
	//INSERT PARTICIPANT INFORMATION
	insert("INSERT INTO participant VALUES('" + participantid + "','" + 
	experiment.getParticipantInformation().getFirstName() + "','" + 
	experiment.getParticipantInformation().getLastName() + "','" + 
	experiment.getParticipantInformation().getAge() + "','" + 
	experiment.getParticipantInformation().getGenderIndex() + "','" + 
	experiment.getParticipantInformation().getRaceIndex() + "','" + 
	experiment.getParticipantInformation().getSchoolClassIndex() + "','" + 
	experiment.getParticipantInformation().getId() + "','" + 
	experiment.getParticipantInformation().getCourse() + "','" + 
	experiment.getParticipantInformation().getStartTime() + "','" +  
	experiment.getParticipantInformation().getEndTime() + "','" +  
	expid + "')");
	
	int methodLength = experiment.getNumMethods();
	//INSERT DATA INTO THE METHOD
	for (int i = 0; i < methodLength; i++) {
		if (experiment.getMethod(i).getMethodType() == Method.Methods.REAL_TIME) {
			System.out.println("real time");
			
			RealTime rttemp = (RealTime)experiment.getMethod(i);
			
			int rtescape = 0;
			if(rttemp.getEscape()==false){rtescape=0;}
			else if(rttemp.getEscape()==true){rtescape=1;}
			int rtruler = 0;
			if(rttemp.getRuler()==false){rtruler=0;}
			else if(rttemp.getRuler()==true){rtruler=1;}
			
			
			int mid = getLastId("method")+1;
			
			//INSERT DOUBLE LIMIT METHOD INTO METHOD TABLE
			insert("INSERT INTO method VALUES('" + mid + "','" +
			experiment.getMethod(i).getQuestion() + "','" + 
			experiment.getMethod(i).getRewardType() + "','" + 
			experiment.getMethod(i).getMinReward() + "','" + 
			experiment.getMethod(i).getMaxReward() + "','" + 
			experiment.getMethod(i).getLaterTime() + "','" + 
			laterTimeUnit(experiment.getMethod(i).getLaterTimeUnits().toString()) + "','" + 
			experiment.getMethod(i).getIntertrialInterval() + "','" + 
			methodTypeInt(experiment.getMethod(i).getMethodType().toString()) + "','" +
			"0','0.0','0','" + 
			responseDel(experiment.getMethod(i).getResponseDelay().toString()) + "','" +
			rttemp.getPointDistType() + "','" + 
			rtescape + "','" +
			rtruler + "')");
			
			//INSERT DOUBLE LIMIT METHOD INTO PARTICIPANT-METHOD TABLE
			insert("INSERT INTO participantmethod VALUES('" + Integer.toString(getLastId("participantmethod")+1) + "','" +
			experiment.getMethod(i).getTotalTimeElapsed() + "','" + 
			indiffPoint(experiment.getMethod(i).getIndifferencePoint().toString()) + "','" + 
			"0','0','" +
			rttemp.getWordsRead() + "','" + 
			rttemp.getNumStop()+ "','" + 
			rttemp.getWordsSkipped() + "','" + 
			mid + "','" + 
			participantid + "')");			
			
			insert("INSERT INTO experimentmethod VALUES('" + Integer.toString(getLastId("experimentmethod")+1) + "','" +
			expid + "','" + 
			mid + "')");
			
			System.out.println("----------------------------------------------------------");
			insert("INSERT INTO investigatorexperiment VALUES('" + Integer.toString(getLastId("investigatorexperiment")+1) + "','" +
			iid + "','" + 
			expid + "')");			
			System.out.println("-----------------------------------------------------------");
			
			
		}
		else if (experiment.getMethod(i).getMethodType() == Method.Methods.DOUBLE_LIMIT) {
			System.out.println("double limit");

			DoubleLimit dltemp = (DoubleLimit)experiment.getMethod(i);
			
			int dlindex = 0;
			if(dltemp.getDoubleDelay()==false){dlindex=0;}
			else if(dltemp.getDoubleDelay()==true){dlindex=1;}
			
			int mid = getLastId("method")+1;
			
			//INSERT DOUBLE LIMIT METHOD INTO METHOD TABLE
			insert("INSERT INTO method VALUES('" + mid + "','" +
			experiment.getMethod(i).getQuestion() + "','" + 
			experiment.getMethod(i).getRewardType() + "','" + 
			experiment.getMethod(i).getMinReward() + "','" + 
			experiment.getMethod(i).getMaxReward() + "','" + 
			experiment.getMethod(i).getLaterTime() + "','" + 
			laterTimeUnit(experiment.getMethod(i).getLaterTimeUnits().toString()) + "','" + 
			experiment.getMethod(i).getIntertrialInterval() + "','" + 
			methodTypeInt(experiment.getMethod(i).getMethodType().toString()) + "','" +
			dlindex + "','0.0','0','" + 
			responseDel(experiment.getMethod(i).getResponseDelay().toString()) + "','" +
			"0','0','0')");
			
			//INSERT DOUBLE LIMIT METHOD INTO PARTICIPANT-METHOD TABLE
			insert("INSERT INTO participantmethod VALUES('" + Integer.toString(getLastId("participantmethod")+1) + "','" +
			experiment.getMethod(i).getTotalTimeElapsed() + "','" + 
			indiffPoint(experiment.getMethod(i).getIndifferencePoint().toString()) + "','" + 
			"0','" + 
			dltemp.getNumScenarios() + "','0','0','0','" + 
			mid + "','" + 
			participantid + "')");
			
			insert("INSERT INTO experimentmethod VALUES('" + Integer.toString(getLastId("experimentmethod")+1) + "','" +
			expid + "','" + 
			mid + "')");
			
		}
		else if (experiment.getMethod(i).getMethodType() == Method.Methods.DECREASING_ADJUSTMENT) {
			System.out.println("decreasing adjustment");
			DecreasingAdjustment datemp = (DecreasingAdjustment)experiment.getMethod(i);

			int daindex =0;
			if(datemp.getDoubleDelay()==false){daindex=0;}
			else if(datemp.getDoubleDelay()==true){daindex=1;}
			
			int mid = getLastId("method")+1;
			
			insert("INSERT INTO method VALUES('" + mid + "','" +
			experiment.getMethod(i).getQuestion() + "','" + 
			experiment.getMethod(i).getRewardType() + "','" + 
			experiment.getMethod(i).getMinReward() + "','" + 
			experiment.getMethod(i).getMaxReward() + "','" + 
			experiment.getMethod(i).getLaterTime() + "','" + 
			laterTimeUnit(experiment.getMethod(i).getLaterTimeUnits().toString()) + "','" + 
			experiment.getMethod(i).getIntertrialInterval() + "','" + 
			methodTypeInt(experiment.getMethod(i).getMethodType().toString()) + "','" +
			daindex + "','0.0','0','" + 
			responseDel(experiment.getMethod(i).getResponseDelay().toString()) + "','0" + 
			"','0','0')");
			
			//INSERT DECREASING ADJUSTMENT METHOD INTO PARTICIPANT-METHOD TABLE
			insert("INSERT INTO participantmethod VALUES('" + Integer.toString(getLastId("participantmethod")+1) + "','" +
			experiment.getMethod(i).getTotalTimeElapsed() + "','" + 
			indiffPoint(experiment.getMethod(i).getIndifferencePoint().toString()) + "','" + 
			"0','" + 
			datemp.getNumScenarios() + "','0','0','0','" + 
			mid + "','" + 
			participantid + "')");
			
			insert("INSERT INTO experimentmethod VALUES('" + Integer.toString(getLastId("experimentmethod")+1) + "','" +
			expid + "','" + 
			mid + "')");
			
		}
		else if (experiment.getMethod(i).getMethodType() == Method.Methods.MULTIPLE_CHOICE) {
			System.out.println("multiple choice");
			
			MultipleChoice temp = (MultipleChoice)experiment.getMethod(i);
			if(temp.getEscape() == true){getesc = 1;}
			else if(temp.getEscape() == false){getesc = 0;}
			
			int mid = getLastId("method")+1;
			
			insert("INSERT INTO method VALUES('" + mid + "','" +
			experiment.getMethod(i).getQuestion() + "','" + 
			experiment.getMethod(i).getRewardType() + "','" + 
			experiment.getMethod(i).getMinReward() + "','" + 
			experiment.getMethod(i).getMaxReward() + "','" + 
			experiment.getMethod(i).getLaterTime() + "','" + 
			laterTimeUnit(experiment.getMethod(i).getLaterTimeUnits().toString()) + "','" + 
			experiment.getMethod(i).getIntertrialInterval() + "','" + 
			methodTypeInt(experiment.getMethod(i).getMethodType().toString()) + "','" +
			"0','0.0','0','" + 
			responseDel(experiment.getMethod(i).getResponseDelay().toString()) + "','" +
			temp.getPointDistributionType() + "','" +
			getesc + "','0')");
			
			//INSERT MULTIPLE CHOICE METHOD INTO PARTICIPANT-METHOD TABLE
			insert("INSERT INTO participantmethod VALUES('" + Integer.toString(getLastId("participantmethod")+1) + "','" +
			experiment.getMethod(i).getTotalTimeElapsed() + "','" + 
			indiffPoint(experiment.getMethod(i).getIndifferencePoint().toString()) + "','" + 
			"0','" + 
			temp.getNumScenarios() + "','0','0','0','" + 
			mid + "','" + 
			participantid + "')");			

			insert("INSERT INTO experimentmethod VALUES('" + Integer.toString(getLastId("experimentmethod")+1) + "','" +
			expid + "','" + 
			mid + "')");			
			
		}
	}
/*	insert("INSERT INTO method VALUES('" + Integer.toString(getLastId("method")+1) + ",'" +
	experiment.getMethod().getQuestion() + "','" + 
	experiment.getMethod().getRewardType() + "','" + 
	experiment.getMethod().getMinReward() + "','" + 
	experiment.getMethod().getMaxReward() + "','" + 
	experiment.getMethod().getLaterTimeUnits() + "','" + 
	experiment.getMethod().getIntertrialInterval() + "','" + 
	experiment.getMethod().getMethodType() + "','" + 
	experiment.getDoubleLimit().getDoubleDelay() + "'," + 
	"'',''" + ",'" + 
	experiment.getMethod().getResponseDelay() + "','" + 
	experiment.getRealTime().getPointDistType() + "','" + 
	experiment.getRealTime().getEscape() + "','" + 
	experiment.getRealTime().getRuler() + "')");
*/	
	//System.out.println(experiment.getParticipantInformation().getLastName());
 }
 
	public float indiffPoint(String indpt)
	{
		float indiff = 0;
		String indef = indpt;
		String[] indifference;
		indifference = indef.split(" ");
		
		indiff = Float.parseFloat(indifference[0]);
		
		return indiff;	
	}
 
	public float responseDel(String respd)
	{
	float responsetime = 0;
	String resp = respd;
	if(resp!="now"){
		String[] response;
		response = resp.split(" ");
		
		
		if(response[1].equals("seconds")){responsetime = Float.parseFloat(response[0]);} 
		else if(response[1].equals("minutes")){responsetime = Integer.parseInt(response[0]) * 60;} 
		else if(response[1].equals("hours")){responsetime = Integer.parseInt(response[0]) * 60 * 60;} 
		else if(response[1].equals("days")){responsetime = Integer.parseInt(response[0]) * 60 * 60 * 24;} 
		else if(response[1].equals("weeks")){responsetime = Integer.parseInt(response[0]) * 60 * 60 * 24 * 7;} 
		else if(response[1].equals("months")){responsetime = Integer.parseInt(response[0]) * 60 * 60 * 24 * 7 * 4;} 
		else if(response[1].equals("years")){responsetime = Integer.parseInt(response[0]) * 60 * 60 * 24 * 7 * 12;} 
		else if(response[1].equals("now")){responsetime = 0;} 
	} else{ responsetime = 0; }
	
	return responsetime;
	}
 
	public int laterTimeUnit(String ltu)
	{
	String latertimeunit=ltu;
	int LaterTUnit=0;
	
	if(latertimeunit.equals("SECONDS")){LaterTUnit=1;}
	else if(latertimeunit.equals("MINUTES")){LaterTUnit=2;}
	else if(latertimeunit.equals("HOURS")){LaterTUnit=3;}
	else if(latertimeunit.equals("DAYS")){LaterTUnit=4;}
	else if(latertimeunit.equals("WEEKS")){LaterTUnit=5;}
	else if(latertimeunit.equals("MONTHS")){LaterTUnit=6;}
	else if(latertimeunit.equals("YEARS")){LaterTUnit=7;}
	
	return LaterTUnit;
	}
	
	public int methodTypeInt(String mti)
	{
	String methodtypeinteger=mti;
	int methtiu=0;
	
	//1=DL, 2=DA, 3=MC, 4=MC-C, 5=MC-Esc, 6=RT
	
	if(methodtypeinteger.equals("DOUBLE_LIMIT")){methtiu=1;}
	else if(methodtypeinteger.equals("DECREASING_ADJUSTMENT")){methtiu=2;}
	else if(methodtypeinteger.equals("MULTIPLE_CHOICE")){methtiu=3;}
	else if(methodtypeinteger.equals("DAYS")){methtiu=4;}
	else if(methodtypeinteger.equals("WEEKS")){methtiu=5;}
	else if(methodtypeinteger.equals("REAL_TIME")){methtiu=6;}
	
	return methtiu;
	}
	

   // Convert password to string*********************************************************
        public String convertPassword(char[] cPassword)
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
	public void main(String[] args) {
    
	}
}