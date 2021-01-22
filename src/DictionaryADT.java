
public interface DictionaryADT {
    public int put (Data record) throws DuplicatedKeyException;

    public void remove (String config) throws InexistentKeyException;

    public Data get (String config);

    public int numDataItems();
}
