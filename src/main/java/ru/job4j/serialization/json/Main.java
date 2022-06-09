package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"});
        final SomeObj someObj = new SomeObj(true, 5959, "someObj",
                new SomeObj.SomeObjInObj[]{
                        new SomeObj.SomeObjInObj(36, "someName"),
                        new SomeObj.SomeObjInObj(1456, "someName1456")});

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
                        + "\"name\":\"someObj\","
                        + "\"SomeObjInObj\":"
                        + "["
                        + "{"
                        + "\"id\": 36,"
                        + "\"name\":\"someName\""
                        + "},"
                        + "{"
                        + "\"id\": 1456,"
                        + "\"name\":\"someName1456\""
                        + "}"
                        + "]}";
        final SomeObj someObjMod = gson.fromJson(someObjJson, SomeObj.class);
        System.out.println(someObjMod);

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        JAXBContext someObjContext = JAXBContext.newInstance(SomeObj.class);
        marshaller = someObjContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(someObj, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        unmarshaller = someObjContext.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            SomeObj someObjResult = (SomeObj) unmarshaller.unmarshal(reader);
            System.out.println(someObjResult);
        }
    }
}
