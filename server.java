/***************************
	Patrick Benitez
	Server.java
****************************/

/****************************************************************************
References
	Fig. 24.5: Server.java
	Set up a Server that will receive a connection from a client, send
	a string to the client, and close the connection.
	I modify the deitel code page 1120 to page 1123.  This code is taken from
	the Deitel Book.

	The Server class sets up a ServerSocket server first.  Then it creates a
	Socket connection the listen to a client connection.  Then it uses the
	OutputStream and InputStream objects to enable the server to communicate
	with the client by sending a large message in a string format.

****************************************************************************/
import java.io.*;
import java.io.File;
import java.io.EOFException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.RandomAccessFile;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.StringTokenizer;

public class Server extends JFrame
{
	private RandomAccessFile input1;
   	private RandomAccessFile output1;
	private int currentEmployeeID;		// keep track of current employee id

	private int currentRecordPosition;	// current record  position
	private String currentRecordPosition1;

	private long sizeOfFile;			// number of bytes for file
	private int numOfRecord;			// number of records
	private boolean insert;				// insert flag
	private boolean delete;				// delete flag
	private boolean modify;				// modify flag

	private JTextField enterField; 		// inputs message from user
	private JTextArea displayArea; 		// display information to user
   	private ObjectOutputStream output; 	// output stream to client
   	private ObjectInputStream input; 	// input stream from client
   	private ServerSocket server; 		// server socket
   	private Socket connection; 			// connection to client
   	private int counter = 1; 			// counter of number of connections
   	private String outgoing_message = "";

	// set up GUI
   	public Server()
   	{
		super( "Server" );

	  	enterField = new JTextField(); // create enterField
	  	enterField.setEditable( false );
	  	enterField.addActionListener(
			new ActionListener()
			{
				// send message to client
		 		public void actionPerformed( ActionEvent event )
				{

			   		sendData( event.getActionCommand() );
			   		enterField.setText( "" );
				} // end method actionPerformed
			} // end anonymous inner class
	  	);  // end call to addActionListener

	  	add( enterField, BorderLayout.NORTH );

	  	displayArea = new JTextArea(); // create displayArea
	  	add( new JScrollPane( displayArea ), BorderLayout.CENTER );

	  	setSize(640, 480 ); // set size of window
	  	setVisible( true ); // show window
   	} // end Server constructor

   	// set up and run server
   	public void runServer()
   	{
    	try // set up server to receive connections; process connections
      	{
        	server = new ServerSocket( 12345, 100 ); // create ServerSocket

         	while ( true )
         	{
            	try
            	{
                	waitForConnection(); // wait for a connection
               		getStreams(); // get input & output streams
               		processConnection(); // process connection
            	} // end try

            	catch ( EOFException eofException )
            	{
                	displayMessage( "\nServer terminated connection" );
            	} // end catch

            	finally
            	{
               	//	closeConnection(); //  close connection
               		counter++;
            	} // end finally
         	} // end while
      	} // end try

      	catch ( IOException ioException )
      	{
        	ioException.printStackTrace();
      	} // end catch
   	} // end method runServer

   	// wait for connection to arrive, then display connection info
   	private void waitForConnection() throws IOException
   	{
    	displayMessage( "Waiting for connection\n" );
     	connection = server.accept(); // allow server to accept connection
      	displayMessage( "Connection " + counter + " received from: " +
        connection.getInetAddress().getHostName() );
   	} // end method waitForConnection

   	// get streams to send and receive data
   	private void getStreams() throws IOException
   	{
      	// set up output stream for objects
    	output = new ObjectOutputStream( connection.getOutputStream() );
      	output.flush(); // flush output buffer to send header information

      	// set up input stream for objects
      	input = new ObjectInputStream( connection.getInputStream() );

      	displayMessage( "\nGot I/O streams\n" );
   	} // end method getStreams

