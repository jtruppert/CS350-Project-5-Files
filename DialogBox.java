//CS350
//Project #5 Files
//Janet Ruppert

//launches dialog box

//takes an instance of CSample as a constructor parameter
//instance variable of CSample to store info from the user

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBox extends JDialog implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//creates the main labels we'll need for every Dialog Box
	private JLabel idLabel;
	private JLabel idnumLabel;
	private JLabel zipcodeLabel;
	private JLabel carrierLabel;
	private JLabel ratingLabel;
	private JLabel servicesLabel;
	
//creates editable text field for Zip Code
	private JTextField myTextField;    
	
//creates button group for Carrier
	private JRadioButton carrierRadioButton1;
	private JRadioButton carrierRadioButton2;
	private JRadioButton carrierRadioButton3;
	private JRadioButton carrierRadioButton4;
	private JRadioButton carrierRadioButton5;
	
//creates button group for Rating 
	private JRadioButton ratingRadioButton1;
	private JRadioButton ratingRadioButton2;
	private JRadioButton ratingRadioButton3;
	private JRadioButton ratingRadioButton4;
	private JRadioButton ratingRadioButton5;

//creates the three check boxes for Services
	private JCheckBox voiceCheckBox;	
	private JCheckBox textCheckBox;
	private JCheckBox dataCheckBox;
		

//creates Submit and Cancel buttons at the bottom
    private JButton submitButton;
    private JButton cancelButton;
    
//creates instance of CSample to store current sample information
    private CSample sample1;
    public CSample getSample() {	return sample1; }
    public void setSample(CSample sample2) {	sample1 = sample2; }
        
    private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    
    private JLabel getID() { return idnumLabel; }
    private String answer;
    public String getAnswer() { return answer; }

