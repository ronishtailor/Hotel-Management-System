import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
public class CommonMethods {
static void createAllTables()
	{
	DConnection dc=new DConnection();
	dc.connect();
	dc.executeOtherQuery("create table if not exists users(user varchar(20) primary key,pass varchar(20),usertype enum('admin','manager','staff'))");
	dc.executeOtherQuery("create table if not exists hoteldetails(hotelName varchar(100),address varchar(50),email varchar(25),contact varchar(20),noofrooms int ,nooffloors int,star int,amenities varchar(25),guestrating decimal(5,2))");
	dc.executeOtherQuery("create table if not exists roomcat(categoryid int primary key,categoryname varchar(20),tv enum('yes','no'),fridge enum('yes','no'),geyser enum('yes','no'),ac enum('yes','no'))");
	dc.executeOtherQuery("create table if not exists room(roomid int primary key,roomnumber int unique key,roomname varchar(20),roomcategory varchar(20),perdayrent int)");
	dc.executeOtherQuery("create table if not exists guest(guestid int primary key,name varchar(35),address varchar(50),contact varchar(15) unique key,dob date,city varchar(20),state varchar(20))");
	dc.executeOtherQuery("create table if not exists employee(eid int primary key,ename varchar(20),address varchar(255))");
	dc.executeOtherQuery("create table if not exists bookings(bookingid int primary key,dateofbooking date,checkindate date,days int,checkoutdate date,adults int,kids int,catid int,roomnumber int,rent int,guestid int,estimate int,advance int)");
	dc.executeOtherQuery("create table if not exists checkin(checkinid int primary key,bookingid int,dateofbooking date,checkindate date,days int,checkoutdate date,adults int,kids int,catid int,roomnumber int,rent int,guestid int,estimate int,advance int)");
	dc.executeOtherQuery("create table if not exists addbill(id int primary key,date date,roomnumber int,Particulars varchar(255),description varchar(20),billno int(4),amount int(4))");		
	dc.executeOtherQuery("create table if not exists hoteldetails(name varchar(255),address varchar(255),email varchar(255),contact varchar(255),rooms int,floors int,amenities varchar(255),guestrating decimal(5,2),star varchar(8))");
	dc.executeOtherQuery("create table if not exists bill(billnumber int primary key,date date,roomnumber int,totaladditional decimal(8,2), noofdays int,rentperday int,totalrent int,discountper decimal(5,2),gstper decimal(5,2),finalbill decimal(8,2))");
	dc.executeOtherQuery("create table if not exists billdetails(billnumber int,date date,particulars varchar(255),description varchar(255),amount decimal(5,2))"); 
	ResultSet rst=dc.executeSelectQuery("select count(*) from hoteldetails");

	//ResultSet rst=dc.executeSelectQuery("select * from ") 
	dc.close();
	}
 static Point getCenter(Dimension frame)
 	{ Dimension desktop=Toolkit.getDefaultToolkit().getScreenSize();
   int x=(desktop.width-frame.width)/2;
   int y=(desktop.height-frame.height)/2;
   return new Point(x,y);
 	}
 static String textValidation(String s1) {
		String s11="";
		for(int i=0;i<s1.length();i++){
			if(s1.charAt(i)=='\'')
				s11+="\\'";
			else
				s11+=s1.charAt(i);
		}
		s11=s11.trim();
		return s11;
	}
 static String[] getAllRoomCategoryName()
 { String catname[]=null; 
	 try {
	 DConnection dc=new DConnection();
	 ResultSet rst=dc.executeSelectQuery("select count(*) from roomcat");
	 rst.next();
	 int n=rst.getInt(1);
	 catname=new String[n];
	rst=dc.executeSelectQuery("Select categoryname from roomcat");
	for(int i=0;rst.next();i++) 
	{
		catname[i]=rst.getString(1);
	}
	}
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 return catname;
 }
 static int getRoomCategoryId(String cname)
 {  String catname=cname;
	int catid=0;
 	try {
	DConnection dc=new DConnection();
	 ResultSet rst=dc.executeSelectQuery("Select categoryid from roomcat where categoryname='"+catname+"'");
	 rst.next();
	catid=rst.getInt(1);
 	}
 	catch(SQLException se)
 	{
 		se.printStackTrace();
 	}
 	return catid;
 }
 static String getRoomCategoryName(int categoryId) {
		String catName="";
		try {
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeSelectQuery("select categoryname from roomcat where categoryid="+categoryId);
			rst.next();
			catName=rst.getString(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return catName;		
	}
static String[] getAllRoomNos(String query) {
	String roomNos[]=null;
	try {
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select count(*) from room "+query);
		rst.next();
		int cnt=rst.getInt(1);
		roomNos=new String[cnt];
		
		rst=dc.executeSelectQuery("select roomnumber from room"+query);
		int i=0;
		while(rst.next()) {
			roomNos[i++]=rst.getString(1);
		}
		dc.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return roomNos;		
}	
static int getGuestId(String mobile) {
	int guestId=0;
	try {
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select guestid from guest where contact='"+mobile+"'");
		if(rst.next())
			guestId=rst.getInt(1);
		else
			guestId=-1;
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return guestId;
}
static String convertDateToDatabaseFormat(String s1) {
	String s2="";
	try {
		SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
		Date d1=sdf1.parse(s1);
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		s2=sdf2.format(d1);
	}
	catch(ParseException e) {
		e.printStackTrace();
	}
	return s2;
}
static String convertDateToReadableFormat(String s1) {
	String s2="";
	try {
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=sdf1.parse(s1);
		SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
		s2=sdf2.format(d1);
	}
	catch(ParseException e) {
		e.printStackTrace();
	}
	return s2;
}
static String[] getAllcheckinroomnumber() {
	String roomnumber[]=null;
	try {
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select count(*) from checkin");
		rst.next();
		int cnt=rst.getInt(1);
		roomnumber=new String[cnt];
		
		rst=dc.executeSelectQuery("select roomnumber from checkin");
		int i=0;
		while(rst.next()) {
			roomnumber[i++]=rst.getString(1);
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return roomnumber;		
}	

}