   	// process connection with client
   	private void processConnection() throws IOException
   	{
    	//String message = "Connection successful";
		// record to store the textfield information
		RandomAccessEmployeeRecord record = new RandomAccessEmployeeRecord();
		// record to read in the file for checking duplicate employee id
		RandomAccessEmployeeRecord record1 = new RandomAccessEmployeeRecord();
		int employeeID1;
		int ReportTo1;

		String firstToken ="";
		String secondToken ="";
		String thirdToken = "";
		String fourthToken = "";
		String fifthToken = "";
		String sixthToken = "";
		String seventhToken = "";
		String eighthToken = "";
		String ninthToken = "";
		String tenthToken = "";
		String elevenToken = "";
		String twelfthToken = "";
		String thirteenthToken = "";
		String fourteenthToken = "";
		String fifteenthToken = "";
		String sixteenthToken = "";
		String seventeenthToken = "";
		String eighteenthToken = "";
		String message = "";
      	sendData( message ); // send connection successful message

      	// enable enterField so server user can send messages
      	setTextFieldEditable( true );

      	do // process messages sent from client
      	{
        	try // read message and display it
         	{

            	message = ( String ) input.readObject(); // read new message
            	System.out.printf("testing message %s ", message);

               	if(message.equals("CLIENT>>>First"))
				{
					openBinaryFile();
					readFirstRecord();
					//message = outgoing_message;
					System.out.printf("outgoing message%s ", outgoing_message);
					sendData(outgoing_message);
				}

				else if(message.equals("CLIENT>>>Next"))
				{
					openBinaryFile();
					readNextRecord();
					sendData(outgoing_message);
				}

				else if(message.equals("CLIENT>>>Previous"))
				{
					openBinaryFile();
					readPreviousRecord();
					sendData(outgoing_message);
				}

				else if(message.equals("CLIENT>>>Last"))
				{
					openBinaryFile();
					readLastRecord();
					sendData(outgoing_message);
				}

				else
				{
					// Using the StringTokenizer class to break up the string
					StringTokenizer st = new StringTokenizer(message,"|");

					try
					{
						firstToken = st.nextToken();
						secondToken = st.nextToken();
						thirdToken = st.nextToken();
						fourthToken = st.nextToken();
						fifthToken = st.nextToken();
						sixthToken = st.nextToken();
						seventhToken = st.nextToken();
						eighthToken = st.nextToken();
						ninthToken = st.nextToken();
						tenthToken = st.nextToken();
						elevenToken = st.nextToken();
						twelfthToken = st.nextToken();
						thirteenthToken = st.nextToken();
						fourteenthToken = st.nextToken();
						fifteenthToken = st.nextToken();
						sixteenthToken = st.nextToken();
						seventeenthToken = st.nextToken();
						eighteenthToken = st.nextToken();
					}

					catch (NoSuchElementException elementException)
					{
						System.err.println("Invalid");
					}

					if(firstToken.equals("CLIENT>>>GoTo"))
					{
						/*

						JOptionPane.showMessageDialog(null, firstToken , " First",
						JOptionPane.WARNING_MESSAGE);

						JOptionPane.showMessageDialog(null, secondToken , " Second",
						JOptionPane.WARNING_MESSAGE);
						*/
						currentRecordPosition = Integer.parseInt(secondToken);

						/*
						JOptionPane.showMessageDialog(null, currentRecordPosition , "currentPosition",
						JOptionPane.WARNING_MESSAGE);
						*/
						openBinaryFile();
						goToRecord();
						sendData(outgoing_message);
						message = "";
					}

					else if(firstToken.equals("CLIENT>>>InCo"))
					{
//						try
//						{
							employeeID1 = Integer.parseInt(secondToken);

							// for debugging purpose
							ReportTo1 = Integer.parseInt(eighteenthToken);

							// get the textfield information and put it in
							// record for checking with the read record
							record.setEmployeeID(employeeID1);
							record.setLastName(thirdToken );
							record.setFirstName(fourthToken);
							record.setTitle(fifthToken);
							record.setTitleOfCourtesy(sixthToken);
							record.setBirthDate(seventhToken);
							record.setHireDate(eighthToken);
							record.setAddress(ninthToken);
							record.setCity(tenthToken);
							record.setRegion(elevenToken);
							record.setPostalCode(twelfthToken);
							record.setCountry(thirteenthToken);
							record.setHomePhone(fourteenthToken);
							record.setExtension(fifteenthToken);
							record.setPhoto(sixteenthToken);
							record.setNotes(seventeenthToken);
							record.setReportsTo(ReportTo1);

							// start a loop until the end of the file
							for(int i = 0; i < numOfRecord; i++)
							{
								// open the file for read and write binary mode
								openBinaryFile();

								// seak to the position
								output1.seek(i * RandomAccessEmployeeRecord.SIZE);

								// read throught the file
								record1.read(output1);

								// if read record employee == textfield record employee
								// set insert flag to false
								if(record1.getEmployeeID() == record.getEmployeeID())
								{
									insert = false;
									break;
								}
								else
									insert = true;
								//	delete = false;
							}

							// insert the record to the end of the file
							if(insert)
							{
								numOfRecord += 1;

								// call RandomAccessFile method seeek to position the file
								// poistion pointer for object output to the byte location
								output1.seek((numOfRecord - 1) * RandomAccessEmployeeRecord.SIZE);

								// write to binary file
								record.write(output1);
								insert = false;
								delete = false;
								closeBinaryFile();
							}
/*
						JOptionPane.showMessageDialog(null, firstToken , " First",
						JOptionPane.WARNING_MESSAGE);

						JOptionPane.showMessageDialog(null, secondToken , " Second",
						JOptionPane.WARNING_MESSAGE);
						JOptionPane.showMessageDialog(null, thirdToken , " Third",
						JOptionPane.WARNING_MESSAGE);

						JOptionPane.showMessageDialog(null, fourthToken , " Fourth",
						JOptionPane.WARNING_MESSAGE);

						JOptionPane.showMessageDialog(null, fifthToken , " Fifth",
						JOptionPane.WARNING_MESSAGE);

						JOptionPane.showMessageDialog(null, sixthToken , " Sixth",
						JOptionPane.WARNING_MESSAGE);
						JOptionPane.showMessageDialog(null, eighteenthToken , " Eighteenth",
						JOptionPane.WARNING_MESSAGE);
*/
					}

					else if(firstToken.equals("CLIENT>>>Remo"))
					{
						/*
						JOptionPane.showMessageDialog(null, firstToken , " First",
						JOptionPane.WARNING_MESSAGE);
						JOptionPane.showMessageDialog(null, secondToken , " Second",
						JOptionPane.WARNING_MESSAGE);
						*/
						currentRecordPosition = Integer.parseInt(secondToken);

						/*
						JOptionPane.showMessageDialog(null, currentRecordPosition , "currentPosition",
						JOptionPane.WARNING_MESSAGE);
						*/
						openBinaryFile();
						goToRecord();
						sendData(outgoing_message);
						message = "";
					}

					else if(firstToken.equals("CLIENT>>>RemoCo"))
					{
						record.setEmployeeID(0);

						// for debugging purpose
						ReportTo1 = Integer.parseInt(eighteenthToken);

						// get the textfield information and put it in
						// record for checking with the read record
						record.setLastName(thirdToken );
						record.setFirstName(fourthToken);
						record.setTitle(fifthToken);
						record.setTitleOfCourtesy(sixthToken);
						record.setBirthDate(seventhToken);
						record.setHireDate(eighthToken);
						record.setAddress(ninthToken);
						record.setCity(tenthToken);
						record.setRegion(elevenToken);
						record.setPostalCode(twelfthToken);
						record.setCountry(thirteenthToken);
						record.setHomePhone(fourteenthToken);
						record.setExtension(fifteenthToken);
						record.setPhoto(sixteenthToken);
						record.setNotes(seventeenthToken);
						record.setReportsTo(ReportTo1);

						// call RandomAccessFile method seeek to position the file
						// poistion pointer for object output to the byte location
						output1.seek((currentRecordPosition-1) * RandomAccessEmployeeRecord.SIZE);

						// write to binary file
						record.write(output1);
						delete = false;
						insert = false;
						//closeBinaryFile();
					}

					else if(firstToken.equals("CLIENT>>>ModifyCo"))
					{
						RandomAccessEmployeeRecord record2 = new RandomAccessEmployeeRecord();
						employeeID1 = Integer.parseInt(secondToken);

						// for debugging purpose
						ReportTo1 = Integer.parseInt(eighteenthToken);

						// get the textfield information and put it in
						// record for checking with the read record
						record2.setEmployeeID(employeeID1);
						record2.setLastName(thirdToken );
						record2.setFirstName(fourthToken);
						record2.setTitle(fifthToken);
						record2.setTitleOfCourtesy(sixthToken);
						record2.setBirthDate(seventhToken);
						record2.setHireDate(eighthToken);
						record2.setAddress(ninthToken);
						record2.setCity(tenthToken);
						record2.setRegion(elevenToken);
						record2.setPostalCode(twelfthToken);
						record2.setCountry(thirteenthToken);
						record2.setHomePhone(fourteenthToken);
						record2.setExtension(fifteenthToken);
						record2.setPhoto(sixteenthToken);
						record2.setNotes(seventeenthToken);
						record2.setReportsTo(ReportTo1);
						output1.seek((currentRecordPosition-1) * RandomAccessEmployeeRecord.SIZE);

						// write to binary file
						record2.write(output1);
					}
				}

				System.out.printf("Mes1 %s", outgoing_message);
				System.out.printf("currentRecordPosition %s", currentRecordPosition);
            	displayMessage( "\n" + message ); // display message
         	} // end try

         	catch ( ClassNotFoundException classNotFoundException )
         	{
            	displayMessage( "\nUnknown object type received" );
         	} // end catch

      	} while ( !message.equals( "CLIENT>>> TERMINATE" ) );
   	} // end method processConnection

