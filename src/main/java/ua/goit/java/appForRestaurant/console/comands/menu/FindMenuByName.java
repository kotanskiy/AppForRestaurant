package ua.goit.java.appForRestaurant.console.comands.menu;


import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.menu.Menu;
import ua.goit.java.appForRestaurant.dao.model.menu.MenuDao;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Scanner;

public class FindMenuByName implements Command{

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public String get() {
        return "find-menu";
    }

    @Override
    public void run() throws PropertyVetoException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        try {
            List<Menu> menus = find(scanner.nextLine());
            for (Menu menu: menus) {
                System.out.println(menu.toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private List<Menu> find(String name){
        name = name.trim();
        return menuDao.getMenu(name);
    }
}
