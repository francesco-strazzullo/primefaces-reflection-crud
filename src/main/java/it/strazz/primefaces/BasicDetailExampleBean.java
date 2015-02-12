package it.strazz.primefaces;

import it.strazz.primefaces.detail.ReflectionDynaFormModelBuilder;
import it.strazz.primefaces.model.User;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.beanutils.MethodUtils;
import org.primefaces.extensions.model.dynaform.DynaFormModel;

@ManagedBean
@ViewScoped
public class BasicDetailExampleBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Object model;
	private String currentClass = User.class.getName();
	private Integer id;
	private Boolean disabled;
	private DynaFormModel formModel;
	
	public String getCurrentClass() {
		return currentClass;
	}

	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
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

	public void onPreRender(){
		try {
			Class<?> toLoadClass = Class.forName(currentClass);
			this.model = MethodUtils.invokeExactStaticMethod(toLoadClass, "get", new Object[]{id});
			this.formModel = new ReflectionDynaFormModelBuilder(toLoadClass).build();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getModel() {
		return model;
	}

	public DynaFormModel getFormModel() {
		return formModel;
	}
}
