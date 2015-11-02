//CS350
//Project #5 Files
//Janet Ruppert

//displays a list of all survey samples entered so far

//a user can click six buttons: add, modify, remove, remove all, open, save
	//add launches dialog box
	//modify launches dialog box 
	//remove deletes selected entry
	//remove all deletes all entries
	//open samples from file
	//save samples to file

//attributes have to be left or right aligned and put under corresponding headings
//list is implemented with JList class
//open and save implemented with JChooseFile class

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MainWindow extends JFrame implements ActionListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//creates the four main buttons
	private  JButton addButton = null;
	private  JButton modifyButton = null;
	private  JButton removeButton = null;
	private  JButton removeallButton = null;
	
//creates two additional buttons and values necessary for files
	private  JButton openfileButton = null;
	private  JButton savefileButton = null;
	private  JFileChooser filechooser = null;
	private  BufferedReader br;
	private  File file;
	int returnVal;
	String currentLine;

//creates main labels we'll need for Main Window
	private  JLabel idLabel = null;
	private  JLabel zipcodeLabel = null;
	private  JLabel carrierLabel = null;
	private  JLabel ratingLabel = null;
	private  JLabel servicesLabel = null;	
	
//creates List for holding object CSample and JList for scrollpane
	private List<CSample> csamplelist;
	private JList<String> samplelist;
	private List<String> samplestrings;
	private String[] ar;
	public List<CSample> getCSampleList() { return csamplelist; }
	public void setCSampleList(List<CSample> c) { csamplelist = c; }
	private int index;
	private int max;
	public int getMaxRecNum() {return max; }
	    
	
