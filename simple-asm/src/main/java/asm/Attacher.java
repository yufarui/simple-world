package asm;

import com.sun.tools.attach.VirtualMachine;

public class Attacher {
    public static void main(String[] args) throws Exception {
        VirtualMachine vm = VirtualMachine.attach("16112");
        vm.loadAgent("E:\\maven_repos\\yu\\simple-asm\\1.0-SNAPSHOT\\simple-asm-1.0-SNAPSHOT.jar");
    }
}
