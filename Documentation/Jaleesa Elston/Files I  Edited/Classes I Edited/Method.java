package edu.jsu.dish;

import java.util.List;
import java.util.ArrayList;

public class Method {
	protected enum Methods  {DECREASING_ADJUSTMENT, DOUBLE_LIMIT, REAL_TIME, MULTIPLE_CHOICE};
	protected float minReward;
	protected float maxReward;
	protected float laterTime;
	protected float intertrialInterval;
	protected String rewardType;
	protected float totalTimeElapsed;
	protected Time.timeUnits laterTimeUnits;
	protected Methods methodType;
	protected Option indifferencePoint;
	protected List<Scenario> scenarios;
	protected Time responseDelay;
	protected String question;
	//protected float maxReadingTime;
	protected Time.timeUnits maxReadingTimeUnits;
	
	public Method(Methods type) {
		this.methodType = type;
		minReward = 0;
		maxReward = 0;
		laterTime = 0;
		//maxReadingTime = 0;
		responseDelay = new Time();
        scenarios = new ArrayList<Scenario>();
        indifferencePoint = new Option();
	}
	
	public void setMinReward(float min) { minReward = min; }
	public float getMinReward() { return minReward; }
	
	public void setMaxReward(float max) { maxReward = max; }
	public float getMaxReward() { return maxReward; }
	
	public void setLaterTime(float later) { laterTime = later; }
	public float getLaterTime() { return laterTime; }
	
	public void setIntertrialInterval(float interval) { intertrialInterval = interval; }
	public float getIntertrialInterval() { return intertrialInterval; }
	
	public void setTotalTimeElapsed(float totTime) { totalTimeElapsed = totTime; }
	public float getTotalTimeElapsed() { return totalTimeElapsed; }
	
	public void setLaterTimeUnits(Time.timeUnits a) { laterTimeUnits = a; }
	public Time.timeUnits getLaterTimeUnits() { return laterTimeUnits; }
	
	public void setRewardType(String r) { rewardType = r; }
	public String getRewardType() { return rewardType; }
	
	public void addScenario(Scenario s) { scenarios.add(s); }
    public Scenario getScenario(int i) throws ArrayIndexOutOfBoundsException { return scenarios.get(i); }
	
	public Methods getMethodType() { return methodType; }
	public void setMethodType(Methods t) { methodType = t; }
	
	public Option getIndifferencePoint() { return indifferencePoint; }
    public void setIndifferencePoint(Option ip) { indifferencePoint = ip; }
	
    public int getNumScenarios() { return scenarios.size(); }
    public List<Scenario> getScenarios() { return scenarios; }
	
	public Time getResponseDelay() { return responseDelay; }
	public void setResponseDelay(Time t) { responseDelay = t; }	
	
	public String getQuestion() { return question; }
	public void setQuestion(String q) { question = q; }
	
	public void setMaxReadingTimeUnits(Time.timeUnits a) { maxReadingTimeUnits = a; }
	public Time.timeUnits getMaxReadingTimeUnits() { return maxReadingTimeUnits; }
	
	public String toString() {
        String s = "";
        s += "Type: " + methodType + "\n";
        s += "Min Reward: " + minReward + "\n";
        s += "Max Reward: " + maxReward + "\n";
        s += "Later Time: " + laterTime + "\n";
        s += "Indifference Point: " + indifferencePoint + "\n";
        if(scenarios.size() > 0) {
            s += "Num Scenarios: " + getNumScenarios() + "\n";
            for(Scenario scen : scenarios) {
                s += "=========================\n";
                s += scen;
                s += "=========================\n";
            }
        }
        return s;
    }
    
    public static int toCode(Methods methodType) {
        if(methodType == Methods.DOUBLE_LIMIT) return 1;
        else if(methodType == Methods.DECREASING_ADJUSTMENT) return 2;
		else if(methodType == Methods.REAL_TIME) return 3;
        else return 4;
    }
	
	public static void main(String[] args) {
		
	}
}