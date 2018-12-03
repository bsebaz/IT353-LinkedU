/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jftur
 */

public class ApplicationForm implements Serializable{
    
    private String universityId;
    private String applicationId;
    private String title;
    
    private ArrayList <Element> elements;
    
    
    public ApplicationForm(){}
    
    public ApplicationForm(String title){
        this.title = title;
        elements = new ArrayList<>();
    }
    
    public ApplicationForm(JSONObject json){
        title = "unnamed";
        elements = new ArrayList<>();
    }
    
    public JSONObject toJSON(){
        JSONObject jform = new JSONObject();
        jform.put("title", title);
        JSONArray els = new JSONArray();
        for(Element e: elements){
            els.put(e.toJSON());
        }
        jform.put("elements",els);
        return null;
    }
    
    @Override
    public String toString(){
        JSONObject obj = toJSON();
        return obj.toString();
    }
    
    public void addField(String type){
        int n = elements.size();
        Element e = null;
        switch(type){
            case "radial":{
                //e = new RadialElement(n);
            }break;
            case "select":{
                //e = new SelectElement(n);
            }break;
            case "shortText":{
                //e = new ShortElement(n);
            }break;
            case "longText":{
                //e = new LongElement(n);
            }break;
            case "file":{
                //e = new FileElement(n);
            }break;
            default:{
                
            }break;
        }
        elements.add(n,e);
    }
    
    public void deleteField(String name){
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).name.equals(name)){
                deleteField(i);
            }
        }
    }
    public void deleteField(){
        deleteField(elements.size()-1);
    }
    public void deleteField(int number){
        elements.remove(number);
        //make sure element numbers are same as index + 1 after removal
    }
    
    public static final String[] FIELD_TYPES = {"radial","select","shortText","longText","file"};
    
    private abstract class Element{
        private int number;
        private String name;
        private String type;
        private String instruction;
        

        Element(){}
        
        Element(int number, String name, String type, String instruction){
            this.number = number;
            this.name = name;
            this.type = type;
            this.instruction = instruction;
        }
        
        JSONObject toJSON(){
            JSONObject obj = new JSONObject();
            obj.put("name",name);
            obj.put("number",number);
            obj.put("type", type);
            obj.put("instruction", instruction);
            return obj;
        }
        
        abstract String toHTML();
        
        int getNumber(){return number;}
        String getName(){return name;}
        String getType(){return type;}
        String getInstruction(){return instruction;}

        void setNumber(int number){this.number = number;}
        void setName(String name){this.name = name;}
        void setType(String type){this.type = type;}
        void setInstruction(String instruction){this.instruction = instruction;}
    }
    
    private class RadialElement extends Element{
        private String[] choices;
        
        RadialElement(int number, String name, String type, String instruction, String[] choices){
            super(number, name, type, instruction);
            this.choices = choices;
        }
        
        @Override
        JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            for(String choice: choices){
                chs.put(choice);
            }
            obj.put("choices",chs);
            return obj;
        }
        
        @Override
        String toHTML(){
            String html = "";
            html += "<h:selectOneRadio id=\"" + getName() +"\" value=\"#{user.favColor1}\">";
            html +=     "<f:selectItem itemValue=\"Red\" itemLabel=\"Color1 - Red\" />";
            html +=     "<f:selectItem itemValue=\"Green\" itemLabel=\"Color1 - Green\" />";
            html +=     "<f:selectItem itemValue=\"Blue\" itemLabel=\"Color1 - Blue\" />";
            html += "</h:selectOneRadio>";
            return html;
        }
        
        void setChoice(int index, String choice){choices[index] = choice;}
        String getChoice(int index){return choices[index];}
        void setChoices(String[] choices){this.choices = choices;}
        String[] getChoices(){return choices;}
    }
    
    private class SelectElement extends Element{
        private String[] choices;
        
        SelectElement(int number, String name, String type, String instruction, String[] choices){
            super(number, name, type, instruction);
            this.choices = choices;
        }
        
        @Override
        JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            for(String choice: choices){
                chs.put(choice);
            }
            obj.put("choices",chs);
            return obj;
        }
        
        @Override
        String toHTML(){
            String html = "";
            html += "<select name=\"" + getName() + "\" size=\"1\">";
            for(int i = 0; i < choices.length; i++){
                html +=     "<option value=\"" + choices[i] + "\">" + choices[i] + "</option>";
            }
            html += "</select>";
            return html;
        }
        
        void setChoice(int index, String choice){choices[index] = choice;}
        String getChoice(int index){return choices[index];}
        void setChoices(String[] choices){this.choices = choices;}
        String[] getChoices(){return choices;}
    }
    
    private class ShortElement{
        
    }
    
    private void renumberElements(){
        renumberElements(0);
    }
    private void renumberElements(int index){
        Element temp = null;
        for(int i = index; i < elements.size(); i++){
            if((temp = elements.get(i)) == null){
                elements.remove(i);
                for(int j = i; j < elements.size(); i++){
                    temp = elements.get(j+1);
                    if(temp != null){
                        temp.setNumber(j);
                    }
                    else{
                        renumberElements(j);
                    }
                    elements.set(j, temp);
                }
            }
        }
    }
    
    public void setTitle(String title){this.title = title;}
    public void setUniversityId(String universityId){this.universityId = universityId;}
    public void setApplicationId(String applicationId){this.applicationId = applicationId;}
    public void setElements(ArrayList<Element> elements){this.elements = elements;}
    public void setElement(int index, Element element){elements.set(index, element);}
    
    public String getTitle(){return title;}
    public String getUniversityId(){return universityId;}
    public String getApplicationId(){return applicationId;}
    public ArrayList<Element> getElements(){return elements;}
    public Element getElement(int index){return elements.get(index);}
}
