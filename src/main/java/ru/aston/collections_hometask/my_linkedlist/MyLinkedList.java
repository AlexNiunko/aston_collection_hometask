package ru.aston.collections_hometask.my_linkedlist;

import ru.aston.collections_hometask.MyList;
import ru.aston.collections_hometask.MyListException;

import java.util.Collection;
import java.util.Comparator;
import java.util.StringJoiner;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head=new Node<>();
    private Node<T> tail=new Node<>();
    private int size=0;

    public MyLinkedList() {
        head.next=tail;
        tail.prev=head;
    }



    @Override
    public boolean add(T t) throws MyListException {
        if (t==null){
            throw new MyListException("Argument is null");
        }
        Node<T>node=new Node<>();
        node.value=t;
        Node lastNode=tail.prev;
        lastNode.next=node;
        node.prev=lastNode;
        tail.prev=node;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T t) throws MyListException {
        if (index<0 || t==null){
            throw new MyListException("Illegal arguments");
        }
        return false;
    }

    @Override
    public T remove(int index) throws MyListException {
        return null;
    }

    @Override
    public boolean remove(T object) {
        Node<T>cursor=head.next;
        while (cursor!=null){
            if (cursor.value.equals(object)){
                if (cursor.next==null){
                    System.out.println("We have founded a tail");
                    Node<T>prev=cursor.prev;
                    prev.next=null;
                    tail.prev=prev;
                    size--;
                    return true;
                }
                Node<T>prev=cursor.prev;
                Node<T>next=cursor.next;
                prev.next=next;
                next.prev=prev;
                size--;
                return true;
            }
            cursor=cursor.next;
        }
        return false;
    }

    @Override
    public T get(int index) throws MyListException {
        if (index>size-1){
            throw new MyListException("The wrong index");
        }
        int flag=index;
        Node<T>cursor=head.next;
        while (flag>0){
            cursor=cursor.next;
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
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) throws MyListException {
        return false;
    }

    @Override
    public void bubbleSort(Comparator<T> comparator) {

    }

    public Node<T> getTail() {
        return tail;
    }

    public void print(){
        Node<T>point=head.next;
        while (point!=null ){
            System.out.println(point);;
            point=point.next;
        }

    }




    private static class Node<T>{
        private Node<T>prev;
        private T value;
        private Node<T>next;


        public Node() {

        }

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder builder=new StringBuilder();
            builder.append("Prev is ").append(prev.value)
                    .append(" Value is ").append(value);
            if (next==null){
                builder.append(" Next is ").append("null");
            }else {
                builder.append(" Next is ").append(next.value);
            }

            return builder.toString();
        }
    }
}
