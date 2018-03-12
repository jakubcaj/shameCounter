package com.idc.sterba.demo.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public abstract class AbstractView extends VerticalLayout implements View {

    @Autowired
    SpringViewProvider viewProvider;

    protected void navigate(String viewName) {
        UI.getCurrent().getNavigator().navigateTo(viewName);
    }

}
