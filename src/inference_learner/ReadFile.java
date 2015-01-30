package inference_learner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReadFile 
{	
	public void readFile( String input, 
						  Ontology ontology, 
						  List<Sentence> sentences, 
						  Map<String,List<Integer>> subject2index, 
						  Map<String,List<Integer>> object2index,
						  Set<String> joints) throws IOException 
	{	
		// do what we came here to do, read the input
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader( input ));
		
		//now that we're reading the file we need to send it on
		//to the regex extractot after we're through
		RegEx regex_extractor = new RegEx();
		
		String line;
		while ((line = br.readLine()) != null) 
	    {
			regex_extractor.match_regex_patterns(line, ontology, sentences, subject2index, object2index, joints);
	    }
	}
}
