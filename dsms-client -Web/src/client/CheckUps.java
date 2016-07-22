package client;

public class CheckUps 
{

	
	public String isValid(String firstName,String lastName,String Phone)
	{
		String returnMessage ="";
		if(!isAlphabetic(firstName.trim()) || firstName.isEmpty() )
			returnMessage = returnMessage +"First Name must only be Alphabetical Characters\n";
		if(!isAlphabetic(lastName.trim()) || lastName.isEmpty())
			returnMessage = returnMessage +"Last Name must only be Alphabetical Characters\n";
		if(!isNumeric(Phone.trim()) || Phone.isEmpty())
			returnMessage = returnMessage +"Phone Number must only be Numbers\n";
		return returnMessage;
	}
	
	
	
	
	
	public boolean isAlphabetic(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	
	
	public boolean isNumeric(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	
	
	
}
