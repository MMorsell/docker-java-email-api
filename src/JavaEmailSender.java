
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

public class JavaEmailSender {

	private final static int port = 9000;
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port: " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.matches("POST\\s+.*")) {
                        	System.out.println("");
                            System.out.println("Match POST");
                            
                            MessageModel message = GetSubjectAndMessageFromPost(line);
                            
                            EmailHelper.SendEmail(message.GetSubject(), message.GetMessage());
                            
                            EmailHelper.SendOkResponseToUser(out, 200);       
                            // process the POST request
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
	}
	
	public static MessageModel GetSubjectAndMessageFromPost(String line) throws UnsupportedEncodingException{
        
		Pattern subjectPattern = Pattern.compile("[Ss][Uu][Bb][Jj][Ee][Cc][Tt][=](.+)[&]");
        Matcher matcher = subjectPattern.matcher(line);
        
        String subject = "";
        String message = "";
        if (matcher.find( )) {
        	System.out.println("Subject is set to: " + URLDecoder.decode(matcher.group(1), "UTF-8"));
        	subject = URLDecoder.decode(matcher.group(1), "UTF-8");
        }

        
        Pattern messagePattern = Pattern.compile("[Mm][Ee][Ss][Ss][Aa][Gg][Ee][=](.+)[& ]");
        Matcher messageMatcher = messagePattern.matcher(line);
        
        if (messageMatcher.find( )) {
        	System.out.println("Message is set to: " + URLDecoder.decode(messageMatcher.group(1), "UTF-8"));
        	message = URLDecoder.decode(messageMatcher.group(1), "UTF-8");
        }
        
        
        MessageModel model = new MessageModel(subject, message);
		return model;
	}
}
