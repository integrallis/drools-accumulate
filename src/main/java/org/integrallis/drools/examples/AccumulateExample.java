package org.integrallis.drools.examples;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

/**
 * This is a sample class to launch a rule.
 */
public class AccumulateExample {

	public static final void main(String[] args) {
	    KieSession ksession = null;
	    try {
	        // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
		    KieContainer kContainer = ks.getKieClasspathContainer();
		    ksession = kContainer.newKieSession("ksession-rules");
	        
			// go !
			Person smokey = new Person("Smokey");
			Ticket ticket1 = new Ticket(smokey, 1500.0, "SPEEDING", false);
			Ticket ticket2 = new Ticket(smokey, 1500.0, "SPEEDING", false);
			Ticket ticket3 = new Ticket(smokey, 1900.0, "SPEEDING", false);
			Ticket ticket4 = new Ticket(smokey, 600.0, "SPEEDING", false);
			
			ksession.insert(smokey);
			ksession.insert(ticket1);
			ksession.insert(ticket2);
			ksession.insert(ticket3);
			ksession.insert(ticket4);
			
			ksession.fireAllRules();
			
			System.out.println("===== QUERYING =====");
			QueryResults results = ksession.getQueryResults( "GetWarrants" );
			
			for ( QueryResultsRow row : results ) {
			    ArrestWarrant warrant = (ArrestWarrant) row.get( "warrant" );
			    System.out.println(warrant);
			}	
		} catch (Throwable t) {
			t.printStackTrace();
        } finally {
          	ksession.dispose();
        }
	}
}