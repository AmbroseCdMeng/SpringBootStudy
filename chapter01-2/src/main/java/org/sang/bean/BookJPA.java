package org.sang.bean;

import javax.persistence.*;

/**
 * Book 实体类（整合 SpringBoot JPA）
 */

// @Entity 表示该类是一个实体类。在项目启动时会根据该类自动生成一个数据表，表名称即 @Entity 的 name 值，如果不配置，默认为类名。
@Entity(name = "t_book")
public class BookJPA {
    // @Id 表示该实体类的主键。所有实体类都要有主键
    @Id
    // @GeneratedValue 表示主键自动生成，strategy 表示生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column 表示表中字段名称和实体类中属性名称的映射。如果不配置，默认生成字段名就是属性名。nullable 表示是否可为空
    @Column(name = "book_name", nullable = false)
    private String name;

    private String author;
    private Float price;

    // @Transient 表示生成数据表时，忽略该字段
    @Transient
    private String description;


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookJPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
