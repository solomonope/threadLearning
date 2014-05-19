/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovworks.threadlearning;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Folorunsho Solomon
 */
public class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    public void run() {

        for (int i = 0; i <= 10; i++) {
            System.out.printf("%s %d * %d = %d\n", Thread.currentThread().getName(), this.number, i, number * i);
        }
    }

    @SuppressWarnings("empty-statement")
    public static void main(String[] a) throws IOException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }

            threads[i].setName("Thread" + i);

        }

        FileWriter file = new FileWriter("./applog.txt");
        PrintWriter printWriter = new PrintWriter(file);

        for (int i = 0; i < 10; i++) {
            printWriter.println(String.format("Main :Status of thread %d is  %s ", i, threads[i].getState()));
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < 10; i++) {
                if (threads[i].getState() != status[i]) {
                    writeThreadInfo(printWriter, threads[i], status[i]);
                    status[i] = threads[i].getState();
                }
            }
            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish && (threads[i].getState() == State.TERMINATED);
            }
        }
    }
}
