/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2Task2;

/**
 *
 * @author sonia
 */
public class QueueAbstract<E> implements Queue<E> {

    protected int head;
    protected int tail;
    protected Object data[];

    //add other fields if required
    public void enqueue(E item) // post: the value is added to the tail of the structure
    {
        add(item);
    }

    public E dequeue() // pre: the queue is not empty
    // post: the head of the queue is removed and returned
    {
        return remove();
    }

    public E getFirst() // pre: the queue is not empty
    // post: the element at the head of the queue is returned
    {
        return get();
    }

    public E peek() // pre: the queue is not empty
    // post: the element at the head of the queue is returned
    {
        return get();
    }

    //implement methods add, get, remove, empty and size
    public void add(E value) {
        if (tail == data.length - 1) {
            System.out.println("Stack is already FULL");
        } else {
            data[tail++] = value;
        }
    }

    public E get() {
        return (E) data[head];
    }

    public int size() {
        return tail;
    }

    public boolean empty() {
        return (tail == 0);
    }

    public E remove() {
        E value;
        if (!empty()) {
            value = (E) data[head];
            for (int k = 0; k < tail; k++) {
                try {
                    data[k] = data[k + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    data[k] = null;
                }
            }
            tail--;
        } else {
            System.out.println("Stack is EMPTY");
            return null;
        }

        return value;
    }

}
