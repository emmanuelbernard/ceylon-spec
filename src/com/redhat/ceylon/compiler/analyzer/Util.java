package com.redhat.ceylon.compiler.analyzer;

import com.redhat.ceylon.compiler.model.Declaration;
import com.redhat.ceylon.compiler.model.GenericType;
import com.redhat.ceylon.compiler.model.Import;
import com.redhat.ceylon.compiler.model.Package;
import com.redhat.ceylon.compiler.model.Scope;
import com.redhat.ceylon.compiler.model.Structure;
import com.redhat.ceylon.compiler.model.Typed;
import com.redhat.ceylon.compiler.model.Unit;
import com.redhat.ceylon.compiler.tree.Tree;

class Util {

    /**
     * Resolve the type against the scope in which it
     * occurs. Imports are taken into account.
     */
    static GenericType getDeclaration(Tree.Type that) {
        return (GenericType) getDeclaration(that.getScope(), that.getUnit(), 
                    that.getIdentifier());
    }
    
    /**
     * Resolve the type against the given scope. Imports 
     * are ignored.
     */
    static GenericType getDeclaration(Scope scope, Tree.Type that) {
        return (GenericType) getDeclaration(scope, null, 
                    that.getIdentifier());
    }

    /**
     * Resolve the type against the scope in which it
     * occurs. Imports are taken into account.
     */
    static Typed getDeclaration(Tree.Member that) {
        return (Typed) getDeclaration(that.getScope(), that.getUnit(), 
                    that.getIdentifier());
    }

    /**
     * Resolve the member against the given scope. Imports 
     * are ignored.
     */
    static Typed getDeclaration(Scope scope, Tree.Member that) {
        return (Typed) getDeclaration(scope, null, 
                    that.getIdentifier());
    }
    
    /**
     * Resolve the declaration against the given package.
     */
    static Declaration getDeclaration(Package pkg, Tree.Identifier id) {
        return getDeclaration(pkg, null, id);
    }

    private static Declaration getDeclaration(Scope scope, Unit unit, Tree.Identifier id) {
        return getDeclaration(scope, unit, id.getText());
    }

    private static Declaration getDeclaration(Scope scope, Unit unit, String name) {
        while (scope!=null) {
            //imports hide declarations in same package
            //but not declarations in local scopes
            if (scope instanceof Package && unit!=null) {
                Declaration d = getImportedDeclaration(unit, name);
                if (d!=null) {
                    return d;
                }
            }
            Declaration d = getLocalDeclaration(scope, name);
            if (d!=null) {
                return d;
            }
            scope = scope.getContainer();
        }
        throw new RuntimeException("Member not found: " + name);
    }
    
    /**
     * Search only directly inside the given scope,
     * without considering containing scopes or 
     * imports. 
     */
    private static Declaration getLocalDeclaration(Scope scope, String name) {
        for ( Structure s: scope.getMembers() ) {
            if (s instanceof Declaration) {
                Declaration d = (Declaration) s;
                if (d.getName().equals(name)) {
                    return d;
                }
            }
        }
        return null;
    }
    
    /**
     * Search the imports of a compilation unit 
     * for the declaration. 
     */
    static Declaration getImportedDeclaration(Unit u, String name) {
        for (Import i: u.getImports()) {
            Declaration d = i.getDeclaration();
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }
    
}
