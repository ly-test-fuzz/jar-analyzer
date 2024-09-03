package com.n1ar4.agent;

import com.n1ar4.agent.core.Task;

import java.lang.instrument.Instrumentation;
import java.net.ServerSocket;
import java.net.Socket;

public class Agent {
    public static String PASSWORD;
    public static Instrumentation staticIns;
    public static Class<?>[] staticClasses;

    @SuppressWarnings("all")
    public static void agentmain(String agentArgs, Instrumentation ins) {
        if (agentArgs == null || agentArgs.trim().equals("")) {
            agentArgs = "12345678";
            System.out.println("default password : " + agentArgs);
        }
        if (agentArgs.length() != 8) {
            return;
        }
        PASSWORD = agentArgs;
        staticIns = ins;

        new Thread(() -> {
            try {
                int port = 10033;
                ServerSocket s = null;
                try {
                    s = new ServerSocket(port);
                } catch (Exception e) {
                    return;
                } finally {
                    if (s != null) {
                        s.close();
                    }
                }
                ServerSocket ss = new ServerSocket(port);
                while (true) {
                    Socket socket = ss.accept();
                    staticClasses = (Class<?>[]) ins.getAllLoadedClasses();
                    new Thread(new Task(socket)).start();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    public static void premain(String agentArgs, final Instrumentation inst) {
        agentmain(agentArgs, inst);
    }

}