package main.java;

import java.util.List;

//Сортировка вставками
public abstract class InsertionSort {
    public static <E extends Comparable<? super E>> void sortASC (List<E> list){
        for(int i = 1; i < list.size(); i++)
            for (int j = i; j > 0 && list.get(j - 1).compareTo(list.get(j)) > 0; j--) {
                E buf = list.get(j - 1);
                list.set(j - 1, list.get(j));
                list.set(j, buf);
            }
    }

    public static <E extends Comparable<? super E>> void sortDESC (List<E> list){
        for(int i = 1; i < list.size(); i++)
            for (int j = i; j > 0 && list.get(j - 1).compareTo(list.get(j)) < 0; j--) {
                E buf = list.get(j - 1);
                list.set(j - 1, list.get(j));
                list.set(j, buf);
            }
    }
}
