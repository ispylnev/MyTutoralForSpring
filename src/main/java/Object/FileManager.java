package Object;

import annotation.ShowResult;
import annotation.ShowTime;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
@Component
public class FileManager {
    @ShowResult
    @ShowTime
    public Set<String> getExecList(String folder){
        File dir = new File (folder);
        Set<String> execList = new TreeSet<>();
        for (String fileName : dir.list()){
            File file = new File(dir.getAbsolutePath()+"\\"+fileName);
            int i = fileName.lastIndexOf(".");
            if (file.isFile() && i!=-1){
                execList.add(fileName.substring(i+1,fileName.length()).toLowerCase());
            }
        }
        return execList;
    }
    @ShowResult
    @ShowTime
    public Map<String,Integer> getExtensionCount(String folder){
        File dir = new File(folder);
        Map<String, Integer> map = new HashMap<>();
        for(String ext : getExecList(folder)){
            FilenameFilter filenameFilter = new CustomFilter(ext);
            map.put(ext, dir.listFiles(filenameFilter).length);
        }
        return map;
    }
}
