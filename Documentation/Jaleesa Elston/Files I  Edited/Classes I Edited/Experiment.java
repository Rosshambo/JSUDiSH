package edu.jsu.dish;

import java.util.List;
import java.util.ArrayList;

public class Experiment {
	private List<Method> method;
	private String title;
	private int number;
	private String instructions;
	private String conclusion;
	private ParticipantInformation ParticipantInfo;
	private List<Investigator> investigator;
        private String choiceList;
        private String daInstructions;
        private String dlInstructions;
        private String mcInstructions;
		private String rtInstructions;

	public Experiment() {
		investigator = new ArrayList<Investigator>();
		title = "";
		number = 1;
		instructions = "";
		method = new ArrayList<Method>();
        ParticipantInfo = new ParticipantInformation();
		conclusion = "";
	}	

	public List<Investigator> getPersonnel() { return investigator; }
	//public Investigator getPersonnel() { return investigator.get(0); }
	public void addPersonnel(Investigator a) { investigator.add(a);	}
	public int getNumPersonnel() { return investigator.size(); }
	public Investigator getPersonnel(int p) throws ArrayIndexOutOfBoundsException { return investigator.get(p); }
	public void setTitle(String t) { title = t; }
	public String getTitle() { return title; }
	public void setNumber(int n) { number = n; }
	public int getNumber () { return number; }
	public void setInstructions(String i) { instructions = i; }
	public String getInstructions() { return instructions; }
	public List<Method> getMethods() { return method; }
	public Method getMethod(int i) throws ArrayIndexOutOfBoundsException { return method.get(i); }
	public void addMethod(Method a) { method.add(a); }
	public int getNumMethods() { return method.size(); }
	public void setParticipantInformation(ParticipantInformation p) { ParticipantInfo = p; }
	public ParticipantInformation getParticipantInformation() { return ParticipantInfo; }
	public void setConclusion(String c) { conclusion = c; }
	public String getConclusion() { return conclusion; }
	
	public void setChoiceList(String cl) { choiceList = cl; }
        public String getChoiceList () { return choiceList; }
        public void setDaInstructions (String da){ daInstructions = da; }
        public String getDaInstructions () { return daInstructions; }
        public void setDlInstructions (String dl){ dlInstructions = dl; }
        public String getDlInstructions () { return dlInstructions; }
        public void setMcInstructions (String mc){ mcInstructions = mc; }
        public String getMcInstructions () { return mcInstructions; }
		public void setRtInstructions (String rt) { rtInstructions = rt; }
		public String getRtInstructions () { return rtInstructions; }
        
	
	public String toString() {
        String s = "";
		s += "Personnel: \n";
		for(int i = 0; i < investigator.size(); i++) {
			s += "   " + investigator.get(i) + "\n";
		}
		s += "Title: " + title + "\n";
		s += "Number: " + number + "\n";
		s += "Instructions: " + instructions + "\n";
		s += "Number of Methods: " + method.size() + "\n";
		for(int i = 0; i < method.size(); i++) {
            s += "+++++++++++++++++++++++++\n";
            s += method.get(i).toString();
            s += "+++++++++++++++++++++++++\n";
 		}
        s += "Participant Information:\n";
        s += ParticipantInfo + "\n";
		s += conclusion + "\n";
        return s;
	}
}