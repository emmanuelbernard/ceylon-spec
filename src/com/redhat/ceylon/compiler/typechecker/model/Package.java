package com.redhat.ceylon.compiler.typechecker.model;

import java.util.ArrayList;
import java.util.List;

public class Package implements Scope {
	List<String> name;
	Module module;
	List<Declaration> members = new ArrayList<Declaration>();

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	@Override
	public List<Declaration> getMembers() {
		return members;
	}

	@Override
	public Scope getContainer() {
		return null;
	}
	
	public String getNameAsString() {
	    StringBuilder sb = new StringBuilder();
	    for (int i=0; i<name.size(); i++) {
	        sb.append(name.get(i));
	        if (i<name.size()-1) sb.append('.');
	    }
	    return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Package[" + getNameAsString() + "]";
	}
	
	@Override
	public List<String> getQualifiedName() {
	    return getName();
	}
	
}