package dao.objects;

public class MP3 {
    private String name;
    private Author author;
    private int id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }


    public void setAuthor(Author author ) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "MP3{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", id=" + id +
                '}';
    }
}
