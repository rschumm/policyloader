package ch.schumm.security;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.schumm.security.policy.Policy;

public class XmlPolicyLoader {

    public List<Policy> loadPoliciesForFilename(String filename) {
        List<Policy> policies = new ArrayList<Policy>();
        PolicyConfig policyConfig;
        try {
            JAXBContext context = JAXBContext.newInstance(PolicyConfig.class);
            Unmarshaller u = context.createUnmarshaller();
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
            policyConfig = (PolicyConfig) u.unmarshal(resourceAsStream);
        } catch (JAXBException e) {
            throw new PolicyException("Fehler beim Laden des Policy-Config-Files: " + filename, e);
        }

        List<String> names = policyConfig.getPolicies();
        for (String name : names) {
            Policy policyForName = instatiatePolicyForName(name);
            if (!isIgnored(policyForName)) {
                policies.add(policyForName);
            }
        }
        return policies;
    }

    public List<Policy> loadPolicies() {
        return loadPoliciesForFilename("policies.xml");
    }

    protected Policy instatiatePolicyForName(String name) {
        Object newInstance = null;
        try {
            newInstance = Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new PolicyException("Fehler beim Instantiieren der Policy: " + name, e);
        }
        return (Policy) newInstance;

    }

    public static void main(String[] args) throws JAXBException {
        PolicyConfig policyConfig = new PolicyConfig();
        policyConfig.addPolicy("ch.schumm.security.policy.ErstePolicy");
        policyConfig.addPolicy("ch.schumm.security.policy.ZweitePolicy");

        JAXBContext context = JAXBContext.newInstance(PolicyConfig.class);

        // Marshalling...
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(policyConfig, System.out);
    }

    protected boolean isIgnored(Policy policy) {
        Class<? extends Policy> policyType = policy.getClass();
        boolean hasIgnoreAnotation = false;
        Annotation[] declaredAnnotations = policyType.getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            if (annotation.annotationType() == IgnorePolicy.class) {
                hasIgnoreAnotation = true;
            }
        }
        return hasIgnoreAnotation;

    }

}
