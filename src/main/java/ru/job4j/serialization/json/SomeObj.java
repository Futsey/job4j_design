package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "someObj")
@XmlAccessorType(XmlAccessType.FIELD)
public class SomeObj {

    @XmlAttribute
    public boolean exist;
    public int id;
    public String name;

    @XmlElementWrapper(name = "someObjects")
    @XmlElement(name = "object")
    public SomeObjInObj[] obj;

    public SomeObj() { }


    @XmlRootElement(name = "SomeObjInObj")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SomeObjInObj {
        @XmlAttribute
        public int id;
        public String name;

        public SomeObjInObj() { }

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
