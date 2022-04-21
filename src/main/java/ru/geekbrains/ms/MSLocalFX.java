package ru.geekbrains.ms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.geekbrains.ms.views.JournalViewController;
import ru.geekbrains.ms.views.common.MainViewController;

@SpringBootApplication
public class MSLocalFX extends Application {

    private ConfigurableApplicationContext context;

    @Autowired
    private MainViewController mainViewController;

    @Override
    public void init() {
        context = SpringApplication.run(getClass());
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(mainViewController.getView(), 800, 600));
        mainViewController.setContent(JournalViewController.class);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }
}
