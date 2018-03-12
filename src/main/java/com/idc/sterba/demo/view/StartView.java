package com.idc.sterba.demo.view;

import com.idc.sterba.demo.NavigatorUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

/** A start view for navigating to the main view */
@SpringView(name = "", ui = NavigatorUI.class)
public class StartView extends VerticalLayout implements View {

    @Autowired
    private SpringNavigator navigator;

    public StartView() {
        setSizeFull();

        Button button = new Button("Go to Main View",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
//                        UI.getCurrent().getNavigator().navigateTo(NavigatorUI.MAINVIEW);
                        navigator.navigateTo(NavigatorUI.MAINVIEW);
                    }
                });
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}