//creates Main Window
	public MainWindow() {
		super("Cell Service Survey");
	    Container c = getContentPane();
	    c.setLayout(null);		
	    
//sets column titles
	    idLabel = new JLabel("Participant ID");
		idLabel.setSize( 200, 30 );
		idLabel.setLocation( 20, 5 );
		idLabel.setForeground(Color.BLUE);
		c.add(idLabel);

		zipcodeLabel = new JLabel("Zip Code");
		zipcodeLabel.setSize( 200, 30 );
		zipcodeLabel.setLocation( 175, 5 );
		zipcodeLabel.setForeground(Color.BLUE);
		c.add(zipcodeLabel);

		carrierLabel = new JLabel("Carrier");
		carrierLabel.setSize( 200, 30 );
		carrierLabel.setLocation( 280, 5 );
		carrierLabel.setForeground(Color.BLUE);
		c.add(carrierLabel);

		ratingLabel = new JLabel("Rating");
		ratingLabel.setSize( 200, 30 );
		ratingLabel.setLocation( 400, 5 );
		ratingLabel.setForeground(Color.BLUE);
		c.add(ratingLabel);
		
		servicesLabel = new JLabel("Services");
		servicesLabel.setSize( 200, 30 );
		servicesLabel.setLocation( 520, 5 );
		servicesLabel.setForeground(Color.BLUE);
		c.add(servicesLabel);

//creates box for list of samples 
		csamplelist = new ArrayList<CSample>();
		
		samplelist = new JList<String>();
		changeList();
		samplelist.setSize(550,180);
		samplelist.setLocation(20,30);
		samplelist.setBorder(BorderFactory.createBevelBorder(1));
		samplelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		samplelist.setFont(new Font("Courier New", Font.PLAIN, 12));
		samplelist.addListSelectionListener(this);
		samplelist.setVisible(true);
		c.add(samplelist);
		
		
//creates four buttons at the bottom of the window		
		addButton = new JButton("Add");
		addButton.setSize(80, 30);
		addButton.setLocation( 20, 230 );
		addButton.addActionListener(this);
		c.add(addButton);
		
		modifyButton = new JButton("Modify");
		modifyButton.setSize( 80, 30 );
		modifyButton.setLocation( 110, 230 );
		modifyButton.addActionListener(this);
		c.add(modifyButton);
		
		removeButton = new JButton("Remove");
		removeButton.setSize( 80, 30 );
		removeButton.setLocation( 200, 230 );
		removeButton.addActionListener(this);
		c.add(removeButton);
		
		removeallButton = new JButton("Remove All");
		removeallButton.setSize( 100, 30 );
		removeallButton.setLocation( 290, 230 );
		removeallButton.addActionListener(this);
		c.add(removeallButton);
		
		openfileButton = new JButton("Open");
		openfileButton.setSize( 80, 30 );
		openfileButton.setLocation( 400, 230 );
		openfileButton.addActionListener(this);
		c.add(openfileButton);
		
		savefileButton = new JButton("Save");
		savefileButton.setSize( 80, 30 );
		savefileButton.setLocation( 490, 230 );
		savefileButton.addActionListener(this);
		c.add(savefileButton);
		
		filechooser = new JFileChooser();
		
		
	    setSize(610, 330);
	    setLocation( 100, 100 );
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
   
	    max = 0;
	}

	public void actionPerformed(ActionEvent e) {
//User clicks Add, new sample is created
    	if(e.getSource()==addButton) 
    	{
    		DialogBox dialog = new DialogBox(this,"Add A Survey Sample", 1, getMaxRecNum(), new CSample(), true);
    		
    		if (!dialog.isCancelled()) 
		    {		
		    	csamplelist.add(dialog.getSample());
		    	changeList();
		    	max++;
		    }
    	}
//User clicks Modify, previous sample is edited
    	else if(e.getSource()==modifyButton) {
    		if(index == -1) {return;}
    		else 
    		{
	    		int index = samplelist.getSelectedIndex();
				if (index >= 0) 
				{
					DialogBox dialog = new DialogBox(this, "Modify A Survey Sample", 1, max, csamplelist.get(index), false);					
					
					if (!dialog.isCancelled()) 
					{
						
						List<CSample> listcopy = new ArrayList<CSample>(csamplelist);
						List<CSample> l = new ArrayList<CSample>();
						for(int i =0; i < listcopy.size(); i++ )
						{
							if (i == index) 
							{
								l.add(dialog.getSample());						
							}
							else 
							{
								l.add(listcopy.get(i));
							}
						}
						setCSampleList(l);changeList();
						
					}
				}
			}
		}

//User clicks Remove, selected sample is deleted
		else if(e.getSource() == removeButton) 
		{
    		if (index <0) { return;}
    		else 
    		{
				csamplelist.remove(index);
				changeList();
    		}
		}
//User clicks Remove All, all samples are deleted
		else if(e.getSource() == removeallButton) {

			/*JOptionPane.showMessageDialog(null, "All survey samples will be removed.", "Message from Cell Service Survey", JOptionPane.WARNING_MESSAGE);
			*/
    		csamplelist = new ArrayList<CSample>();
    		changeList();
				
		}
//User clicks Open, file chooser GUI opens
		else if(e.getSource() == openfileButton) {
			returnVal = filechooser.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				
				file = filechooser.getSelectedFile();
				
				//adds the file to the string window
				try {
					br = new BufferedReader(new FileReader(file));
					samplestrings = new ArrayList<String>();
					
					while((currentLine = br.readLine()) != null) {
						@SuppressWarnings("unused")
						String s = currentLine;
						samplestrings.add(currentLine);
				    	ar = new String[csamplelist.size() +1];
				    	int i = 0;
				    	for (String n : samplestrings) {
				    		ar[i] = n;
				    		i++;
				    	}
				    	samplelist.setListData(ar);
						System.out.println(currentLine);
						
						String csamplestring = currentLine;
						CSample csample = new CSample();
						
						int csampleid = Integer.parseInt(csamplestring.substring(0,1));
						System.out.println(String.format("ID = (%d)", csampleid));
						csample.setID(csampleid);

						String csamplezip = csamplestring.substring(22,27);
						System.out.println(String.format("zip = (%s)", csamplezip));
						csample.setZipcode(csamplezip);
						
						String csamplecarrier = csamplestring.substring(38,46);
						System.out.println(String.format("carrier = (%s)", csamplecarrier));
						csample.setCarrier(csamplecarrier);

						String csamplerating = csamplestring.substring(58,59);
						System.out.println(String.format("rating = (%s)", csamplerating));
						csample.setRating(csamplerating);

						String charconverter = csamplestring.substring(csamplestring.length()-3,csamplestring.length());
						char[] csampleservices = new char[3];

						System.out.println(String.format("char = (%s)", charconverter));
						csampleservices[0] = charconverter.charAt(0);
						csampleservices[1] = charconverter.charAt(1);
						csampleservices[2] = charconverter.charAt(2);
						csample.setServices(csampleservices);						
						
						csamplelist.add(csamplelist.size(), csample);
					}
				} catch (Exception error) {
					error.printStackTrace();
				}
				
				
			}
		}
		else if (e.getSource()==savefileButton) {
//User clicks Save, file saver GUI opens
			String content = "";
			for(String s: samplestrings) {
				content += s;
				content += "\n";
			}
			System.out.println(String.format("content = (%s)", content));
			
			filechooser = new JFileChooser();
			filechooser.setDialogTitle("Specify a file to save");  
			int returnVal = filechooser.showSaveDialog(null);	
			
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
			    file = filechooser.getSelectedFile();
			    System.out.println("Save as file: " + file.getAbsolutePath());
				try {
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					for(String s: samplestrings) {
						content = "";
						content += s;
						bw.write(content);
						bw.write("\n");
						System.out.println(String.format("content = (%s)", content));
						
					}
					bw.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
			
			
			
			if (returnVal == JFileChooser.CANCEL_OPTION) {
				
			}
		}
    }

	public void changeList() {
    	// update the rows to match the data we have
    	samplestrings = new ArrayList<String>();
    	for (CSample s : csamplelist) {
    		samplestrings.add(s.stringify());
    	}
    	ar = new String[csamplelist.size()];
    	int i = 0;
    	for (String s : samplestrings) {
    		ar[i] = s;
    		i++;
    	}
    	samplelist.setListData(ar);
    }
    
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainWindow mainwindow = new MainWindow();
	}

	@SuppressWarnings("rawtypes")
	public void valueChanged(ListSelectionEvent arg0) {
		JList samplelist1 = (JList)arg0.getSource();
		index = samplelist1.getSelectedIndex();
	}

}
