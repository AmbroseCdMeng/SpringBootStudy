package org.sang.bean;

public class Role {
    private Integer id;
    private String name;
    private String nameZH;
    /* Setter & Getter */

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

    public String getNameZH() {
        return nameZH;
    }

    public void setNameZH(String nameZH) {
        this.nameZH = nameZH;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZH='" + nameZH + '\'' +
                '}';
    }
}
