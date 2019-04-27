package ru.silversource.sortingbyinserting.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.silversource.sortingbyinserting.exceptions.ErrorCode;
import ru.silversource.sortingbyinserting.exceptions.SortException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GetOpt {

    //private String[] args;
    private Map<String, String> argsMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(Sorter.class);

    public GetOpt(String[] args) throws SortException {
            createMapArgs(args);
    }

    private void createMapArgs(String[] args) throws SortException {
        argsMap = new HashMap<>();
        for (String arg : args){
            if(arg.startsWith("--")){
                String[] pair = arg.split("=");
                argsMap.put(pair[0].replaceFirst("--", ""), pair[1]);
            }else if(Pattern.matches("^[a-zA-Z]:\\\\[a-z-]+\\\\", arg)) {
                argsMap.put("path", arg);
            }else{
                throw new SortException(ErrorCode.MISSING_PATH_ARGUMENT);
            }
        }
    }

    public String getPath() throws SortException {

        return argsMap.get("path");
    }

    public String getPrefix() throws SortException {
        String prefix = argsMap.get("out-prefix");
        if(prefix == null){
            return "default_";
        }
        return prefix;
    }

    public String getContentType() throws SortException{
        String type = argsMap.get("content-type");
        if(type == null) {
            return "s";
        }
        return type;
    }

    public String getSortMode() throws SortException, ArrayIndexOutOfBoundsException {
        String mode = argsMap.get("sort-mode");
        if(mode == null) {
            return "a";
        }
        return mode;
    }

    public Map<String, String> getOptions(){
        return argsMap;
    }
}
