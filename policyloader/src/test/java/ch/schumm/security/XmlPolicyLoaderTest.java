package ch.schumm.security;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ch.schumm.security.policy.Policy;

public class XmlPolicyLoaderTest {

	@Test
	public void testLoader() {
		XmlPolicyLoader policyLoader = new XmlPolicyLoader();
		
		List<Policy> policies = policyLoader.loadPolicies();
		
		assertEquals("eins", policies.get(0).getName()); 
		assertEquals("zwei", policies.get(1).getName()); 
		
	}
	
	
	@Test
	public void testInstatiate() {
		XmlPolicyLoader loader = new XmlPolicyLoader(); 
		Policy policyForName = loader.instatiatePolicyForName("ch.schumm.security.policy.ErstePolicy");
		assertEquals("eins", policyForName.getName()); 
		
		
	}

}
