package ua.goit.java.appForRestaurant.console;

import java.beans.PropertyVetoException;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    private Map<String, Command> commands;

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true){
            str = scanner.nextLine().trim();
            if (!str.trim().equals("")){
                if (str.equals("exit")){
                    break;
                }else {
                    try{
                        parse(str);
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public void parse(String str) throws PropertyVetoException {
        str = str.trim();
        if (commands.containsKey(str)){
            commands.get(str).run();
        }else {
            System.out.println("unknown command!");
        }

    }
}
