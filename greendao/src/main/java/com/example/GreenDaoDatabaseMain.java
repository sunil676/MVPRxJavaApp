package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoDatabaseMain {


    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(5, "com.sunil.data.model");
        addArticle(schema);
        new DaoGenerator().generateAll(schema, "../MVPRxJavaApp/app/src/main/java/");
    }

    private static void addArticle(Schema schema) {
        Entity article = schema.addEntity("Article");
        article.addIdProperty();
        article.addStringProperty("tittle").notNull();
        article.addStringProperty("description");
        article.addStringProperty("author");
    }
}
