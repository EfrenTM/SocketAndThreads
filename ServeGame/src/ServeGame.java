import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServeGame {
  
  
  public static void main(String[] args){
	  final int PORT = 40060;
	  
	  try {
		ServerSocket socketServer = new ServerSocket(PORT);
		
		while(true) {
			Socket socket  = socketServer.accept();
			System.out.println("Alguien se conecto ");
			ThreadClient thread = new ThreadClient(socket);
			thread.start();
		}
		
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }
 
}
