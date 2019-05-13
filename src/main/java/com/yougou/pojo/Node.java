package com.yougou.pojo;

public class Node {
    private String id;
    private String pid;
    private String name;
    private boolean parent;

    public Node(String id, String pid, String name, boolean parent) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }
}
