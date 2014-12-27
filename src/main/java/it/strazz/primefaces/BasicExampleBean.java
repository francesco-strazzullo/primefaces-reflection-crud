package it.strazz.primefaces;

import it.strazz.primefaces.model.ReflectionColumnModelBuilder;
import it.strazz.primefaces.model.ColumnModel;
import it.strazz.primefaces.model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BasicExampleBean implements Serializable{
	
	private List<Person> people = new ArrayList<Person>(0);
	private List<ColumnModel> columns = new ArrayList<ColumnModel>(0);
	
	@PostConstruct
	public void init() {
		people.add(new Person("Francesco", "Strazzullo", 28));
		people.add(new Person("Maria", "Strazzullo", 22));
		
		columns = new ReflectionColumnModelBuilder(Person.class).build();
	}
	
	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
}
