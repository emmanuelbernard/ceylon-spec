package com.redhat.ceylon.compiler.model;

import java.util.ArrayList;
import java.util.List;

public class Module {
	
	List<String> name;
	List<Package> packages = new ArrayList<Package>();
	List<Module> dependencies = new ArrayList<Module>();
	
	public List<String> getName() {
		return name;
	}
	
	public void setName(List<String> name) {
		this.name = name;
	}
	
	public List<Package> getPackages() {
		return packages;
	}
	
	public List<Module> getDependencies() {
		return dependencies;
	}
	
	public List<Package> getAllPackages() {
		List<Package> list = new ArrayList<Package>();
		list.addAll(packages);
		for (Module m: dependencies) {
			list.addAll(m.getPackages());
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "Module[" + name + "]";
	}
	
}
