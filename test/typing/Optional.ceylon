class Optional() {
    
    class X() {}
    class Y() {}
    X? x = X();
    X? y = null;
    X? z = x;
    X? w = y;
    
    void xx(X x) {}
    
    if (exists x) {
        xx(x);
    }
    
    if (exists X xxx = x) {
        xx(xxx);
    }
    
    if (exists @error Y xxx = x) {}
    
    local sx = { X() };
    local sxn = { X(), null };
    local sy = { Y() };
    local syn = { Y(), null };
    local sxy = { X(), Y() };
    variable local ss := { X(), Y(), null };
    ss:=sx;
    ss:=sy;
    ss:=sxy;
    ss:=sxn;
    ss:=syn;
    local bs = { X(), "foo" };
    @error ss:=bs;
    
    class Foo<T>() {
        shared T? optional = null;
        shared T definite { throw; }
        shared T[]? optionalList { throw; }
        shared T[] list { throw; }
    }
    
    String? optional = Foo<String>().optional;
    String definite = Foo<String>().definite;
    String[]? optionalList = Foo<String>().optionalList;
    String[] list = Foo<String>().list;
    
    @error String sssss = list.first;
    @error Natural nnnn = list.lastIndex;
    
    if (nonempty list) {
        String s = list.first;
        Natural li = list.lastIndex;
    }
    
    if (nonempty Sequence<String> strings = optionalList) {
        String s = strings.first;
        Natural li = strings.lastIndex;
    }
    
    Sequence<String> stuff = { "foo" };
    Character[][] chars = stuff[].characters;

    String[] nostuff = {};
    Character[][] nochars = nostuff[].characters;
    
    String? maybestuff = null;
    Character[]? maybechars = maybestuff?.characters;
    
    Character[] somechars = {};
    Natural scs = somechars.size;
    local sci = somechars.iterator;
    
    @type["Nothing|String|Integer|Sequence<Object>"] String? | String | String? | Integer | Sequence<Object> foobar1 = -1;
    @type["Nothing|Empty|Sequence<String>|Integer"] String[]? | String[] | Sequence<String> | Integer foobar2 = 1.integer;
    
    @type["Sequence<Nothing|String|Integer|Sequence<Object>|Empty>"] local xyz = { foobar1, foobar2 };
    
    //TODO: I think the type parameter X does
    //      not hide the X defined above - it
    //      should!
    /*shared void entries<X>(X... sequence) 
            given X satisfies Equality {
        if (nonempty sequence) {
            entries<X>(sequence.clone);
        }
    }*/
    
}