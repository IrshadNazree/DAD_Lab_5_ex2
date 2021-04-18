import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		ItemProduct itemP = new ItemProduct();
		itemP.setName("test");
		itemP.setPrice(10);
		
		try {
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();
			Socket sc = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + itemP);
			oos.writeObject(itemP);
			oos.flush();
			
			// Open stream to receive object
			ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
			
			// Get object from stream and display details
			itemP = (ItemProduct) ois.readObject();
			System.out.println ("Id for " + itemP.getName() + " is " + itemP.getItemProductID());
			
			// Close all closeable objects
			oos.close();
			ois.close();
			sc.close();
			
		} catch (IOException | ClassNotFoundException e){
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
