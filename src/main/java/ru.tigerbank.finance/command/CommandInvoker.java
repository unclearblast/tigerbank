package ru.tigrbank.finance.command;

public class CommandInvoker {

    public void execute(Command command) {

        long start = System.currentTimeMillis();

        command.execute();

        long end = System.currentTimeMillis();

        System.out.println("Execution time: " + (end - start) + " ms");
    }
}
