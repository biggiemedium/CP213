package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author David Brown
 * @version 2022-02-25
 */
public class IA extends Student {

	private String course;
	
	public IA(String lastName, String firstName, String id, String course) {
		super(lastName, firstName, id);
		this.course = course;
	}
	
	public String getCourse() {
		return this.course;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Course: " + course;
	}
}
