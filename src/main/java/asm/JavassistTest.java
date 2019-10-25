package asm;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistTest {

    public static void main(String[] args) throws Exception {
//        attempted  duplicate class definition for name
//        Base b = new Base();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("asm.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("System.out.println(\"start\"); ");
        m.insertAfter("System.out.println(\"end\"); ");
        Class c = cc.toClass();
        cc.writeFile("D:\\");
        Base h = (Base) c.newInstance();
        h.process();
    }
}
