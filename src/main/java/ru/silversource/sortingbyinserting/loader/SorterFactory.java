package ru.silversource.sortingbyinserting.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.silversource.sortingbyinserting.exceptions.ErrorCode;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SorterFactory {

    String path;
    String prefix;
    String contentType;
    String sortMode;
    List<File> files;

    private static final Logger LOGGER = LoggerFactory.getLogger(SorterFactory.class);

    public SorterFactory(String path, String prefix, String contentType, String sortMode) {
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

    public void goSort() {
        LOGGER.debug("Start sorting method start()!");
        File folder = new File(path);
        if(!folder.exists()){
            LOGGER.info(ErrorCode.PATH_NOT_FOUND.getErrorString());
            return ;
        }
        files = Arrays.asList(folder.listFiles());
        for(File file: files){
            Sorter sorter = new Sorter(file, prefix, sortMode, contentType);
            LOGGER.debug("Command start sort: ", file.getPath());
            sorter.start();
        }
    }
}
