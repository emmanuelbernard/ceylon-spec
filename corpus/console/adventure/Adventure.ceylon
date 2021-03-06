doc "A text-based adventure game."
void adventure() {

	doc "The player's current location."
	shared variable Location currentLocation := World.initialLocation; 

    doc "A special location for things which 
         the player has picked up." 
	shared Location backpack = Location("your backpack", "Contains the things you have picked up.");
		
	shared variable Natural life := 100;
	
    Random<Natural> rand = RandomNatural(1..10);
	
	shared Float backpackWeight {
		return Math.sum( backpack.things[].weight )
	}
	
	shared void display(String message) {
		process.writeLine(message);
	}
	
	shared Natural random() {
		return rand.next()
	}
	
	void go(String where) {
		//TODO!
	}
	
	void get(String name) {
		Thing? thing = currentLocation.thing(name);
		if (exists thing) {
			if (is Artifact thing) {
				thing.get(this);
			}
			else {
				display("You can't pick up a " name ".");
			}
		}
		else {
			display("You don't see a " name " here.");
		}
	}
	
	void drop(String name) {
		Thing? thing = backpack.thing(name);
		if (exists thing) {
			if (is Artifact thing) {
				thing.drop(this);
			}
			else {
				display("You can't drop a " name ".");
			}
		}
		else {
			display("You don't have a " name ".");
		}
	}
	
	void tell(String name, String where) {
		//TODO!
	}
	
	void kill(String name, String weaponName) {
		Thing? thing = currentLocation.thing(name);
		if (exists thing) {
			if (is Creature thing) {
				Thing? weapon = backpack.thing(name);
				if (exists weapon) {
					if (is Artifact weapon) {
						thing.kill(this, weapon);
					}
					else {
						display("You can't fight with a " weaponName ".");
					}
				}
				else {
					display("You don't have a " weaponName ".");
				}		
			}
			else {
				display("You can't kill a " name ".");
			}
		}
		else {
			display("You don't see a " name " here.");
		}
	}
	
	void use(String name) {
		Thing? thing = backpack.thing(name);
		if (exists thing) {
			if (is Artifact thing) {
				thing.use(this);
			}
			else {
				display("You can't use a " name ".");
			}
		}
		else {
			display("You don't have a " name" .");
		}
	}
	
	while (life>0) {
		String input = process.readLine();
		Iterator<String> tokens = input.tokens().iterator();
		try {
			String command = tokens.next();
			switch(command)
			case ("go") {
				go( tokens.next() );
			}
			case ("get") {
				get( tokens.next() );
			}
			case ("drop") {
				drop( tokens.next() );
			}
			case ("use") {
				use( tokens.next() );
			}
			case ("tell") {
				String name = tokens.next();
				String go = tokens.next();
				String where = tokens.next();
				tell(name, where);
			}
			case ("kill") {
				String name = tokens.next();
				String with = tokens.next();
				String weaponName = tokens.next();
				kill(name, weaponName);
			}
			else {
				display("You don't know how to do that.");
			}
		}
		catch (ExhaustedIteratorException eie) {
			display("Give me a bit more information, please!");
		}
	}
	

}