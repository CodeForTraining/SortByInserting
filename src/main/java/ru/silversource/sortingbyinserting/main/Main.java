package ru.silversource.sortingbyinserting.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.silversource.sortingbyinserting.exceptions.SortException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    String path;
    String prefix;
    String contentType;
    String sortMode;

    public static void main(String[] args) {

        GetOpt opt = new GetOpt();
        if(opt.setArgs(args)) {
            Sort sort = new Sort(opt.getPath(), opt.getPrefix(), opt.getContentType(), opt.getSortMode());
            LOGGER.debug("Start sorting!");
            sort.goSort();
        }
    }
}
