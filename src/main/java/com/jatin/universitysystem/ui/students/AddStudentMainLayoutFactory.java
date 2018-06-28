package com.jatin.universitysystem.ui.students;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.Student;
import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.service.student.AddStudentService;
import com.jatin.universitysystem.service.university.ShowAllUniversitiesService;
import com.jatin.universitysystem.utils.Gender;
import com.jatin.universitysystem.utils.NotificationMessages;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

	private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener{

		private static final long serialVersionUID = 1L;
		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox<String> gender;
		private Button saveButton;
		private Button clearButton;
		private ComboBox<University> university;
		List<University> universityList;

		Binder<Student> binder;
		private Student student;
       private StudentSavedListener studentSavedListener;
        
        public AddStudentMainLayout(StudentSavedListener studentSavedListener)
        {
        	
        	this.studentSavedListener = studentSavedListener;
        }

		public AddStudentMainLayout init() {

			binder = new BeanValidationBinder<>(Student.class);
			student = new Student();
			
			List<String> genderList = new ArrayList<>();
			genderList.add(Gender.MALE.getString());
			genderList.add(Gender.FEMALE.getString());
			
			firstName = new TextField(StringUtils.FIRST_NAME.getString());
			lastName = new TextField(StringUtils.LAST_NAME.getString());
			age = new TextField(StringUtils.AGE.getString());
			gender = new ComboBox<>(StringUtils.GENDER.getString());
			university = new ComboBox<>(StringUtils.UNIVERSITY.getString());
			university.setWidth("100%");

      		saveButton = new Button(StringUtils.SAVE.getString());
			clearButton = new Button(StringUtils.CANCEL.getString());
			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			gender.setItems(genderList);
			
			return this;
		}
		
		public AddStudentMainLayout load() {
			
         universityList = showAllUniversitiesService.showAllUniversities();
			
		university.setItems(universityList);
		
		return this;
		}

		public AddStudentMainLayout bind() {
			
			binder.forField(firstName).withNullRepresentation("")
			  .bind(Student::getFirstName, Student::setFirstName);
			
			binder.forField(firstName).
			withValidator(new StringLengthValidator("Too short", 3, 256)).bind("firstName");
			
			
			
			binder.forField(lastName).withNullRepresentation("")
			  .bind(Student::getLastName, Student::setLastName);
			
			binder.forField(lastName).
			withValidator(new StringLengthValidator("Too short", 3, 256)).bind("lastName");
			
			
			
			binder.forField(age).withNullRepresentation("")
			  .withConverter(
			    new StringToIntegerConverter("Must enter a number"))
			  .bind(Student::getAge, Student::setAge);
			
			
			binder.forField(age)
			.withNullRepresentation("")
            .withConverter(new StringToIntegerConverter("Must enter a number"))
            .withValidator(new IntegerRangeValidator("Age must be between 1 and 100", 1, 100)).bind("age");
			
			binder.forField(gender).withNullRepresentation("")
			  .bind(Student::getGender, Student::setGender);
			
			binder.forField(gender).
			withValidator(new StringLengthValidator("Must specify gender", 1, 256)).bind("gender");
			
			binder.forField(university).withNullRepresentation(null)
			  .bind(Student::getUniversity, Student::setUniversity);
			
			binder.forField(university).asRequired("Must specify University")
			.bind("university");
			
			binder.setBean(student);
			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout layout = new GridLayout(2, 4);
			layout.setSizeUndefined();
			layout.setSpacing(true);

			layout.addComponent(firstName, 0, 0);
			layout.addComponent(lastName, 1, 0);

			layout.addComponent(age, 0, 1);
			layout.addComponent(gender, 1, 1);

			layout.addComponent(university, 0, 2, 1, 2);

			layout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 3);

			return layout;
		}

		public void buttonClick(ClickEvent event) {
			if (event.getSource() == this.saveButton) {
				try{
			
					binder.writeBean(student);
					
				} catch(Exception e) {
					
					Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
					
					return;
				}
				
				addStudentService.saveStudent(student);
				studentSavedListener.studentSaved();
				Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
				clearFields();
				
			} else {
				clearFields();
			}
		}

		private void clearFields() {
			firstName.setValue("");
			lastName.setValue("");
			age.setValue("");
			gender.setValue("");
			university.setValue(null);
		}

		

}
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	
	@Autowired
	private AddStudentService addStudentService;
	
	
	public Component createComponent(StudentSavedListener studentSavedListener) {
		
		return new AddStudentMainLayout(studentSavedListener).init().load().bind().layout();
	}
	
	
}