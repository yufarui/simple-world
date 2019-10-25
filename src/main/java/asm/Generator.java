package asm;


import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Generator {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("asm/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
        classReader.accept(myClassVisitor, ClassReader.SKIP_DEBUG);

        byte[] data = classWriter.toByteArray();

        File f = new File("target/classes/asm/Base.class");
        try (FileOutputStream fout = new FileOutputStream(f)) {
            fout.write(data);
        }

        System.out.println("now generator successing");
    }
}
