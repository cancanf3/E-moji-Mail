import java.io.*;
import java.util.*;
import java.net.*;

public class Connection {

	private ServerSocket server;
	private Socket socket;
	private OutputStream out;
	private PrintWriter pw;

	public static void main ( String args[] ) throws IOException {
		try {
			Connection connect = new Connection();
			connect.listening();
			connect.closing();
		}
		catch ( IOException e) {
			throw e;
		}
	}


	public Connection () throws IOException {
		try {
			server = new ServerSocket(8080);
		}
		catch ( IOException e) {
			throw e;
		}
	}


	public String listening () throws IOException {
		BufferedReader br;
		StringBuilder stringBuilder;
		String line;

		System.out.println("Opening a Connection in Port 8080");
		try { 


			socket = server.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stringBuilder = new StringBuilder();

			while((line = br.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}

			System.out.println(stringBuilder.toString());
			return stringBuilder.toString();

		}
		catch ( IOException e) {
			throw e;
		}

	}

	public void responding ( String message ) throws IOException {
		try {
			out = socket.getOutputStream();
			pw = new PrintWriter(out, true);
			pw.println(message);
		}
		catch ( IOException e) {
			throw e;
		}

	} 

	public void closing () throws IOException {
		try { 
			pw.close();
			socket.close();
		}
		catch ( IOException e) {
			throw e;
		}
	}
}