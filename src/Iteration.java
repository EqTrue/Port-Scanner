import java.io.File;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class Iteration extends TimerTask {

    private static final String folder = "results/";
    private static final String extension = ".log";

    @Override
    public void run() {
        final String date[] = LocalDateTime.now().toString().split("T");
        System.out.println("Scanning Ports - " + date[1]);

        new File(folder + date[0] + '/').mkdirs();
        PortScanner.getOpenPorts(folder +  date[0] + '/' + date[1] + extension);
    }
}