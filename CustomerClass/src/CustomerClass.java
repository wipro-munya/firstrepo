import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class CustomerClass {
	
private int	Customer_ID;
private String	Customer_FirstName;
private String	Customer_LastName;	
private String	Customer_Address1;
private String Customer_PhoneNumber;

CustomerClass() {
	Customer_ID = 1234;
	Customer_FirstName = "john";
	Customer_LastName = "Doe";
	Customer_Address1 = "12 We work";
	Customer_PhoneNumber = "00";
}
/*
 * initiate customer details 
 * 
 * */
public void getCustomerDetails (){
	Scanner input = new Scanner(System.in);
	System.out.println("Please enter Customer First Name");
	this.Customer_FirstName = input.nextLine();
	
	System.out.println("Please enter Customer Last Name");
	this.Customer_LastName = input.nextLine();
	
	System.out.println("Please enter Address ");
	this.Customer_Address1 = input.nextLine();

	System.out.println("Please enter Customer Contact Number");
	
	this.Customer_PhoneNumber = input.nextLine();

}

/*
 * get customer details
 * */
public int getCustomer_ID() {
	return Customer_ID;
}

/*
 * set customer details
 * */
public void setCustomer_ID(int customer_ID) {
	Customer_ID = customer_ID;
}

/*
 * get customer first name
 * */
public String getCustomer_FirstName() {
	return Customer_FirstName;
}

/*
 * set customer first name
 * */
public void setCustomer_FirstName(String customer_FirstName) {
	Customer_FirstName = customer_FirstName;
}

/*
 * get customer last name
 * */
public String getCustomer_LastName() {
	return Customer_LastName;
}

/*
 * set customer last name
 * */
public void setCustomer_LastName(String customer_LastName) {
	Customer_LastName = customer_LastName;
}

/*
 * get customer address
 * */
public String getCustomer_Address1() {
	return Customer_Address1;
}

/*
 * set customer address
 * */
public void setCustomer_Address1(String customer_Address1) {
	Customer_Address1 = customer_Address1;
}

/*
 * get customer details
 * */
public String getCustomer_PhoneNumber() {
	return Customer_PhoneNumber;
}
public void setCustomer_PhoneNumber(String customer_PhoneNumber) {
	Customer_PhoneNumber = customer_PhoneNumber;
}

public void addCustomer(int customerID){

	// TODO Auto-generated method stub
//establish communication
	Connection con = null;
	String url="jdbc:oracle:thin:@localhost:1521:orcl1";
	String uname="scott";
	String pwd="tiger";
	try {
		con =DriverManager.getConnection(url,uname,pwd);
		System.out.println("Connected");
		
		 String sql = " insert into customer values ( ?, ?, ?, ?,?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(sql);
		 preparedStmt.setInt(1, customerID);
		 preparedStmt.setString(2, this.getCustomer_FirstName());
		 preparedStmt.setString(3, this.getCustomer_LastName());
		 preparedStmt.setString(4, this.getCustomer_Address1());
		 preparedStmt.setString(5, this.getCustomer_PhoneNumber());
		 
		// execute the preparedstatement
	      preparedStmt.executeUpdate();
	      System.out.println("Done");
	      con.close();
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


	
}
public void deleteCustomer(int id){
	
	Connection con = null;
	String url="jdbc:oracle:thin:@localhost:1521:orcl1";
	String uname="scott";
	String pwd="tiger";
	try {
		con =DriverManager.getConnection(url,uname,pwd);
		System.out.println("Connected");
		 
		// Delete record
	      String sql = "delete from customer where ID = ?";
	      PreparedStatement preparedStmt = con.prepareStatement(sql);
		 
		// execute the preparedstatement
	      preparedStmt.setInt(1, id);
	      preparedStmt.executeUpdate();
	      System.out.println("Done");
	      con.close();
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void updateCustomerDetails(int id){
	Scanner input = new Scanner(System.in);
	Connection con = null;
	String url="jdbc:oracle:thin:@localhost:1521:orcl1";
	String uname="scott";
	String pwd="tiger";
	
	try {
		con =DriverManager.getConnection(url,uname,pwd);
		String sql = null;
		System.out.println("Connected");
		System.out.println("Which details would you like to change? \n1. First Name? \n2. Last Name \n3. Address \n4. Phone");
		
		String choice =input.nextLine();
		//String changedVariable = input.nextLine();
//String sql = "update customer set FIRST = '"+changedVariable+"' where id ="+id;
		
		switch(choice){
		case  "1":
			System.out.println("Change First name to: ");
			String changedVariable = input.nextLine();
			sql = "update customer set FIRST = '"+changedVariable+"' where id ="+id;
			break;
		
		case  "2":
			System.out.println("Change Last name to: ");
			String changedLastName = input.nextLine();
			sql = "update customer set LAST = '"+changedLastName+"' where id ="+id;
		break;
		
		case  "3":
			System.out.println("Change Address: ");
			String changedAddress = input.nextLine();
			sql = "update customer set ADDRESS = '"+changedAddress+"' where id ="+id;
	break;
		
		case "4":
		System.out.println("Change Phone Number: ");
		String changedPhoneNumber = input.nextLine();
		sql = "update customer set PHONE = '"+changedPhoneNumber+"' where id ="+id;
		break;
		
		default:
			System.out.println("invalid character");
			break;
		}
		
	 System.out.println(sql);
	      PreparedStatement preparedStmt = con.prepareStatement(sql);
	     preparedStmt.executeUpdate();
	     
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void getCustomerDetails(int id){
	
	//Establish Connection
	Connection con = null;
	String url="jdbc:oracle:thin:@localhost:1521:orcl1";
	String uname="scott";
	String pwd="tiger";
			try {
				con=DriverManager.getConnection(url,uname,pwd);
				System.out.println("Connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println(e.getMessage());
			}
			
			//Create a Statement Object
			try {
				Statement st=con.createStatement();
				Scanner scan=new Scanner(System.in);
				System.out.println("Enter ID to search");
				 id=scan.nextInt();
				
				String sql="select * from customer where ID="+id;
				System.out.println(sql);
				ResultSet rs=st.executeQuery(sql);
				boolean flag=false;
				while (rs.next())
				{
					flag=true;
					System.out.println("First Name: "+rs.getString("First"));
					System.out.println("Last Name: "+rs.getString("Last"));
					System.out.println("Address: "+rs.getString("Address"));
					System.out.println("Phone Number: "+rs.getString("Phone"));
				}
				
				if (!flag)
				System.out.println("Record not found");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}

public void getCustomerDetails(String firstName){
	
	//Establish Connection
	Connection con = null;
	String url="jdbc:oracle:thin:@localhost:1521:orcl1";
	String uname="scott";
	String pwd="tiger";
			try {
				con=DriverManager.getConnection(url,uname,pwd);
				System.out.println("Connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println(e.getMessage());
			}
			
			//Create a Statement Object
			try {
				Statement st=con.createStatement();
				Scanner scan=new Scanner(System.in);
				System.out.println("Enter name to search");
				 firstName=scan.nextLine();
				String sql="select * from customer where FIRST= '"+firstName+"'";
				ResultSet rs=st.executeQuery(sql);
				boolean flag=false;
				while (rs.next())
				{
					flag=true;
					System.out.println("id: "+rs.getString("ID"));
					System.out.println("First Name: "+rs.getString("First"));
					System.out.println("Last Name: "+rs.getString("Last"));
					System.out.println("Address: "+rs.getString("Address"));
					System.out.println("Phone Number: "+rs.getString("Phone"));
				}
				
				if (!flag)
				System.out.println("Record not found");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}

}
