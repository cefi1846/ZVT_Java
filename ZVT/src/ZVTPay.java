import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ZVTPay{
  public static void main (String[] args) throws IOException{
	  //--------------
	  Socket socket = new Socket("172.30.78.21", 22000);
	  DataInputStream dIn = new DataInputStream(socket.getInputStream());
	  DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
	  //--------------
	  String password = "000001";
	  //anmelden(socket, password);
	  payment(socket);
	  //send(socket, dOut);
	    /*try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    while (true) {
	    	System.out.print(receive(socket, dIn));
	    	if (receive(socket, dIn).contains("Bitte Karte\neinstecken")) break;
	    }
	    
	    //receive(socket, dIn);*/
	    
    
  }
  
  public static void anmelden(Socket socket, String password) throws IOException {
    //anmelden
	  DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
	  DataInputStream dIn = new DataInputStream(socket.getInputStream());
        //String password = "000000";
        byte passwd1 = 0, passwd2 = 0, passwd3 = 0;
        byte[] reads = {};
        try {
                Byte.parseByte(password.substring(0, 2), 16);
                Byte.parseByte(password.substring(2, 4), 16);
                Byte.parseByte(password.substring(4, 6), 16);
              } catch(Exception e) {
                System.out.println("FEHLER bei der Passwortconvertierung");
              } 
        
            byte[] commandLogOn = {(byte)0x06, (byte)0x00, (byte)0x10, (byte)passwd1, (byte)passwd2, (byte)passwd3, (byte)0x08, (byte)0x09, (byte)0x78, (byte)0x03, (byte)0x00, (byte)0x06, (byte)0x06, (byte)0x26, (byte)0x04, (byte)0x0a, (byte)0x02, (byte)0x06, (byte)0xd3};
            //System.out.println(commandLogOn.length);
            dOut.write(commandLogOn, 0, commandLogOn.length);
            
        	String dataString = "";
        	
        	try {
        	    while (true) {
        	    	dataString += dIn.read();
        	    	if (dataString.contains("12800")) {
        	    		System.out.println("Anmeldung erfolgreich durchgeführt!");
        	    		break;
        	    		
        	    	}
        	    }
        	    
        	    //dataString += in.read() + " ";
        	    //dataString += in.read() + " ";
        	    //dataString += in.read() + " ";

        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
            
            socket.close();
                 
  }
  
  public static void payment(Socket socket) throws IOException {
		//payment
	  DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
	  DataInputStream dIn = new DataInputStream(socket.getInputStream());
      
      String euro = "8";
      String cent = "67";
      byte eurobyte = Byte.parseByte(euro,16);
      byte centbyte = Byte.parseByte(cent,16);
      
      //byte[] commandLeft = {(byte)0x06, (byte)0x01, (byte)0x09, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)eurobyte, (byte)centbyte, (byte)0x19, (byte)0x01, (byte)0x00, (byte)0x40};
      byte[] commandLeft = {(byte)0x06, (byte)0x01, (byte)0x09, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)eurobyte, (byte)centbyte, (byte)0x19, (byte)0x00};
      byte[] commandSuccess = {(byte)0x80, (byte)0x00, (byte)0x00};
      dOut.write(commandLeft);
     
  	String dataString = "";
  	while (true) {
    	dataString += dIn.read();
    	if (dataString.contains("12800")) {
    		try {
    			TimeUnit.SECONDS.sleep(1);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		//dOut.write(commandSuccess);
    		System.out.println("Betrag empfangen, führe Zahlung aus!");
    		break;
    		
    	}
    }
  	
  	while (true) {
  	    	dataString += (char)dIn.read();
  	    	if (dataString.contains("Bitte Karte\neinstecken")) {
  	    		try {
  	  			TimeUnit.SECONDS.sleep(1);
  	  		} catch (InterruptedException e) {
  	  			// TODO Auto-generated catch block
  	  			e.printStackTrace();
  	  		}
  	    		dOut.write(commandSuccess);
  	    	System.out.println("Bitte Karte einstecken!");
  	    	break;
  	    	//System.out.println(dataString);
  	    	}
  	    }
  	
  	  while (true) {
  		dataString += (char)dIn.read();
  		  if (dataString.contains("Karte entnehmen")) {
  			try {
  				TimeUnit.SECONDS.sleep(1);
  			} catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			dOut.write(commandSuccess);
	    	System.out.println("Bitte Karte entnehmen!");
	    	break;
	    	//System.out.println(dataString);
  		  }
	    }
  	  
  	while (true) {
  		dataString += (char)dIn.read();
  		  if (dataString.contains("ec")) {
  			try {
  				TimeUnit.SECONDS.sleep(1);
  			} catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			dOut.write(commandSuccess);
	    	System.out.println("Zahlungsart: EFT offline\nDie Zahlung wurde erfolgreich durchgeführt.");
	    	System.out.println();
	    	System.out.println("Ermächtigung zum Lastschrifteinzug\n\nIch ermächtige oben genanntes Unternehmen, den heute fälligen, o.g. Betrag unter o.g. Mandats-Referenz (M-ID) einmalig von meinem durch die verwendete Karte identifizierten Konto per Lastschrifteinzugs einzuziehen. Die Frist zur Ankündigung des Lastschrifteinzuges wird auf einen Tag verkürzt. Die Belastung meines Kontos erfolgt an dem Geschäftstag, der dieser Zahlung folgt. Hinweis: Ich kann innerhalb von 8 Wochen, beginnend mit dem Belastungsdatum, die Erstattung des belasteten Betrages verlangen. Es gelten dabei die mit meinem Kreditinstitut vereinbarten Bedingungen.\n\nIch weise mein Kreditinstitut unwiderruflich an,\n\ndie Lastschrift einzulösen und im Falle der Nichteinlösung der Lastschrift dem o. g. Unternehmen, oder, bei Forderungsabtretung, dem jeweiligen Gläubiger oder deren Beauftragten auf Anforderung meinen Namen und meinen Anschrift zur Geltendmachung oder Forderung mitzuteilen.\n\n\n\n________________________________\nUnterschrift des Karteninhabers\n\n\nDatenschutzrechtliche Informationen\n\nWir erfassen Ihre Zahlungsinformationen (Kontonummer, Bankleitzahl, Kartenverfalldatum, und-folgenummer, Datum, Uhrzeit, Betrag, Terminalkennung, Standort des Terminals) zum Zweck der Zahlungsabwicklung, zur Kartenprüfung und Verhinderung von Kartenmissbrauch.\nWird bei einer Zahlung im Elektronischen Lastschriftverfahren (d. h. mit Girocard und Unterschrift) eine Lastschrift von Ihrer Bank nicht eingelöst oder von Ihnen widerrufen (Rücklastschrift), wird dies in eine Sperrdatei eingetragen, die bei oben genannter Firma geführt wird. Solange ein Sperreintrag besteht, ist eine Zahlung mit Girocard und Unterschrift nicht möglich. Der Eintrag in der Sperrdatei wird gelöscht, sobald die Forderung vollständig beglichen wurde oder wenn Sie Rechte aus dem getätigten Kauf geltend machen (z.B. bei Sachmangel oder Rückgabe der Ware");
	    	break;
	    	//System.out.println(dataString);
  		  }
	    }
  	    
  	    //dataString += in.read() + " ";
  	    //dataString += in.read() + " ";
  	    //dataString += in.read() + " ";

  	socket.close();

/*while (true) {
	dataString += dIn.read() + " ";
	System.out.println(dataString);
}*/
  }
}