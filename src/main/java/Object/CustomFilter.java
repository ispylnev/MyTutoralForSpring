package Object;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFilter implements FilenameFilter {

    private String extension;

    public CustomFilter (String extension){
        this.extension = extension;
    }

    public boolean accept(File file, String name) {
        return name.toUpperCase().endsWith("." + extension.toUpperCase());
    }
}
