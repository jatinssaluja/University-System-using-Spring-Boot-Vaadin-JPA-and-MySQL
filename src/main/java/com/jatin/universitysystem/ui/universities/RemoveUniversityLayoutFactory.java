package com.jatin.universitysystem.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.service.university.RemoveUniversityService;
import com.jatin.universitysystem.service.university.ShowAllUniversitiesService;
import com.jatin.universitysystem.ui.UniversityMainUI;
import com.jatin.universitysystem.utils.NotificationMessages;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = RemoveUniversityLayoutFactory.NAME, ui=UniversityMainUI.class)
public class RemoveUniversityLayoutFactory extends VerticalLayout implements View, Button.ClickListener{
	
public static final String NAME = "removeuniversity";
	
	@Autowired
	private ShowAllUniversitiesService universitiesService;
	
	@Autowired
	private RemoveUniversityService removeUniversityService;

	
	private List<University> universities;
	private ListDataProvider<University> container;
	private Grid<University> universitiesTable;
	private Button removeUniversityButton;
	
	 public void addLayout()
	   {
		 
		 removeUniversityButton = new Button(StringUtils.REMOVE_STUDENT.getString());
		 setMargin(true);
		 universitiesTable = new Grid<>(University.class);
		 universitiesTable.setColumnOrder("universityName", "universityCity", "universityCountry");
		 universitiesTable.removeColumn("id");
		 universitiesTable.removeColumn("students");
		 universitiesTable.getColumn("universityName").setWidth(200);
		 universitiesTable.getColumn("universityCountry").setWidth(200);
		 universitiesTable.getColumn("universityCity").setExpandRatio(1);
		 
		 container = new ListDataProvider<University>(universities);
		 universitiesTable.setDataProvider(container);
		   
		 universitiesTable.setSelectionMode(SelectionMode.MULTI);
		 removeUniversityButton.addClickListener(this);
	     removeUniversityButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
	    addComponent(universitiesTable);
	    addComponent(removeUniversityButton);
	   }
	 
	 
	 private void loadUniversities() {
	   		universities = universitiesService.showAllUniversities();
	   	   }

		@Override
		public void buttonClick(ClickEvent event) {
			
			MultiSelectionModel selectionModel = (MultiSelectionModel) universitiesTable.getSelectionModel();
			
			for(Object selectedItem : selectionModel.getSelectedItems()) {
				University university = (University) selectedItem;
				container.getItems().remove(university);
				container.refreshAll();
				removeUniversityService.removeUniversity(university);
			}
			
			Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
					NotificationMessages.UNIVERSITY_REMOVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
			
			
			
			
		}
	
       public void enter(ViewChangeEvent event) {
		
		removeAllComponents();
    	   
		loadUniversities();
		addLayout();

       }
       

}
