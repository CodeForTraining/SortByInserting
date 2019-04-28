package ru.silversource.sortingbyinserting.sort_methods;

import ru.silversource.sortingbyinserting.exceptions.ErrorCode;
import ru.silversource.sortingbyinserting.exceptions.SortException;

import java.util.List;

public class InsertionSort {

    public static List<String> sortListInt(List<String> array, int ascending) throws SortException{
        for(int j = 0; j < array.size(); j++){
            try {
                int temp = Integer.parseInt(array.get(j));
                int i = j - 1;
                for (; i >= 0 && Integer.parseInt(array.get(i))*ascending > temp*ascending; i--) {
                    array.set(i + 1, array.get(i));
                }
                array.set(i + 1, "" + temp);
            }catch (NumberFormatException e){
                throw new SortException(ErrorCode.NOT_INTEGER);
            }
        }
        return array;
    }

    public static List<String> sortListString(List<String> array, int ascending){
        for(int j = 0; j < array.size(); j++){
            String temp = array.get(j);
            int i = j- 1;
            for(;i >= 0 && array.get(i).compareTo(temp)*ascending > 0;i--){
                array.set(i + 1, array.get(i));
            }
            array.set(i + 1, temp);
        }
        return array;
    }

    public static Integer[] sort(Integer[] arrayIn){
        Integer[] arrayInteger = arrayIn.clone();
        int size = arrayInteger.length;
        for (int i = 0; i < size - 1; i++) {
            Integer last = arrayInteger[size - 1];
            for (int k = 0; k <= i; k++) {
                if (last < arrayInteger[k]) {
                    for (int j = size - 1; j > k; j--) {
                        arrayInteger[j] = arrayInteger[j - 1];
                    }
                    arrayInteger[k] = last;
                    break;
                }else if(k == i) {
                    for (int j = size - 1; j > i + 1; j--) {
                        arrayInteger[j] = arrayInteger[j - 1];
                    }
                    arrayInteger[i+1] = last;
                }
            }
        }
        return arrayInteger;
    }

    public static String[] sortArrInt(String[] array){

        for(int j = 0; j < array.length; j++){
            int temp = Integer.parseInt(array[j]);
            int i = j- 1;
            for(;i >= 0 && Integer.parseInt(array[i]) <= temp;i--){
                array[i + 1] = array[i];
            }
            array[i + 1] = "" + temp;
        }
        return array;
    }

    public static String[] sortArrString(String[] array){
        for(int j = 0; j < array.length; j++){
            String temp = array[j];
            int i = j- 1;
            for(;i >= 0 && array[i].compareTo(temp)<0;i--){
                array[i + 1] = array[i];
            }
            array[i + 1] = temp;
        }
        return array;
    }
}
