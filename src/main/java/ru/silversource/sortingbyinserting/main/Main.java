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

        GetOpt opt = new GetOpt(args);
        Sort sort = null;
        try {
            sort = new Sort(opt.getPath(), opt.getPrefix(), opt.getContentType(), opt.getSortMode());
        }catch (SortException e){
            System.out.println(e.getErrorCode().getErrorString());//else help info
            return;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter all arguments!");
        }
        LOGGER.debug("Start sorting!");
        System.out.println(sort.start());
    }
}
