package it.strazz.primefaces.detail;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

public class ReflectionDynaFormModelBuilder {
	
	private Class modelClass;
	private Comparator<PropertyDescriptor> propertySortComparator;
	private Predicate propertyFilterPredicate;
	private Set<String> excludedProperties;
	private static Set<String> defaultExcludedProperties = new HashSet<String>(0);
	
	static{
		defaultExcludedProperties.add("class");
	}
	
	public ReflectionDynaFormModelBuilder(Class modelClass) {
		this.modelClass = modelClass;
		this.propertyFilterPredicate = PredicateUtils.truePredicate();
		this.propertySortComparator = new Comparator<PropertyDescriptor>() {
			public int compare(PropertyDescriptor o1, PropertyDescriptor o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		this.excludedProperties = new HashSet<String>(0);
	}
	
	public ReflectionDynaFormModelBuilder setPropertyFilterPredicate(Predicate p){
		this.propertyFilterPredicate = p;
		return this;
	}
	
	public ReflectionDynaFormModelBuilder setPropertySortComparator(Comparator<PropertyDescriptor> c){
		this.propertySortComparator = c;
		return this;
	}
	
	public ReflectionDynaFormModelBuilder setExcludedProperties(Set<String> p){
		this.excludedProperties = p;
		return this;
	}
	
	public ReflectionDynaFormModelBuilder setExcludedProperties(String...p){
		this.excludedProperties = new HashSet<String>(0);
		for (String excludedProperty : p) {
			this.excludedProperties.add(excludedProperty);
		}
		return this;
	}
	
	public DynaFormModel build(){
		DynaFormModel formModel = new DynaFormModel();
		
		List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(Arrays.asList(PropertyUtils.getPropertyDescriptors(modelClass)));
		
		CollectionUtils.filter(propertyDescriptors, PredicateUtils.andPredicate(propertyFilterPredicate, new Predicate() {
			public boolean evaluate(Object object) {
				PropertyDescriptor propertyDescriptor = (PropertyDescriptor) object;
				return 
						propertyDescriptor.getReadMethod() != null && 
						propertyDescriptor.getWriteMethod() != null &&
						!defaultExcludedProperties.contains(propertyDescriptor.getName()) &&
						!excludedProperties.contains(propertyDescriptor.getName());
			}
		}));
		
		Collections.sort(propertyDescriptors, propertySortComparator);
		
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			DynaFormRow row = formModel.createRegularRow();  
			
			DynaFormLabel label = row.addLabel(propertyDescriptor.getName());
	        DynaFormControl input = row.addControl(new DynaPropertyModel(propertyDescriptor.getName()), propertyDescriptor.getPropertyType().getSimpleName().toLowerCase());  
	        label.setForControl(input);  
		}
		
		return formModel;
	}
}
