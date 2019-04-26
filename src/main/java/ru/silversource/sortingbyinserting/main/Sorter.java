package ru.silversource.sortingbyinserting.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sorter extends Thread {

    File file;
    Set<Integer> numbersIn;
    Set<String> stringsIn;
    List<Integer> numbersOut;
    List<String> stringsOut;


    public Sorter(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        sort();
    }

    private void sort() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            stringsIn = reader.lines().collect(Collectors.toSet());
        }catch (IOException e){

        }

    }
}
