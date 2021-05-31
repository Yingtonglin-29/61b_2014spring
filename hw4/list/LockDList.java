package list;

public class LockDList extends DList {

    @Override
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        DListNode node = (DListNode) new LockDListNode(item, prev, next); // 向上转型 (cast is not necessary)
        return node;
    }
      
    @Override
    public void remove(DListNode node) {
        LockDListNode lockNode = (LockDListNode) node; // 向下转型 (risky)
        if (lockNode.lock == true) return;
        else {
            super.remove(node);
        }
    }

    public void lockNode(DListNode node) {
        LockDListNode lockingNode = (LockDListNode) node; // 向下转型 (risky)
        lockingNode.lock = true;
    }
}
