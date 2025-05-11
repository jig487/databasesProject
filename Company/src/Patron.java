public class Patron {
    private int pid;
    private String firstName; 
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String membershipStartDate;
	
	public Patron(int pid, String firstName, String lastName, String email, String phone, String address, String membershipStartDate) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMembershipStartDate() {
        return this.lastName;
    }

    public void setMembershipStartDate(String msd) {
        this.membershipStartDate = msd;
    }

	@Override
	public String toString() {
		return "Patron [PID=" + pid + ", FirstName=" + firstName + ", Lastname=" + lastName + ", Email=" + email + ", Phone=" + phone + ", Address=" + address + ", MembershipStartDate =" + membershipStartDate + "]";
	}
}