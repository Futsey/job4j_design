package ru.job4j.serialization.json;

import java.util.Arrays;

public class SomeObj {

    public boolean exist;
    public int id;
    public String name;
    public SomeObjInObj[] obj;

    public static class SomeObjInObj {
        public int id;
        public String name;

        public SomeObjInObj(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public SomeObj(boolean exist, int id, String name, SomeObjInObj[] obj) {
        this.exist = exist;
        this.id = id;
        this.name = name;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "SomeObj{"
                + "exist=" + exist
                + ", id=" + id
                + ", name='" + name + '\''
                + ", obj=" + Arrays.toString(obj)
                + '}';
    }
}
