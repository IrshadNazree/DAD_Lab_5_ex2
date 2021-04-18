import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static void main(String[] args) {
		
		try {
			int portNo = 4228;
			ServerSocket ssc = new ServerSocket(portNo);
			System.out.println("Ready for request");
	
			while (true) {
				Socket sc = ssc.accept();
				ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
				
				ItemProduct itemP = (ItemProduct) ois.readObject();
//				System.out.println(itemP.getName() + " " + itemP.getPrice());
				itemP.setItemProductID(1);
							
				ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
				oos.writeObject(itemP);
				
				System.out.println("Ready for next request");
				
				ois.close();
				oos.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
