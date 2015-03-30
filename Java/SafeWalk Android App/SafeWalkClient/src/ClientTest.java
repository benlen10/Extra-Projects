import java.io.*;
import java.net.Socket;

public class ClientTest implements Runnable {
    private String host;
    private int port;
    private String request;
    
    public ClientTest(String host, int port, String request) {
        this.host = host;
        this.port = port;
        this.request = request;

    }
    
    public void run() {
        try (
            Socket s = new Socket(this.host, this.port);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ) {
            System.out.println("<== " + request);
            out.println(this.request);
            System.out.printf("%s ==> %s\n",request.split(",")[0],in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            File f = new File(args[2]);
            FileInputStream fr = new FileInputStream(f);
            BufferedReader readTests = new BufferedReader(new InputStreamReader(fr));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                //System.out.println(s);
                if (userInput.equals("")) {
                    String line = readTests.readLine();
                    if (line != null) {
                        ClientTest ct = new ClientTest(args[0], Integer.parseInt(args[1]), line);
                        new Thread(ct).start();
                    }
                } else {
                    ClientTest ct = new ClientTest(args[0], Integer.parseInt(args[1]), userInput);
                    new Thread(ct).start();
                }
            }
            
        } catch (IndexOutOfBoundsException|NumberFormatException e) {
            System.out.println(e);
            System.out.println("Arguments: HOSTADDRESS PORT FILE.TXT");
        }
    }
}