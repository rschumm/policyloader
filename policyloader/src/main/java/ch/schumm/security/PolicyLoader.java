package ch.schumm.security;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.schumm.security.policy.Policy;

public class PolicyLoader {
	
	public List<Policy> loadPolicies() throws JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Policy> policies = new ArrayList<Policy>(); 
		JAXBContext context = JAXBContext.newInstance(PolicyConfig.class);
		Unmarshaller u = context.createUnmarshaller(); 
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("policies.xml"); 
		PolicyConfig policyConfig = (PolicyConfig) u.unmarshal(resourceAsStream);
		
		List<String> names = policyConfig.getPolicies();
		for (String name : names) {
			Policy policyForName = instatiatePolicyForName(name);
			policies.add(policyForName); 
		}
		return policies; 
	}
	
	protected Policy instatiatePolicyForName(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Object newInstance = Class.forName(name).newInstance();
		return (Policy) newInstance; 
		
	}
	
	
	public static void main(String[] args) throws JAXBException {
		PolicyConfig policyConfig = new PolicyConfig();
		policyConfig.addPolicy("ch.schumm.security.policy.ErstePolicy"); 
		policyConfig.addPolicy("ch.schumm.security.policy.ZweitePolicy"); 
		
		
		JAXBContext context = JAXBContext.newInstance(PolicyConfig.class);
		 
		//Marshalling... 
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);	
		m.marshal(policyConfig, System.out); 
	}

}
