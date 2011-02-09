import com.redhat.ceylon.compiler.model.Module;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.redhat.ceylon.compiler.model.Package;

import static com.redhat.ceylon.compiler.util.PrintUtil.importPathToString;

/**
 * Keep compiler contextual information like the package stack and the current module
 *
 * @author Emmanuel Bernard <emmanuel@hibernate.org>
 */
class Context {
    private LinkedList<Package> packageStack = new LinkedList<Package>();
    private Module module;

    Context() {
        final Package pkg = new Package();
        pkg.setName( new ArrayList<String>(0) );
        packageStack.add(pkg);
    }

    public Module getModule() {
        return module;
    }

    public void push(String path) {
        createPackageAndAddToModule(path);
    }

    private void createPackageAndAddToModule(String path) {
        Package pkg = new Package();
        final Package lastPkg = packageStack.peekLast();
        List<String> parentName = lastPkg.getName();
        final ArrayList<String> name = new ArrayList<String>(parentName.size() + 1);
        name.addAll( parentName );
        name.add(path);
        pkg.setName(name);
        if (module != null) {
            module.getPackages().add(pkg);
            pkg.setModule(module);
        }
        packageStack.addLast(pkg);
    }

    public void pop() {
        removeLastPackageAndModuleIfNecessary();
    }

    private void removeLastPackageAndModuleIfNecessary() {
        packageStack.pollLast();
        final boolean moveAboveModuleLevel = module != null && module.getName().size() > packageStack.size();
        if (moveAboveModuleLevel) {
            module = null;
        }
    }

    public void defineModule() {
        if ( module == null ) {
            module = new Module();
            final List<String> moduleName = packageStack.peekLast().getName();
            if (moduleName.size() == 0) {
                throw new RuntimeException("Module cannot be top level");
            }
            module.setName(moduleName);
        }
        else {
            StringBuilder error = new StringBuilder("Found two modules within the same hierarchy: '");
            error.append( importPathToString( module.getName() ) )
                .append( "' and '" )
                .append( importPathToString( packageStack.peekLast().getName() ) )
                .append("'");
            throw new RuntimeException( error.toString() );
        }
    }

    public Package getPackage() {
        return packageStack.peekLast();
    }
}
