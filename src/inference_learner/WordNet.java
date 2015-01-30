package inference_learner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

public class WordNet 
{
	private String wnhome;
	private String path;
	private URL url;
	private static IDictionary dict;
	
	

	public WordNet() throws IOException
	{ 
		// construct the URL to the Wordnet dictionary directory
		wnhome = System.getenv("WNHOME");
		path = wnhome + File.separator + "dict";
		url = new URL ("file", null , path );
		
		// construct the dictionary object and open it
		dict = new Dictionary ( url ) ;
		dict.open();
	}
	
	
	public List<String> getHypernyms( String input_word, POS pos ) throws IOException
    {	
		//make a NEW list of hypernyms every time this is called
		List<String> generated_hypernyms = new ArrayList<>();
		
		//System.out.println( "    input word: " + input_word );
		// get the synset of 'input_word'
		IIndexWord idxWord = dict.getIndexWord( input_word, pos);
		
		if(dict.getIndexWord( input_word, pos ) == null)
		{
			generated_hypernyms.add(input_word);
		}
		else
		{
			generated_hypernyms.add(input_word);
			
			IWordID wordID = idxWord.getWordIDs().get(0); // 1st meaning
			IWord word = dict.getWord( wordID );
			ISynset synset = word.getSynset();
    	
			// get the hypernyms
			List < ISynsetID > hypernyms = synset.getRelatedSynsets( Pointer.HYPERNYM );
    	
			if( hypernyms.size() > 0)
			{
				// print out each hypernyms id and synonyms
				List < IWord > words;
				// sid, how the hypernyms are organized (in wordnet)
				for( ISynsetID sid : hypernyms ) 
				{
					words = dict.getSynset( sid ).getWords ();
					
					//generated_hypernyms.add(input_word);
					for( Iterator <IWord> i = words.iterator(); i.hasNext(); ) 
					{
						generated_hypernyms.add( i.next().getLemma() );
					}
				}
			}
		}
		return generated_hypernyms;
    }
	
	public String getStem(String word)
	{
		WordnetStemmer stem =  new WordnetStemmer( dict );
		 
		List<String> stemmed_words = stem.findStems(word,  POS.VERB);
		
		if ( !stemmed_words.isEmpty() )
			return stemmed_words.get(0);
		else
			return word;
	}
	
}
	