//DialogBox constructor including instance of CSample as parameter
    public DialogBox(MainWindow owner, String title, int initVal, int max, CSample sample, boolean isNewRecord) {
		super(owner, title, true);
		sample1 = new CSample(sample);
	    Container c = getContentPane();
	    c.setLayout(null);		


//Participant ID section
	    idLabel = new JLabel("Participant ID:");	
		idLabel.setSize( 200, 50 );
		idLabel.setLocation( 30, 10 );
		idLabel.setForeground(Color.BLUE);
		c.add(idLabel);
		
		idnumLabel = new JLabel("000000");	
		idnumLabel.setSize( 200, 50 );
		idnumLabel.setLocation( 150, 10 );
		idnumLabel.setForeground(Color.magenta);
		idnumLabel.setVisible(true);
		c.add(idnumLabel);
		
//Zipcode section
	    zipcodeLabel = new JLabel("Zipcode:");	
		zipcodeLabel.setSize( 200, 50 );
		zipcodeLabel.setLocation( 30, 50 );
		zipcodeLabel.setForeground(Color.BLUE);
		c.add(zipcodeLabel);
		
		myTextField = new JTextField(20);
		myTextField.setSize( 80, 35 );
		myTextField.setLocation( 150, 60 );
		myTextField.addActionListener(this);
		myTextField.setEditable(true);
		c.add(myTextField);
	   
		
//Carrier section
	    carrierLabel = new JLabel("Carrier:");	
	    carrierLabel.setSize( 200, 50 );
	    carrierLabel.setLocation( 30, 100 );
	    carrierLabel.setForeground(Color.BLUE);
		c.add(carrierLabel);

		carrierRadioButton1 = new JRadioButton("AT&T");
		carrierRadioButton1.setSize(100, 35);
		carrierRadioButton1.setLocation(45, 130);
		carrierRadioButton1.addActionListener(this);
		c.add(carrierRadioButton1);
		
		carrierRadioButton2 = new JRadioButton("T-Mobile");
		carrierRadioButton2.setSize(100, 35);
		carrierRadioButton2.addActionListener(this);
		carrierRadioButton2.setLocation(45, 155);
		c.add(carrierRadioButton2);

		carrierRadioButton3 = new JRadioButton("Verizon");
		carrierRadioButton3.setSize(100, 35);
		carrierRadioButton3.addActionListener(this);
		carrierRadioButton3.setLocation(45, 180);
		c.add(carrierRadioButton3);
		
		carrierRadioButton4 = new JRadioButton("Sprint");
		carrierRadioButton4.setSize(100, 35);
		carrierRadioButton4.addActionListener(this);
		carrierRadioButton4.setLocation(45, 205);
		c.add(carrierRadioButton4);
		
		carrierRadioButton5 = new JRadioButton("Others");
		carrierRadioButton5.setSize(100, 35);
		carrierRadioButton5.setLocation(45, 230);
		carrierRadioButton5.addActionListener(this);
		c.add(carrierRadioButton5);
		
		ButtonGroup carriergroup = new ButtonGroup();
		carriergroup.add(carrierRadioButton1);
		carriergroup.add(carrierRadioButton2);
		carriergroup.add(carrierRadioButton3);
		carriergroup.add(carrierRadioButton4);
		carriergroup.add(carrierRadioButton5);
		pack();
		
		
//Rating section
	    ratingLabel = new JLabel("Rating");		
	    ratingLabel.setSize( 200, 50 );
	    ratingLabel.setLocation( 300, 100 );
	    ratingLabel.setForeground(Color.BLUE);
		c.add(ratingLabel);

		ratingRadioButton1 = new JRadioButton("Excellent(E)");
		ratingRadioButton1.setSize(100, 35);
		ratingRadioButton1.setLocation(300, 130);
		ratingRadioButton1.addActionListener(this);
		c.add(ratingRadioButton1);
		
		ratingRadioButton2 = new JRadioButton("Very Good(V)");
		ratingRadioButton2.addActionListener(this);
		ratingRadioButton2.setSize(100, 35);
		ratingRadioButton2.setLocation(300, 155);
		c.add(ratingRadioButton2);

		ratingRadioButton3 = new JRadioButton("Good(G)");
		ratingRadioButton3.addActionListener(this);
		ratingRadioButton3.setSize(100, 35);
		ratingRadioButton3.setLocation(300, 180);
		c.add(ratingRadioButton3);
		
		ratingRadioButton4 = new JRadioButton("Fair(F)");
		ratingRadioButton4.addActionListener(this);
		ratingRadioButton4.setSize(100, 35);
		ratingRadioButton4.setLocation(300, 205);
		c.add(ratingRadioButton4);
		
		ratingRadioButton5 = new JRadioButton("Poor(P)");
		ratingRadioButton5.addActionListener(this);
		ratingRadioButton5.setSize(100, 35);
		ratingRadioButton5.setLocation(300, 230);
		c.add(ratingRadioButton5);
		
		ButtonGroup ratinggroup = new ButtonGroup();
		ratinggroup.add(ratingRadioButton1);
		ratinggroup.add(ratingRadioButton2);
		ratinggroup.add(ratingRadioButton3);
		ratinggroup.add(ratingRadioButton4);
		ratinggroup.add(ratingRadioButton5);
		pack();
		
		
//Services section
		servicesLabel = new JLabel("To what services do you subscribe?");
		servicesLabel.setSize( 2000000, 50 );
		servicesLabel.setLocation( 30, 275 );
		servicesLabel.setForeground(Color.BLUE);
		c.add(servicesLabel);
		
		voiceCheckBox = new JCheckBox("Voice(V)");
		voiceCheckBox.addActionListener(this);
		voiceCheckBox.setSize(100,25);
		voiceCheckBox.setLocation(35, 310);
		c.add(voiceCheckBox);
		
		textCheckBox = new JCheckBox("Text messaging(T)");
		textCheckBox.addActionListener(this);
		textCheckBox.setSize(150,25);
		textCheckBox.setLocation(170, 310);
		c.add(textCheckBox);
		
		dataCheckBox = new JCheckBox("Data plan(D)");
		dataCheckBox.addActionListener(this);
		dataCheckBox.setSize(1000,25);
		dataCheckBox.setLocation(350, 310);
		c.add(dataCheckBox);
		
		
//Submit Button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setSize( 125, 30 );
		submitButton.setLocation( 50, 400 );
		c.add(submitButton);	
		
//Cancel Button
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setSize( 125, 30 );
		cancelButton.setLocation( 300, 400 );
		c.add(cancelButton);	

//Generates DialogBox based on previous sample
		if(!isNewRecord) {
			idnumLabel.setText(String.format("%08d", sample.getID()));
			myTextField.setText(sample.getZipcode());
			
			String carrier = sample.getCarrier();
			
			if(carrier == "AT&T    "){
				carrierRadioButton1.setSelected(true);
			}
			else if(carrier == "T-Mobile"){
				carrierRadioButton2.setSelected(true);
			}
			else if(carrier == "Verizon "){
				carrierRadioButton3.setSelected(true);
			}
			else if(carrier == "Sprint  "){
				carrierRadioButton4.setSelected(true);
			}
			else if (carrier == "Others  "){
				carrierRadioButton5.setSelected(true);				
			}
			
			String rating = sample.getRating();
			if(rating == "E"){
				ratingRadioButton1.setSelected(true);
			}
			else if(rating == "V"){
				ratingRadioButton2.setSelected(true);
			}
			else if(rating == "G"){
				ratingRadioButton3.setSelected(true);
			}
			else if(rating == "F"){
				ratingRadioButton4.setSelected(true);
			}
			else if(rating == "P"){
				ratingRadioButton5.setSelected(true);
			}
			
			char[] services = sample.getServices();
			if (services[0] == 'V') {
				voiceCheckBox.setSelected(true);
			}
			if (services[1] == 'T') {
				textCheckBox.setSelected(true);
			}
			if (services[2] == 'D') {
				dataCheckBox.setSelected(true);
			}
		}
//Generates new sample
		else {
			idnumLabel.setText(String.format("%08d", (max +1)));
		}

		setSize( 500, 500 );
		setLocationRelativeTo(owner);
		setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
// User clicks submit and sample info is updated
    	if (e.getSource()==submitButton) {	

    		System.out.println("CLIK submit");
    		
			sample1 = new CSample(getSample());
			sample1.setID(Integer.parseInt(getID().getText()));
			
//sets value of Carrier in sample
			if(carrierRadioButton1.isSelected()) {
				sample1.setCarrier("AT&T    ");
			}
			if(carrierRadioButton2.isSelected()) {
				sample1.setCarrier("T-Mobile");
			}
			if(carrierRadioButton3.isSelected()) {
				sample1.setCarrier("Verizon ");
			}
			if(carrierRadioButton4.isSelected()) {
				sample1.setCarrier("Sprint  ");
			}
			if(carrierRadioButton5.isSelected()) {
				sample1.setCarrier("Others  ");
			}
			
//sets value of Rating in sample
			if(ratingRadioButton1.isSelected()) {
				sample1.setRating("E");
			}
			if(ratingRadioButton2.isSelected()) {
				sample1.setRating("V");
			}
			if(ratingRadioButton3.isSelected()) {
				sample1.setRating("G");
			}
			if(ratingRadioButton4.isSelected()) {
				sample1.setRating("F");
			}
			if(ratingRadioButton5.isSelected()) {
				sample1.setRating("P");
			}
			
			
//sets value of Services in sample
			char[] services = new char[]{'-','-','-'};
			if (voiceCheckBox.isSelected()) {
				services[0] = 'V';
			}
			else if(!voiceCheckBox.isSelected()) {
				services[0] = '-';
			}
			
			if (textCheckBox.isSelected()) {
				services[1] = 'T';
			}
			else if(!textCheckBox.isSelected()) {
				services[1] = '-';
			}
			if (dataCheckBox.isSelected()) {
				services[2] = 'D';
			}
			else if(!dataCheckBox.isSelected()) {
				services[2] = '-';
			}
			((CSample)sample1).setServices(services);
			
//sets value of Zipcode in sample
			sample1.setZipcode(myTextField.getText());

			setSample(sample1);
			cancelled = false;
		    setVisible(false);
		}
    	
    	
		else if(e.getSource()==cancelButton) {
		    cancelled = true;
		    setVisible(false);
		}
    }
}
