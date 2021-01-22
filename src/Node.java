
public class Node<Data> {
    private Node<Data> nextNode;
    private Node<Data> previousNode;
    private Data element;
    
    //constructor to create a basic empty node
    public Node()
    {
        nextNode = null;
        previousNode = null;
        element = null;
    }
    
    //constructor to create a node, in our case an element of type Data
    public Node (Data element)
    {
        nextNode = null;
        previousNode = null;
        this.element = element;
    }
    
    //get next method, it obtains the next node which is the next data record
    public Node<Data> getNext()
    {
        return nextNode;
    }
    
    //get previous method, obtains the node previous to this
    public Node<Data> getPrevious()
    {
    	return previousNode;
    }
    //setter method, sets the next node in the linked list as another data record
    public void setNext (Node<Data> node)
    {
        nextNode = node;
    }
    
    //setter method, sets the previous node in the linked list
    public void setPrevious (Node<Data> node)
    {
    	previousNode = node;
    }
    
    //getter method, returns the current element
    public Data getElement()
    {
        return element;
    }
    
    //setter method, sets the current element as the data record
    public void setElement (Data element)
    {
        this.element = element;
    }
}
