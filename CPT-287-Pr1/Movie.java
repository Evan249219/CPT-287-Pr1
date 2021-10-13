//Evan Colyer

import java.text.*;
import java.util.*;


public class Movie {
	  String name;
	  Date releaseDate;
	  String description;
	  Date receiveDate;
	  String rStatus;
	  enum movieStatus {RELEASED, RECEIVED;};
	  


	  //constructor
	  public Movie(String moviename, Date moviereleasedate, String moviedescription, Date moviereceivedate, String movstatus) {
		 name = moviename;
		 releaseDate = moviereleasedate;
	     description = moviedescription;
	     receiveDate = moviereceivedate;
	     rStatus = movstatus;

	  }
	  
	  //set all our setters
	  public void setName(String mName) {
		  name = mName;
	  }
	  
	  public void setReleaseDate(Date relDate) {
		  releaseDate = relDate;
	  }
	  
	  public void setDescription(String mdesc) {
		  description = mdesc;
	  }
	  
	  public void setReceiveDate(Date recDate) {
		  receiveDate = recDate;
	  }
	  
	  public void setrStatus(String reStatus) {
		  rStatus = reStatus;
	  }
	  
	  public String getName() {
		  return name;
	  }
	  
	  public Date getReleaseDate() {
		  return releaseDate;
	  }
	  
	  public String getDescription() {
		  return description;
	  }
	  
	  public Date getReceiveDate() {
		  return receiveDate;
	  }
	  
	  public String getStatus() {
		  return rStatus;
	  }
	  
	  //override toString to allow for easy printing
	  @Override
	  public String toString() {
		  DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		  String strreleaseDate = dateFormat.format(releaseDate);
		  String strreceiveDate = dateFormat.format(receiveDate);
		  
		  return "Movie name: " + name + "\r\n" + "Movie Release Date: " + strreleaseDate + "\r\n" + "Description: " + description + "\r\n" + "Receive Date: " + strreceiveDate + "\r\n" + "Movie Status: " + rStatus + "\r\n";
		  
	  }
}

	  