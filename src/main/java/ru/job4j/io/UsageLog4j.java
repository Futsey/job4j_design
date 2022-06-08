package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        String name = "Petr Arsentev";
        int age = 33;
        short apartmentSize = 69;
        byte children = 2;
        long students = 100500;
        float workEfficiency = 89.3F;
        double relaxEfficiency = 146.3D;
        char firstLiteralOfAlphabet = 'A';
        boolean alive = true;

        LOG.debug("User info name : {}, "
                        + "age : {}, "
                        + "apartmentSize : {}, "
                        + "children : {}, students : {}, "
                        + "workEfficiency : {}, "
                        + "relaxEfficiency : {}, "
                        + "firstLiteralOfAlphabet : {}, "
                        + "age : {}",
                name,
                age,
                apartmentSize,
                children,
                students,
                workEfficiency,
                relaxEfficiency,
                firstLiteralOfAlphabet,
                alive);

        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
