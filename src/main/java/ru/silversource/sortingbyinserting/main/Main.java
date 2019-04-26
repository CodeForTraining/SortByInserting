package ru.silversource.sortingbyinserting.main;


import ru.silversource.sortingbyinserting.exceptions.SortException;

public class Main {

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
        System.out.println(sort.start());
    }
}
