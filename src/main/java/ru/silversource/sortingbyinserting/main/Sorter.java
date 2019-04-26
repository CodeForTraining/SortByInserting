package ru.silversource.sortingbyinserting.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Sorter extends Thread {

    private File file;
    private String prefix;
    private boolean ascending;
    private String type;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);


    public Sorter(File file, String prefix, String ascending, String type) {
        this.file = file;
        this.prefix = prefix;
        this.ascending = ascending.equals("a")? true : false;
        this.type = type;
    }

    @Override
    public void run(){
        LOGGER.debug("Start sort file: {}", file.getPath());
        sort();
    }

    private void sort() {
        sortArray(getArray());
    }

    private void sortArray(Set array){
        LOGGER.debug("Sort array", array);
    }

    /*private Set getArrayStrings(){
        LOGGER.debug("Sorting file sort(): {}", file.getPath());
        Set linesArray = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            linesArray = reader.lines().collect(Collectors.toSet());
        }catch (IOException e){
            System.out.println("Not file");
            LOGGER.info("Can't sort file: {}", file.getPath());
        }
        return linesArray;
    }*/

    private Set getArray(){
        LOGGER.debug("Sorting file sort(): {}", file.getPath());
        Set linesArray = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            for(String line: reader.lines().collect(Collectors.toSet())){
                if(type.equals("i")){
                    linesArray.add(Integer.parseInt(line.trim()));
                    LOGGER.debug("Array is numeric");
                }else {
                    linesArray.add(line.trim());
                    LOGGER.debug("Array is numeric");
                }
            }
        }catch (IOException e){
            LOGGER.info("Can't read file: {}", file.getPath());
        }catch (NumberFormatException e){
            LOGGER.info("This file \"{}\" may not be sorted as the digital!!!", file);
        }
        return linesArray;
    }
}
