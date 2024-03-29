package ru.aston.collections_hometask.my_linkedlist;

import ru.aston.collections_hometask.MyList;
import ru.aston.collections_hometask.MyListException;
import ru.aston.collections_hometask.Person;
import ru.aston.collections_hometask.my_arraylist.MyArrayList;
import ru.aston.collections_hometask.my_arraylist.MyArrayListTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

public class MyLinkedListTest {
    public static void main(String[] args) throws MyListException {
        int[] array = new int[]{4, 1, 0, 6, 2, 3, 4};
        Collection<Integer> collection = new ArrayList<>();
        for (int value : array) {
            collection.add(value);
        }
        MyList<Integer> integerMyList = new MyLinkedList<>(collection);
        System.out.printf("Here is our list %s , list size is %d  \n", integerMyList, integerMyList.size());
        System.out.printf("Removed element is %d , our list is %s, list size is %d  \n", integerMyList.remove(3), integerMyList, integerMyList.size());
        integerMyList.bubbleSort(new MyIntegerComparator());
        System.out.printf("Here is sorted list %s \n", integerMyList);
        System.out.println();

        String[] writers = {"Pushkin", "Onore de Balsak", "Yanka Kupala", "Maksim Bagdanovich", "Vasil Bykau"};
        String[] foreignWriters = {"Hemminguei", "Mark Tven"};
        Collection<String> listForeignWriters = new ArrayList<>();
        for (String item : foreignWriters) {
            listForeignWriters.add(item);
        }
        MyList<String> writersList = new MyLinkedList<>();
        for (String item : writers) {
            writersList.add(item);
        }
        System.out.printf("Here are the best writers %s , size is %d \n", writersList, writersList.size());
        writersList.add(1, "Michail Bulgakov");
        System.out.printf("Here is changed list %s , size is %d \n", writersList, writersList.size());
        writersList.addAll(3, listForeignWriters);
        System.out.printf("Here is the joined list %s , size is %d \n", writersList, writersList.size());
        writersList.bubbleSort(new MyStringComparator());
        System.out.printf("Here is the sorted list %s \n", writersList);

        Person person1 = new Person("Alex", 14, 134);
        Person person2 = new Person("Tom", 18, 194);
        Person person3 = new Person("Jack", 32, 184);
        Person person4 = new Person("Viktor", 42, 178);
        Person person5 = new Person("Jofrey", 52, 179);
        System.out.println();

        MyList<Person> personMyLinkedList = new MyLinkedList<>();
        personMyLinkedList.add(person1);
        personMyLinkedList.add(person3);
        personMyLinkedList.add(person2);
        personMyLinkedList.add(person5);
        personMyLinkedList.add(person4);
        System.out.printf("Person list %s \n", personMyLinkedList);
        personMyLinkedList.bubbleSort(new MyPersonAgeComparator());
        System.out.printf("This is persons sorted by age %s", personMyLinkedList);
        personMyLinkedList.bubbleSort(new MyPersonHeightComparator().thenComparing(new MyPersonAgeComparator()));
        System.out.printf("This is persons sorted by height and then by age  %s \n", personMyLinkedList);

    }

    private static class MyIntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class MyStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.charAt(0) - o2.charAt(0);
        }
    }

    private static class MyPersonAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    private static class MyPersonHeightComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return (int) (o1.getHeight() - o2.getHeight());
        }
    }


}
