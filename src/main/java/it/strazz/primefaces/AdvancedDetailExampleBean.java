package it.strazz.primefaces;

import it.strazz.primefaces.detail.DynaPropertyModel;
import it.strazz.primefaces.detail.FormControlBuilder;
import it.strazz.primefaces.detail.ReflectionDynaFormModelBuilder;

import java.beans.PropertyDescriptor;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

@ManagedBean
@ViewScoped
public class AdvancedDetailExampleBean extends BasicDetailExampleBean{
	
	private static final String NOTES_FIELD = "notes";
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Comparator<PropertyDescriptor> getPropertyComparator() {
		return new Comparator<PropertyDescriptor>() {
			public int compare(PropertyDescriptor first, PropertyDescriptor second) {
				if(NOTES_FIELD.equals(first.getName())){
					return 1;
				}else if(NOTES_FIELD.equals(second.getName())){
					return -1;
				}else{
					return ReflectionDynaFormModelBuilder.DEFAULT_PROPERTY_COMPARATOR.compare(first, second);
				}
			}
		};
	}
	
	@Override
	protected Map<String, FormControlBuilder> getCustomBuilders() {
		Map<String, FormControlBuilder> toReturn = new HashMap<String, FormControlBuilder>(0);
		toReturn.put(NOTES_FIELD, new FormControlBuilder() {
			public void populateRow(DynaFormRow row) {
				//We will show notes in a textArea
				DynaFormLabel label = row.addLabel(NOTES_FIELD);
		        DynaFormControl input = row.addControl(new DynaPropertyModel(NOTES_FIELD), "text");  
		        label.setForControl(input);
			}
		});
		return toReturn;
	}
}
