Usage: java -jar sort.jar <args...>
           (to execute a jar file)
where args usage:
    <path in dir> [--out-prefix=...] [--content-type=<i|s>] [--sort-mode=<a|d>]
    
where args include:
    path_in_dir         required argument, full path to the directory containing
                        the files to be sorted (example "C:\in-dir\")
    --out-prefix        the prefix for the sorted file, cannot be empty
                            (example "prefix_")
    --content-type      type of items to be sorted: i-integer, s-string
    --sort-mode         mode of sorting: a - ascending, d - descending