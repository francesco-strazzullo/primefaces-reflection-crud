package it.strazz.primefaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private static List<User> users = new ArrayList<User>();

	private Integer id;
	private String lastName;
	private String firstName;
	
	static{
		users.add(new User(0, "Solid","Snake"));
		users.add(new User(1, "Vulcan","Raven"));
		users.add(new User(2, "Meryl","Silverburgh"));
		users.add(new User(3, "Hal","Emmerich"));
		users.add(new User(4, "Frank","Jaeger"));
	}

	public User(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public User() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static List<User> getAll(){
		return users;

	}

	public static User get(final Integer id){
		return (User) CollectionUtils.find(users, new Predicate() {
			public boolean evaluate(Object object) {
				return ((User) object).getId().equals(id);
			}
		});
	}

	public static User store(User p){
		if(p.getId() == null){
			User maxUserId = Collections.max(users, new Comparator<User>() {
				public int compare(User o1, User o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
			
			p.setId(maxUserId.getId()+1);
			
			users.add(p);
		}else{
			users.set(p.getId(), p);
		}

		return p;
	}

	public static void delete(User p){
		users.remove(p);
	}
}