package inference_learner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;

public class WordNet 
{
	private String wnhome;
	private String path;
	private URL url;
	private IDictionary dict;

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
	
	
	public void getHypernyms( String inut_word ) throws IOException
    {	
    	
    	// get the synset of 'input_word'
    	//IIndexWord idxWord = dict . getIndexWord (inut_word, POS . NOUN ) ;
		IIndexWord idxWord = dict . getIndexWord (inut_word, null ) ;
    	IWordID wordID = idxWord . getWordIDs () . get (0) ; // 1st meaning
    	IWord word = dict . getWord ( wordID ) ;
    	ISynset synset = word . getSynset () ;
    	
    	// get the hypernyms
    	List < ISynsetID > hypernyms =
    	synset . getRelatedSynsets ( Pointer . HYPERNYM ) ;
    	
    	// print out each h y p e r n y m s id and synonyms
    	List < IWord > words ;
    	for( ISynsetID sid : hypernyms ) {
    	words = dict . getSynset ( sid ) . getWords () ;
    	System . out . print ( sid + " {") ;
    	for( Iterator < IWord > i = words . iterator () ; i . hasNext () ;) {
    	System . out . print ( i . next () . getLemma () ) ;
    	if( i . hasNext () )
    	System . out . print (", ") ;
    	}
    	System . out . println ("}") ;
    	}
    	 
    	 
    }
    
	public void getStem(String word)
	{
     //JWS ws = new JWS("C:/Program Files/WordNet","2.1");  
	 WordnetStemmer stem =  new WordnetStemmer( dict );
	 System.out.println("test" + stem.findStems(word, null) );
	}
}
