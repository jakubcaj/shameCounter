package com.idc.sterba.demo.view;

import com.idc.sterba.demo.NavigatorUI;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import org.springframework.beans.factory.annotation.Autowired;

/** Main view with a menu (with declarative layout design) */
@DesignRoot
@SpringView()
public class MainView extends VerticalLayout implements View {

    @Autowired
    private SpringNavigator navigator;

    // Menu navigation button listener
    class ButtonListener implements Button.ClickListener {
        String menuitem;
        public ButtonListener(String menuitem) {
            this.menuitem = menuitem;
        }

        @Override
        public void buttonClick(Button.ClickEvent event) {
            // Navigate to a specific state
//            UI.getCurrent().getNavigator().navigateTo(NavigatorUI.MAINVIEW + "/" + menuitem);
            navigator.navigateTo(NavigatorUI.MAINVIEW + "/" + menuitem);
        }
    }

    VerticalLayout menuContent;
    Panel equalPanel;
    Button logout;

    public MainView() {
        Design.read(this);

        menuContent.addComponentsAndExpand(new Button("add match view",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
//                        UI.getCurrent().getNavigator().navigateTo("addMatch");
                        navigator.navigateTo("addMatch");
                    }
                }));

        menuContent.addComponent(new Button("Pig",
                new ButtonListener("pig")));
        menuContent.addComponent(new Button("Cat",
                new ButtonListener("cat")));
        menuContent.addComponent(new Button("Dog",
                new ButtonListener("dog")));
        menuContent.addComponent(new Button("Reindeer",
                new ButtonListener("reindeer")));
        menuContent.addComponent(new Button("Penguin",
                new ButtonListener("penguin")));
        menuContent.addComponent(new Button("Sheep",
                new ButtonListener("sheep")));

        // Allow going back to the start
        logout.addClickListener(event -> // Java 8
                UI.getCurrent().getNavigator().navigateTo(""));
    }

    @DesignRoot("animalView.html")
    class AnimalViewer extends VerticalLayout {
        Label watching;
        Embedded pic;
        Label back;

        public AnimalViewer(String animal) {
            Design.read(this);

            watching.setValue("You are currently watching a " +
                    animal);
            pic.setSource(new ThemeResource(
                    "img/" + animal + "-128px.png"));
            back.setValue("and " + animal +
                    " is watching you back");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (event.getParameters() == null
                || event.getParameters().isEmpty()) {
            equalPanel.setContent(
                    new Label("Nothing to see here, " +
                            "just pass along."));
            return;
        } else
            equalPanel.setContent(new AnimalViewer(
                    event.getParameters()));
    }
}
