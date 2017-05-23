/***************************
	Patrick Benitez
	RandomAccessEmployeeRecord.java
****************************/

/****************************************************************************
References:
	Fig. 14.23: RandomAccessEmployeeRecord.java
	Subclass of EmployeeRecord for random-access file programs.
	I modify the deitel code page 708 to page 709.  This code is taken from
	the Deitel Book.

	The RandomAccessEmployeeRecord serves an an intermediate class which is
	the extension of the Employees class.  The RandomAccessEmployeeRecord
	can have a RandomAccessFile which is useful for direct access
	applications.  It require that all the records in a file be the same
	fixed length.  Using fixed length records makes it easy for a program
	to calculate the exact location of any record relative to the beginning
	of the file.
****************************************************************************/


import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessEmployeeRecord extends Employees
{
	// Record Size in bytes
    public static final int SIZE = 458;

   // no-argument constructor calls other constructor with default values
   	public RandomAccessEmployeeRecord()
   	{
		this( 0, "", "","", "", "", "","", "","", "", "", "","", "", "", 0  );
   	} // end no-argument RandomAccessEmployeeRecord constructor

   // The constructor initialize a RandomAccessEmployeeRecord
   	public RandomAccessEmployeeRecord( int employee_id, String last_name, String first_name,
   						String title1, String title_of_courtesy, String birth_date,
   						String hire_date, String address1, String city1, String region1,
   						String postal_code, String country1, String home_phone,
   						String extension1, String photo1, String notes1,int reports_to)

   	{
		super( employee_id, last_name, first_name, title1,title_of_courtesy, birth_date,
			hire_date, address1, city1, region1, postal_code, country1, home_phone,
			extension1, photo1, notes1,reports_to);
   	} // end four-argument RandomAccessEmployeeRecord constructor


   // This function read a record from specified RandomAccessFile
   public void read( RandomAccessFile file ) throws IOException
   {
	   	setEmployeeID(file.readInt());
	   	setLastName(readName( file ));
		setFirstName(readName( file ));
		setTitle(readName( file ));
		setTitleOfCourtesy(readName( file ));
		setBirthDate(readName( file ));
		setHireDate(readName( file ));
		setAddress(readName( file ));
		setCity(readName( file ));
		setRegion(readName( file ));
		setPostalCode(readName( file ));
		setCountry(readName( file ));
		setHomePhone(readName( file ));
		setExtension(readName( file ));
		setPhoto(readName( file ));
		setNotes(readName( file ));
		setReportsTo(file.readInt());

   	} // end method read

   // This function ensures that name is proper length
   	private String readName( RandomAccessFile file ) throws IOException
   	{

		char name[] = new char[ 15 ], temp;

      	for ( int count = 0; count < name.length; count++ )
      	{
        	temp = file.readChar();
         	name[ count ] = temp;
      	} // end for

      	return new String( name ).replace( '\0', ' ' );
   	} // end method readName

   // This function writes a record to specified RandomAccessFile
   	public void write( RandomAccessFile file ) throws IOException
   	{
		file.writeInt( getEmployeeID() );
      	writeName( file, getLastName() );
      	writeName( file, getFirstName() );
      	writeName( file, getTitle());
      	writeName( file, getTitleOfCourtesy() );
      	writeName( file, getBirthDate());
      	writeName( file, getHireDate());
      	writeName( file, getAddress());
     	writeName( file, getCity());
      	writeName( file, getRegion());
      	writeName( file, getPostalCode());
      	writeName( file, getCountry());
      	writeName( file, getHomePhone());
      	writeName( file, getExtension());
      	writeName( file, getPhoto());
      	writeName( file, getNotes());
      	file.writeInt( getReportsTo()  );

   } // end method write

   // This function writes a name to file; maximum of 15 characters
   private void writeName( RandomAccessFile file, String name )
      throws IOException
   {
      StringBuffer buffer = null;

      if ( name != null )
         buffer = new StringBuffer( name );
      else
    //     buffer = new StringBuffer( 312 );

    //  buffer.setLength( 312 );
      	 buffer = new StringBuffer( 15 );

      buffer.setLength( 15 );
      file.writeChars( buffer.toString() );
   } // end method writeName
} // end class RandomAccessEmployeeRecord

/*************************************************************************
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
