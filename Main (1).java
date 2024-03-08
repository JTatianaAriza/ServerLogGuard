//Principal Class
public class Main {
    public static void main(String[] args) {
       
        ServerLogGuard logGuard = new ServerLogGuard("server.log");
        logGuard.analyzeLog();
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServerLogGuard {
    private String logFilePath; 
    // Constructor 
    public ServerLogGuard(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    
    public void analyzeLog() {
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (containsError(line)) {
                    System.out.println("Alerta: Se detectó un error en el registro del servidor.");
                    System.out.println("Detalles: " + line);
                    sendNotification("Error detectado", line);
                }
                if (line.contains("CRITICAL")) {
                    System.out.println("Alerta crítica: Se detectó un evento crítico en el registro del servidor.");
                    System.out.println("Detalles: " + line);
                    
                    takeAction(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean containsError(String line) {
        return line.contains("ERROR");
    }

    
    private void sendNotification(String subject, String message) {
  
        System.out.println("Enviando notificación: " + subject + " - " + message);
    }

    private void takeAction(String eventDetails) {
        System.out.println("Tomando acciones inmediatas: " + eventDetails);
    }
}
