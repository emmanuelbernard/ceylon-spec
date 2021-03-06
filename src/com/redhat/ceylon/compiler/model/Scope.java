package com.redhat.ceylon.compiler.model;

import java.util.List;

/**
 * Represents a namespace which contains named
 * members: a method, attribute, class, interface, 
 * package, or module.
 * 
 * @author Gavin King
 *
 */
public interface Scope {
	List<Structure> getMembers();
	Scope getContainer();
}
