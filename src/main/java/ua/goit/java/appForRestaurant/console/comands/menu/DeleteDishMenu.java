package ua.goit.java.appForRestaurant.console.comands.menu;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.menu.MenuDao;

import java.beans.PropertyVetoException;
import java.util.Scanner;

public class DeleteDishMenu implements Command{

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public String get() {
        return "delete-dish-menu";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter id in the menu that will be deleted to the dish: ");
        String idMenu = scanner.nextLine();
        System.out.print("enter id dish: ");
        String idDish = scanner.nextLine();
        menuDao.deleteDishInMenu(Integer.parseInt(idMenu.trim()), Integer.parseInt(idDish.trim()));
    }
}
