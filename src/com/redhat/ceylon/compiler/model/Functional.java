package com.redhat.ceylon.compiler.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a construct which may have a 
 * list of parameters or more than one:
 * a formal parameter or method. We don't
 * include classes here because they can 
 * have only one list of parameters.
 * 
 * TODO: do we need a type to abstract
 *       classes, parameters, and methods?
 * 
 * @author Gavin King
 *
 */
public class Functional extends Typed {
	
	List<List<Parameter>> parameters = new ArrayList<List<Parameter>>();
	
	public List<List<Parameter>> parameters() {
		return parameters;
	}
}