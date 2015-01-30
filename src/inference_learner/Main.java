package inference_learner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import java.io.BufferedOutputStream;
//import java.io.FileOutputStream;
//import java.io.PrintStream;

public class Main 
{
	private static String folder_structure = "/home/matthias/Workbench/SUTD/2_January/learning_first-order_horn_clauses_from_web_text/reverb/code/input_data/"; 
	private static String input_file = "stackoverflow_test.txt";
	//private static String output_file = "output.txt";
	
	//map to store learned 'rules'		
	static Map<Sentence, Integer> ruleCount = new HashMap<>();
	
	private static List<Sentence> sentences = new ArrayList<>();
	private static Map<String,List<Integer>> subject2index = new HashMap<>();
	private static Map<String,List<Integer>> object2index = new HashMap<>();
	private static Set<String> joints = new HashSet<>();
	
	public static void main(String[] args) throws IOException 
	{
		//programatically change output
		//System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream( folder_structure + output_file ))));
		
		ReadFile file_reader = new ReadFile();
		Ontology ontology = new Ontology();
		MakeInferences make_inferences = new MakeInferences();
		Confidence confidence = new Confidence();
		
		file_reader.readFile( folder_structure + input_file , ontology, sentences, subject2index, object2index, joints);
		
		make_inferences.inference_maker(ontology, confidence, ruleCount, joints, subject2index, sentences, object2index );
		
		confidence.confidence( ruleCount );
		
	}
}
