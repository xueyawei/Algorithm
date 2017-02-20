import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
    private Node first, last;
    private int sizeOfQueue = 0;

    private class Node
    {
        Node next, prev;
        Item item;
    }

    private class queIterator implements Iterator<Item>
    {
        private Node currentNode = first;

        public boolean hasNext()
        {
            return currentNode != null;
        }

        public Item next()
        {
            if(currentNode.item==null)
                throw new java.util.NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public Deque()                           // construct an empty deque
    {

    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return first == null;
    }

    public int size()                        // return the number of items on the deque
    {
        return sizeOfQueue;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (isEmpty())
        {
            first = new Node();
            first.item = item;
            first.prev = null;
            last = first;
            last.next = null;
            sizeOfQueue++;
        } else
        {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.prev = null;
            oldFirst.prev = first;
            sizeOfQueue++;

        }

    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (isEmpty())
        {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
            sizeOfQueue++;
        } else
        {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldLast;
            oldLast.next = last;
            sizeOfQueue++;
        }
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (sizeOfQueue == 0)
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        sizeOfQueue--;
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (sizeOfQueue == 0)
            throw new java.util.NoSuchElementException();
        Item oldItem = last.item;
        last.prev.next = null;
        last = null;
        sizeOfQueue--;
        return oldItem;


    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new queIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
