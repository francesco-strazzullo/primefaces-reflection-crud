package it.strazz.primefaces;

import it.strazz.primefaces.columns.ColumnModel;
import it.strazz.primefaces.columns.ReflectionColumnModelBuilder;
import it.strazz.primefaces.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.beanutils.MethodUtils;

@ManagedBean
@ViewScoped
public class ViewParamExampleBean implements Serializable{
	
	private List<ColumnModel> columns = new ArrayList<ColumnModel>(0);
	private String currentClass = User.class.getName();
	
	public void onPreRender() {
		try {
			columns = new ReflectionColumnModelBuilder(Class.forName(currentClass)).
					setExcludedProperties("id").
					build();
		} catch (ClassNotFoundException e) {
			//Will not happen
			columns = null;
		}
	}
	
	public List<Object> getData(){
		try {
			return (List<Object>) MethodUtils.invokeExactStaticMethod(Class.forName(currentClass), "getAll", null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}
	
	public String getCurrentClass() {
		return currentClass;
	}

	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
	}
}
