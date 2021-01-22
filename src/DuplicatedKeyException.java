
public class DuplicatedKeyException extends RuntimeException {
	
	public DuplicatedKeyException(String error) {
		super(error + "has been found twice");
	}
}
