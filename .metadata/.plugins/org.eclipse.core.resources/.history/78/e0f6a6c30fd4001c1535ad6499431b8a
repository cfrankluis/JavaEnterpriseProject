package application.toolbox;

import java.util.regex.Pattern;

import java.util.regex.Pattern;

public class Verification {
	
	/**
	 * checks to see if the email is fomated correctly
	 * 
	 * @param email
	 * @param checkEmail
	 * @return
	 */
	private static boolean checkUserInput(String email) {
		if (email == null)
			return false;

		String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailFormat);

		return pat.matcher(email).matches();
	}

}
