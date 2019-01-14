package test.java;

import main.java.InsertionSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestInsertionSort extends InsertionSort {
    @Test
    void sortIntListASC() {
        List<Integer> list_i = Arrays.asList(323, 4678, 9, 234, 12, 15);
        List<Integer> sorted_list_i = Arrays.asList(9, 12, 15, 234, 323, 4678);

        InsertionSort.sortASC(list_i);
        assertIterableEquals(sorted_list_i, list_i );
    }

    @Test
    void sortStrListASC() {
        List<String> list_s =  Arrays.asList("б", "в", "а", "г", "ж", "е");
        List<String> sorted_list_s =  Arrays.asList("а", "б", "в", "г", "е", "ж");

        InsertionSort.sortASC(list_s);
        assertIterableEquals(sorted_list_s, list_s);
    }

    @Test
    void sortDoubleListASC() {
        List<Double> list_d = Arrays.asList(323.2, 4678.51, 9.5, 9.2, 51.51, 51.50);
        List<Double> sorted_list_d = Arrays.asList(9.2, 9.5, 51.5, 51.51, 323.2, 4678.51);

        InsertionSort.sortASC(list_d);
        assertIterableEquals(sorted_list_d, list_d);
    }

    @Test
    void sortIntListDESC() {
        List<Integer> list_i = Arrays.asList(323, 4678, 9, 234, 12, 15);
        List<Integer> sorted_list_i = Arrays.asList(4678, 323, 234, 15, 12, 9);

        InsertionSort.sortDESC(list_i);
        assertIterableEquals(sorted_list_i, list_i);
    }

    @Test
    void sortStrListDESC() {
        List<String> list_s =  Arrays.asList("б", "в", "а", "г", "ж", "е");
        List<String> sorted_list_s =  Arrays.asList("ж", "е", "г", "в", "б", "а");

        InsertionSort.sortDESC(list_s);
        assertIterableEquals(sorted_list_s, list_s);
    }

    @Test
    void sortDoubleListDESC() {
        List<Double> list_d = Arrays.asList(323.2, 4678.51, 9.5, 9.2, 51.51, 51.50);
        List<Double> sorted_list_d = Arrays.asList(4678.51, 323.2, 51.51, 51.5, 9.5, 9.2);

        InsertionSort.sortDESC(list_d);
        assertIterableEquals(sorted_list_d, list_d);
    }

}