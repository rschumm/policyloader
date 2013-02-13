package ch.schumm.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PolicyConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<String> policies = new ArrayList<String>(); 
	
	public void addPolicy(String name){
		policies.add(name); 
	}
	
	public List<String> getPolicies() {
		return policies;
	}
	
	public void setPolicies(List<String> policies) {
		this.policies = policies;
	}
	
	

}
