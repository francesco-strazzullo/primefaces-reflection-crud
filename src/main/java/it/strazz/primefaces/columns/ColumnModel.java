package it.strazz.primefaces.columns;

import java.io.Serializable;

public class ColumnModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String property;
	private String header;
	private Class<?> type;
	
	public ColumnModel() {}
	
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

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
}
