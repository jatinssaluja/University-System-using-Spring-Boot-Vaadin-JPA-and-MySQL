package com.jatin.universitysystem.ui.universities;


import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.ui.UniversityMainUI;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = UniversityLayoutFactory.NAME, ui=UniversityMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener{

	public static final String NAME = "adduniversity";
	
    

	
	private TabSheet tabSheet;
	
	@Autowired
	private AddUniversityLayoutFactory addUniversityFactory;
	
	@Autowired
	private ShowAllUniversitiesLayoutFactory showAllUniversitiesLayoutFactory;
	
	@Autowired
	private UniversityStatisticsLayoutFactory universityStatisticsLayoutFactory;
	
	public void addLayout()
	{
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addUniversityTab = addUniversityFactory.createComponent(this);
		
		Component showUniversityTab = showAllUniversitiesLayoutFactory.createComponent();;
		
		Component showStaticsTab = universityStatisticsLayoutFactory.createComponent();
		
		tabSheet.addTab(addUniversityTab,StringUtils.MENU_ADD_UNIVERSITY.getString());
		tabSheet.addTab(showUniversityTab,StringUtils.MENU_SHOW_UNIVERSITY.getString());
		tabSheet.addTab(showStaticsTab,StringUtils.MENU_CHART_UNIVERSITY.getString());
		
		addComponent(tabSheet);
	}
	
	@Override
	public void universitySaved() {
	
		showAllUniversitiesLayoutFactory.refreshTables();
		universityStatisticsLayoutFactory.refresh();
		
	}
	
	public void enter(ViewChangeEvent event) {
		
		removeAllComponents();
		addLayout();
	}


	
}