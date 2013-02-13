package ch.schumm.security;

import static org.junit.Assert.*;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import ch.schumm.security.policy.Policy;

public class PolicyLoaderTest {

	@Test
	public void testLoader() throws JAXBException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PolicyLoader policyLoader = new PolicyLoader();
		
		List<Policy> policies = policyLoader.loadPolicies();
		
		assertEquals("eins", policies.get(0).getName()); 
		assertEquals("zwei", policies.get(1).getName()); 
		
	}
	
	
	@Test
	public void testInstatiate() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		PolicyLoader loader = new PolicyLoader(); 
		Policy policyForName = loader.instatiatePolicyForName("ch.schumm.security.policy.ErstePolicy");
		assertEquals("eins", policyForName.getName()); 
		
		
	}

}
