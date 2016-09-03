package ua.goit.java.appForRestaurant.gui;

import javafx.scene.Node;

public interface Controller {
    Node getView();
    void setView (Node view);
}
