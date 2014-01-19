import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * The FullTimeEmployee class represents a part-time employee. It supports all
 * the operations of a regular employee but it has more fields.
 * @see FullTimeEmployee
 */
@Root
public class PartTimeEmployee extends Employee {
	/**
	 * Hourly wage for the employee as a float
	 */
	@Attribute
	float hourlyWage;
	/**
	 * The average number of hours worked in a week for the employee
	 */
	@Attribute
	float hrsPerWeek;
	/**
	 * The number of hours worked in a year for the employee
	 */
	@Attribute
	int weeksPerYear;

	/**
	 * The empty constructor for Simple which must be implemented.
	 */
	public PartTimeEmployee(@Attribute(name="empNumber")int id) {
		super(id);
	}

	/**
	 * Constructor which has extra parameters for a part time employee
	 * 
	 * @param wage
	 *            the employee's hourly wage as a float
	 * @param hrs
	 *            the hours worked per week as a float
	 * @param weeks
	 *            the weeks worked per year as a float
	 * @see Employee
	 */
	public PartTimeEmployee(int id, boolean male, String first, String middle,
			String last, String loc, float rate, float wage, float hrs,
			int weeks) {
		super(id, male, first, middle, last, loc, rate);
		hourlyWage = wage;
		hrsPerWeek = hrs;
		weeksPerYear = weeks;
		assert checkInvariants();
	}

	/**
	 * extended invariant checking for part time employees
	 * 
	 * @return whether the invariants are satisfied
	 */
	@Override
	boolean checkInvariants() {
		return super.checkInvariants() && hourlyWage >= 0 && hrsPerWeek >= 0
				&& weeksPerYear >= 0;
	}

	/**
	 * @return the gross pay for a part-time employee
	 */
	@Override
	float getGrossPay() {
		return hourlyWage * hrsPerWeek * weeksPerYear;
	}
}
