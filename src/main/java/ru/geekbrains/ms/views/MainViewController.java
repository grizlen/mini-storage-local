package ru.geekbrains.ms.views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MainViewController {

    private final Map<String, ContentViewController> viewMap = new HashMap<>();
    private BorderPane borderPane;
    private Label lblTitle = new Label();
    private HBox commandsBox = new HBox();

    @PostConstruct
    private void init() {
        log.info("MainViewController");
    }

    public void registerView(ContentViewController viewController) {
        log.info("register view {}", viewController.getClass().getSimpleName());
        viewMap.put(viewController.getClass().getName(), viewController);
    }

    public BorderPane getView() {
        if (borderPane == null) {
            borderPane = new BorderPane();
            HBox.setHgrow(commandsBox, Priority.ALWAYS);
            commandsBox.setAlignment(Pos.CENTER_RIGHT);
            HBox navBox = new HBox(lblTitle, commandsBox);
            borderPane.setTop(navBox);
            borderPane.setCenter(new Label("Main view"));
        }
        return borderPane;
    }

    public <T extends ContentViewController> T setContent(Class<T> viewControllerClass) {
        log.info("set content {}", viewControllerClass.getSimpleName());
        T controller = (T) viewMap.get(viewControllerClass.getName());
        if (controller == null) {
            log.error("view controller {} not found.", viewControllerClass.getSimpleName());
            return null;
        }
        borderPane.setCenter(controller.getView());
        controller.open();
        lblTitle.setText(controller.getTitle());
        setCommands(controller.getCommands());
        return controller;
    }

    private void setCommands(ViewCommand... commands) {
        commandsBox.getChildren().clear();
        for (ViewCommand cmd : commands) {
            commandsBox.getChildren().add(cmd.asButton());
        }
    }
}
