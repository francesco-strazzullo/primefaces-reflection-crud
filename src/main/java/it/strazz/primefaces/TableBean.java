package it.strazz.primefaces;

import it.strazz.primefaces.model.ColumnDescriptor;
import it.strazz.primefaces.model.ColumnDescriptorModelBuilder;
import it.strazz.primefaces.model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TableBean implements Serializable{
	
	private List<Person> people = new ArrayList<Person>(0);
	private List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>(0);
	
	public TableBean() {
		people.add(new Person("Francesco", "Strazzullo", 28));
		people.add(new Person("Maria", "Strazzullo", 22));
		
		columns = new ColumnDescriptorModelBuilder(Person.class).build();
	}
	
	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<ColumnDescriptor> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnDescriptor> columns) {
		this.columns = columns;
	}
}
