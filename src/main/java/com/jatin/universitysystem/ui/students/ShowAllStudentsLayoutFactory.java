package com.jatin.universitysystem.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.service.student.ShowAllStudentsService;
import com.jatin.universitysystem.ui.UIComponentBuilder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder{
	
	private List<Student> students;
	private ListDataProvider<Student> container;
	private Grid<Student> studentsTable;
	
	
	private class ShowAllStudentsLayout extends VerticalLayout {

		public ShowAllStudentsLayout init() {

			setMargin(true);
			
			studentsTable = new Grid<>(Student.class);
			studentsTable.setColumnOrder("firstName", "lastName", "age", "gender", "university");
			studentsTable.removeColumn("id");
			
			
			return this;
		}

		public ShowAllStudentsLayout layout() {
			addComponent(studentsTable);
			return this;
		}

		public ShowAllStudentsLayout load() {
			students = showAllStudentsService.showAllStudents();
			container = new ListDataProvider<Student>(students);
			studentsTable.setDataProvider(container);
			return this;
		}
	}

	public void refreshTables() {
		
		students = showAllStudentsService.showAllStudents();
		container = new ListDataProvider<Student>(students);
		studentsTable.setDataProvider(container);
	}

	@Autowired
	private ShowAllStudentsService showAllStudentsService;

	public Component createComponent() {
		return new ShowAllStudentsLayout().init().load().layout();
	}

}
