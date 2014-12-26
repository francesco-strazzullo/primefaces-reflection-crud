package it.strazz.primefaces.model;

import java.io.Serializable;

public class ColumnDescriptor implements Serializable {
	
	private String property;
	private String header;
	
	public ColumnDescriptor() {}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}

}
