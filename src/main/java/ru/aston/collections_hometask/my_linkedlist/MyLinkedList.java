package ru.aston.collections_hometask.my_linkedlist;

import ru.aston.collections_hometask.MyList;
import ru.aston.collections_hometask.MyListException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.StringJoiner;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public MyLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }


    public MyLinkedList(Collection<T> collection) throws MyListException {
        this();
        T[] array = (T[]) collection.toArray();
        for (T t : array) {
            this.add(t);
        }
    }

    private Node<T> getNode(int index) {
        int flag = index;
        Node<T> node = head.next;
        while (index > 0) {
            node = node.next;
            index--;
        }
        return node;
    }

    private T[] toArray() {
        int flag = size;
        T[] array = (T[]) new Object[size];
        Node<T> first = head.next;
        while (first != null) {
            array[size - flag] = first.value;
            first = first.next;
            flag--;
        }
        return array;
    }

    private void clear() throws MyListException {
        Node<T> node = head.next;
        while (node != null) {
            T value = node.value;
            remove(value);
            node = node.next;
        }

    }


    @Override
    public boolean add(T t) throws MyListException {
        if (t == null) {
            throw new MyListException("Argument is null");
        }
        Node<T> node = new Node<>();
        node.value = t;
        Node lastNode = tail.prev;
        lastNode.next = node;
        node.prev = lastNode;
        tail.prev = node;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T t) throws MyListException {
        if (index < 0 || t == null) {
            throw new MyListException("Illegal arguments");
        }
        Node<T> newNode = new Node<>();
        newNode.value = t;
        Node<T> node = getNode(index);
        Node<T> prev = node.prev;
        node.prev = newNode;
        newNode.next = node;
        prev.next = newNode;
        newNode.prev = prev;
        this.size++;
        return true;
    }

    @Override
    public T remove(int index) throws MyListException {
        if (index > size - 1) {
            throw new MyListException("The wrong index");
        }
        Node<T> node = getNode(index);
        T value = node.value;
        remove(value);
        return value;
    }

    @Override
    public boolean remove(T object) {
        Node<T> cursor = head.next;
        while (cursor != null) {
            if (cursor.value.equals(object)) {
                if (cursor.next == null) {
                    Node<T> prev = cursor.prev;
                    prev.next = null;
                    tail.prev = prev;
                    size--;
                    return true;
                }
                Node<T> prev = cursor.prev;
                Node<T> next = cursor.next;
                prev.next = next;
                next.prev = prev;
                size--;
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }

    @Override
    public T get(int index) throws MyListException {
        if (index > size - 1) {
            throw new MyListException("The wrong index");
        }
        int flag = index;
        Node<T> cursor = head.next;
        while (flag > 0) {
            cursor = cursor.next;
            flag--;
        }
        return cursor.value;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) throws MyListException {
        if (index < 0 || collection == null) {
            throw new MyListException("Illegal arguments");
        }
        try {
            T[] array = (T[]) collection.toArray();
            int flag = index;
            for (int i = 0; i < collection.size(); i++) {
                add(flag, array[i]);
                flag++;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) throws MyListException {
        if (collection == null) {
            throw new MyListException("Illegal arguments");
        }
        try {
            T[] array = (T[]) collection.toArray();
            for (int i = 0; i < collection.size(); i++) {
                add(array[i]);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public void bubbleSort(Comparator<T> comparator) throws MyListException {
        T[] array = toArray();
        clear();
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 1; i < array.length; i++) {
                if (comparator.compare(array[i], array[i - 1]) < 0) {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    flag = false;
                }
            }
        }
        for (T t : array) {
            add(t);
        }
    }

    public void print() {
        Node<T> point = head.next;
        while (point != null) {
            System.out.println(point);
            point = point.next;
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<T> point = head.next;
        while (point != null) {
            builder.append("[").append(point.value).append("]");
            point = point.next;
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLinkedList<?> that = (MyLinkedList<?>) o;

        if (size != that.size) return false;
        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        return tail != null ? tail.equals(that.tail) : that.tail == null;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        result = 31 * result + size;
        return result;
    }

    private static class Node<T> {
        private Node<T> prev;
        private T value;
        private Node<T> next;


        public Node() {
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Prev is ").append(prev.value)
                    .append(" Value is ").append(value);
            if (next == null) {
                builder.append(" Next is ").append("null");
            } else {
                builder.append(" Next is ").append(next.value);
            }

            return builder.toString();
        }

    }
}
