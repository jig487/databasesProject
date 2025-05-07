public class Patron {
    private int pid;
    private String firstName; 
    private String lastName;
	
	public Patron(int oid, String firstName, String lastName) {
		super();
        this.pid = oid;
        this.firstName = firstName;
        this.lastName = lastName;
	}

    public int getpid() {
        return this.pid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	@Override
	public String toString() {
		return "Patron [PID=" + pid + ", FirstName=" + firstName + ", Lastname=" + lastName + "]";
	}
}