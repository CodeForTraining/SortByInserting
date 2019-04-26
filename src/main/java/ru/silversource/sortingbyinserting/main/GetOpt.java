package ru.silversource.sortingbyinserting.main;

import ru.silversource.sortingbyinserting.exceptions.ErrorCode;
import ru.silversource.sortingbyinserting.exceptions.SortException;

public class GetOpt {

    String[] args;

    public GetOpt(String[] args) {
        this.args = args;
    }

    public String getPath() {
        String path = args[0];
        return path;
    }

    public String getPrefix() throws SortException {
        String prefix;
        try {
            prefix = args[1];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new SortException(ErrorCode.MISSING_PREFIX);
        }
        if(prefix.startsWith("--out-prefix")) {
            return prefix.replace("--out-prefix=", "");
        }else{
            throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    public String getContentType() throws SortException{
        String type = null;
        try {
            type = args[2];
        }catch (ArrayIndexOutOfBoundsException e){
            return type;
        }
        if(type.startsWith("--content-type")) {
            return type.replace("--content-type=", "");
        }else{
            throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    public String getSortMode() throws SortException, ArrayIndexOutOfBoundsException {
        String mode = null;
        try {
            mode = args[3];
        }catch (ArrayIndexOutOfBoundsException e){
            return mode;
        }
        if(mode.startsWith("--sort-mode")) {
            return mode.replace("--sort-mode=", "");
        }else{
            throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    public String getOption(String arg) throws SortException, ArrayIndexOutOfBoundsException {
        String opt = null;
        try {
            opt = arg;
        }catch (ArrayIndexOutOfBoundsException e){
            return opt;
        }
        if(opt.startsWith("--sort-mode")) {
            return opt.replace("--sort-mode=", "");
        }else{
            throw new SortException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }
}
