package dao.implm;
import annatations.ShowTransAlive;
import dao.interfaces.MP3Dao;
import dao.objects.Author;
import dao.objects.MP3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("sqlDAO")
public class SQLiteDAO implements MP3Dao {
    private static final String mp3Table = "mp3";
    private static final String mp3View = "mp3_view";
//  параметризованные нативные запросы
    private NamedParameterJdbcTemplate jdbcTemplate;

//    избавились от нативного кода sql
    private SimpleJdbcInsert simpleJdbcInsert;

    private DataSource dataSource;



    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("mp3" );
        this.dataSource = dataSource;
    }
//    @Override
//    public void insertNamedJdbc(MP3 mp3) {
////        this for JDBCTempalte
////        String sql = "insertNamedJdbc into mp3 (name, author) VALUES (?, ?)";
////        jdbcTemplate.update(sql, new Object[] { mp3.getName(), mp3.getAuthor() }
//        String sql = "insert into mp3 (name, author) VALUES (:name, :author)";
//        MapSqlParameterSource param = new MapSqlParameterSource();
//        param.addValue("name", mp3.getName());
//        param.addValue("author", mp3.getAuthor());
//        jdbcTemplate.update(sql,param);
//
//    }

//    подключил аспектами в xml
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    @ShowTransAlive
    public int insertMP3(MP3 mp3){
        MapSqlParameterSource param = new MapSqlParameterSource();
        String sqlInsertMp3 = "insert into mp3(author_id, name) VALUES (:author_id, :name)";
        int author_id = insertAuthor(mp3.getAuthor());
        param.addValue("author_id",author_id);
        param.addValue("name", mp3.getName());
        return jdbcTemplate.update(sqlInsertMp3,param);
    }

    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
    public int insertAuthor(Author author) {
      String sql = "insert into author(author_name) VALUES (:name)";
      MapSqlParameterSource param = new MapSqlParameterSource();
      param.addValue("name",author.getName());
//      Захватим ID  записи
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql,param,keyHolder);
        return  keyHolder.getKey().intValue();
    }


//
//    @Override
//    public void insertSimpleJdbc(MP3 mp3) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("name", mp3.getName());
//        params.addValue("author", mp3.getAuthor());
//        int i = simpleJdbcInsert.execute(params);
//        System.err.print(i);
//    }

//    @Override
//    public void insertListNamedJdbs(List<MP3> listMp3) {
//        for (MP3 mp3 : listMp3){
//            insertNamedJdbc(mp3);
//        }

//    }

    @Override
    public int bacthInsertListNamedJdbs(List <MP3> listMp3) {

//        String sql = "insert into mp3 (name,author) values (:name,:author)";
//        int i=0;
//        SqlParameterSource [] batch = new SqlParameterSource[mp3.size()];
//
//        for(MP3 listMp3 : mp3 ){
//
//            MapSqlParameterSource params = new MapSqlParameterSource();
//            params.addValue("name", listMp3.getName());
//            params.addValue("author", listMp3.getAuthor());
//            batch [i] =  params;
//            i++;
        //        Плохо т.к. можно перепутать параметры при использовании запроса выше (sql)
//        SqlParameterSource [] batch = SqlParameterSourceUtils.createBatch(mp3.toArray());
//        int[] count =  jdbcTemplate.batchUpdate(sql,batch);
//        return count.length;
//        int i = 0;
//        for (MP3 mp3:listMp3 ){
//            insertAuthorAndMP3(mp3);
//            i++;
//        }
//        return i;


        return 0;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from mp3 where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        jdbcTemplate.update(sql,param);

    }

    @Override
    public MP3 getMP3ByID(int id) {
        String sql = "select * from mp3 where id=:id";
//        Чтобы не юзать "?"
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());

    }

    @Override
    public Map<String, Integer> getStat() {
       String sql = "select author_name, count(*) as count from" + mp3View + "group by author_name";
       return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {

           @Override
           public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
               Map <String,Integer> map = new HashMap<>();
               while (resultSet.next()){
                   String author = resultSet.getString("author_name");
                   int count = resultSet.getInt("count");
                   map.put(author,count);
               }
               return map;
           }
       });
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "Select * from mp3 where name =:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name",name);
        return jdbcTemplate.query(sql, params, new MP3RowMapper());

    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        return null;
    }

    @Override
    public int getcountMP3() {
        String sql = "select count(*) from mp3";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);

    }

    //      Mapper  for select from BD
    static private final class MP3RowMapper implements RowMapper<MP3> {

        @Override
        public MP3 mapRow(ResultSet resultSet, int rouNum) throws SQLException {
            Author author = new Author();
            author.setId(resultSet.getInt("author_id"));
            author.setName("author_name");

            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("id"));
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(author);
            return mp3;
        }
    }
}
