import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{


    private Item[] s;
    private int n = 0;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        s = (Item[]) new Object[2];
    }

    private void resize(int length)
    {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < n; i++)
        {
            copy[i] = s[i];
        }
        s = copy;
    }

    private class RandomIterator implements Iterator<Item>
    {
        private int i;
        private Item[] items;

        public RandomIterator()
        {
            i = n - 1;
            items = (Item[]) new Object[s.length];
            for (int j = 0; j < s.length; j++)
            {
                items[j] = s[j];
            }
        }

        public boolean hasNext()
        {
            return i >= 0;
        }

        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            int position = StdRandom.uniform(i + 1);
            Item thisItem = items[position];
            items[position] = items[i--];
            return thisItem;
        }

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }


    public boolean isEmpty()                 // is the queue empty?
    {
        return n == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return n;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (s.length == n)
            resize(2 * n);
        s[n++] = item;

    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randomPosition = StdRandom.uniform(n);
        Item dequeueItem = s[randomPosition];
        s[randomPosition] = s[n - 1];
        s[--n] = null;
        if (n > 0 && n == s.length / 4)
            resize(s.length / 2);
        return dequeueItem;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return s[StdRandom.uniform(n)];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
