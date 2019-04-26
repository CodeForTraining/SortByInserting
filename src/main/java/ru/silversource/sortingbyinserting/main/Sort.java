package ru.silversource.sortingbyinserting.main;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sort {

    String path;
    String prefix;
    String contentType;
    String sortMode;
    List<File> files;

    public Sort(String path, String prefix, String contentType, String sortMode) {
        this.path = path;
        this.prefix = prefix;
        this.contentType = contentType;
        this.sortMode = sortMode;
    }

    public Set<String> getOptions(){
        Set<String> opt = new HashSet();
        opt.add(path);
        opt.add(prefix);
        opt.add(contentType);
        opt.add(sortMode);
        return opt;
    }

    public String start() {
        File folder = new File(path);
        files = Arrays.asList(folder.listFiles());
        for(File file: files){
            Sorter sorter = new Sorter(file);
            sorter.start();
        }
        return "Complete!";
    }
}
