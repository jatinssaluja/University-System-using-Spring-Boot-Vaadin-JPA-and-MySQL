package com.jatin.universitysystem.navigator;

import com.google.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

public class UniversityNavigator extends Navigator{
	
	public UniversityNavigator(UI ui, SingleComponentContainer container) {
		super(ui, container);
		// TODO Auto-generated constructor stub
	}
	
	private static UniversityNavigator getNavigator()
	{
		
		UI ui = UI.getCurrent();
		
		Navigator navigator = ui.getNavigator();
		
		return (UniversityNavigator)navigator;
	}
	
	public static void navigate(String path)
	{
	    try{	
		getNavigator().navigateTo(path);
	    }
	    
	    catch(Exception e)
	    {
	    	return;
	    }
	}
	
	
	public void navigateTo(String viewname)
	{
		super.navigateTo(Strings.nullToEmpty(viewname));
		
	}

}
