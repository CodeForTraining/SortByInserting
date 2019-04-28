package ru.silversource.sortingbyinserting.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.silversource.sortingbyinserting.exceptions.ErrorCode;
import ru.silversource.sortingbyinserting.exceptions.SortException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GetOpt {

    private Map<String, String> argsMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    public GetOpt() {

    }

    private void createMapArgs(String[] args) throws SortException {
        if(args.length == 0){
            throw new SortException(ErrorCode.MISSING_PATH_ARGUMENT);
        }
        argsMap = new HashMap<>();
        for (String arg : args){
            if(arg.startsWith("--")){
                String[] pair = arg.split("=");
                String key = pair[0].replaceFirst("--", "");
                try {
                    String value = pair[1];
                    checkPair(key, value);
                    argsMap.put(key, value);
                }catch (ArrayIndexOutOfBoundsException e){
                    throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
                }
            }else if(Pattern.matches("^[a-zA-Z]:\\\\[a-z!@#$%^&*()_+=\"';-]+\\\\", arg)) {
                argsMap.put("path", arg);
            }else{
                throw new SortException(ErrorCode.MISSING_PATH_ARGUMENT);
            }
        }
    }

    private void checkPair(String key, String value) throws SortException {
        switch (key) {
            case "out-prefix":
                if(value.equals("")){
                    throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
                }
                break;
            case "content-type":
                if (value.equals("i")||value.equals("s")){
                    break;
                }else{
                    throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
                }
            case "sort-mode":
                if (value.equals("a")||value.equals("d")) {
                    break;
                }else {
                    throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
                }
            default:
                throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    public String getPath(){

        return argsMap.get("path");
    }

    public String getPrefix(){
        String prefix = argsMap.get("out-prefix");
        if(prefix == null){
            return "default_";
        }
        return prefix;
    }

    public String getContentType(){
        String type = argsMap.get("content-type");
        if(type == null) {
            return "s";
        }
        return type;
    }

    public String getSortMode(){
        String mode = argsMap.get("sort-mode");
        if(mode == null) {
            return "a";
        }
        return mode;
    }

    public Map<String, String> getOptions(){
        return argsMap;
    }

    public boolean setArgs(String[] args) {
        try {
            createMapArgs(args);
        }catch (SortException e){
            LOGGER.info(e.getErrorCode().getErrorString());
            printHelpArgs();
            return false;
        }
        return true;
    }

    private void printHelpArgs() {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/readme.txt")))){
            while((line = reader.readLine()) != null ) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
