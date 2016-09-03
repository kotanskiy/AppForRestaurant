package ua.goit.java.appForRestaurant.console.comands.menu;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.menu.Menu;
import ua.goit.java.appForRestaurant.dao.model.menu.MenuDao;

import java.beans.PropertyVetoException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CreateMenu implements Command{

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public String get() {
        return "create-menu";
    }

    @Override
    public void run() throws PropertyVetoException {
        Menu menu = fillingMenuWithId(createMenuWithId());
        menuDao.create(menu.getId(), menu.getName());
    }

    private Menu fillingMenuWithId(Menu menu){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        menu.setName(scanner.nextLine().trim());
        return menu;
    }

    private Menu createMenuWithId(){
        Menu menu = new Menu();
        List<Menu> menus = menuDao.getAll();
        menus.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return new Integer(o1.getId()).compareTo(o2.getId());
            }
        });
        int id = 0;
        for (Menu m: menus) {
            if (m.getId() == id){
                id++;
            }else {
                menu.setId(id);
            }
        }
        menu.setId(id);
        return menu;
    }
}
