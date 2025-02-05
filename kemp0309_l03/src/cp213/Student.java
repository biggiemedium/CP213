package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author James
 * @version 2025-02-05
 */
public class Student implements Comparable<Student> {

    // Attributes
    private LocalDate birthDate = null;
    private String forename = "";
    private int id = 0;
    private String surname = "";

    /**
     * Instantiates a Student object.
     *
     * @param id        student ID number
     * @param surname   student surname
     * @param forename  name of forename
     * @param birthDate birthDate in 'YYYY-MM-DD' format
     */
    public Student(int id, String surname, String forename, LocalDate birthDate) {

	this.id = id;
	this.surname = surname;
	this.forename = forename;
	this.birthDate = birthDate;

	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString() Creates a formatted string of student data.
     */
    @Override
    public String toString() {
	String string = "";
	
	string += "Name: " + surname + ", " + forename + "\n";
	string += "ID: " + id + "\n";
	string += "Birthdate: " + birthDate.getYear() + "-" 
	        + birthDate.getMonthValue() + "-" + birthDate.getDayOfMonth();
	
	return string;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Student target) {
    	int result = 0;
    	result = surname.compareTo(target.surname);

    	if(result == 0) {
    		result = target.getForename().compareTo(forename);
    	}

    	if(result == 0) {
    		result = Integer.compare(id, target.id);
    	}
    	
    	return result;
    }


    // getters and setters defined here

    /**
     * @return birthDate
     */
    public LocalDate getBirthDate() {
    	return this.birthDate;
    }
    
    /**
     * @param date
     */
    public void setBirthDate(LocalDate date) {
    	this.birthDate = date;
    }
    
    /**
     * @return id
     */
    public int getId() {
    	return this.id;
    }

    
    /**
     * @param value
     */
    public void setId(int value) {
    	this.id = value;
    }
    
    /**
     * @return forename
     */
    public String getForename() {
    	return this.forename;
    }
    
    
    /**
     * @param value
     */
    public void setForename(String value) {
    	this.forename = value;
    }
    
    
    
    /**
     * @return surname
     */
    public String getSurname() {
    	return this.surname;
    }
    
    /**
     * @param value
     */
    public void setSurname(String value) {
    	this.surname = value;
    }
}
