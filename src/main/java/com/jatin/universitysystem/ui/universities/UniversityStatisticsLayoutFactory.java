package com.jatin.universitysystem.ui.universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jatin.universitysystem.model.entity.University;
import com.jatin.universitysystem.service.university.ShowAllUniversitiesService;
import com.jatin.universitysystem.service.university.UniversityStatisticsService;
import com.jatin.universitysystem.ui.UIComponentBuilder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversityStatisticsLayoutFactory implements UIComponentBuilder{

	
	private List<University> universities;
	private UniversityStatisticsLayout universityStatisticsLayout;

	
	private class UniversityStatisticsLayout extends VerticalLayout{
		
		
		    public UniversityStatisticsLayout() {
		    	
		    	universityStatisticsLayout = this;
		    	
		    }
		
		
		public UniversityStatisticsLayout load() {
			
			universities = showAllUniversitiesService.showAllUniversities();
			
			return this;
			
		}
		
		
        public Component layout() {
			
		
             setMargin(true);
			
			for(University university : universities) {
				
				int numOfStudents = universityStatisticsService.getUniversityStatistics(university.getId());
				Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+"  -  "+numOfStudents+" students"+"</p>", ContentMode.HTML);
				addComponent(label);
			}
			
			return this;
		  }
		
		
		
	}
	
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	@Autowired
	private UniversityStatisticsService universityStatisticsService;
	
	@Override
	public Component createComponent() {
		
		
		return new UniversityStatisticsLayout().load().layout();
	}

	public void refresh() {
		
		universityStatisticsLayout.removeAllComponents();
		universityStatisticsLayout.load().layout();
		
		
	}
	
	
	

}
