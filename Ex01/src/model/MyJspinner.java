package model;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class MyJspinner extends JSpinner {

	private boolean isEditable;

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEnabled) {
		super.setEnabled(isEnabled);
		
		this.isEditable = isEnabled;
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) this.getEditor();
		editor.getTextField().setEnabled(isEnabled);
		editor.getTextField().setEditable(isEnabled);
	}
	
	public MyJspinner(SpinnerModel model) {
        super(model);
    }
}
