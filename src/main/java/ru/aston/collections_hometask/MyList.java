package ru.aston.collections_hometask;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<T> {
    boolean add(T t) throws MyListException;
    boolean add(int index,T t) throws MyListException;
    T remove(int index) throws MyListException;
    boolean remove(T object);
    T get(int index) throws MyListException;
    int size();
    boolean addAll(int index,Collection<? extends T> collection) throws MyListException;
    boolean addAll(Collection<? extends T> collection) throws MyListException;
    void bubbleSort(Comparator<T>comparator) throws MyListException;
}
