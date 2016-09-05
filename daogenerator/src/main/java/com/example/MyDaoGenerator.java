package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    Schema schema;

    public static void main(String [] args){
        MyDaoGenerator myDaoGenerator = new MyDaoGenerator();
        myDaoGenerator.generator();
    }
    public MyDaoGenerator(){
        schema = new Schema(1,"com.jsy.greendao");
        addBean(schema);
        addUser(schema);
    }

    public void addUser(Schema schema){
        Entity note = schema.addEntity("User");
        note.addIdProperty();
        note.addStringProperty("name").notNull();
        note.addDateProperty("gender");
    }
    public void addBean(Schema schema){
        Entity note = schema.addEntity("Note");
        note.addStringProperty("text").notNull();
        note.addDateProperty("date");
    }

    public void generator(){
        try {
            new DaoGenerator().generateAll(schema,"C:\\Users\\Administrator\\Downloads\\MyGreenDAO-master\\MyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
