/***************************
	Patrick Benitez
	CPSC 341
	Professor Holliday
	Project 6
	Client.java
****************************/

/****************************************************************************

References
	Fig. 24.7: Client.java
	Client that reads and displays information sent from a Server.
	I modify the deitel code page 1126 to page 1129.  This code is taken from
	the Deitel Book.

	The Client Class creates all the GUI.  It creates a Socket so it can
	communicate with the server.  Then it uses the ObjectOutputStream to send
	data to the server.  It uses the ObjectInputStream to receive the data
	from the server


*****************************************************************************/
import java.io.*;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.StringTokenizer;

public class Client extends JFrame
{
//	private JTextField enterField; // enters information from user
//  private JTextArea displayArea; // display information to user
	private JLabel  label1, label2, label3, label4, label5, label6, label7,
					label8, label9, label10, label11, label12, label13, label14,
					label15, label16, label17;
	private JTextField textField1, textField2, textField3, textField4, textField5,
					   textField6, textField7, textField8, textField9, textField10,
					   textField11, textField12, textField13, textField14,
					   textField15, textField16, textField17;
	private JButton firstButton, nextButton, previousButton, lastButton, gotoButton,
					insertButton, removeButton, modifyButton, commitButton;
	private int currentEmployeeID;		// keep track of current employee id
	private int currentRecordPosition;	// current record  position
	private long sizeOfFile;			// number of bytes for file
	private int numOfRecord;			// number of records

	private	boolean insert;				// insert flag
	private boolean delete;				// delete flag
	private boolean modify;				// modify flag
   	private ObjectOutputStream output; // output stream to server
   	private ObjectInputStream input; // input stream from server
   	private String message = ""; // message from server
   	private String chatServer; // host server for this application
   	private Socket client; // socket to communicate with server
   //	private boolean insert;


   	// initialize chatServer and set up GUI
  	public Client( String host )
   	{
    	super("North Wind Trading Company");

      	chatServer = host; // set server to which this client connects


		currentRecordPosition = 0;
		sizeOfFile = 0;
		numOfRecord = 0;
		currentEmployeeID = 0;

		setLayout(new FlowLayout());
		Box box = Box.createVerticalBox();

		label1 = new JLabel("Employee ID");
		add(label1);
		textField1 = new JTextField(10);
		add(textField1);

		label2 = new JLabel("Last Name");
		add(label2);
		textField2 = new JTextField(15);
		add(textField2);

		label3 = new JLabel("First Name");
		add(label3);
		textField3 = new JTextField(10);
		add(textField3);

		label4 = new JLabel("Title");
		add(label4);
		textField4 = new JTextField(15);
		add(textField4);

		label5 = new JLabel("Title of Courtesy");
		add(label5);
		textField5 = new JTextField(10);
		add(textField5);

		label6 = new JLabel("Birth Date");
		add(label6);
		textField6 = new JTextField(10);
		add(textField6);

		label7 = new JLabel("Hire Date");
		add(label7);
		textField7 = new JTextField(15);
		add(textField7);

		label8 = new JLabel("Address");
		add(label8);
		textField8 = new JTextField(15);
		add(textField8);

		label9 = new JLabel("City");
		add(label9);
		textField9 = new JTextField(10);
		add(textField9);

		label10 = new JLabel("Region");
		add(label10);
		textField10 = new JTextField(10);
		add(textField10);

		label11 = new JLabel("Postal Code");
		add(label11);
		textField11 = new JTextField(10);
		add(textField11);

		label12 = new JLabel("Country");
		add(label12);
		textField12 = new JTextField(15);
		add(textField12);

		label13 = new JLabel("Home Phone");
		add(label13);
		textField13 = new JTextField(20);
		add(textField13);

		label14 = new JLabel("Extension");
		add(label14);
		textField14 = new JTextField(20);
		add(textField14);

		label15 = new JLabel("Photo");
		add(label15);
		textField15 = new JTextField(10);
		add(textField15);

		label16 = new JLabel("Notes");
		add(label16);
		textField16 = new JTextField(10);
		add(textField16);

		label17 = new JLabel("Report To");
		add(label17);
		textField17 = new JTextField(10);
		add(textField17);

		Box box1 = Box.createHorizontalBox();
		firstButton = new JButton("First");
		box1.add(firstButton);

		nextButton = new JButton("Next");
		box1.add(nextButton);

		previousButton = new JButton("Previous");
		box1.add(previousButton);

		lastButton = new JButton("Last");
		box1.add(lastButton);

		gotoButton = new JButton("GoTo");
		box1.add(gotoButton);

		insertButton = new JButton("Insert");
		box1.add(insertButton);

		removeButton = new JButton("Remove");
		box1.add(removeButton);

		modifyButton = new JButton("Modify");
		box1.add(modifyButton);

		commitButton = new JButton("Commit");
		box1.add(commitButton);
/*
		enterField = new JTextField(); // create enterField
		enterField.setEditable( false );
		enterField.addActionListener(
			new ActionListener()
			{
				// send message to server
				public void actionPerformed( ActionEvent event )
				{
					sendData( event.getActionCommand() );
					enterField.setText( "" );
				} // end method actionPerformed
			} // end anonymous inner class
		); // end call to addActionListener

			add( enterField, BorderLayout.SOUTH );

		 	displayArea = new JTextArea("",10, 10); // create displayArea
     		add( new JScrollPane( displayArea ), BorderLayout.CENTER );
*/
			add(box1);

		firstButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the first button,
				// this function will get the first record
				// and set first button and previous button false.
				// It also set the last button and next button true
				public void actionPerformed(ActionEvent event)
				{
					setFirst();
					System.out.printf("Message %s ", message);
					sendData( event.getActionCommand() );
					firstButton.setEnabled(false);
					previousButton.setEnabled(false);
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}// end anonymous inner class
		);

		nextButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the next button,
				// this function will display to the next
				// record.  It sets the enable the first
				// and previous button
				public void actionPerformed(ActionEvent event)
				{
					setNext();
					System.out.printf("Message %s ", message);
					sendData( event.getActionCommand() );
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}		// end anonymous inner class
		);

		previousButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the previous button,
				// this function will display previous record.
				// It will enable the last and next button
				public void actionPerformed(ActionEvent event)
				{
					setPrevious();
					System.out.printf("Message %s ", message);
					sendData( event.getActionCommand() );
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}		// end anonymous inner class
		);

		lastButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the last button,
				// it disable the last and next button.
				// This function enable the first and previous
				// button
				public void actionPerformed(ActionEvent event)
				{
					setLast();
					System.out.printf("Message %s ", message);
					sendData( event.getActionCommand() );
					lastButton.setEnabled(false);
					nextButton.setEnabled(false);
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}		// end anonymous inner class
		);

		gotoButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the goto button, this function
				// will prompt the user to enter the employee id
				// and display the record.  It will enable the last,
				// first, next, previous button
				public void actionPerformed(ActionEvent event)
				{
					setGoTo();
					System.out.printf("byeMessage %s \n ", message);
				//	sendData( event.getActionCommand());
					sendData( message );
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}	// end anonymous inner class
		);

		insertButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the insert button, this function
				// will prompt the user to enter the employee id
				// and other fields.  Data will not be inserted into
				// the binary unless the user click on commit button
				public void actionPerformed(ActionEvent event)
				{
					setInsert();
					sendData( event.getActionCommand() );
					insert();
					textField1.setBackground(Color.white);
				}
			}	// end anonymous inner class
		);

		removeButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the remove button, this function
				// will prompt the user to enter the employee id
				// and display the record.  Data will not be remove
				// unless the user click on commit button
				public void actionPerformed(ActionEvent event)
				{
					setRemove();
					//sendData( event.getActionCommand() );
					sendData( message );
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
					textField1.setBackground(Color.white);
				}
			}	// end anonymous inner class
		);

		modifyButton.addActionListener(

							// anonymous inner class
			new ActionListener()
			{
				// When user click on the modify button, this function
				// will prompt the user allow user to modify the
				// record and display the record.
				public void actionPerformed(ActionEvent event)
				{
					setModify();
					sendData( message );
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
				}
			}	// end anonymous inner class
		);

		commitButton.addActionListener(

			// anonymous inner class
			new ActionListener()
			{
				// When user click on the commit button, this function
				// will either insert the data in binary file or
				// deleting the data by setting employee id =0
				public void actionPerformed(ActionEvent event)
				{
					setCommit();
					sendData( message );
					lastButton.setEnabled(true);
					nextButton.setEnabled(true);
					firstButton.setEnabled(true);
					previousButton.setEnabled(true);
				}
			}	// end anonymous inner class
		);

	//	setSize(1024, 500);
     	setSize(640, 350 ); // set size of window
      	setVisible( true ); // show window
   	} // end Client constructor

   	// connect to server and process messages from server
   	public void runClient()
   	{
    	try // connect to server, get streams, process connection
      	{
        	connectToServer(); // create a Socket to make connection
        	getStreams(); // get the input and output streams
         	processConnection(); // process connection
      	} // end try
      	catch ( EOFException eofException )
      	{
        	displayMessage( "\nClient terminated connection" );
      	} // end catch
      	catch ( IOException ioException )
      	{
        	ioException.printStackTrace();
      	} // end catch
      	finally
      	{
        	closeConnection(); // close connection
      	} // end finally
   	} // end method runClient

   	// connect to server
   	private void connectToServer() throws IOException
   	{
    	displayMessage( "Attempting connection\n" );

      	// create Socket to make connection to server
      	client = new Socket( InetAddress.getByName( chatServer ), 12345 );

      	// display connection information
      	displayMessage( "Connected to: " +
        client.getInetAddress().getHostName() );
   	} // end method connectToServer

   	// get streams to send and receive data
   	private void getStreams() throws IOException
   	{
    	// set up output stream for objects
      	output = new ObjectOutputStream( client.getOutputStream() );
      	output.flush(); // flush output buffer to send header information

      	// set up input stream for objects
    	input = new ObjectInputStream( client.getInputStream() );

      	displayMessage( "\nGot I/O streams\n" );
   	} // end method getStreams

   	// process connection with server
  	private void processConnection() throws IOException
   	{
    	// enable enterField so client user can send messages
      	setTextFieldEditable( true );

      	do // process messages sent from server
      	{
        	try // read message and display it
         	{
            	message = ( String ) input.readObject(); // read new message
            	displayMessage( "\n" + message ); // display message

				System.out.printf("Client" + message);

//            	System.out.printf("Client" + message);
         	} // end try

         	catch ( ClassNotFoundException classNotFoundException )
         	{
            	displayMessage( "\nUnknown object type received" );
         	} // end catch

      	} while ( !message.equals( "SERVER>>> TERMINATE" ) );
   	} // end method processConnection

   	// close streams and socket
   	private void closeConnection()
   	{
    	displayMessage( "\nClosing connection" );
      	setTextFieldEditable( false ); // disable enterField

      	try
      	{
        	output.close(); // close output stream
         	input.close(); // close input stream
         	client.close(); // close socket
      	} // end try
     	catch ( IOException ioException )
      	{
        	ioException.printStackTrace();
     	} // end catch
   	} // end method closeConnection

   	// send message to server
   	private void sendData( String message )
   	{

		System.out.printf("message %s \n", message);
    	try // send object to server
      	{
        	output.writeObject( "CLIENT>>>" + message );
        	output.flush(); // flush data to output
         //	displayMessage( "\nCLIENT>>> " + message );
      	} // end try
      	catch ( IOException ioException )
      	{
    //    	displayArea.append( "\nError writing object" );
      	} // end catch
   	} // end method sendData

   	// send message to server
   	private void sendData1( String message )
   	{

		System.out.printf("message %s \n", message);
    	try // send object to server
      	{
        	output.writeObject( "CLIENT>>> " + message );
        	output.flush(); // flush data to output
         //	displayMessage( "\nCLIENT>>> " + message );
      	} // end try
      	catch ( IOException ioException )
      	{
    //    	displayArea.append( "\nError writing object" );
      	} // end catch
   	} // end method sendData


   	// manipulates displayArea in the event-dispatch thread
   	private void displayMessage( final String messageToDisplay )
   	{
    	SwingUtilities.invokeLater(
        	new Runnable()
         	{
            	public void run() // updates displayArea
            	{
					int employeeID;   			// 4 byte
					String lastName ="";			// 30 byte
					String firstName ="";			// 30 byte
					String title ="";				// 30 byte
					String titleOfCourtesy ="";		// 30 byte
					String birthDate ="";			// 30 byte
					String hireDate ="";			// 30 byte
					String Address ="";				// 30 byte
					String city ="";				// 30 byte
					String region ="";				// 30 byte
					String postalCode ="";			// 30 byte
					String country ="";				// 30 byte
					String homePhone ="";			// 30 byte
					String extension ="";			// 30 byte
					String photo ="";				// 30 byte
					String notes ="";				// 30 byte
					int reportsTo;				// 4 byte
					String employeeID1 = "";
					String reportsTo1 = "";

					System.out.printf("testing%s", message);
					StringTokenizer st = new StringTokenizer(message, "|", false);

					 while (st.hasMoreTokens())
					 {
						System.out.println(st.nextToken());

						try
						{
							employeeID1 = st.nextToken();
							//employeeID = Integer.parseInt(employeeID1);
							lastName = st.nextToken();
							firstName = st.nextToken();
							title = st.nextToken();
							titleOfCourtesy = st.nextToken();
							birthDate = st.nextToken();;
							hireDate = st.nextToken();
							Address = st.nextToken();
							city = st.nextToken();
							region = st.nextToken();
							postalCode = st.nextToken();
							country = st.nextToken();
							homePhone = st.nextToken();
							extension = st.nextToken();
							photo = st.nextToken();
							notes = st.nextToken();
							reportsTo1 = st.nextToken();
						}
						catch (NoSuchElementException elementException)
						{
							System.err.println("Invalid");
						}
				 	}

				 	textField1.setText(employeeID1);
					textField2.setText(lastName);
					textField3.setText(firstName);
					textField4.setText(title);
					textField5.setText(titleOfCourtesy);
					textField6.setText(birthDate);
					textField7.setText(hireDate);
					textField8.setText(Address);
					textField9.setText(city);
					textField10.setText(region);
					textField11.setText(postalCode);
					textField12.setText(country);
					textField13.setText(homePhone);
					textField14.setText(extension);
					textField15.setText(photo);
					textField16.setText(notes);
					textField17.setText(reportsTo1);
               // 	displayArea.append( messageToDisplay );
            	} // end method run
         	}  // end anonymous inner class
      	); // end call to SwingUtilities.invokeLater
   	} // end method displayMessage

   	// manipulates enterField in the event-dispatch thread
   	private void setTextFieldEditable( final boolean editable )
   	{
      	SwingUtilities.invokeLater(
        	new Runnable()
        	{
            	public void run() // sets enterField's editability
            	{
                //	enterField.setEditable( editable );
            	} // end method run
         	} // end anonymous inner class
      	); // end call to SwingUtilities.invokeLater
   	} // end method setTextFieldEditable

	public void insert()
	{
		String output = "";
		textField1.setText(output);
		textField2.setText(output);
		textField3.setText(output);
		textField4.setText(output);
		textField5.setText(output);
		textField6.setText(output);
		textField7.setText(output);
		textField8.setText(output);
		textField9.setText(output);
		textField10.setText(output);
		textField11.setText(output);
		textField12.setText(output);
		textField13.setText(output);
		textField14.setText(output);
		textField15.setText(output);
		textField16.setText(output);
		textField17.setText(output);
		insert = true;
	}

	public void setFirst()
	{
		message = "First";
	}

	public void setNext()
	{
		message = "Next";
	}

	public void setPrevious()
	{
		message = "Previous";
	}

	public void setLast()
	{
		message = "Last";
	}

	public void setGoTo()
	{
		String employeeID = JOptionPane.showInputDialog("Enter the Employee ID");
		message = "";
		message += "GoTo";
		message += "|";
		message += employeeID;
		System.out.printf("\nhiMessage%s \n", message);
	}

	public void setInsert()
	{
		message = "In";
	}

	public void setRemove()
	{
		String employeeID = JOptionPane.showInputDialog("Enter the Employee ID");
		message = "";
		message += "Remo";
		message += "|";
		message += employeeID;
		delete = true;
	}

	public void setModify()
	{
		message = "Modify";
		textField1.setBackground(Color.red);
	}

	public void setCommit()
	{
		modify = true;


		String employeeID = "";

		if(insert)
		{
			message += "Co";
			message += "|" + textField1.getText() + "|" + textField2.getText() + "|"
				    + textField3.getText() + "|" + textField4.getText() + "|"
				    + textField5.getText() +"|" + textField6.getText() +"|"
				    + textField7.getText() +"|" + textField8.getText() +"|"
				    + textField9.getText() +"|" + textField10.getText() + "|"
				    + textField11.getText() + "|" + textField12.getText()
				    + "|" + textField13.getText() +"|" + textField14.getText() +"|"
				    + textField15.getText() + "|" + textField16.getText() +"|"
				    + textField17.getText();
			insert = false;
			delete = false;
		}

		else if(delete || insert)
		{
			message = "";
			message += "RemoCo";
			message += "|" + textField1.getText() + "|" + textField2.getText() + "|"
				    + textField3.getText() + "|" + textField4.getText() + "|"
				    + textField5.getText() +"|" + textField6.getText() +"|"
				    + textField7.getText() +"|" + textField8.getText() +"|"
				    + textField9.getText() +"|" + textField10.getText() + "|"
				    + textField11.getText() + "|" + textField12.getText()
				    + "|" + textField13.getText() +"|" + textField14.getText() +"|"
				    + textField15.getText() + "|" + textField16.getText() +"|"
				    + textField17.getText();
			JOptionPane.showMessageDialog(null, "Employee ID set to 0" , " Record Deleted",
						JOptionPane.WARNING_MESSAGE);
					delete = false;
					insert = false;
		}

		else if(modify)
		{
			message = "";
			message += "ModifyCo";
			message += "|" + textField1.getText() + "|" + textField2.getText() + "|"
					+ textField3.getText() + "|" + textField4.getText() + "|"
					+ textField5.getText() +"|" + textField6.getText() +"|"
					+ textField7.getText() +"|" + textField8.getText() +"|"
					+ textField9.getText() +"|" + textField10.getText() + "|"
					+ textField11.getText() + "|" + textField12.getText()
					+ "|" + textField13.getText() +"|" + textField14.getText() +"|"
					+ textField15.getText() + "|" + textField16.getText() +"|"
					+ textField17.getText();
		}
		textField1.setBackground(Color.white);
	}

	public String getMessage()
	{
		return message;
	}


} // end class Client
/**************************************************************************
 * (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
