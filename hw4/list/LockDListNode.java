package list;

public class LockDListNode extends DListNode{
    
    protected boolean lock;

    public LockDListNode(Object i, DListNode p, DListNode n) {
        super(i, p, n); // 父类没有take no parameters的构造器，所以这里要explicitly写出super构造器with parameter
        this.lock = false;
    }
}
