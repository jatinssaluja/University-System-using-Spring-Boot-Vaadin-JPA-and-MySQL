package com.jatin.universitysystem.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.service.university.ShowAllUniversitiesService;
import com.jatin.universitysystem.ui.UIComponentBuilder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllUniversitiesLayoutFactory implements UIComponentBuilder{
	
	private List<University> universities;
	private ListDataProvider<University> container;
	private Grid<University> universitiesTable;
	
	private class ShowAllUniversitiesLayout extends VerticalLayout {

		

		public ShowAllUniversitiesLayout init() {

			setMargin(true);
			setWidth("100%");
			
			universitiesTable = new Grid<>(University.class);
			universitiesTable.setColumnOrder("universityName", "universityCountry","universityCity");
			universitiesTable.removeColumn("id");
			universitiesTable.removeColumn("students");
			universitiesTable.getColumn("universityName").setWidth(200);
			universitiesTable.getColumn("universityCountry").setWidth(200);
			universitiesTable.getColumn("universityCity").setExpandRatio(1);
			
			return this;
		}

		public ShowAllUniversitiesLayout layout() {
			addComponent(universitiesTable);
			return this;
		}

		public ShowAllUniversitiesLayout load() {
			universities = showAllUniversitiesService.showAllUniversities();
			container = new ListDataProvider<University>(universities);
			universitiesTable.setDataProvider(container);
			return this;
		}
	}

	public void refreshTables() {
		
		universities = showAllUniversitiesService.showAllUniversities();
		container = new ListDataProvider<University>(universities);
		universitiesTable.setDataProvider(container);
	}

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	public Component createComponent() {
		return new ShowAllUniversitiesLayout().init().load().layout();
	}


}
