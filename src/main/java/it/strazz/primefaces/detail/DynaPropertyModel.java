package it.strazz.primefaces.detail;

import java.io.Serializable;

public class DynaPropertyModel implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private String name;  
    private Object value;
    
    public DynaPropertyModel() {}
    
    public DynaPropertyModel(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
