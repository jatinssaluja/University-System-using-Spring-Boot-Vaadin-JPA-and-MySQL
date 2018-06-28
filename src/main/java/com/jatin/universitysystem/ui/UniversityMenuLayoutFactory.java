package com.jatin.universitysystem.ui;

import com.jatin.universitysystem.navigator.UniversityNavigator;
import com.jatin.universitysystem.utils.StringUtils;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ItemClick;
import com.vaadin.ui.Tree.ItemClickListener;
import com.vaadin.ui.VerticalLayout;


@org.springframework.stereotype.Component
public class UniversityMenuLayoutFactory implements UIComponentBuilder{
	
private class UniversityMenu extends VerticalLayout implements ItemClickListener{
		
	
		Tree<String> tree;
		TreeData<String> treeData;

		public UniversityMenu init() {
			
	
		  tree = new Tree<>();
		  treeData = new TreeData<>();
		  
		  tree.addItemClickListener(this);
		  
		  return this;
		}
		
		public UniversityMenu layout() {
			
			setWidth("100%");
			setHeightUndefined();
			
			// Items with hierarchy
			treeData.addItem(null,StringUtils.MENU_STUDENTS.getString());
			treeData.addItem(StringUtils.MENU_STUDENTS.getString(),StringUtils.MENU_ADD_STUDENT.getString());
			treeData.addItem(StringUtils.MENU_STUDENTS.getString(),StringUtils.MENU_REMOVE_STUDENT.getString());
			
			
			treeData.addItem(null,StringUtils.MENU_UNIVERSITIES.getString());
			treeData.addItem(StringUtils.MENU_UNIVERSITIES.getString(),StringUtils.MENU_OPERATIONS_UNIVERSITY.getString());
			treeData.addItem(StringUtils.MENU_UNIVERSITIES.getString(),StringUtils.MENU_REMOVE_UNIVERSITY.getString());
			TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(treeData);
			tree.setDataProvider(inMemoryDataProvider);
			tree.expand(StringUtils.MENU_STUDENTS.getString()); // Expand programmatically
			tree.expand(StringUtils.MENU_UNIVERSITIES.getString()); // Expand programmatically
			
			
			
			addComponent(tree);
			
			return this;
		}

		

		@Override
		public void itemClick(ItemClick event) {
			
           String selectedItemPath = (String) event.getItem();
           
           System.out.println(selectedItemPath);
			
			if(selectedItemPath == null)
			{
				return;
			
			}
			
			String path = selectedItemPath.toLowerCase().replaceAll("\\s+","");
			UniversityNavigator.navigate(path);
			
		}
	}

	public Component createComponent() {
		return new UniversityMenu().init().layout();
	}

}
