package ru.aston.collections_hometask.my_linkedlist;

import ru.aston.collections_hometask.MyListException;

import java.util.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) throws MyListException {
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
       list.remove(new Integer(4));
       list.remove(new Integer(3));
        list.print();
      System.out.println(list.getTail());



    }
}
