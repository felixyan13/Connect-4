
public class Data {

	/*
	 * declaring instance variables
	 * @param key to store the keys of the hash code
	 * @param score to store the score from the game
	 * @param level to store what level is being played
	 */
private String key;
private int score;
private int level;


//the main constructor, which helps store the values that will be input into this class
	public Data(String key, int score, int level)
	{
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	//getter to return key value
	public String getKey() {
		return key;
	}
	
	//getter to get the score value
	public int getScore() {
		return score;
	}
	
	//getter to get the level value
	public int getLevel() {
		return level;
	}


	
}
