package Assignment;



public class UserInfo {

	private String name;
	private String userID;
	
	public UserInfo()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void getUser(String name)
	{
		setName(name);
		if (name.contains(" "))
		{
			int pos_space = name.lastIndexOf(" ");
			String firstName = name.substring(0, pos_space-1);
			String surName = name.substring(pos_space+1, name.length());
			String firstChar = firstName.substring(0,1);
			setUserID(firstChar+surName);
		}
		else setUserID("guest");
	}
}
