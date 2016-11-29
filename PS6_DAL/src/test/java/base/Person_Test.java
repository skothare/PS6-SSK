package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	
	@AfterClass
	public static void teardownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		PersonDAL.deletePerson(person1.getPersonID());
	}
	
	@Test
	public void addPersonTest(){
		PersonDomainModel person;
		PersonDAL.addPerson(person1);
		person= PersonDAL.getPerson(person1.getPersonID());
		assertTrue(person != null);
	}
	
	/**
	 * Tests if the person details are deleted from the database.
	 */
	@Test
	public void deletePersonTest(){
		PersonDomainModel person;
		PersonDAL.addPerson(person1);
		person = PersonDAL.getPerson(person1.getPersonID());
		
		PersonDAL.deletePerson(person1.getPersonID());
		person = PersonDAL.getPerson(person1.getPersonID());
		assertTrue(person != null);
	}
	
	/**
	 * Tests if the person details of the person added are updated or not.
	 */
	@Test
	public void updatePersonTest(){
		PersonDomainModel person;
		PersonDAL.addPerson(person1);
		person1.setCity("Boston");
		person1.setStreet("Harbour Street");
		assertTrue(person1.getCity()=="Boston");
		assertTrue(person1.getStreet()=="Harbour Street");
		
		PersonDAL.updatePerson(person1);
		person = PersonDAL.getPerson(person1.getPersonID());
		assertTrue(person.getCity() == person1.getCity());
		assertTrue(person.getStreet() == person1.getStreet());
	}
}
