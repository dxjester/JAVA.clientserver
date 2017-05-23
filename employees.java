/************************
	Patrick Benitez
	Employees.java
*************************/

/****************************************************************************
	References:  I code this from my head

	The Employee class maintains the information of an Employee.  It has a
	default constructor, constructor, accessor and modifiers

*****************************************************************************/

public class Employees
{

	private int employeeID;   			// 4 byte
	private String lastName;			// 30 byte
	private String firstName;			// 30 byte
	private String title;				// 30 byte
	private String titleOfCourtesy;		// 30 byte
	private String birthDate;			// 30 byte
	private String hireDate;			// 30 byte
	private String Address;				// 30 byte
	private String city;				// 30 byte
	private String region;				// 30 byte
	private String postalCode;			// 30 byte
	private String country;				// 30 byte
	private String homePhone;			// 30 byte
	private String extension;			// 30 byte
	private String photo;				// 30 byte
	private String notes;				// 30 byte
	private int reportsTo;				// 4 byte

	// Default constructor that sets everything to null
	public Employees()
    {
		this( 0, "", "","", "", "", "","", "","", "", "", "","", "", "", 0 );
		// call seventeen-argument constructor
    }

	// Constructor that calls the modifier set function to initialize each data type
	public Employees(int employee_id, String last_name, String first_name, String title1,
						String title_of_courtesy, String birth_date, String hire_date, String address1,
						String city1, String region1, String postal_code, String country1,
						String home_phone, String extension1, String photo1, String notes1,
						int reports_to)
	{
		setEmployeeID(employee_id);
		setLastName(last_name);
		setFirstName(first_name);
		setTitle(title1);
		setTitleOfCourtesy(title_of_courtesy);
		setBirthDate(birth_date);
		setHireDate(hire_date);
		setAddress(address1);
		setCity(city1);
		setRegion(region1);
		setPostalCode(postal_code);
		setCountry(country1);
		setHomePhone(home_phone);
		setExtension(extension1);
		setPhoto(photo1);
		setNotes(notes1);
		setReportsTo(reports_to);
	}

	// This function modifier set the employee id
	public void setEmployeeID(int employeeid)
	{
		employeeID = employeeid;
	}

	// This function accessor get the employee id
	public int getEmployeeID()
	{
		return employeeID;
	}

	// This function modifier set the last name
	public void setLastName(String lastname)
	{
		lastName = lastname;
	}

	// This function accessor get the last name
	public String getLastName()
	{
		return lastName;
	}

	// This function modifier set the first name
	public void setFirstName(String firstname)
	{
		firstName = firstname;
	}

	// This function accessor get the first name
	public String getFirstName()
	{
		return firstName;
	}

	// This function modifier set the title
	public void setTitle(String Title)
	{
		title = Title;
	}

	// This function accessor get the title
	public String getTitle()
	{
		return title;
	}

	// This function modifier set the title of courtesy
	public void setTitleOfCourtesy(String titleofcourtesy)
	{
		titleOfCourtesy = titleofcourtesy;
	}

	// This function accessor get the title of courtesy
	public String getTitleOfCourtesy()
	{
		return titleOfCourtesy;
	}

	// This function modifier get the last birthdate
	public void setBirthDate(String birthdate)
	{
		birthDate = birthdate;
	}

	// This function accessor get the birthdate
	public String getBirthDate()
	{
		return birthDate;
	}

	// This function modifier set the hire date
	public void setHireDate(String hiredate)
	{
		hireDate = hiredate;
	}

	// This function accessor get the hire date
	public String getHireDate()
	{
		return hireDate;
	}

	// This function modifier set the address
	public void setAddress(String address)
	{
		Address = address;
	}

	// This function accessor set the address
	public String getAddress()
	{
		return Address;
	}

	// This function modifier set the city
	public void setCity(String City)
	{
		city = City;
	}
	// This function accessor get the city
	public String getCity()
	{
		return city;
	}

	// This function modifier set the region
	public void setRegion(String Region)
	{
		region = Region;
	}

	// This function accessor get the region
	public String getRegion()
	{
		return region;
	}

	// This function modifier set the postal code
	public void setPostalCode(String postalcode)
	{
		postalCode = postalcode;
	}

	// This function accessor get the postal code
	public String getPostalCode()
	{
		return postalCode;
	}

	// This function modifier set the country
	public void setCountry(String Country)
	{
		country = Country;
	}

	// This function accessor get the country
	public String getCountry()
	{
		return country;
	}

	// This function modifier set the home phone
	public void setHomePhone(String homephone)
	{
		homePhone = homephone;
	}

	// This function accessor get the home phone
	public String getHomePhone()
	{
		return homePhone;
	}


	// This function modifier set the extension
	public void setExtension(String Extension)
	{
		extension = Extension;
	}

	// This function accessor get the extension
	public String getExtension()
	{
		return extension;
	}

	// This function modifier set the photo
	public void setPhoto(String Photo)
	{
		photo = Photo;
	}

	// This function accessor get the photo
	public String getPhoto()
	{
		return photo;
	}

	// This function modifier set the notes
	public void setNotes(String Notes)
	{
		notes = Notes;
	}

	// This function accessor get the notes
	public String getNotes()
	{
		return notes;
	}

	// This function modifier set the report to
	public void setReportsTo(int ReportsTo)
	{
		reportsTo = ReportsTo;
	}

	// This function accessor get the report to
	public int getReportsTo()
	{
		return reportsTo;
	}

}
