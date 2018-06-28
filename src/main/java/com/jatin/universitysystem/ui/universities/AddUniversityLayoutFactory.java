package com.jatin.universitysystem.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.service.university.AddUniversityService;
import com.jatin.universitysystem.utils.NotificationMessages;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {
	
	private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener{

		
		private TextField universityName;
		private TextField universityCountry;
		private TextField universityCity;
		private Button saveButton;
		private Button clearButton;
		Binder<University> binder;
		private University university;
		private UniversitySavedListener universitySavedListener;
		
		
		public AddUniversityLayout(UniversitySavedListener universitySavedListener) {
			
			this.universitySavedListener = universitySavedListener;
		}

		public AddUniversityLayout init() {
			
			binder = new BeanValidationBinder<>(University.class);
			university = new University();
			
			universityName = new TextField(StringUtils.UNIVERSITY_NAME.getString());
			universityCountry = new TextField(StringUtils.UNIVERSITY_COUNTRY.getString());
			universityCity = new TextField(StringUtils.UNIVERSITY_CITY.getString());
			
			saveButton = new Button(StringUtils.SAVE.getString(), this);
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			clearButton = new Button(StringUtils.CANCEL.getString());
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			
			return this;
		}
		
		public AddUniversityLayout bind() {
			
			binder.forField(universityName).withNullRepresentation("")
			  .bind(University::getUniversityName, University::setUniversityName);
			
			binder.forField(universityName).
			withValidator(new StringLengthValidator("Too short", 3, 256)).bind("universityName");
			
			
			
			binder.forField(universityCity).withNullRepresentation("")
			  .bind(University::getUniversityCity, University::setUniversityCity);
			
			binder.forField(universityCity).
			withValidator(new StringLengthValidator("Too short", 3, 256)).bind("universityCity");
			

			binder.forField(universityCountry).withNullRepresentation("")
			  .bind(University::getUniversityCountry, University::setUniversityCountry);
			
			binder.forField(universityCountry).
			withValidator(new StringLengthValidator("Too short", 3, 256)).bind("universityCountry");
			
		
			binder.setBean(university);
			
			return this;
		}
		
		
		public Component layout() {
			
			
			setMargin(true);

			GridLayout grid = new GridLayout(1, 4);
			grid.setSizeUndefined();
			grid.setSpacing(true);

			grid.addComponent(universityName,0,0);
			grid.addComponent(universityCountry,0,1);
			grid.addComponent(universityCity,0,2);
			grid.addComponent(new HorizontalLayout(saveButton),0,3);
			
			
			
			addComponent(grid);
			
			return this;
		}
		
		
		
		
		
		@Override
		public void buttonClick(ClickEvent event) {
			
			if (event.getSource() == this.saveButton) {
				try{
			
					binder.writeBean(university);
					
				} catch(Exception e) {
					
					Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
					
					return;
				}
				
				addUniversityService.saveUniversity(university);
				universitySavedListener.universitySaved();
				Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
						NotificationMessages.UNIVERSITY_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
				System.out.println(university);
				clearFields();
				
			} else {
				clearFields();
			}
			
		}
		
		private void clearFields() {
			universityName.setValue("");
			universityCity.setValue("");
			universityCountry.setValue("");

		}

	}
	
	@Autowired
	private AddUniversityService addUniversityService;
	
	public Component createComponent(UniversitySavedListener universitySavedListener) {
		
		return new AddUniversityLayout(universitySavedListener).init().bind().layout();
	}

}
