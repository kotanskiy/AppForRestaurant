package ua.goit.java.appForRestaurant.console.comands.menu;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.menu.MenuDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class DeleteMenu implements Command{

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public String get() {
        return "delete-menu";
    }

    @Override
    public void run() throws PropertyVetoException {
        menuDao.delete(anyMenu());
    }

    private int anyMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the menu id , which must be removed: ");
        String idString = scanner.nextLine();
        idString = idString.trim();
        return Integer.parseInt(idString);
    }
}
