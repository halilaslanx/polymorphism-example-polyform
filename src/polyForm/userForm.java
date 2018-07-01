package polyForm;

import java.util.ArrayList;
import java.util.List;

public class userForm {

	// after TextField example
	public static void printFieldLine(Field field) {
		System.out.println(field.displayLabel() + field.displayValue());
	}

	// after email example
	public static void printForm(List<Field> form) {	//explain List<Field> as a type
		System.out.println("---------------- SIGN UP ---------------");
		for (Field field : form) {
			printFieldLine(field);
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}

	// after email example, look for cue
	public static void printFormDefinition(List<Field> form) {	//explain List<Field> as a type
		System.out.println("---------------- SIGN UP ---------------");
		for (Field field : form) {
			System.out.println(field.getFieldDefinition());
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
	//close to end of class
	public static void resetForm(List<Field> form) {
		for (Field field : form) {
			field.setValue(field.getZeroValue());	// implement getZeroValue
		}
	}

	public static void main(String[] args) {
		Field myField = new Field("undefined");

		System.out.println(myField.getFieldDefinition()); // run
		System.out.println(myField.displayLabel()); // run

		// delete previous and run
		System.out.println(myField.displayLabel() + myField.displayValue());

		TextField name = new TextField("Name");
		name.setDefaultValue("Full Name");

		System.out.println(name.getFieldDefinition());	//run
		printFieldLine(name);							//run

		TextField company = new TextField("Company");
		company.setDefaultValue("Company Name");
		printFieldLine(company);						//run

		System.out.println("------------");
		
		// this is where the beauty of polymorphism shines
		List<Field> signUpForm = new ArrayList<>();		// explain
		signUpForm.add(name);
		signUpForm.add(company);
		
		for (Field field : signUpForm) {
			System.out.println(field.getFieldDefinition()); // run
		}
		
		System.out.println("------------");
		for (Field field : signUpForm) {
			printFieldLine(field);						// run
		}
		
		name.setValue("Halil Aslan");
		System.out.println("name valid: " + name.isValid());	// try invalid long name here
		////////////// TEXTFIELD TO THIS POINT
		
		EmailField email = new EmailField("Email");
		email.setDefaultValue("user@domain.com");
		
		signUpForm.add(email);
		printForm(signUpForm);	// implement printForm at this point
		
		System.out.println();
		company.setValue("CyberTek");	
		printForm(signUpForm);	// explain how the list is updated when company is changed
		
		email.setValue("halil@cybertekschool.com");
		System.out.println("email valid: " + email.isValid());  // try halilcybertek as email
		
		//implement printFormDefinition
		printFormDefinition(signUpForm);
		
		//after password
		PasswordField password = new PasswordField("Password");
		password.setDefaultValue("Enter password");
		
		signUpForm.add(password);
		printForm(signUpForm);
		
		System.out.println();
		password.setValue("12345!");
		printForm(signUpForm);
		System.out.println("password valid: " + password.isValid());  
		// try password without "!"
		
		// after number field
		Field size = new NumberField("Company Size");		// explain how you can use Field for numberfield
		size.setDefaultValue("Number of Employees");
		signUpForm.add(size);
		
		size.setValue(25);  // try -1
		System.out.println("Size valid: " + size.isValid()); 
		printForm(signUpForm);
		
		//after phone field
		Field phone = new PhoneField("Phone");
		phone.setDefaultValue("Phone number");		
		signUpForm.add(phone);
		
		phone.setValue(1234567890L);		// no alphanumeric check, will crash
		System.out.println("phone valid: " + phone.isValid());  // implement isValid for Field class
													// try with phone with one digit less
 		printForm(signUpForm);
		
		// after percent field
		Field likely = new PercentField("Likely to buy");
		likely.setDefaultValue(99);		
		signUpForm.add(likely);
		
		//likely.setValue(50);	// run before setting value	
		System.out.println("likely valid: " + likely.isValid()); 
													
 		printForm(signUpForm);
 		
 		//after class is done
 		// implement resetform
 		resetForm(signUpForm);
 		printForm(signUpForm);
 		
 		//if you still hav time 
 		//implement checkForm to see if all fields are valid
	}

}
