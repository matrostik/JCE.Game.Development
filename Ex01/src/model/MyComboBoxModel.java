package model;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MyComboBoxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {

	T[] objects;
	T selectedObject;

	public MyComboBoxModel(T[] objects) {
		this.objects = objects;
	}


	public T getElementAt(int index) {
		return objects[index];
	}

	public int getSize() {
		return objects.length;
	}

	public void setSelectedItem(Object anItem) {
		selectedObject = (T)anItem; // to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	public T getSelectedItem() {
		return selectedObject; // to add the selection to the combo box
	}
	
	
}