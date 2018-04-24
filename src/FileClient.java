import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
	
	private Socket s;
	
	public FileClient(String host, int port, String file) {
		try {
			s = new Socket(host, port);
			sendFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile(String file) throws IOException {
		File zdj = new File(file);
		long size = zdj.length();
		System.out.println("File size: " + size + " B.");
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());;
		FileInputStream fis = new FileInputStream(zdj);
		byte[] buffer = new byte[4096];
		Scanner scanner = new Scanner(System.in);
		System.out.println("Wpisz nazwe zdjecia do wyslania.");
		String photoName = scanner.nextLine();
		dos.writeUTF(photoName + ".jpg");
		System.out.println("photo name: " + photoName);
		scanner.close();
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		fis.close();
		dos.close();	
	}
	
	public static void main(String[] args) {
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		FileClient fc = new FileClient("127.0.0.1", 1988, "/home/arek/Desktop/test_server_source/pic.jpg");
	}

}