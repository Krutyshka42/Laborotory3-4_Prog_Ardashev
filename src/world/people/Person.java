package world.people;

import addedexceptions.FruitRejectedException;
import world.Fruit;
import world.enums.Location;
import world.receivers.FruitPile;

public abstract class Person {
    private final String name;
    private Location location;

    protected Person(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public Location getLocation(){
        return location;
    }

    public void moveTo(Location newLocation){
        location =newLocation;
        System.out.println(name + " переместился в "+ newLocation);
    }

    public abstract void collect(Fruit fruit, FruitPile pile) throws FruitRejectedException;



}
