package inference_learner;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx 
{	
	
	public void match_regex_patterns( String line, 
									  Ontology ontology, 
									  List<Sentence> sentences, 
									  Map<String,List<Integer>> subject2index, 
									  Map<String,List<Integer>> object2index,
									  Set<String> joints )
	{
		Pattern p = Pattern.compile("'(.*?)'\\('(.*?)',\\s*'(.*?)'\\)\\.");
		//p.matcher refers to pattern!
		Matcher m = p.matcher(line);
		
		if( m.matches() ) 
		{
			// they just say match anything dig the shit then match anything dig the shit then match anything
			//and then the anything that they match, it's put to strings.
			String verb    = m.group(1);
			String object  = m.group(2);
			String subject = m.group(3);
        
			ontology.addSentence( new Sentence( verb, object, subject ), sentences,  subject2index, object2index, joints);
		}
	}
}
