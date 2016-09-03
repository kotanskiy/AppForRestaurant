package ua.goit.java.appForRestaurant.console.comands.menu;

import ua.goit.java.appForRestaurant.console.Command;
import ua.goit.java.appForRestaurant.dao.model.menu.Menu;
import ua.goit.java.appForRestaurant.dao.model.menu.MenuDao;

import java.beans.PropertyVetoException;
import java.util.List;

public class GetAllMenu implements Command{

    private MenuDao menuDao;

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public String get() {
        return "get-all-menu";
    }

    @Override
    public void run() throws PropertyVetoException {
        List<Menu> menus = menuDao.getAll();
        for (Menu m: menus) {
            System.out.println(m.toString());
        }
    }
}
