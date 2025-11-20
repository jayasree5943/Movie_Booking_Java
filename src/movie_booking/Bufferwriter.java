package movie_booking;
import java.util.*;
import java.io.*;
public class Bufferwriter {

	public static void saveUsersToFile(ArrayList<User> users) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("I:\\user.txt"))) {

	     
			for (User u : users) {
	            bw.write(
	                u.getId() + "," +
	                u.getFirstName() + "," +
	                u.getLastName() + "," +
	                u.getEmail() + "," +
	                u.getPhoneNo() + "," +
	                u.getPassword()
	            );
	            bw.newLine();
	        }

	        System.out.println("✔ Users saved to file.");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
