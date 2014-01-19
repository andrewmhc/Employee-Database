import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Entry;

/**
 * The Employee class represents a generic employee. Most methods should be
 * based on this. It supports serialisation using Simple.
 */
@Root
public abstract class Employee implements Entry {
	/**
	 * List of valid work locations for the workLocation field.
	 */
	public static String[] validWorkLocations = { "Mississauga", "Regina",
			"Ottawa" };
	/**
	 * The employee number, which is also the key for this class
	 */
	@Attribute final int empNumber;
	/**
	 * The sex of the employee (true for male, false for female).
	 */
	@Attribute
	boolean isMale;
	/**
	 * The employee names Only the middle name can be zero-length
	 */
	@Attribute
	String firstName, middleName, lastName;
	/**
	 * The work location, which must be a member of the validWorkLocations
	 * array.
	 */
	@Attribute
	String workLocation;
	/**
	 * The deductions rate as a float from 0 to 1.
	 */
	@Attribute
	float deductionsRate;

	/**
	 * Retrieves the hash value for hashing purposes. In this implementation,
	 * it's just the employee number.
	 * 
	 * @return The hash code
	 */
	@Override
	public int hashCode() {
		return empNumber;
	}

	/**
	 * Tests for equality between two employees by comparing the employee
	 * numbers, which should be unique.
	 * 
	 * @return whether they are equal
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof Employee && ((Employee) o).empNumber == empNumber;
	}

	/**
	 * The constructor used by the serialisation library which doesn't set any
	 * Attribute or Element* fields. Must be implemented by all subclasses and
	 * initialise any resources needed.
	 */
	public Employee(@Attribute(name="empNumber")int id) {
		empNumber=id;
	}

	/**
	 * Construct a basic employee with these fields.
	 * 
	 * @param id
	 *            initial employee id, which shouldn't change after insertion
	 * @param male
	 *            whether the employee is male
	 * @param first
	 *            the first name of the employee
	 * @param middle
	 *            the middle name of the employee
	 * @param last
	 *            the last name of the employee
	 * @param loc
	 *            the work location of the employee
	 * @param rate
	 *            the deduction rate of the employee
	 */
	public Employee(int id, boolean male, String first, String middle,
			String last, String loc, float rate) {
		empNumber = id;
		isMale = male;
		firstName = first;
		middleName = middle;
		lastName = last;
		assert loc.length() > 0;
		workLocation = loc.substring(0, 1).toUpperCase()
				+ loc.toLowerCase().substring(1);
		deductionsRate = rate;
		assert checkInvariants();
	}

	/**
	 * Checks the invariants to see if the employee is valid
	 * 
	 * @return whether the employee is valid
	 */
	boolean checkInvariants() {
		for (String l : validWorkLocations)
			if (l.equals(workLocation))
				return empNumber >= 100000 && empNumber < 1000000
						&& firstName.length() > 0 && middleName != null
						&& lastName.length() > 0 && deductionsRate >= 0
						&& deductionsRate <= 1;
		return false;
	}

	/**
	 * Calculates and returns the gross pay. Subclasses must implement this
	 * method.
	 * 
	 * @return the gross pay as a float
	 */
	abstract float getGrossPay();

	/**
	 * calculate the net pay as the gross pay less the deduction as given by the
	 * deductions rate
	 * 
	 * @return the net pay
	 */
	float getNetPay() {
		return getGrossPay() * (1 - deductionsRate);
	}

	/**
	 * simple test to make sure the checkInvariants and serialisation works
	 * 
	 * @param argv
	 * @throws Exception
	 *             if construction or serialisation fails
	 */
	public static void main(String[] argv) throws Exception {
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		new org.simpleframework.xml.core.Persister().write(
				new FullTimeEmployee(100000, true, "first", "middle", "last",
						"Mississauga", 0.2f, 50000, 5, 14), baos);
		System.out.print(baos.toString());
	}

	/**
	 * In this implementation, it returns the class name.
	 * 
	 * @return the name of the XML element
	 */
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
}
