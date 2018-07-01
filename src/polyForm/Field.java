package polyForm;

class Field {
	private String label = "Undefined";
	private Object value = "";
	private Object defaultValue = "undefined";
	
	Object getZeroValue() {			// do not implement - extra extra
		return "";
	}

	int labelDisplayLength = 20;
	int valueDisplayLength = 30;
	
	Field(String label) {
		this.label = label;
	}
	
	// after displayLabel
	static String nOf(String str, int nTimes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nTimes; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	void setValue(Object value) {
		this.value = value;
	}

	void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	String getLabel() {
		return label;
	}

	// because these fields are for forms, getValue will return string
	String getValue() {
		String strValue = value.toString();
		if (strValue.equals(""))
			return getDefaultValue();
		else
			return value.toString();
	}

	String getDefaultValue() {
		return defaultValue.toString();
	}

	String getType() {
		return "Field";
	}

	String getFieldDefinition() {
		return getType() + "[" + "label=" + getLabel() + ", default_value=" + getDefaultValue() + ", value= "
				+ getValue() + "]";
	}
	// run driver at this point

	String displayLabel() {
		return label + Field.nOf(" ", labelDisplayLength - label.length()) + ": ";
	}
	// run driver at this point

	String displayValue() {
		// placeHolder - display depends on alignment
		return getValue();
	}
	
	// implement after phone example
	// phone will not compile
	boolean isValid() {
		return false;
	}
}

/// talk about person and object
/// Person p1
/// Object o1
/// o1 = p1;        works because I can do the same things I've been doing with o1 reference
/// p1 = o1;        does not work. Because object cannot do the things that person can do
/// p1 = (Person) o1	works, because o1 can actually be referring to a Person, if it doesn't
//                      it will fail at RunTime with class cast exception

// now say Person and Engineer
// Person walk & talk, Engineer do all that stuff plus design & test
// If you ask an Engineer to talk as a Person it can do that
// But if you ask a Person to design as an Engineer it depends if that Person is pointing to
// an engineer. So compiler doesn't allow that unless you tell it to do with casting.
// Engineer and Teacher 	does not work. Because none of them are given to do  
//                          all the things the other do 
// 



class TextField extends Field {
	TextField(String label) {
		super(label);
	}
	
	String getType() {
		return "TextField";
	}

	String displayValue() {
		return getValue() + Field.nOf("_", valueDisplayLength - getValue().length());
	}

	// isValid is after all classes
	boolean isValid() {
		return getValue().length() <= valueDisplayLength;
	}
}
/// run all text field

class EmailField extends TextField {
	EmailField(String label) {
		super(label);
	}
	
	String getType() {
		return "EmailField";
	}

	boolean isValid() {
		return getValue().contains("@") && getValue().contains(".");
	}
}
// run email field

class PasswordField extends TextField {
	PasswordField(String label) {
		super(label);
	}
	
	String getType() {
		return "PasswordField";
	}
	
	// we don't want to show actual password, so override displayValue
	String getValue() {
		if (super.getValue().equals(getDefaultValue()))
			return super.getValue();
		else
			return Field.nOf("*", super.getValue().length());
	}

	boolean isValid() {
		// don't use this getValue because it is masked
		return super.getValue().contains("!") || super.getValue().contains("$");
	}
}
// run password field

class NumberField extends Field {
	Object getZeroValue() {			// do not implement - extra extra
		return 0;
	}
	
	NumberField(String label) {
		super(label);
	}
	
	String getType() {
		return "NumberField";
	}

	String displayValue() {
		return Field.nOf("_", valueDisplayLength - getValue().length()) + getValue();
	}

	// has to be positive
	boolean isValid() {
		return getNumberValue() >= 0;
	}

	long getNumberValue() {
		return Long.parseLong(super.getValue());
	}
	// at this point add getNumber value to Field, it will return 0

}

class PhoneField extends NumberField {
	Object getZeroValue() {			// do not implement - extra extra
		return 1111111111L;
	}
	
	PhoneField(String label) {
		super(label);
	}
	
	String getType() {
		return "PhoneField";
	}

	String getValue() {
		String pNo = super.getValue();
		return pNo.substring(0, 3) + "-" + pNo.substring(3, 6) + "-" + pNo.substring(6);
	}

	boolean isValid() {
		return super.isValid() && getValue().length() == 12;
	}
}

// extra
class PercentField extends NumberField {
	PercentField(String label) {
		super(label);
	}
	
	String getType() {
		return "PercentField";
	}

	String getValue() {
		return super.getValue() + "%";
	}

	boolean isValid() {
		return super.isValid() && getNumberValue() <= 100;
	}
}
