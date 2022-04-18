package ru.geekbrains.ms.views;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
            borderPane.setCenter(new Label("Main view"));
        }
        return borderPane;
    }

    public <T extends ContentViewController> T setContent(Class<T> viewControllerClass) {
        log.info("set content {}", viewControllerClass.getSimpleName());
        T conttroller = (T) viewMap.get(viewControllerClass.getName());
        if (conttroller == null) {
            log.error("view controller {} not found.", viewControllerClass.getSimpleName());
            return null;
        }
        borderPane.setCenter(conttroller.getView());
        return conttroller;
    }
}
