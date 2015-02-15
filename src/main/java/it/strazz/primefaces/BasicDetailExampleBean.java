package it.strazz.primefaces;

import it.strazz.primefaces.detail.FormControlBuilder;
import it.strazz.primefaces.detail.ReflectionDynaFormModelBuilder;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.beanutils.MethodUtils;
import org.primefaces.extensions.model.dynaform.DynaFormModel;

@ManagedBean
@ViewScoped
public class BasicDetailExampleBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Object model;
	private Class currentClass;
	private String currentClassName;
	private Integer id;
	private Boolean disabled = false;
	private DynaFormModel formModel;
	
	public final void onPreRender(){
		try {
			currentClass = Class.forName(currentClassName);
			this.formModel = new ReflectionDynaFormModelBuilder(currentClass)
				.setExcludedProperties("id")
				.setPropertySortComparator(getPropertyComparator())
				.putCustomBuilders(getCustomBuilders())
				.build();
			if(id != null){
				this.model = MethodUtils.invokeExactStaticMethod(currentClass, "get", new Object[]{id});
			}else{
				this.model = currentClass.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Comparator<PropertyDescriptor> getPropertyComparator() {
		return ReflectionDynaFormModelBuilder.DEFAULT_PROPERTY_COMPARATOR;
	}

	protected Map<String, FormControlBuilder> getCustomBuilders() {
		//No Custom
		return new HashMap<String, FormControlBuilder>(0);
	}

	public final String save(){
		try {
			MethodUtils.invokeExactStaticMethod(currentClass, "store", new Object[]{this.model});
			return "param.xhtml?class=" + currentClassName + "&faces-redirect=true";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getCurrentClassName() {
		return currentClassName;
	}

	public void setCurrentClassName(String currentClass) {
		this.currentClassName = currentClass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Object getModel() {
		return model;
	}

	public DynaFormModel getFormModel() {
		return formModel;
	}
}
