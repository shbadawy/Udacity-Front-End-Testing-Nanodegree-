package udacity.project2;

import java.util.Random;

public class globalVariables {

	public static int sleepTime = 100;
	public static Random randomObj = new Random();
	public static Integer randID = randomObj.nextInt(10000000);
	public static String email = "shimaa.badawy" + randID.toString()+ "@test.com";
	public static String password = "123456789SB";
		
	public static void setRandomID() {
		
		randID = randomObj.nextInt(10000000);
		email = "shimaa.badawy" + randID.toString()+ "@test.com";
		
	}
	
}
