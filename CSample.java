//CS350
//Project #5 Files
//Janet Ruppert

//stores information from survey sample
//instance variables to represent fields of a survey sample
//function to combine all the field values into a string using a format suitable for display on the main window

public class CSample {

//Creates instance variables which represent survey fields
	private int participantid;
	private String zipcode;
	private String carrier;
	private String rating;
	private char[] services = new char[3];
	
//Constructor to initialize a sample	
	public CSample() {
		this.participantid = 000001;
		this.zipcode = "";
		this.carrier = "";
		this.rating = "";
		this.services = new char[]{'-','-','-'};
	}

//Constructor from already existing sample 
	public CSample(CSample sample) {
		this.setID(sample.getID());
		this.setZipcode(sample.getZipcode());
		this.setCarrier(sample.getCarrier());
		this.setRating(sample.getRating());
		this.setServices(sample.getServices());
	}

//Combines all field values into a string which is passed to main window
	public String stringify()	{
		String string = "";
		string += String.format("%d", participantid);
		string += "                     ";
		string += String.format("%s\t", zipcode); 
		if(zipcode.length() < 4){
			string+="     ";			
		} 
		string += "          ";
		string += String.format("%s\t", carrier);
		if(carrier.length() <3){
			string+="        ";
		}
		string += "           ";
		string += String.format("%s", rating);
		if(rating == ""){
			string+=" ";
		}
		string += "               ";
		String b = String.valueOf(services);
		string += b;
		System.out.println(String.format("here is the string: -%s-", string));
		return string;
	}

//Functions for accessing and altering survey field values
	public void setID(int id) { participantid = id; }
	public int getID(){ return (participantid); }
	
	public void setZipcode(String zip) { zipcode = zip; }
	public String getZipcode(){ return zipcode; }
	
	public void setCarrier(String carrier1) { carrier = carrier1; }
	public String getCarrier(){ return carrier; }

	public void setRating(String rating1) { rating = rating1; }
	public String getRating(){ return rating; }

	public void setServices(char[] services1) { services = services1; }
	public char[] getServices(){ return services; }
}
