package socialmediaplatform.model;

public class Account {
	
	private int accountId;
	private String fName;
	private String lName;
	private String bDate;
	private String username;
	private String password;
	private String email;
	private int sQuest1;
	private int sQuest2;
	private int sQuest3;
	private String sAnsw1;
	private String sAnsw2;
	private String sAnsw3;
	private String bioDesc;
	
	
	
	// Constructors
	
	public Account(int accountId, String fName, String lName, String bDate, String username, String password,
			String email, int sQuest1, int sQuest2, int sQuest3, String sAnsw1, String sAnsw2, String sAnsw3,
			String bioDesc) {
		super();
		this.accountId = accountId;
		this.fName = fName;
		this.lName = lName;
		this.bDate = bDate;
		this.username = username;
		this.password = password;
		this.email = email;
		this.sQuest1 = sQuest1;
		this.sQuest2 = sQuest2;
		this.sQuest3 = sQuest3;
		this.sAnsw1 = sAnsw1;
		this.sAnsw2 = sAnsw2;
		this.sAnsw3 = sAnsw3;
		this.bioDesc = bioDesc;
	}
	
	public Account() {
		super();
	}
	
	
	
	// Getters and Setters
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getsQuest1() {
		return sQuest1;
	}

	public void setsQuest1(int sQuest1) {
		this.sQuest1 = sQuest1;
	}

	public int getsQuest2() {
		return sQuest2;
	}

	public void setsQuest2(int sQuest2) {
		this.sQuest2 = sQuest2;
	}

	public int getsQuest3() {
		return sQuest3;
	}

	public void setsQuest3(int sQuest3) {
		this.sQuest3 = sQuest3;
	}

	public String getsAnsw1() {
		return sAnsw1;
	}

	public void setsAnsw1(String sAnsw1) {
		this.sAnsw1 = sAnsw1;
	}

	public String getsAnsw2() {
		return sAnsw2;
	}

	public void setsAnsw2(String sAnsw2) {
		this.sAnsw2 = sAnsw2;
	}

	public String getsAnsw3() {
		return sAnsw3;
	}

	public void setsAnsw3(String sAnsw3) {
		this.sAnsw3 = sAnsw3;
	}

	public String getBioDesc() {
		return bioDesc;
	}

	public void setBioDesc(String bioDesc) {
		this.bioDesc = bioDesc;
	}

	@Override
	public String toString() {
		return "\nAccount [accountId=" + accountId + ", fName=" + fName + ", lName=" + lName + ", bDate=" + bDate
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", sQuest1=" + sQuest1
				+ ", sQuest2=" + sQuest2 + ", sQuest3=" + sQuest3 + ", sAnsw1=" + sAnsw1 + ", sAnsw2=" + sAnsw2
				+ ", sAnsw3=" + sAnsw3 + ", bioDesc=" + bioDesc + "]";
	}
}
