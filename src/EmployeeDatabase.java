import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.util.Dictionary;
/**
 * Set representing an employee database.
 * Not all set functions are implemented; unsupported operations do not have
 * annotations.
 */
public class EmployeeDatabase extends Dictionary<Employee>implements Set<Employee>{
	/**
	 * Size of hash table (slots). Not saved because the entries are re-hashed.
	 */
	static final int k=10;
	/**
	 * Number of elements in the hash table.
	 * Not saved because the entries will be re-inserted.
	 */
	private int size=0;
	/**
	 * Format for the saved database as an annotated class
	 */
	@Root protected static class EmployeeDatabaseBackup{
		/**
		 * Version for the format, which should be increased each revision.
		 */
		@SuppressWarnings("unused")@org.simpleframework.xml.Version(revision=1.0)private double version;
		@ElementList(inline=true,type=Employee.class) EmployeeDatabase db;
		/**
		 * Empty constructor for Simple
		 */
		public EmployeeDatabaseBackup(){}
		/**
		 * Construct a backup object from a database
		 * @param empdb the database to back up
		 */
		public EmployeeDatabaseBackup(EmployeeDatabase empdb){
			db=empdb;
		}
	}
	/**
	 * The actual hash table with size elements, where each element e is in
	 * hash(e).
	 */
	protected final ArrayList<ArrayList<Employee>>hash=new ArrayList<ArrayList<Employee>>(k);
	/**
	 * Retrieve an employee by employee number.
	 * @param id the employee number
	 * @return the employee
	 */
	public Employee get(int id){
		for(Employee e:hash.get(id%k))
			if(e.empNumber==id)
				return e;
		return null;
	}
	/**
	 * Empty constructor for Simple.
	 */
	public EmployeeDatabase() {
		while(hash.size()<k)
			hash.add(new ArrayList<Employee>());
	}
	/**
	 * Fire all employees from the database.
	 */
	@Override public void clear(){
		for(ArrayList<Employee>l:hash)
			l.clear();
		size=0;
	}
	/**
	 * Add the employee to the database.
	 * @param e the employee to add
	 * @return if the add was successful
	 */
	@Override public boolean add(Employee e){
		if(contains(e)||!hash(e).add(e))
			return false;
		++size;
		return true;
	}
	/**
	 * Get the bucket for employee e.
	 * @param e the employee
	 * @return the bucket index
	 */
	private ArrayList<Employee>hash(Employee e){
		return hash.get(e.hashCode()%k);
	}
	/**
	 * Tests whether the employee is in the database.
	 * @param o the employee
	 * @return whether o is in the hash table
	 */
	@Override public boolean contains(Object o){
		return hash((Employee)o).contains(o);
	}
	/**
	 * Tests if the set is empty
	 * @return whether the set is empty
	 */
	@Override public boolean isEmpty(){
		return size==0;
	}
	/**
	 * Iterate through the set
	 * @return an Iterator<Employee>
	 */
	@Override public Iterator<Employee>iterator() {
		return new Iterator<Employee>(){
			final Iterator<ArrayList<Employee>>it=hash.iterator();
			Iterator<Employee>cur=it.next().iterator();
			@Override public boolean hasNext(){
				while(!cur.hasNext())
					if(it.hasNext())
						cur=it.next().iterator();
					else return false;
				return true;
			}
			@Override public Employee next(){
				while(!cur.hasNext())
					cur=it.next().iterator();
				return cur.next();
			}
			@Override public void remove(){
				cur.remove();
			}
		};
	}
	/**
	 * Remove the employee from the database.
	 * @param o the employee to remove
	 * @return whether the remove was successful
	 */
	@Override public boolean remove(Object o) {
		return hash((Employee)o).remove(o)&&size-->0;
	}
	/**
	 * Get the number of employees in the database.
	 * @return the number of employees in the database
	 */
	@Override public int size(){
		return size;
	}
	@Override public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	@Override public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	@Override public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	@Override public boolean addAll(Collection<? extends Employee> c) {
		throw new UnsupportedOperationException();
	}
	@Override public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Read an employee database from an input stream and return it.
	 * @param f the input stream to read the database from
	 * @return the new database
	 */
	public static EmployeeDatabase readFromStream(InputStream f){
		try{
			return new Persister().read(EmployeeDatabaseBackup.class,f).db;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Write the database to an output stream.
	 * @param f the stream to write to
	 * @return whether the write was successful
	 */
	public boolean writeToStream(OutputStream f){
		try{
			new Persister().write(new EmployeeDatabaseBackup(this),f);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Simple test to write a backup file with garbage data and read it again,
	 * and iterate through it.
	 * @param argv
	 * @throws FileNotFoundException
	 */
	public static void main(String[]argv) throws FileNotFoundException{
		final String BACKUP_FILE="employee_database_backup.xml";
		EmployeeDatabase empdb=new EmployeeDatabase();
		empdb.add(new FullTimeEmployee(100000, true, "first", "middle", "last", "Mississauga", 0.2f, 50000, 5, 14));
		empdb.add(new FullTimeEmployee(100001, true, "first2", "middle2", "last2", "Mississauga", 0.4f, 62500, 5, 14));
		empdb.writeToStream(new FileOutputStream(BACKUP_FILE));
		System.out.println(empdb.size()+" employees written");
		empdb=EmployeeDatabase.readFromStream(new FileInputStream(BACKUP_FILE));
		for(Employee e:empdb)
			assert e.checkInvariants();
		System.out.println(empdb.size()+" employees read: "+empdb);
	}
}
