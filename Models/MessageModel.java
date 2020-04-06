
public class MessageModel {
	
	private static String _subject;
	private static String _message;
	
	public MessageModel(String subject,String message) {
		_subject = subject;
		_message = message;
	}
	
	
	public String GetSubject() {
		return _subject;
	}
	
	public String GetMessage() {
		return _message;
	}
}
