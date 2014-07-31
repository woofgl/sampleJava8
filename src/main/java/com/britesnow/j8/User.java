package com.britesnow.j8;

public class User{

    private String id;
    private String name;
    private Long age;
    private String sex;

    public User(String name) {
        this.name = name;
    }

    public User(String id, String name, String age,String sex) {
        this.id = id;
        this.name = name;
        this.age = Long.parseLong(age);
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString() {
        return "" + name + "(" + age + ")";
    }
}