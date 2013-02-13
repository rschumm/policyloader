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
	
	public List<Policy> loadPolicies() {
		List<Policy> policies = new ArrayList<Policy>(); 
		PolicyConfig policyConfig;
        try {
            JAXBContext context = JAXBContext.newInstance(PolicyConfig.class);
            Unmarshaller u = context.createUnmarshaller(); 
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("policies.xml"); 
            policyConfig = (PolicyConfig) u.unmarshal(resourceAsStream);
        } catch (JAXBException e) {
            throw new PolicyException("Fehler beim Laden der Policy", e); 
        }
		
		List<String> names = policyConfig.getPolicies();
		for (String name : names) {
			Policy policyForName = instatiatePolicyForName(name);
			policies.add(policyForName); 
		}
		return policies; 
	}
	
	protected Policy instatiatePolicyForName(String name)  {
		Object newInstance = null;
        try {
            newInstance = Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new PolicyException("Fehler beim instantiieren der Policy: ", e); 
        }
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
