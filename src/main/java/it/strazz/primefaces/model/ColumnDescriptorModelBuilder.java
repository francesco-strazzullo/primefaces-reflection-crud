package it.strazz.primefaces.model;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang3.StringUtils;

public class ColumnDescriptorModelBuilder {
	private static Set<String> defaultExcludedProperties = new HashSet<String>(0);
	private Class modelClass;
	private Comparator<PropertyDescriptor> propertySortComparator;
	private Predicate propertyFilterPredicate = PredicateUtils.truePredicate();
	
	static{
		defaultExcludedProperties.add("class");
	}
	
	public ColumnDescriptorModelBuilder(Class modelClass) {
		this.modelClass = modelClass;
	}
	
	public List<ColumnDescriptor> build(){
		List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>(0);
		
		List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>(Arrays.asList(PropertyUtils.getPropertyDescriptors(modelClass)));
		
		CollectionUtils.filter(propertyDescriptors, PredicateUtils.andPredicate(propertyFilterPredicate, new Predicate() {
			public boolean evaluate(Object object) {
				PropertyDescriptor propertyDescriptor = (PropertyDescriptor) object;
				return propertyDescriptor.getReadMethod() != null && !defaultExcludedProperties.contains(propertyDescriptor.getName());
			}
		}));
		
		if(propertySortComparator != null){
			Collections.sort(propertyDescriptors, propertySortComparator);
		}	
		
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			ColumnDescriptor columnDescriptor = new ColumnDescriptor();
			columnDescriptor.setProperty(propertyDescriptor.getName());
			columnDescriptor.setHeader(StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(propertyDescriptor.getName())," ")));
			columns.add(columnDescriptor);
		}
		
		
		
		return columns;
		
	}
	
	
}
