/**
 * ObjectListNode.java
 * 
 * @author Brenda Tang
 * @version 05 December 2016
 */

public class ObjectListNode implements ObjectListNodeInterface {
    private Object info;
    private ObjectListNode next;
    
    // Default ctor    
    public ObjectListNode() {
        info = null;
        next = null;
    }

    // One-arg ctor
    public ObjectListNode (Object o) {
        info = o;
        next = null;
    }    
    
    // Two-arg ctor
    public ObjectListNode (Object o, ObjectListNode p) {
        info = o;
        next = p;
    }       

    // Sets info field
    public void setInfo(Object o) {
        info = o;
    }    

    // Returns object in info field
    public Object getInfo() {
        return info;
    }

    // Sets next field
    public void setNext(ObjectListNode p) {
        next = p;
    }

    // Returns object in info field
    public ObjectListNode getNext() {
        return next;
    }
}