package com.jatin.universitysystem.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.jatin.universitysystem.navigator.UniversityNavigator;
import com.jatin.universitysystem.ui.students.StudentLayoutFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path="/ui")
@Title("University System")
@Theme("valo")
public class UniversityMainUI extends UI{
	
	private Panel changeTab = new Panel();
	
	@Autowired
	private UniversityLogoLayoutFactory universityLogoLayoutFactory;
	
	@Autowired
	private UniversityMenuLayoutFactory universityMenuLayoutFactory;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout rootLayout = new VerticalLayout(); // container for UI components
		
		changeTab.setHeight("100%");
		
		rootLayout.setSizeFull();
		rootLayout.setMargin(true);
		
		Panel content = new Panel();
		content.setWidth("75%");
		content.setHeight("100%");
	
		Panel logoContent = new Panel();
		logoContent.setWidth("75%");
		logoContent.setHeightUndefined();
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		uiLayout.addComponent(changeTab);
		
		Component logo = universityLogoLayoutFactory.createComponent();
		Component menu = universityMenuLayoutFactory.createComponent();
		uiLayout.addComponent(menu);
		
		uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
		uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
		
		uiLayout.setExpandRatio(menu, 1);
        uiLayout.setExpandRatio(changeTab, 2);
		
		logoContent.setContent(logo);
		content.setContent(uiLayout);
		
		rootLayout.addComponent(logoContent);
		rootLayout.addComponent(content);
		rootLayout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
		rootLayout.setComponentAlignment(logoContent, Alignment.TOP_CENTER);
		rootLayout.setExpandRatio(content, 1);
		
		
		initNavigator();
		
		setContent(rootLayout);
		
	}
	
	private void initNavigator()
	{
		
		UniversityNavigator navigator = new UniversityNavigator(this, changeTab);
		
		applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		
		navigator.addProvider(viewProvider);
		
		navigator.navigateTo(StudentLayoutFactory.NAME);
		
	}


}
