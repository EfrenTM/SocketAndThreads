
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientGame {

  
    public static void main(String[] args) {
      final int PORT = 40060; 
      final String HOST = "localhost";
      
        try {
            Socket socket = new Socket(HOST, PORT);
            cominicateWithServe(socket);
            
        } catch (IOException ex) {
            Logger.getLogger(ClientGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void cominicateWithServe(Socket socket) {
        OutputStream os = null;
        InputStream   getInformation= null;
        String lineTwo;
        try {
            os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            getInformation = socket.getInputStream();
    		InputStreamReader getInfoReader = new InputStreamReader(getInformation);
            BufferedReader getInfoBuffer = new BufferedReader(getInfoReader);
            
            Scanner keyboard = new Scanner(System.in);
            String line;
           
            do{
                System.out.println("Escribe ahi");
                line = keyboard.nextLine();
                bw.write(line);
                bw.newLine();
                bw.flush();
                
                lineTwo = getInfoBuffer.readLine();
                System.out.println(lineTwo);
                
                
                
            }while(line!= "FIN");
            
        } catch (IOException ex) {
            Logger.getLogger(ClientGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    
}
