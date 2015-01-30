package inference_learner;


import java.io.IOException;
import java.util.Objects;

import edu.mit.jwi.item.POS;

public class Sentence 
{
	private String verb;
	private String object;
	private String subject;
	
	
	
	public Sentence(String verb, String object, String subject ) throws IOException
	{
		//WordNet wordnet = new WordNet();
		
		this.verb = verb;
		this.object = object;
		this.subject = subject;
		
		//this.verb = wordnet.getStem( verb );
		
		//this.object = wordnet.getStem( object );
		
		//this.subject = wordnet.getStem( subject );
		
		//System.out.println( wordnet.getHypernyms( this.verb, POS.VERB ) );
		
		
		
		//wordnet.getHypernyms( this.object, POS.NOUN );
		
		//wordnet.getHypernyms( this.subject, POS.NOUN );
		
		
		
	}
	
	public String getVerb()
	{
		return verb; 
	}
	
	public String getObject()
	{
		return object; 
	}
			
	public String getSubject()
	{
		return subject;
	}
	
	public String toString()
	{
		return verb + "(" + object + ", " + subject + ").";
	}
	
	// necessary for confidence comparison
	@Override
	public boolean equals(Object other)
	{
	    if (!(other instanceof Sentence))
	        return false;
	    if (other == this)
	        return true;
	    Sentence o = (Sentence) other;
	    return o.subject.equals(subject) && o.object.equals(object) && o.verb.equals(verb);
	}
	
	// necessary for confidence comparison
	@Override
	public int hashCode ()
	{
	    return Objects.hash(object, subject, verb);
	}
}
