package com.idc.sterba.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
//@StyleSheet({"VAADIN/themes/m/styles.scss"})
@Theme("valo")
public class NavigatorUI extends UI {
    //Navigator navigator;

    @Autowired
    private SpringNavigator navigator;

    private ComponentContainer container;

    public static final String MAINVIEW = "main";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Shame counter");

        container = new HorizontalLayout();
        navigator.init(this, container);

        setContent(container);

        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                container.addComponent(event.getNewView().getViewComponent());
                return true;
            }
        });
    }
}
