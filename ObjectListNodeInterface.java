/**
 * interface for ObjectListNode
 * 
 * @author Brenda Tang
 * @version 05 December 2016
 */
public interface ObjectListNodeInterface
{
    public void setInfo(Object o);
    public Object getInfo();
    public void setNext(ObjectListNode p);
    public ObjectListNode getNext();
}
