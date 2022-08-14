package com.yokish.salon.utils;

public class SecondThread extends Thread {
    public void run() {
        System.out.println(getName() + " Выполнил этот вывод окна");
    }
}