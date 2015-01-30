package inference_learner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakeInferences 
{
	public void inference_maker ( Ontology ontology, 
							      Confidence confidence, 
							      Map<Sentence, Integer> ruleCount, 
							      Set<String> joints, 
							      Map<String,List<Integer>> subject2index, 
							      List<Sentence> sentences,
							      Map<String,List<Integer>> object2index ) throws IOException
	{
		for( String joint: ontology.getJoints( joints ) )
		{
			for( Integer subind: ontology.getSubjectIndices( joint, subject2index ) )
			{
				Sentence xaS = ontology.getSentence( subind, sentences );

				for( Integer obind: ontology.getObjectIndices( joint, object2index ) )
				{

					Sentence yOb = ontology.getSentence( obind, sentences );
                
					Sentence s = new Sentence( xaS.getVerb(),
                                               xaS.getObject(),
                                               yOb.getSubject() );
                					
					confidence.numberRules( s, ruleCount );
					
				}
			}
		}
	}
}
