package org.sang.bean;

/**
 * 整合 MongoDB 的实体类
 */
public class BookMongo {
    private Integer id;
    private String name;
    private String author;

    @Override
    public String toString() {
        return "BookMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
