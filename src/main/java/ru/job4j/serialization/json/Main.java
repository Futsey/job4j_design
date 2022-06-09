package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final SomeObj someObj = new SomeObj(true, 5959, "someObj",
                new SomeObj.SomeObjInObj[
                        new SomeObj.SomeObjInObj(36, "someName"),
                        new SomeObj.SomeObjInObj(1456, "someName1456")]);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        System.out.println(gson.toJson(someObj));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        final String someObjJson =
                "{"
                        + "\"exist\":true,"
                        + "\"id\":5959,"
                        + "\"name\":\"someObj\""
                        + "["
                        + "{"
                        + "\"id\": 36"
                        + "\"name\":\"someName\""
                        + "},"
                        + "{"
                        + "\"id\": 1456"
                        + "\"name\":\"someName1456\""
                        + "},"
                        + "]\"}";
        final SomeObj someObjMod = gson.fromJson(someObjJson, SomeObj.class);
        System.out.println(someObjMod);
    }
}
