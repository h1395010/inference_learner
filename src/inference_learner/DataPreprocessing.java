package inference_learner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.POS;

public class DataPreprocessing 
{

	public static void dataPreprocessing( String raw_subject, 
							              String raw_object, 
							              String raw_verb,
							              List<Sentence> sentences,  
										  Map<String,List<Integer>> subject2index, 
										  Map<String,List<Integer>> object2index, 
										  Set<String> joints ) throws IOException 
	{
		WordNet wordnet = new WordNet();
		
		
		String verb = wordnet.getStem( raw_verb );
		
		String object = wordnet.getStem( raw_object );
				
		String subject = wordnet.getStem( raw_subject );
		
		
		List<String> verb_hypernyms = wordnet.getHypernyms(verb, POS.VERB);
		List<String> object_hypernyms = wordnet.getHypernyms(object, POS.NOUN);
		List<String> subject_hypernyms = wordnet.getHypernyms(subject, POS.NOUN);


		for(String verb_hypernym : verb_hypernyms)
		    for(String object_hypernym : object_hypernyms)
		        for(String subject_hypernym : subject_hypernyms)
		            //sentences.add(new Sentence(verb_hypernym, object_hypernym, subject_hypernym));
					//new Sentence( verb_hypernym, object_hypernym, subject_hypernym );
					Ontology.addSentence( new Sentence( verb_hypernym, object_hypernym, subject_hypernym ), sentences,  subject2index, object2index, joints);
		
					
					//return sentences;
		//System.out.println("example_sentence " + sentences );
		
	}
}	
		
//		List<String> verb_hypernym_container = wordnet.getHypernyms( verb, POS.VERB );
//				
//		List<String> object_hypernym_container = wordnet.getHypernyms( verb, POS.VERB );
//		
//		List<String> subject_hypernym_container = wordnet.getHypernyms( verb, POS.VERB );
//				
//		for( String generated_hypernym : verb_hypernym_container ) 
//		{
//			//System.out.println("generated_hypernym " + generated_hypernym );
//			Sentence example_sentence = new Sentence( generated_hypernym, object, subject );
//			System.out.println("example_sentence " + example_sentence );
//			
//		}
		
		
		
//		//List<Sentence> sentences;
//		List<String> verb_hypernyms = wordnet.getHypernyms(verb, POS.VERB);
//		List<String> object_hypernyms = wordnet.getHypernyms(object, POS.NOUN);
//		List<String> subject_hypernyms = wordnet.getHypernyms(subject, POS.NOUN);
//
//		for(String verb_hypernym : verb_hypernyms)
//		    for(String object_hypernym : object_hypernyms)
//		        for(String subject_hypernym : subject_hypernyms)
//		            sentences.add(new Sentence(verb_hypernym, object_hypernym, subject_hypernym));
//
//		//return sentences;
//		System.out.println("example_sentence " + sentences );
				
		
		
		//wordnet.getHypernyms( this.object, POS.NOUN );
				
		//wordnet.getHypernyms( this.subject, POS.NOUN );
		
		//Sentence example_sentence = new Sentence( verb, object, subject );
		//sentences.add( example_sentence );
		
		//return return_sentence;

