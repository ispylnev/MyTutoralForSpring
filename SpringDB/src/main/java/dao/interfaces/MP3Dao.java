package dao.interfaces;

import dao.objects.Author;
import dao.objects.MP3;

import java.util.List;
import java.util.Map;

public interface MP3Dao {
//    для использования в нативных запросов  с параметрами
//    void insertNamedJdbc(MP3 mp3);

    int insertAuthor(Author author);

    int insertMP3(MP3 mp3);

//    для избавления от нативных запросов SQL
//    void insertSimpleJdbc(MP3 mp3);

//    для использвоания нативного запроса с обращением к БД через цикл и используя метод insrt()
//    void insertListNamedJdbs(List<MP3> ListMp3);

//    не обращаемся к БД при добавление нескольких записей
    int bacthInsertListNamedJdbs(List<MP3> listMp3);

    void delete(int id);

    MP3 getMP3ByID(int id);

    Map<String, Integer> getStat();

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getcountMP3();
}
