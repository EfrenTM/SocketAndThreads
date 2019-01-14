import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;


public class ThreadClient extends Thread{
   Socket socket;
   
   public ThreadClient(Socket sk){
	   this.socket = sk;
   }
   
   @Override
   
   public void run() {
	   OutputStream sendInformation= null;
	   InputStream   getInformation= null;
	   
	   try {
		getInformation = socket.getInputStream();
		InputStreamReader getInfoReader = new InputStreamReader(getInformation);
        BufferedReader getInfoBuffer = new BufferedReader(getInfoReader);
        
        sendInformation = socket.getOutputStream();
        OutputStreamWriter sendInfoWrite = new OutputStreamWriter(sendInformation);
        BufferedWriter sendInfoBuffer = new BufferedWriter(sendInfoWrite);
        
        Inet4Address ip = (Inet4Address) socket.getInetAddress();
        String laIP = ip.getHostAddress();
        
        while(true){
        	System.out.println("Esperando respuesta....");
            String linea = getInfoBuffer.readLine();
            System.out.println(laIP +": " + linea);
            
            numberMachine(writeOption(linea));
            playGame();
            
            sendInfoBuffer.write(playGame());
            sendInfoBuffer.newLine();
            sendInfoBuffer.flush();
        }
        
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
   }

	private String playGame() {
		 int valorMoneda = (int) (Math.random()*2)+1;
		 String winner="";
		 if(valorMoneda == 2 ) {
			 System.out.println("gana cara");
			 winner="gana cara";
		 }if((valorMoneda == 1)) {
			 System.out.println("gana cruz");
			 winner="gana cruz";
		 }
		 
		 return winner;
   }

	private int numberMachine(int writeOption) {
	int selectMachin=0;
	
	if(writeOption == 1){
		selectMachin=2;
		 System.out.println("la maquina a elegido cara");
	}else {
		selectMachin=1;
		 System.out.println("la maquina a elegido cruz");
	}
	
	return selectMachin;
}

	private static int writeOption(String line) {
		int selectOption=0;
		
		if(line.equals("cruz")){
			 System.out.println("el jugador a elegido cruz");
			 selectOption = 1;
			 
		}if(line.equals("cara")){
			System.out.println(" el jugador a elegido  cara");
			selectOption = 2;
		}	
		
		return selectOption;
	}
}
