package ua.goit.java.appForRestaurant.gui;

import javafx.scene.Node;

public abstract class AbstractController implements Controller{
    private Node view;

    public Node getView() {
        return view;
    }

    public void setView (Node view){
        this.view = view;
    }
}
