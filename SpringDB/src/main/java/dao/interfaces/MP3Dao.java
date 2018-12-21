package dao.interfaces;

import dao.objects.MP3;

import java.util.List;

public interface MP3Dao {
//    для использования в нативных запросов  с параметрами
    void insertNamedJdbc(MP3 mp3);

//    для избавления от нативных запросов
    void insertSimpleJdbc(MP3 mp3);

//    для использвоания нативного запроса с обращением к БД через цикл и используя метод insrt()
    void insertListNamedJdbs(List<MP3> ListMp3);
//    не обращаемся к БД при добавление нескольких записей
    int bacthInsertListNamedJdbs(List<MP3> mp3);

    void delete(int id);

    MP3 getMP3ByID(int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getcountMP3();
}
