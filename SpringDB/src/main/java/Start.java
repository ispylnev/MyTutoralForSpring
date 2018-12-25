import dao.implm.SQLiteDAO;
import dao.interfaces.MP3Dao;
import dao.objects.Author;
import dao.objects.MP3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        Author author = new Author();
        author.setName("Ivan");

        MP3 mp3 = new MP3();
        mp3.setName("song");
        mp3.setAuthor(author);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MP3Dao sqlLite = (SQLiteDAO) context.getBean("sqlDAO");
        System.out.println(sqlLite.insertAuthorAndMP3(mp3));


//        MP3 mp3 = new MP3();
//        mp3.setAuthor("Kate");
//        mp3.setName("Song3");
//
//        MP3 mp4 = new MP3();
//        mp4.setAuthor("Kateeeee");
//        mp4.setName("Song3333");
//
//        List<MP3> listMp3 =  new ArrayList<>();
//        listMp3.add(mp3);
//        listMp3.add(mp4);



//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
//        MP3Dao sqLiteDAO = (MP3Dao) applicationContext.getBean("sqlDAO");
//        sqLiteDAO.insertNamedJdbc(mp3);
//        sqLiteDAO.getcountMP3();
//        sqLiteDAO.insertSimpleJdbc(mp3);
//        sqLiteDAO.bacthInsertListNamedJdbs(listMp3);

    }
}
