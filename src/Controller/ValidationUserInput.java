package Controller; 

import java.util.Date;
import java.util.Map;

import javax.swing.text.JTextComponent;

public class ValidationUserInput { 
     
    public static final String VALID = "Valid Input"; 

    //validate required text field 
    public static String validateRequiredTextField(String field, String value) { 

        if(value.equals("")) //empty text input 
            return field + " cannot be empty!";//error message 
        else 
            return VALID; 
    } 
    
  //validate required text fields 
    public static String validateRequiredTextFields(Map<String, JTextComponent> requiredTextFieldMap) { 

    	String validationResult = VALID;
    	
    	for (Map.Entry<String, JTextComponent> requiredTextField : requiredTextFieldMap.entrySet()) {
		    String field = requiredTextField.getKey();
		    String value = requiredTextField.getValue().getText();
		    
		    validationResult = ValidationUserInput.validateRequiredTextField(field, value);
		    
		    if(validationResult.equals(VALID) == false) 
				return validationResult;
		    
		}
    	
    	return validationResult;
    } 

    //validate required positive number field 
    public static String validateRequiredPositiveNumberField(String field, String value) { 

        try {  

            int num = Integer.parseInt(value);              

            if(num > 0) 
                return VALID; 
            else 
                return field + " must be a positive number";//error message 
            
        }catch(Exception ex) { 

            return field + " cannot be empty and must be a number!";//error message 
        } 

    } 

    //validate required Date from JCalendar 
    public static String validateRequiredDateField(String field, Date value) { 

        if(value == null) //empty text input 

            return field + " cannot be empty and may have invalid value!";//error message 
        else 
            return VALID; 
    } 

  

} 