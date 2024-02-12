package ru.aston.collections_hometask.my_linkedlist;

import ru.aston.collections_hometask.MyListException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) throws MyListException {
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.print();
        System.out.println(list.size());

        Collection<Integer> collection=new ArrayList<>();
        collection.add(5);
        collection.add(6);
        collection.add(7);
        collection.add(8);
        collection.add(9);

        list.addAll(collection);
        list.print();
        System.out.println(list.size());




    }
}
