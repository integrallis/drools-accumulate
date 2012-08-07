package org.integrallis.drools.examples;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

/**
 * This is a sample class to launch a rule.
 */
public class AccumulateExample {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
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
			
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("accumulate.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}