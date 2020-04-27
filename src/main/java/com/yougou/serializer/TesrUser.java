package com.yougou.serializer;

import java.io.Serializable;

public class TesrUser implements Serializable {

     


    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TesrUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
