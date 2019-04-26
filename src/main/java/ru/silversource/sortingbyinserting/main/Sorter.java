package ru.silversource.sortingbyinserting.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.silversource.sortingbyinserting.exceptions.SortException;
import ru.silversource.sortingbyinserting.sort_methods.InsertionSort;

import java.io.*;
import java.util.*;
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

    private void sort(){
        LOGGER.debug("Sorting file sort(): {}", file.getPath());
        List<String> list = null;
        File fileOut = new File("C:/out_dir/" + prefix + file.getName());
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines().collect(Collectors.toList());

            if(type.equals("s")) {
                //strings = InsertionSort.sortString(strings);
                list = InsertionSort.sortListString(list);
            }else {
                list = InsertionSort.sortListInt(list);
            }

            LOGGER.debug("Array is string: {}", list);

        } catch (FileNotFoundException ex) {
            LOGGER.debug("File not found: {}", file.getPath());
        }catch (IOException e){
            LOGGER.debug("Can't read file: {}", file.getPath());
        } catch (SortException e) {
            LOGGER.info("File not sorting as digital: {}", file.getPath());
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))){
            for(String line: list) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }catch (FileNotFoundException ex) {
            LOGGER.debug("File not found: {}", fileOut.getPath());
        }catch (IOException e) {
            LOGGER.debug("Can't write file: {}", fileOut.getPath());
        }
    }
}
