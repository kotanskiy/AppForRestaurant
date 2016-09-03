package ua.goit.java.appForRestaurant.console;

import java.beans.PropertyVetoException;

public interface Command {
    public String get();
    public void run() throws PropertyVetoException;
}
