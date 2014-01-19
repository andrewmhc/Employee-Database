import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * The FullTimeEmployee class represents a full-time employee. It supports all
 * the operations of a regular employee but it has more fields.
 * 
 * @see PartTimeEmployee
 */
@Root
public class FullTimeEmployee extends Employee {
	/**
	 * Salary in dollars (annual)
	 */
	@Attribute
	int salary;
	/**
	 * Complete years of service for the employee
	 */
	@Attribute
	int yearsOfService;
	/**
	 * Annual maximum vacation days
	 */
	@Attribute
	int daysVacPerYear;

	/**
	 * The empty constructor for Simple which must be implemented.
	 */
	public FullTimeEmployee(@Attribute(name="empNumber")int id) {
		super(id);
	}

	/**
	 * Constructor with extra parameters for a full-time employee
	 * 
	 * @param sal
	 * @param yrs
	 * @param vac
	 * @see Employee
	 */
	public FullTimeEmployee(int id, boolean male, String first, String middle,
			String last, String loc, float rate, int sal, int yrs, int vac) {
		super(id, male, first, middle, last, loc, rate);
		salary = sal;
		yearsOfService = yrs;
		daysVacPerYear = vac;
		assert checkInvariants();
	}

	/**
	 * Check superclass invariants as well as full-time employee invariants
	 * 
	 * @return whether the invariants are satisfied
	 */
	@Override
	boolean checkInvariants() {
		return super.checkInvariants() && salary >= 0 && yearsOfService >= 0
				&& daysVacPerYear >= 0;
	}

	/**
	 * Calculate the gross pay
	 * 
	 * @return the gross pay as a float
	 */
	@Override
	float getGrossPay() {
		return salary;
	}
}
