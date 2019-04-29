package ru.silversource.sortingbyinserting.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    String path;
    String prefix;
    String contentType;
    String sortMode;

    public static void main(String[] args) {

        GetOpt opt = new GetOpt();
        if(opt.setArgs(args)) {
            SorterFactory sorterFactory = new SorterFactory(opt.getPath(), opt.getPrefix(), opt.getContentType(), opt.getSortMode());
            LOGGER.debug("Start sorting!");
            sorterFactory.goSort();
        }
    }
}
