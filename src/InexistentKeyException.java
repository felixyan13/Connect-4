
public class InexistentKeyException extends RuntimeException {
	
	public InexistentKeyException(String error) {
		super("Error " + error + "not found");
	}
}
