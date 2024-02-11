package ru.aston.collections_hometask.my_arraylist;

import ru.aston.collections_hometask.MyList;

import java.util.*;

public class MyArrayList<T> implements MyList<T> {

    private final int DEFAULT_CAPACITY = 10;
    private final double INCREASE_VALUE = 1.5;

    private T[] storage;
    private int size;

    public MyArrayList() {
        this.storage = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int size) {
        if (size < 0) {
            try {
                throw new MyListException("Illegal size");
            } catch (MyListException e) {
                System.out.println("Can't create list ,there is a wrong size");
            }
        }
        this.size = 0;
        this.storage = (T[]) new Object[size];
    }

    public MyArrayList(Collection<? extends T> collection) {
        size = collection.size();
        this.storage = (T[]) collection.toArray(new Object[size]);
    }


    public boolean add(int index, T t) throws MyListException {
        if (index < 0 || index > this.size) {
            throw new MyListException("The wrong index");
        }
        if (this.storage.length - 1 < this.size) {
            this.storage = increaseStorage();
        }
        try {
            insert(this.storage, t, this.size, index);
            this.size++;
        } catch (Exception e) {
            System.out.println("Failed to add " + t.toString());
            return false;
        }
        return true;
    }

    public boolean add(T t) {
        int slot = this.size + 1;
        if (slot > this.storage.length) {
            this.storage = increaseStorage();
        }
        try {
            this.storage[slot - 1] = t;
            this.size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public T remove(int index) throws MyListException {
        if (index < 0 || index > size - 1) {
            throw new MyListException("The wrong index");
        }
        T element = this.storage[index];
        try {
            delete(this.storage, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    @Override
    public boolean remove(T object) {
        for (int i = 0; i < size - 1; i++) {
            if (this.storage[i].equals(object)) {
                delete(this.storage, i);
                return true;
            }
        }
        return false;
    }

    public T get(int index) throws MyListException {
        if (index < 0 || index > size - 1) {
            throw new MyListException("Illegal index, we can't found value");
        }
        return this.storage[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean addAll(int index, Collection<? extends T> collection) throws MyListException {
        if (collection == null) {
            throw new IllegalArgumentException("Collection is null");
        }
        int flag = index;
        T[] temp = (T[]) collection.toArray();
        for (int i = 0; i < temp.length; i++) {
            if (!add(flag, temp[i])) {
                return false;
            }
            flag++;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) throws MyListException {
        if (collection == null) {
            throw new MyListException("The collection is null");
        }
        T[] temp = (T[]) collection.toArray();
        for (int i = 0; i < temp.length; i++) {
            if (!add(temp[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void bubbleSort(Comparator<T> comparator) {
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 1; i < this.size; i++) {
                if (comparator.compare(this.storage[i], this.storage[i - 1]) < 0) {
                    flag = false;
                    T temp = this.storage[i - 1];
                    this.storage[i - 1] = this.storage[i];
                    this.storage[i] = temp;
                }
            }
        }
    }

    static <T extends Comparable<T>> void sort(Collection<T> collection) throws MyListException {
        MyArrayList<T>list=new MyArrayList<>(collection);
        collection.clear();
        list.bubbleSort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < list.size(); i++) {
            collection.add(list.get(i));
        }
    }

    static <T extends Comparable<T>> void sort(Collection<T> collection,Comparator<T>comparator) throws MyListException {
        MyArrayList<T>list=new MyArrayList<>(collection);
        collection.clear();
        list.bubbleSort(comparator);
        for (int i = 0; i < list.size(); i++) {
            collection.add(list.get(i));
        }
    }



    private void insert(T[] array, T value, int size, int index) {
        int flag = size;
        int in = index;
        for (int i = flag; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
    }


    private T[] increaseStorage() {
        T[] temp = this.storage;
        T[] newStorage = (T[]) new Object[(int) (temp.length * INCREASE_VALUE)];
        for (int i = 0; i < temp.length; i++) {
            newStorage[i] = temp[i];
        }
        return newStorage;
    }

    private void delete(T[] array, int index) {
        array[index] = null;
        for (int i = index; i < this.size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[this.size - 1] = null;
        this.size--;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String delimeterFirst = "[";
        String delimeterSecond = "]";
        String comma = ",";
        for (int i = 0; i < this.size; i++) {
            StringBuilder builder1 = new StringBuilder();
            builder1.append(delimeterFirst).append(this.storage[i]).append(delimeterSecond);
            if (i != this.size - 1) {
                builder1.append(comma);
            }
            builder.append(builder1);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        if (size != that.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(storage);
        result = 31 * result + size;
        return result;
    }
}