   	// close streams and socket
   	private void closeConnection()
   	{
    	displayMessage( "\nTerminating connection\n" );
      	setTextFieldEditable( false ); // disable enterField

      	try
      	{
        	output.close(); // close output stream
        	input.close(); // close input stream
         	connection.close(); // close socket
      	} // end try
      	catch ( IOException ioException )
      	{
        	ioException.printStackTrace();
      	} // end catch
   	} // end method closeConnection

   	// send message to client
   	private void sendData( String message )
   	{
      	try // send object to client
      	{
			if(message.equals("First"))
			{
				openBinaryFile();
				readFirstRecord();
				message = outgoing_message;
			}

			else if(message.equals("Next"))
			{
				openBinaryFile();
				readNextRecord();
				message = outgoing_message;
			}

			else if(message.equals("Previous"))
			{
				openBinaryFile();
				readPreviousRecord();
				message = outgoing_message;
			}

			else if(message.equals("Last"))
			{
				openBinaryFile();
				readLastRecord();
				message = outgoing_message;
			}

			else if(message.equals("Goto"))
			{
				openBinaryFile();
				goToRecord();
				message = outgoing_message;
			}

			output.writeObject( "" + message );
         	output.flush(); // flush output to client
         	displayMessage( "\nSERVER>>> " + message );
		/*
        	output.writeObject( "SERVER>>> " + message );
         	output.flush(); // flush output to client
         	displayMessage( "\nSERVER>>> " + message );
         */
      	} // end try
      	catch ( IOException ioException )
      	{
        	displayArea.append( "\nError writing object" );
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
                	displayArea.append( messageToDisplay ); // append message
            	} // end method run
         	} // end anonymous inner class
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
               		enterField.setEditable( editable );
            	} // end method run
         	}  // end inner class
      	); // end call to SwingUtilities.invokeLater
   	} // end method setTextFieldEditable

   	// This function opens the file in binary mode
   	public void openBinaryFile()
   	{
   		try
   		{
   			output1 = new RandomAccessFile("Employee.bin", "rw");

   			// get the size of file in bytes
   			sizeOfFile = output1.length();

   			// get the number of records
           	numOfRecord = (int)output1.length()/RandomAccessEmployeeRecord.SIZE;
   		}

   		catch(IOException ioException)
   		{
   			System.err.println("File does not exist");
   		}
   	}

   	// This function close the file after done writing the
   	// binary file
   	public void closeBinaryFile()
   	{
   		try
   		{
   			if(output1 != null)
   				output1.close();
   		}

   		catch(IOException ioException)
   		{
   			System.err.println("Error closing file");
   			System.exit(1);
   		}
   	}

  	// enable user to select file to open
	public void openFile()
	{
		try // open file
		{
			input1 = new RandomAccessFile( "Employee.bin", "r" );

			// get the size of file in bytes
			sizeOfFile = input1.length();

			// get the number of records
			numOfRecord = (int)input1.length()/RandomAccessEmployeeRecord.SIZE;

			// For debugging purpose only
			System.out.printf("%-10snumber of records \n", numOfRecord);

			// For debugging purpose only
			System.out.printf("%-10ssize of the file in bytes \n", sizeOfFile);
		} // end try

		catch ( IOException ioException )
		{
			System.err.println( "File does not exist." );
			ioException.printStackTrace();
		} // end catch
	} // end method openFile

   	// close file and terminate application
   	public void closeFile()
   	{
		try // close file and exit
      	{
        	if ( input != null )
            input.close();
      	} // end try

      	catch ( IOException ioException )
      	{
        	System.err.println( "Error closing file." );
        	System.exit( 1 );
      	} // end catch
   	} // end method closeFile

    // This method will read the first and display records
   	public void readFirstRecord()
    {
 		int tempNum = 0;
 		tempNum = numOfRecord - 1;
 		String output = "";
 		RandomAccessEmployeeRecord record;
 		currentRecordPosition = numOfRecord-tempNum+1;

 		try
 		{
 			do{
 				record = getRecord(currentRecordPosition);
 				if(record.getEmployeeID() == 0)
 					currentRecordPosition++;

 			}while(record.getEmployeeID() == 0);

 			// for debugging purpose only
 			System.out.printf("numOfRecord %d \n", numOfRecord);
 			System.out.printf("tempNum %d\n", tempNum);
 			System.out.printf("currentRecordPosition %d\n", currentRecordPosition );
			output += " ";
			output += "|";
 			output += record.getEmployeeID();
 			output += "|";
 			output += record.getLastName();
 			output += "|";
 			output += record.getFirstName();
 			output += "|";
 			output += record.getTitle();
 			output += "|";
 			output += record.getTitleOfCourtesy();
 			output += "|";
 			output += record.getBirthDate();
 			output += "|";
 			output += record.getHireDate();
  			output += "|";
 			output += record.getAddress();
 			output += "|";
 			output += record.getCity();
 			output += "|";
 			output += record.getRegion();
 			output += "|";
 			output += record.getPostalCode();
 			output += "|";
 			output += record.getCountry();
 			output += "|";
 			output += record.getHomePhone();
 			output += "|";
 			output += record.getExtension();
 			output += "|";
 			output += record.getPhoto();
 			output += "|";
 			output += record.getNotes();
 			output += "|";
 			output += record.getReportsTo();
 			System.out.printf("output%s", output);
			outgoing_message = output;
 		}

 		catch ( EOFException eofException ) // close file
 		{
 		       return; // end of file was reached
 		} // end catch

 		catch ( IOException ioException )
 		{
 			System.err.println( "Error reading file." );
 		    System.exit( 1 );
       	} // end catch

   	} // end method readRecords

	// This method will read the next record and display record
  	public void readNextRecord()
   	{
		currentRecordPosition = currentRecordPosition + 1;
		String output = "";
		RandomAccessEmployeeRecord record;
		try
		{
			do
			{
				record = getRecord(currentRecordPosition);
				if(record.getEmployeeID() == 0)
					currentRecordPosition++;

			}while(record.getEmployeeID() == 0);

			// for debugging purpose
			System.out.printf("numOfRecord %d \n", numOfRecord);
			System.out.printf("currentRecordPosition %d\n", currentRecordPosition );

			output += " ";
			output += "|";
			output += record.getEmployeeID();
			output += "|";
			output += record.getLastName();
			output += "|";
			output += record.getFirstName();
			output += "|";
			output += record.getTitle();
			output += "|";
			output += record.getTitleOfCourtesy();
			output += "|";
			output += record.getBirthDate();
			output += "|";
			output += record.getHireDate();
			output += "|";
			output += record.getAddress();
			output += "|";
			output += record.getCity();
			output += "|";
			output += record.getRegion();
			output += "|";
			output += record.getPostalCode();
			output += "|";
			output += record.getCountry();
			output += "|";
			output += record.getHomePhone();
			output += "|";
			output += record.getExtension();
			output += "|";
			output += record.getPhoto();
			output += "|";
			output += record.getNotes();
			output += "|";
			output += record.getReportsTo();
			outgoing_message = output;
		}

		catch ( EOFException eofException ) // close file
		{
		       return; // end of file was reached
		} // end catch

		catch ( IOException ioException )
		{
			System.err.println( "Error reading file." );
		    System.exit( 1 );
      	} // end catch
  	}

	// This method will read the previous record and display record
  	public void readPreviousRecord()
   	{
		currentRecordPosition = currentRecordPosition - 1;
		String output = "";
		RandomAccessEmployeeRecord record;

		try
		{
			do
			{
				record = getRecord(currentRecordPosition);
				if(record.getEmployeeID() == 0)
					currentRecordPosition--;

			}while(record.getEmployeeID() == 0);

			output += " ";
			output += "|";
			output += record.getEmployeeID();
			output += "|";
			output += record.getLastName();
			output += "|";
			output += record.getFirstName();
			output += "|";
			output += record.getTitle();
			output += "|";
			output += record.getTitleOfCourtesy();
			output += "|";
			output += record.getBirthDate();
			output += "|";
			output += record.getHireDate();
			output += "|";
			output += record.getAddress();
			output += "|";
			output += record.getCity();
			output += "|";
			output += record.getRegion();
			output += "|";
			output += record.getPostalCode();
			output += "|";
			output += record.getCountry();
			output += "|";
			output += record.getHomePhone();
			output += "|";
			output += record.getExtension();
			output += "|";
			output += record.getPhoto();
			output += "|";
			output += record.getNotes();
			output += "|";
			output += record.getReportsTo();
			outgoing_message = output;
		}

		catch ( EOFException eofException ) // close file
		{
		   return; // end of file was reached
		} // end catch

		catch ( IOException ioException )
		{
			System.err.println( "Error reading file." );
			System.exit( 1 );
      	} // end catch
  	}

  // This method will read the last record and display record
  	public void readLastRecord()
   	{
		String output = "";
		currentRecordPosition = numOfRecord;
		RandomAccessEmployeeRecord record;
		try
		{
			do{
				record = getRecord(currentRecordPosition);
				if(record.getEmployeeID() == 0)
					currentRecordPosition--;

			}while(record.getEmployeeID() == 0);

			output += " ";
			output += "|";
			output += record.getEmployeeID();
			output += "|";
			output += record.getLastName();
			output += "|";
			output += record.getFirstName();
			output += "|";
			output += record.getTitle();
			output += "|";
			output += record.getTitleOfCourtesy();
			output += "|";
			output += record.getBirthDate();
			output += "|";
			output += record.getHireDate();
			output += "|";
			output += record.getAddress();
			output += "|";
			output += record.getCity();
			output += "|";
			output += record.getRegion();
			output += "|";
			output += record.getPostalCode();
			output += "|";
			output += record.getCountry();
			output += "|";
			output += record.getHomePhone();
			output += "|";
			output += record.getExtension();
			output += "|";
			output += record.getPhoto();
			output += "|";
			output += record.getNotes();
			output += "|";
			output += record.getReportsTo();
			outgoing_message = output;
		}

		catch ( EOFException eofException ) // close file
		{
		   return; // end of file was reached
		} // end catch

		catch ( IOException ioException )
		{
			System.err.println( "Error reading file." );
			System.exit( 1 );
      	} // end catch
  	}

	public void goToRecord()
	{
		//String employeeID = JOptionPane.showInputDialog("Enter the Employee ID");
		//currentRecordPosition = Integer.parseInt(employeeID);
		String output = "";
		try
		{
		//	currentRecordPosition = Integer.parseInt(currentRecordPosition1);
/*
			JOptionPane.showMessageDialog(null, currentRecordPosition , " currentPosition",
					JOptionPane.WARNING_MESSAGE);
*/
			openFile();

			RandomAccessEmployeeRecord record = getRecord(currentRecordPosition);

			output += " ";
			output += "|";
			output += record.getEmployeeID();
			output += "|";
			output += record.getLastName();
			output += "|";
			output += record.getFirstName();
			output += "|";
			output += record.getTitle();
			output += "|";
			output += record.getTitleOfCourtesy();
			output += "|";
			output += record.getBirthDate();
			output += "|";
			output += record.getHireDate();
			output += "|";
			output += record.getAddress();
			output += "|";
			output += record.getCity();
			output += "|";
			output += record.getRegion();
			output += "|";
			output += record.getPostalCode();
			output += "|";
			output += record.getCountry();
			output += "|";
			output += record.getHomePhone();
			output += "|";
			output += record.getExtension();
			output += "|";
			output += record.getPhoto();
			output += "|";
			output += record.getNotes();
			output += "|";
			output += record.getReportsTo();
			outgoing_message = output;
		}

		catch ( EOFException eofException ) // close file
		{
			return; // end of file was reached
		} // end catch

		catch ( IOException ioException )
		{
			System.err.println( "Error reading file." );
			System.exit( 1 );
		} // end catch

	}
   // This method get a record from the file.  It is taken from
   // the Deitel book on page 720.  It reads the record with the given
   // employee ID and stores its information in a RandomAccessEmployeeRecord
   // object
   	public RandomAccessEmployeeRecord getRecord(int employeeID)
   		throws IllegalArgumentException, NumberFormatException, IOException
   	{
   		RandomAccessEmployeeRecord record = new RandomAccessEmployeeRecord();

   		if(employeeID < 1 || employeeID > 100)
   			throw new IllegalArgumentException("Out of range");

   		input1.seek((employeeID - 1) * RandomAccessEmployeeRecord.SIZE);
   		record.read(input1);

   		return record;
	}

} // end class Server

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
