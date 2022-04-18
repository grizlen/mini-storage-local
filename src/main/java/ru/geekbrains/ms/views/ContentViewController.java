package ru.geekbrains.ms.views;

import javafx.scene.layout.VBox;

public interface ContentViewController {
    VBox getView();
    void open();
    String getTitle();
    ViewCommand[] getCommands();
}
