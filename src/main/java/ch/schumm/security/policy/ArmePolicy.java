package ch.schumm.security.policy;

import ch.schumm.security.IgnorePolicy;

@IgnorePolicy
public class ArmePolicy extends Policy {

	@Override
	public String getName() {
		return "arme"; 
	}

}
