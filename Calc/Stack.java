package calc;

/**
 *
 * @author Brendan Morgenstern
 */
public class Stack {
    private Object[] data;
    private int top;
    public int count(){
        return top+1;
    }
    public boolean empty(){
        return (top < 0);
    }
    
    /**
     * The method will attempt to push the given object onto the stack,
     * and provide an error message if it's unable to
     * @param o The object to be pushed onto the stack 
     */
    public void push(Object o){
        try{
            data[++top] = o;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Stack limits reached, " + o + " was not pushed.");
        }
        
    }
    /**
     * Returns the item at the top of the stack without altering the stack
     * @return Object at the top of the stack
     */
    public Object peek(){
        if(empty())
            return null;
        else
            return data[top];
    }
    /**
     * Returns the item at the top of the stack by popping it off
     * @return Object at the top of the stack
     */
    public Object pop(){
        if(empty())
            return null;
        else
            return data[top--];
    }
    public Stack(int maxEl){
        if(maxEl < 1)
            throw new NegativeArraySizeException();
        data = new Object[maxEl];
        top = -1;
    }
}
