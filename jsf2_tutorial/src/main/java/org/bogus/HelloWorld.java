package org.bogus;
 
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
 
@Named //assigns this bean the name of "helloWorld"...its "de-caplitalized" class name
@SessionScoped  //creates one bean for each user session.
public class HelloWorld implements Serializable {

	private final String text = "Hello World, Romildo!";
 
	public String getText() {
		return text;
	}
 
	private static final long serialVersionUID = 1L;
}