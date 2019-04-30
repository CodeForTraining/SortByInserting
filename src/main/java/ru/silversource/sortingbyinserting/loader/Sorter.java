package ru.silversource.sortingbyinserting.loader;

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
    private int ascending;
    private String type;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);


    public Sorter(File file, String prefix, String ascending, String type) {
        this.file = file;
        this.prefix = prefix;
        this.ascending = ascending.equals("a")? 1 : -1;
        this.type = type;
    }

    @Override
    public void run(){
        LOGGER.debug("Start sort file: {}", file.getPath());
        sort();
    }

    private void sort(){
        List<String> list = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines().collect(Collectors.toList());

            if(type.equals("s")) {
                //strings = InsertionSort.sortString(strings);
                list = InsertionSort.sortListString(list, ascending);
            }else {
                list = InsertionSort.sortListInt(list, ascending);
            }

            LOGGER.debug("Array is string: {}", list);

        } catch (FileNotFoundException ex) {
            LOGGER.info("File not found: {}", file.getPath());
            return;
        }catch (IOException e){
            LOGGER.info("Can't read file: {}", file.getPath());
            return;
        } catch (SortException e) {
            LOGGER.info("File not sorting as digital: {}", file.getPath());
            return;
        }
        File fileOut = new File(file.getParent()+ "\\" + prefix + file.getName());
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
