package otg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTP {

	FTPClient client = new FTPClient();
	FTPPass ftppass = new FTPPass();
	
	public FTP() throws IOException {
		setConnection();
	}
	
	
	public void setConnection() throws IOException {
		
		try {
			client.connect("otgiusa.dk");
			boolean login = client.login("bjarnen@otgiusa.dk", ftppass.getPass()); //logger ind på FTP server og henter koden fra seperat klasse (psw skal ikke ses på Github)
			client.changeWorkingDirectory("/javafolder"); //skifter mappen der overføres i til javafolder
			client.setFileType(2); 
			client.setBufferSize(0);

			if (login) {
				System.out.println("Connected to FTP"); //hvis det var muligt at logge in, skal der komme en konsol kommando
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Kode til upload af billede
	public void storeFile(String filepath, String filename) throws Exception {
		
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filepath); 
			Long T1 = System.currentTimeMillis ();
			client.storeFile(filename, fis);
			Long T2 = System.currentTimeMillis ();
			System.out.println("File was upload in: " +(T2-T1)/1000 + " seconds");
			fis.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//Kode til download af billeder
	public void getFile(String file) throws IOException {

		FileOutputStream fos = null;
		
		try {
			String filename = "notifications/" + file;
            fos = new FileOutputStream(filename);
            
            client.retrieveFile(file, fos);
            
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Lukker forbindelsen
	public void closeConnection () throws IOException  {
		client.logout();
		client.disconnect();
	}
}
