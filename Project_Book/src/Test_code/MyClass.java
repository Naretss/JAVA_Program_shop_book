package Test_code;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClass {

	public static void main(String[] args) {
		
		String sDate = "2013-08-03";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		
		try {
			Date date = df.parse(sDate);
		    System.out.println("Full Date : " + date);
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
	}
	
}