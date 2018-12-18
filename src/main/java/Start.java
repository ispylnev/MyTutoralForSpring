import Objects.FileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileUtil = (FileManager) context.getBean("fileManager");
        fileUtil.getExtensionCount("c:\\Windows\\System32");
//        fileUtil.getExtensionList("c:\\Windows\\");
    }
}
