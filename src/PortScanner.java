import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PortScanner {

    private static final int MAX_PORT = 65535;
    private static String ip;

    /**
     * Create a timer task that will run() Iteration.java every number of seconds
     * @param args 1:URL to check 2:NUmber of seconds per check
     */
    public static void main(String[] args) {
        ip = args[0];
        try {
            new Timer().schedule(new Iteration(), 0, 1000 * Long.parseLong(args[1]));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Provide ONE input for target ip and ONE input parameter for timer time(s)");
        }
    }

    /**
     * Log a list of ports that pose no error when connecting to them via
     * Socket
     * @param logFile name of file to log results
     */
    static void getOpenPorts(String logFile) {
        int count = 0;
        try (Writer stream = new BufferedWriter(new FileWriter(logFile))) {
            for (int port = 0; port <= MAX_PORT; port++) {
                try (Socket socket = new Socket(ip, port)){
                    socket.setSoTimeout(1000);
                    new Scanner(socket.getInputStream());
                    socket.setKeepAlive(false);
                    stream.write(ip + ':' + port + '\n');
                }
                catch (IOException e){
                    count++;
                }
            }
            stream.write("\nTotal closed ports: " + count);
            stream.write("\nTotal open ports: " + (MAX_PORT-count));
        }
        catch(IOException e){
            System.err.println("Could not create log file_");
        }
    }

}
