/**
   * Project 5
   * @author Ben Lenington, bleningt@purdue.edu, 810
   */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

public class SafeWalkServer implements Runnable {

	ServerSocket ss;
	ArrayList<String> database;

	public SafeWalkServer(int port) throws IOException, SocketException {
		if (port < 1025 || port > 65535) {
			System.out.println("Invalid Port");
			ss.close();
		}
		ss = new ServerSocket(port);
		ss.setReuseAddress(true);
	}

	public SafeWalkServer() throws IOException, SocketException {
		ss = new ServerSocket(0);
		ss.setReuseAddress(true);
	}

	public int getLocalPort() {
		return ss.getLocalPort();

	}

	public String toListString(ArrayList<String> list) {
		String result = "[";
		for (int i = 0; i < database.size(); i++) {
			if (i > 0) {
				result += ", ";
			}
			result += Arrays.toString(database.get(i).split(","));
		}
		result += "]";
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {

			boolean running = true;
			boolean match;
			boolean command = false;
			ArrayList<String> database = new ArrayList<String>();
			ArrayList<Socket> clients = new ArrayList<>();
			this.database = database;
			while (running) {

				System.out.println("Run Method");
				Socket soc = ss.accept(); // Wait for client

				match = false;
				System.out.println("Connected");

				PrintWriter pw = new PrintWriter(soc.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						soc.getInputStream()));
				String line = in.readLine();
				// Check Input Type
				if (line.charAt(0) == ':') {
					if (line.equals(":LIST_PENDING_REQUESTS")) {
						command = true;
						if (database.size() < 1) {
							System.out.println("Waitlist currently empty.");
							pw.println("Waitlist currently empty.");
							pw.flush();
							pw.close();
							in.close();
							soc.close();
						} else {

							pw.println(toListString(database));
							pw.flush();
							pw.close();
							in.close();
							soc.close();
						}
					}

					if (line.equals(":RESET")) {
						command = true;
						for (int i = 0; i < clients.size(); i++) {
							System.out
									.println("J " + i + ", " + clients.size());
							PrintWriter pw1 = new PrintWriter(clients.get(i)
									.getOutputStream());
							pw1.println("ERROR: connection reset");
							pw1.flush();
							pw1.close();
							clients.get(i).close();
						}
						clients.clear();

						pw.println("RESPONSE: success");
						pw.flush();
						pw.close();
						in.close();
						soc.close();

					}

					if (line.equals(":SHUTDOWN")) {
						command = true;
						if (clients.size() > 0) {
							for (int i = 0; i < clients.size(); i++) {
								PrintWriter pw1 = new PrintWriter(clients
										.get(i).getOutputStream());
								if (clients.get(i) != null) {
									pw1.println("ERROR: connection reset");
									pw1.flush();
									pw1.close();
									clients.get(i).close();
								}
							}
						}
						clients.clear();

						pw.println("RESPONSE: success");
						pw.flush();
						in.close();
						pw.close();
						soc.close();
						running = false;
						return;
					}

					if (!command) {
						System.out.println("Invalid Command");
						pw.println("ERROR: invalid request");
						pw.flush();
						pw.close();
						in.close();
						soc.close();
					}
				}

				if (line.charAt(0) != ':') {
					boolean stat = true;
					System.out
							.println("Standard Input Recieved: Checking format...");
					int counter = 0;
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) == ',') {
							counter++;
						}
					}

					if (counter < 3 || line.length() < 8) {
						System.out.println("Invalid Input (Failed 1st Check");
						pw.println("ERROR: invalid request");

						pw.flush();
						pw.close();
						in.close();
						soc.close();
						stat = false;
					}

					ArrayList<String> imp = new ArrayList<String>(
							Arrays.asList(line.split("\\s*,\\s*")));
					// Check input for validity

					if (stat) {
						boolean check = false;
						boolean check1 = false;
						String[] allowed = { "CL50", "EE", "LWSN", "\\*",
								"PMU", "PUSH" };
						for (int i = 0; i < allowed.length; i++) {
							if (imp.get(1).contains(allowed[i])) {
								if ((imp.get(1).equals('*') == false))
									check = true;
							}
						}
						for (int i = 0; i < allowed.length; i++) {
							if (imp.get(2).matches(allowed[i])) {
								check1 = true;
							}

						}
						if (!check || !check1) {
							System.out
									.println("Invalid Input. Failed 2nd Test");
							pw.println("ERROR: invalid request");
							pw.flush();
							in.close();
							soc.close();
							stat = false;
						}

					}

					if (stat) {
						if (imp.get(1).equals(imp.get(2))) {

							System.out
									.println("Invalid Input: 3rd. Location and Destination cannot match.");
							pw.println("ERROR: invalid request");
							pw.flush();
							pw.close();
							in.close();
							soc.close();
							stat = false;
						}
					}
					if (stat) {
						System.out.println("Input is VALID");

						// Check for matching requests
						System.out.println(database.size());
						if (database.size() > 0) {
							for (int i = 0; i < (database.size()); i++) {
								ArrayList<String> tmp = new ArrayList<String>(
										Arrays.asList(database.get(i).split(
												"\\s*,\\s*")));

								if ((imp.get(1).equals(tmp.get(1))
										&& imp.get(2).equals(tmp.get(2)) || ((imp
										.get(1).equals(tmp.get(1)) && (imp.get(
										2).equals("*") || tmp.get(2)
										.equals("*")))))
										&& (imp.get(2).equals("*") && tmp
												.get(2).equals("*")) == false) {

									System.out.println("Match Found");
									match = true;
									System.out.println("RESPONSE: "
											+ database.get(i));
									pw.println("RESPONSE: " + database.get(i));
									pw.flush();
									PrintWriter pw1 = new PrintWriter(clients
											.get(i).getOutputStream());
									System.out.println("RESPONSE: " + line);
									pw1.println("RESPONSE: " + line);
									pw1.flush();
									database.remove(i);
									clients.get(i).close();
									clients.remove(i);
									soc.close();

								}
							}

						}
					}

					if (match == false && stat) {
						System.out.println("No Match found");
						database.add(line);
						clients.add(soc);
					}

				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException, SocketException {
		SafeWalkServer s = new SafeWalkServer();
		System.out.println("Port not specified. Using Free Port "
				+ s.getLocalPort());
		s.run();

	}
}
// s = new SafeWalkServer();
// s.run();
