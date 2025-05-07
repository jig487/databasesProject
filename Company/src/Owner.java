public class Owner {
    private int oid;
    private String firstName; 
    private String lastName;
	
	public Owner(int oid, String firstName, String lastName) {
		super();
        this.oid = oid;
        this.firstName = firstName;
        this.lastName = lastName;
	}

    public int getoid() {
        return this.oid;
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
		return "Owner [OID=" + oid + ", FirstName=" + firstName + ", Lastname=" + lastName + "]";
	}
}
