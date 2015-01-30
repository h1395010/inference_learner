package inference_learner;

import static java.util.Collections.reverseOrder;
import java.util.Map;

public class Confidence 
{
			
	//store data
	public void numberRules( Sentence sentence, Map<Sentence, Integer> ruleCount) 
	{
		if (!ruleCount.containsKey(sentence))
		{
			ruleCount.put(sentence, 0);
		}
		ruleCount.put(sentence, ruleCount.get(sentence) + 1);
	}
	
	public void confidence( Map<Sentence, Integer> ruleCount )
	{		
		ruleCount.entrySet().stream()
	    .filter(e -> e.getValue() > 0 )
        .sorted(reverseOrder(Map.Entry.comparingByValue()))
        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
	}
}
