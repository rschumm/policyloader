package ch.schumm.security;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ch.schumm.security.policy.Policy;

public class PolicyLoaderTest {

	@Test
	public void testLoader() {
		PolicyLoader policyLoader = new PolicyLoader();
		
		List<Policy> policies = policyLoader.loadPolicies();
		
		assertEquals("eins", policies.get(0).getName()); 
		assertEquals("zwei", policies.get(1).getName()); 
		
	}
	
	
	@Test
	public void testInstatiate() {
		PolicyLoader loader = new PolicyLoader(); 
		Policy policyForName = loader.instatiatePolicyForName("ch.schumm.security.policy.ErstePolicy");
		assertEquals("eins", policyForName.getName()); 
		
		
	}

}
