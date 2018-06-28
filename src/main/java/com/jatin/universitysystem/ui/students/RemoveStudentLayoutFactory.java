package com.jatin.universitysystem.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.service.student.RemoveStudentService;
import com.jatin.universitysystem.service.student.ShowAllStudentsService;
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

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui=UniversityMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {
	
	public static final String NAME = "removestudent";
	
	@Autowired
	private ShowAllStudentsService studentService;
	
	@Autowired
	private RemoveStudentService removeStudentService;

	
	private List<Student> students;
	private ListDataProvider<Student> container;
	private Grid<Student> studentsTable;
	private Button removeStudentButton;
	
	 public void addLayout()
	   {
		 
		 removeStudentButton = new Button(StringUtils.REMOVE_STUDENT.getString());
		 setMargin(true);
		 studentsTable = new Grid<>(Student.class);
		 studentsTable.setColumnOrder("firstName", "lastName", "age", "gender");
		 studentsTable.removeColumn("id");
		 
		 container = new ListDataProvider<Student>(students);
		 studentsTable.setDataProvider(container);
		   
		 studentsTable.setSelectionMode(SelectionMode.MULTI);
		 removeStudentButton.addClickListener(this);
	     removeStudentButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
	    addComponent(studentsTable);
	    addComponent(removeStudentButton);
	   }
	 
	 
	 private void loadStudents() {
	   		students = studentService.showAllStudents();
	   	   }

		@Override
		public void buttonClick(ClickEvent event) {
			
			MultiSelectionModel selectionModel = (MultiSelectionModel) studentsTable.getSelectionModel();
			
			for(Object selectedItem : selectionModel.getSelectedItems()) {
				Student student = (Student) selectedItem;
				container.getItems().remove(student);
				container.refreshAll();
				removeStudentService.removeStudent(student);
			}
			
			Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
					NotificationMessages.STUDENT_REMOVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
			
			
			
			
		}
	
       public void enter(ViewChangeEvent event) {
		
		removeAllComponents();
    	   
		loadStudents();
		addLayout();

       }
       
       

}