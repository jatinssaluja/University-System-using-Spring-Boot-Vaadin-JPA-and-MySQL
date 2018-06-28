package com.jatin.universitysystem.ui.students;



import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.ui.UniversityMainUI;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = StudentLayoutFactory.NAME, ui=UniversityMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener{

	public static final String NAME = "addstudent";
	
   
	
	private TabSheet tabSheet;
	
	@Autowired
	private AddStudentMainLayoutFactory mainLayoutFactory;
	
	@Autowired
	private ShowAllStudentsLayoutFactory showStudentsLayoutFactory;

   public void addLayout()
   {
	   
	   setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addStudentMainTab = mainLayoutFactory.createComponent(this);
		Component showStudentsTab = showStudentsLayoutFactory.createComponent();
		
		tabSheet.addTab(addStudentMainTab, StringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentsTab, StringUtils.SHOW_ALL_STUDENTS.getString());
		
		addComponent(tabSheet);
   }
	
	public void enter(ViewChangeEvent event) {
		
		removeAllComponents();
		addLayout();
		
	}

	@Override
	public void studentSaved() {
		showStudentsLayoutFactory.refreshTables();
		
	}
}
