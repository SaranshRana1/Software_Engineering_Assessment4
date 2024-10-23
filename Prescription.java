import java.util.ArrayList;
import java.io.FileWriter; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
public class Prescription {
	private int prescID;
	private String firstName;
	private String lastName;
	private String address;
	private float sphere;
	private float axis;
	private float cylinder;
	private Date examinationDate;
	private String optometrist;
	private String[] remarkTypes= {"Client","Optometrist"};
	private ArrayList <String> postRemarks= new ArrayList<>();
	
	//additional variables for my solution
	private String myRemark;
	private String myRemarkType;
	
	public void setPrescription(int p, String f, String l, String a, float s, float x, float c, Date e, String o){
		prescID = p;
		firstName = f;
		lastName = l;
		address = a;
		sphere = s;
		axis = x;
		cylinder = c;
		examinationDate = e;
		optometrist = o;
	}
	
	public void setRemark(String r, String t){
		myRemark = r;
		myRemarkType = t;
		postRemarks.add(r);
	}
	
	public boolean addPrescription(){
		int ConditionCount = 0;
		//to be able to check correct format of date
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String date = f.format(examinationDate);
		String correct = "dd/mm/yyyy"; // format we want
		if(firstName.length() > 3 && firstName.length() < 16 && lastName.length() > 3 && lastName.length() < 16){
			ConditionCount++; //checks length of first and last name
		}
		if(address.length() > 19){
			ConditionCount++; // checks length of address
		}
		if(sphere >= -20.00 && sphere <= 20.00 && cylinder >= -4.00 && cylinder <= 4.00 && axis >= 0 && axis <= 180){
			ConditionCount++; // checks int and double values
		}
		if(date == correct){
			ConditionCount++; // checks date is in correct format
		}
		if(optometrist.length() > 7 && optometrist.length() < 26){
			ConditionCount++; // checks optometrist length
		}
		if(ConditionCount != 5){return false;} // returns false of conditions arent met
		try {
		      FileWriter prescriptionOut = new FileWriter("Presc.txt");
		      prescriptionOut.write(prescID + ": " + firstName + " " + lastName + ". " + address + ". " + sphere + " " + cylinder + " " + axis + ". " + examinationDate + ". "+ optometrist);
		      prescriptionOut.close();	      // for writing to file
		    } catch (IOException e) {
		      e.printStackTrace();}
		return true;
	
	}
	
	public boolean addRemark(){
		int ConditionCount = 0;
		int wordCount = myRemark.split("\\s").length;
		if(postRemarks.size() <= 2){
			ConditionCount++; //checks number of remarks
		}
		if(myRemarkType == remarkTypes[0] || myRemarkType == remarkTypes[1]){
			ConditionCount++; //checks type of remark
		}
		if(wordCount >= 6 && wordCount <= 20){
			ConditionCount++; //checks remark word count
		}
		if(Character.isUpperCase(myRemark.charAt(0))){
			ConditionCount++; //checks if the first character is in upper case
		}
		if(ConditionCount != 4){return false;} // if conditions arent met
		try {
		      FileWriter prescriptionOut = new FileWriter("Presc.txt");
		      prescriptionOut.write(firstName + " " + lastName + ". " + address + ". " + sphere + " " + cylinder + " " + axis + ". " + examinationDate + ". "+ optometrist);
		      prescriptionOut.close();	     // writing to file 
		    } catch (IOException e) {
		      e.printStackTrace();}
		return true;
	}
}
