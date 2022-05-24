package application.IP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AutoIp {

	public static void main(String[] args) {

		String ip;
		String hostname;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
//			hostname = ip.getHostName();
//			String hostIp = ip.getHostAddress();
//			System.out.println("Your current IP address : " + ip);
//			System.out.println("Your current Hostname : " + hostIp);

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
	}

}
