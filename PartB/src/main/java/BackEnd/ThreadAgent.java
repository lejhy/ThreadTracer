package BackEnd;

import BackEnd.ThreadTracer;

import java.lang.instrument.Instrumentation;
import java.util.jar.Manifest;

public class ThreadAgent {

    public static void main(String[] args) {
        ThreadTracer tt = new ThreadTracer();
        System.out.println("PART 2");
        try {
            Thread.sleep(1000);
            tt = new ThreadTracer();
        } catch (InterruptedException e) {
            System.out.println("There is a interupt problem");
        }
    }

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("Agent");
        while (true) {
            System.out.println("\n\n\n\t>>>>");
            ThreadTracer tt = new ThreadTracer();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.exit(1);
            }
        }

    }
}
