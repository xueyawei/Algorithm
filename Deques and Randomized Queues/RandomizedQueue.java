import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{


    private Item[] s;
    private int N = 0;

    private void resize(int length)
    {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }
        s = copy;
    }

    private class randomIterator implements Iterator<Item>
    {
        private int i;

        public randomIterator()
        {
            i = N - 1;
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
            Item thisItem = s[position];
            s[position] = s[i--];
            return thisItem;
        }

        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        s = (Item[]) new Object[2];
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return N;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (s.length == N)
            resize(2 * N);
        s[N++] = item;

    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randomPosition = StdRandom.uniform(N);
        Item dequeueItem = s[randomPosition];
        s[randomPosition] = s[N - 1];
        s[N--] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return dequeueItem;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return s[StdRandom.uniform(N)];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new randomIterator();
    }

    public static void main(String[] args)   // unit testing (optional)
    {

    }
}
