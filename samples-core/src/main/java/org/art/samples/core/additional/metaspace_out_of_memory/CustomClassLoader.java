package main.java.org.art.samples.core.additional.metaspace_out_of_memory;

public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader() {
        super();
    }

    public Class<?> _defineClass(String name, byte[] buff, int off, int len) {
        return super.defineClass(name, buff, off, len);
    }
}
