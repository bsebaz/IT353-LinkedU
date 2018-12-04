/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jftur
 */
@SessionScoped
@ManagedBean
public class ApplicationForm implements Serializable{
    
    private String universityId;
    private String applicationId;
    private String title;
    
    private ArrayList <Element> elements;
    
    
    public ApplicationForm(){
        this("Unnamed");
    }
    
    public ApplicationForm(String title){
        this.title = title;
        elements = new ArrayList<>();
        elements.add(new RadialElement(elements.size()+1,"firstQuestion", "Click a bubble (please work)", new String[]{"Oh","my","Gosh"}));
        elements.add(new ShortElement(elements.size()+1,"secondQuestion", "Type a short thing (please work)",0,12,12));
        elements.add(new LongElement(elements.size()+1,"thirdQuestion", "Type a thing (please work)",50,5,"Here's some default text"));
    }
    
    public ApplicationForm(JSONObject json){
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
    
    public static final String[] FIELD_TYPES = {"radial","select","short","long","file"};
    
    public abstract class Element implements Serializable{
        private int number;
        private String name;
        private String type;
        private String instruction;

        public Element(){}
        
        public Element(int number, String name, String type, String instruction){
            this.number = number;
            this.name = name;
            this.type = type;
            this.instruction = instruction;
        }
        
        public JSONObject toJSON(){
            JSONObject obj = new JSONObject();
            obj.put("name",name);
            obj.put("number",number);
            obj.put("type", type);
            obj.put("instruction", instruction);
            return obj;
        }
        
        public abstract String toHTML();
        
        public int getNumber(){return number;}
        public String getName(){return name;}
        public String getType(){return type;}
        public String getInstruction(){return instruction;}

        public void setNumber(int number){this.number = number;}
        public void setName(String name){this.name = name;}
        public void setType(String type){this.type = type;}
        public void setInstruction(String instruction){this.instruction = instruction;}
    }
    
    public class RadialElement extends Element{
        private String[] choices;
        
        RadialElement(int number, String name, String instruction, String[] choices){
            super(number, name, "radial", instruction);
            this.choices = choices;
        }
        
        @Override
        public JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            for(String choice: choices){
                chs.put(choice);
            }
            obj.put("choices",chs);
            return obj;
        }
        
        @Override
        public String toHTML(){
            String html = "<table>\n" +
                    "   <tr>\n";
            for(int i = 0; i < choices.length; i++){
            html += "      <td>\n" +
                    "         <input type = \"radio\" name = \"" + getName() + "\" \n" +
                    "            id = \"" + getName() + i + "\" value =" + choices[i] + " />\n" +
                    "         <label for = \"" + getName() + i + "\">" + choices[i] + "</label>\n";
            }
            html += "      </td>\n" +
                    "   </tr>\n" +
                    "</table><br/>";
            return html;
        }
        
        public void setChoice(int index, String choice){choices[index] = choice;}
        public String getChoice(int index){return choices[index];}
        public void setChoices(String[] choices){this.choices = choices;}
        public String[] getChoices(){return choices;}
    }
    
    public class SelectElement extends Element{
        private String[] choices;
        
        public SelectElement(int number, String name, String type, String instruction, String[] choices){
            super(number, name, type, instruction);
            this.choices = choices;
        }
        
        @Override
        public JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            for(String choice: choices){
                chs.put(choice);
            }
            obj.put("choices",chs);
            return obj;
        }
        
        @Override
        public String toHTML(){
            String html = "";
            html += "<select name=\"" + getName() + "\" size=\"1\">";
            for(int i = 0; i < choices.length; i++){
                html +=     "<option value=\"" + choices[i] + "\">" + choices[i] + "</option>";
            }
            html += "</select><br/>";
            return html;
        }
        
        public void setChoice(int index, String choice){choices[index] = choice;}
        public String getChoice(int index){return choices[index];}
        public void setChoices(String[] choices){this.choices = choices;}
        public String[] getChoices(){return choices;}
    }
    
    public class LongElement extends Element{
        private int width;
        private int height;
        private String defText;
        
        public LongElement(int number, String name, String instruction, int width, int height, String defText){
            super(number, name, "long", instruction);
            this.width = width;
            this.height = height;
            this.defText = defText;
        }
        
        @Override
        public JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            return obj;
        }
        
        @Override
        public String toHTML(){
            String html = "";
            html += "<textarea rows=\"" + height + "\" cols=\"" + width + "\">\n" +
                    defText + "\n" +
                    "</textarea><br/> ";
            return html;
        }
    }
    
    public class ShortElement extends Element{
        private int minLength;
        private int maxLength;
        private int size;
        
        public ShortElement(int number, String name, String instruction, int minLength, int maxLength, int size){
            super(number, name, "short", instruction);
            this.minLength = minLength;
            this.maxLength = maxLength;
            this.size = size;
        }
        
        @Override
        public JSONObject toJSON(){
            JSONObject obj = super.toJSON();
            JSONArray chs = new JSONArray();
            return obj;
        }
        
        @Override
        public String toHTML(){
            String html = "";
            html += //"<label for=\"" + getName() + "\">" + getInstruction() + ":</label>\n" +
                "<input type=\"text\" id=\"" + getName() + "\" name=\"" + getName() + "\" required\n" +
                "       minlength=\"" + minLength + "\" maxlength=\"" + maxLength + "\" size=\"" + size + "\"><br/><br/>";
            return html;
        }
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
    
    public String getTitle(){setTitle(title);return title;}
    public String getUniversityId(){return universityId;}
    public String getApplicationId(){return applicationId;}
    public ArrayList<Element> getElements(){return elements;}
    public Element getElement(int index){return elements.get(index);}
}
