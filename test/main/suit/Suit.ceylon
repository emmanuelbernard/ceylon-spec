abstract class Suit(String name) 
    of hearts | diamonds | clubs | spades
    extends Case(name) {}

@error class Broken() of BrokenCase {} 