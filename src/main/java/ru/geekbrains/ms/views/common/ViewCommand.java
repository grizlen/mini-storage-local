package ru.geekbrains.ms.views.common;

import javafx.scene.control.Button;

public class ViewCommand {

    public static ViewCommand create(String title, CommandAction action) {
        return new ViewCommand(title, action);
    }

    private final String title;
    private final CommandAction action;

    public ViewCommand(String title, CommandAction action) {
        this.title = title;
        this.action = action;
    }

    public Button asButton() {
        Button button = new Button(title);
        button.setOnAction(actionEvent -> execute());
        return button;
    }

    private void execute() {
        if (action != null) {
            action.execute();
        }
    }

    public interface CommandAction {
        void execute();
    }
}
