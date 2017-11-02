package edu.jsu.dish;

import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class ExperimentLoader extends DefaultHandler
{    
    private Experiment experiment;
    private Investigator investigator;
    private Method method;
    private Scenario scenario;
    private Option option; 
    private String rewardType;
    private String timeUnits;
    private String currentElement;
	private int[] tickArray;
	private String fileName;
	//private DistributionType dt;
    
	public ExperimentLoader (String file){
		File experimentFile = new File(file);
		experiment = new Experiment();
		investigator = new Investigator();
		scenario = new Scenario();
		option = new Option();
		rewardType = "";
		timeUnits = "";
		currentElement = "";
		tickArray = new int[10];
		try {
			SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			File schemaLocation = new File("experiment.xsd");
			Schema schema = factory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(experimentFile);
			validator.validate(source);
			XMLReader parser = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			parser.setContentHandler(this);
			parser.parse(file);
		}
		catch (SAXException ex) {
			// If the file doesn't validate against the schema...
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error in Experiment XML", JOptionPane.ERROR_MESSAGE);
		}  
		catch(FileNotFoundException ex) {
			// If the file doesn't exist...
			JOptionPane.showMessageDialog(null, "The file could not be found.", "File Not Found", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ex) {
			// If other bad stuff happens...
			JOptionPane.showMessageDialog(null, "An I/O error has occurred.", "I/O Error", JOptionPane.ERROR_MESSAGE);
		}
	/*	catch(InvalidXMLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid XML", JOptionPane.ERROR_MESSAGE);
		}*/
	}
	
    public String validate (String xmlFilename){
		String xmlFile = xmlFilename; 
		if (xmlFile.contains(".xml")){
			xmlFile = "";
			return xmlFile;
		}//end if (xmlFile.contains(".xml"))
		else{
			xmlFile = "This is not an  xml file";
			return xmlFile;
		} //end else
	} //end validate method
   
    public Experiment getExperiment() { return experiment; }
     
    private Time convertToTime(float value, String units) {
		Time t = new Time();
		t.setValue(value);
		if(units == null || units.equalsIgnoreCase("SECONDS")) {
			t.setUnits(Time.timeUnits.SECONDS);
		}
		else if(units.equalsIgnoreCase("MINUTES")) {
			t.setUnits(Time.timeUnits.MINUTES);
		}
		else if(units.equalsIgnoreCase("HOURS")) {
			t.setUnits(Time.timeUnits.HOURS);
		}
		else if(units.equalsIgnoreCase("DAYS")) {
			t.setUnits(Time.timeUnits.DAYS);
		}
		else if(units.equalsIgnoreCase("WEEKS")) {
			t.setUnits(Time.timeUnits.WEEKS);
		}
		else if(units.equalsIgnoreCase("MONTHS")) {
			t.setUnits(Time.timeUnits.MONTHS);
		}
		else if(units.equalsIgnoreCase("YEARS")) {
			t.setUnits(Time.timeUnits.YEARS);
		}
		return t;
	}	
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
		//System.out.println("i'm in the START ELEMENT method");
		//System.out.println("localName is: " + localName);
		//System.out.println("current element is:" + currentElement);
		//System.out.println();
		String name = localName;
		if(name.equalsIgnoreCase("CHOICELIST")) {
			currentElement = "CHOICELIST";
		}
		else if(name.equalsIgnoreCase("CONCLUSION")) {
			currentElement = "CONCLUSION";
		}
		else if(name.equalsIgnoreCase("DA")) {
			currentElement = "DA";
			method = new DecreasingAdjustment();
		}
		else if(name.equalsIgnoreCase("DAINSTRUCTIONS")) {
			currentElement = "DAINSTRUCTIONS";	
		}
		else if(name.equalsIgnoreCase("DEPARTMENT")) {
			currentElement = "DEPARTMENT";	
		}
		else if(name.equalsIgnoreCase("DISTRIBUTION")) {
			currentElement = "DISTRIBUTION";
		}
		else if(name.equalsIgnoreCase("DL")) {
			currentElement = "DL";
			method = new DoubleLimit();
		}
		else if(name.equalsIgnoreCase("DLINSTRUCTIONS")) {
			currentElement = "DLINSTRUCTIONS";	
		}
		else if(name.equalsIgnoreCase("DOUBLEDELAY")) {
			currentElement = "DOUBLEDELAY";	
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("EXPERIMENT")) {
			currentElement = "EXPERIMENT";
			experiment = new Experiment();
		}
		else if(name.equalsIgnoreCase("FILENAME")) {
			currentElement = "FILENAME";
		}
		else if(name.equalsIgnoreCase("INSTRUCTIONS")) {
			currentElement = "INSTRUCTIONS";	
		}
		else if(name.equalsIgnoreCase("INTERTRIALINTERVAL")) {
			currentElement = "INTERTRIALINTERVAL";
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("INVESTIGATOR")) {
			currentElement = "INVESTIGATOR";	
			investigator = new Investigator();
		}
		else if(name.equalsIgnoreCase("LATERTIME")) {
			currentElement = "LATERTIME";
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("MAXREADINGTIME")){
			currentElement = "MAXREADINGTIME";	
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("MAXREWARD")) {
			currentElement = "MAXREWARD";		
		}
		else if(name.equalsIgnoreCase("MAXTIME")) {
			currentElement = "MAXTIME";	
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("MC")) {
			currentElement = "MC";
			method = new MultipleChoice();
		}
		else if(name.equalsIgnoreCase("MCINSTRUCTIONS")) {
			currentElement = "MCINSTRUCTIONS";	
		}
		else if(name.equalsIgnoreCase("METHODS")) {
			currentElement = "METHODS";	
		}
		else if(name.equalsIgnoreCase("MINREWARD")) {
			currentElement = "MINREWARD";		
		}
		else if(name.equalsIgnoreCase("NAME")) {
			currentElement = "NAME";		
		}
		else if(name.equalsIgnoreCase("NUMBER")) {
			currentElement = "NUMBER";		
		}
		else if(name.equalsIgnoreCase("NUMTRIALS")) {
			currentElement = "NUMTRIALS";		
		}
		else if(name.equalsIgnoreCase("OPTION")) {
			currentElement = "OPTION";
			option = new Option();
		}
		else if(name.equalsIgnoreCase("PERSONNEL")) {
			currentElement = "PERSONNEL";
		}
		else if(name.equalsIgnoreCase("PRECISION")) {
			currentElement = "PRECISION";
		}
		else if(name.equalsIgnoreCase("QUESTION")) {
			currentElement = "QUESTION";
		}
		else if(name.equalsIgnoreCase("RESPONSEDELAY")) {
			currentElement = "RESPONSEDELAY";		
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("REWARD")) {
			currentElement = "REWARD";		
		}
		else if(name.equalsIgnoreCase("REWARDTYPE")) {
			currentElement = "REWARDTYPE";		
		}
		else if(name.equalsIgnoreCase("RT")) {
			currentElement = "RT";
			method = new RealTime();
		}
		else if(name.equalsIgnoreCase("RTINSTRUCTIONS")) {
			currentElement = "RTINSTRUCTIONS";	
		}
		else if(name.equalsIgnoreCase("SCENARIO")) {
			currentElement = "SCENARIO";
            scenario = new Scenario();
		}
		else if(name.equalsIgnoreCase("SHOWRULER")) {
			currentElement = "SHOWRULER";
		}
		else if(name.equalsIgnoreCase("SOONERTIME")) {
			currentElement = "SOONERTIME";
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("TICKMARK1")) {
			currentElement = "TICKMARK1";
		}
		else if(name.equalsIgnoreCase("TICKMARK2")) {
			currentElement = "TICKMARK2";
		}
		else if(name.equalsIgnoreCase("TICKMARK3")) {
			currentElement = "TICKMARK3";
		}
		else if(name.equalsIgnoreCase("TICKMARK4")) {
			currentElement = "TICKMARK4";
		}
		else if(name.equalsIgnoreCase("TICKMARK5")) {
			currentElement = "TICKMARK5";
		}
		else if(name.equalsIgnoreCase("TICKMARK6")) {
			currentElement = "TICKMARK6";
		}
		else if(name.equalsIgnoreCase("TICKMARK7")) {
			currentElement = "TICKMARK7";
		}
		else if(name.equalsIgnoreCase("TICKMARK8")) {
			currentElement = "TICKMARK8";
		}
		else if(name.equalsIgnoreCase("TICKMARK9")) {
			currentElement = "TICKMARK9";
		}
		else if(name.equalsIgnoreCase("TICKMARK10")) {
			currentElement = "TICKMARK10";
		}
		else if(name.equalsIgnoreCase("TIME")) {
			currentElement = "TIME";		
			timeUnits = atts.getValue("units");
		}
		else if(name.equalsIgnoreCase("TITLE")) {
			currentElement = "TITLE";		
		}
	}
	public void endElement(String namespaceURI, String localName, String qName) {
		//System.out.println("i'm in the END ELEMENT method");
		//System.out.println("name is: " + localName);
		//System.out.println();
		String name = localName;
		if(name.equalsIgnoreCase("CHOICELIST")) {}
		else if(name.equalsIgnoreCase("CONCLUSION")) {}
		else if(name.equalsIgnoreCase("DA")) {
			experiment.addMethod(method);
			//method = null;
		}
		else if(name.equalsIgnoreCase("DAINSTRUCTIONS")) {}
		else if(name.equalsIgnoreCase("DEPARTMENT")) {}
		else if(name.equalsIgnoreCase("DISTRIBUTION")) {}
		else if(name.equalsIgnoreCase("DL")) {
			experiment.addMethod(method);
			//method = null;
		}
		else if(name.equalsIgnoreCase("DLINSTRUCTIONS")) {}
		else if(name.equalsIgnoreCase("DOUBLEDELAY")) {
			//timeUnits = null;
		}
		else if(name.equalsIgnoreCase("EXPERIMENT")) {}
		else if(name.equalsIgnoreCase("FILENAME")) {}
		else if(name.equalsIgnoreCase("INSTRUCTIONS")) {}
		else if(name.equalsIgnoreCase("INTERTRIALINTERVAL")) {}
		else if(name.equalsIgnoreCase("INVESTIGATOR")) {
			experiment.addPersonnel(investigator);
			//investigator = null;
		}
		else if(name.equalsIgnoreCase("LATERTIME")) {}
		else if(name.equalsIgnoreCase("MAXREADINGTIME")) {
			//timeUnits = null;
		}
		else if(name.equalsIgnoreCase("MAXREWARD")) {}
		else if(name.equalsIgnoreCase("MAXTIME")) {
			//timeUnits = null;
		}
		else if(name.equalsIgnoreCase("MC")) {
			experiment.addMethod(method);
			//method = null;
		}
		else if(name.equalsIgnoreCase("MCINSTRUCTIONS")) {}
		else if(name.equalsIgnoreCase("METHODS")) {}
		else if(name.equalsIgnoreCase("MINREWARD")) {}
		else if(name.equalsIgnoreCase("NAME")) {}
		else if(name.equalsIgnoreCase("NUMBER")) {}
		else if(name.equalsIgnoreCase("NUMTRIALS")) {}
		else if(name.equalsIgnoreCase("OPTION")) {
			scenario.addOption(option);
			//option = null;
		}
		else if(name.equalsIgnoreCase("PERSONNEL")) {}
		else if(name.equalsIgnoreCase("QUESTION")) {}
		else if(name.equalsIgnoreCase("RESPONSEDELAY")) {
			//timeUnits = null;
		}
		else if(name.equalsIgnoreCase("REWARD")) {}
		else if(name.equalsIgnoreCase("REWARDTYPE")) {}
		else if(name.equalsIgnoreCase("RT")) {
			experiment.addMethod(method);
			//method = null;
		}
		else if(name.equalsIgnoreCase("RTINSTRUCTIONS")) {}
		else if(name.equalsIgnoreCase("SCENARIO")) {
			method.addScenario(scenario);
            //scenario = null;
		}
		else if(name.equalsIgnoreCase("SHOWRULER")) {}
		else if(name.equalsIgnoreCase("TICKMARK1")) {}
		else if(name.equalsIgnoreCase("TICKMARK2")) {}
		else if(name.equalsIgnoreCase("TICKMARK3")) {}
		else if(name.equalsIgnoreCase("TICKMARK4")) {}
		else if(name.equalsIgnoreCase("TICKMARK5")) {}
		else if(name.equalsIgnoreCase("TICKMARK6")) {}
		else if(name.equalsIgnoreCase("TICKMARK7")) {}
		else if(name.equalsIgnoreCase("TICKMARK8")) {}
		else if(name.equalsIgnoreCase("TICKMARK9")) {}
		else if(name.equalsIgnoreCase("TICKMARK10")) {}
		else if(name.equalsIgnoreCase("TIME")) {
			//timeUnits = null;
		}
		else if(name.equalsIgnoreCase("TITLE")) {}
	}
	
	
	public void characters(char ch[], int start, int length) {
		//System.out.println("I'm in the CHARACTERS method");
		//System.out.println("characters current element is: " + currentElement);
		//System.out.println();
		String value = new String(ch, start, length);
		value = value.trim();
		if(!value.equals("")) {
			if(currentElement.equalsIgnoreCase("CHOICELIST")) {
				experiment.setChoiceList(value);
			}
			else if(currentElement.equalsIgnoreCase("CONCLUSION")) {
				experiment.setConclusion(value);
            }
            else if(currentElement.equalsIgnoreCase("DAINSTRUCTIONS")) {
				experiment.setDaInstructions(value);
			}
			else if(currentElement.equalsIgnoreCase("DEPARTMENT")) {
				investigator.setDepartment(value);
			}
			else if(currentElement.equalsIgnoreCase("DISTRIBUTION")) {
				//((MultipleChoice)method).setPointDistributionType(value);
			}
            else if(currentElement.equalsIgnoreCase("DLINSTRUCTIONS")) {
				experiment.setDlInstructions(value);
			}           
			else if(currentElement.equalsIgnoreCase("FILENAME")){
				//System.out.println("Filename is " + value);
					((RealTime)method).setReadFile(value);
				//System.out.println("Filename has been set to " + ((RealTime)method).getReadFile();
			}
			else if(currentElement.equalsIgnoreCase("INSTRUCTIONS")) {
				experiment.setInstructions(value);
			}
            else if(currentElement.equalsIgnoreCase("INTERTRIALINTERVAL")) {
				method.setIntertrialInterval(Float.parseFloat(value));
			}
			else if(currentElement.equalsIgnoreCase("MAXREADINGTIME")) {
				float v = Float.parseFloat(value);
				//Time t = convertToTime(v, timeUnits);
				((RealTime)method).setMaxTime(Integer.parseInt(value));
				method.setMaxReadingTimeUnits(Time.timeUnits.MINUTES);
			}
			else if(currentElement.equalsIgnoreCase("MAXREWARD")) {
				//System.out.println("previous rewardtype: " + rewardType);
				method.setMaxReward(Float.parseFloat(value));
				method.setRewardType(rewardType);
				//System.out.println("rewardtype is now: " + rewardType);
			}
			else if(currentElement.equalsIgnoreCase("MAXTIME")) {
				float v = Float.parseFloat(value);
				//Time t = convertToTime(v, timeUnits);
				method.setLaterTime(v);
				method.setLaterTimeUnits(Time.timeUnits.SECONDS);
			}
            else if(currentElement.equalsIgnoreCase("MCINSTRUCTIONS")) {
				experiment.setMcInstructions(value);
			}
			else if(currentElement.equalsIgnoreCase("MINREWARD")) {
				method.setMinReward(Float.parseFloat(value));
				method.setRewardType(rewardType);
			}
			else if(currentElement.equalsIgnoreCase("NAME")) {
                String[] name = value.split("\\W+", 2);
				investigator.setName(name[0] + " " + name[1]);
			}
			else if(currentElement.equalsIgnoreCase("NUMBER")) {
				experiment.setNumber(Integer.parseInt(value));
			}
			else if(currentElement.equalsIgnoreCase("NUMTRIALS")) {
				((DecreasingAdjustment)method).setNumScenarios(Integer.parseInt(value));
			}
            else if(currentElement.equalsIgnoreCase("PRECISION")) {
				((DecreasingAdjustment)method).setPrecision(Integer.parseInt(value));
			}
			else if(currentElement.equalsIgnoreCase("QUESTION")) {
				method.setQuestion(value);
			}
			else if(currentElement.equalsIgnoreCase("RTINSTRUCTIONS")) {
				experiment.setRtInstructions(value);
			}
			else if(currentElement.equalsIgnoreCase("RESPONSEDELAY")) {
				float v = Float.parseFloat(value);
				Time t1 = convertToTime(v, timeUnits);
				method.setResponseDelay(t1);
			}
			else if(currentElement.equalsIgnoreCase("REWARD")) {
				option.setReward(new Reward(Float.parseFloat(value), rewardType));
			}
			else if(currentElement.equalsIgnoreCase("REWARDTYPE")) {
                                rewardType = value;
			}
			else if(currentElement.equalsIgnoreCase("SHOWRULER")) {
				if (value.equals("1"))
					((RealTime)method).setRuler(true);
				else
					((RealTime)method).setRuler(false);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK1")) {
				tickArray[0] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK2")) {
				tickArray[1] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK3")) {
				tickArray[2] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK4")) {
				tickArray[3] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK5")) {
				tickArray[4] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK6")) {
				tickArray[5] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK7")) {
				tickArray[6] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK8")) {
				tickArray[7] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK9")) {
				tickArray[8] = Integer.parseInt(value);
			}
			else if(currentElement.equalsIgnoreCase("TICKMARK10")) {
				tickArray[9] = Integer.parseInt(value);
				((RealTime)method).setTick(tickArray);
			}
			else if(currentElement.equalsIgnoreCase("TIME")) {
				float v = Float.parseFloat(value);
				Time t = convertToTime(v, timeUnits);
				option.setTime(t);
			}
			else if(currentElement.equalsIgnoreCase("TITLE")) {
				experiment.setTitle(value);
			}
		}
	}
}