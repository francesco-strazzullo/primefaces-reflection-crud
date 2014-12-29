package it.strazz.primefaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class Car implements Serializable{

	private static final long serialVersionUID = 1L;

	private static List<Car> cars = new ArrayList<Car>();

	private Integer id;
	private String brand;
	private String color;
	private Integer year;
	private boolean used;
	
	static{
		cars.add(new Car(0, "Honda","Yellow",1995,false));
		cars.add(new Car(1, "Volvo","Black",1973,true));
		cars.add(new Car(1, "Audi","Silver",1987,false));
		cars.add(new Car(1, "Renault","White",1963,true));
		cars.add(new Car(1, "Volkswagen","Black",1985,true));
	}

	

	public Car(Integer id, String brand, String color, Integer year, boolean used) {
		super();
		this.id = id;
		this.brand = brand;
		this.color = color;
		this.year = year;
		this.used = used;
	}

	public Car() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public static List<Car> getAll(){
		return cars;

	}

	public static Car get(final Integer id){
		return (Car) CollectionUtils.find(cars, new Predicate() {
			public boolean evaluate(Object object) {
				return ((Car) object).getId().equals(id);
			}
		});
	}

	public static Car store(Car p){
		if(p.getId() == null){
			Car maxUserId = Collections.max(cars, new Comparator<Car>() {
				public int compare(Car o1, Car o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
			
			p.setId(maxUserId.getId()+1);
			
			cars.add(p);
		}else{
			cars.set(p.getId(), p);
		}

		return p;
	}

	public static void delete(Car p){
		cars.remove(p);
	}
}