package world;

import java.util.ArrayList;
import java.util.Random;
import addedexceptions.FruitRejectedException;
import world.enums.Location;
import world.enums.NextSeason;
import world.receivers.Bag;
import world.receivers.FruitPile;
import world.receivers.HomeStock;

public final class Gatherer {

    private Bag bag;
    private final String name;
    private Location location;
    private final ArrayList <Fruit> handLoad;

    public Gatherer(String name, Location location){
        this.name = name;
        this.handLoad = new ArrayList <Fruit> ();
        this.location = location;
    }

    public void moveTo(Location newLocation){
        location = newLocation;
        System.out.println(name+ " переместился в " + newLocation);
    }

    public void collect(Fruit fruit, FruitPile pile) throws FruitRejectedException {
        pile.add(fruit);
        System.out.println(name + " собрал " + fruit + " и положил в " + pile);
    }

    public void takeByHand(FruitPile pile){
        Fruit fruit = pile.take(name);

        if (fruit!=null){
            handLoad.add(fruit);
        }

    }

    public void pickUpBag(Bag bag){
        this.bag = bag;
    }

    public void loadBagFrom(FruitPile pile) throws FruitRejectedException{

        while (!pile.isEmpty()) {
            Fruit fruit = pile.peek(name);
            bag.add(fruit);
            pile.take(name);
            System.out.println(name + " положил " + fruit + " в мешок");
        }
    }

    public void unloadAtHome(HomeStock stock) throws FruitRejectedException{

        boolean flag = false;
        while (!handLoad.isEmpty()){
            stock.add(handLoad.get(handLoad.size()-1));
            handLoad.remove(handLoad.size()-1);
            flag = true;
        }
        if (flag) {
            System.out.println(name + " выложил всё из рук в домашнее хранилище");
        }

        flag = false;
        while (bag != null && !bag.isEmpty()){
            Fruit fruit = bag.peek(name);
            stock.add(fruit);
            bag.take(name);
            flag = true;
        }
        if (flag){
            System.out.println(name + " разгрузил мешок в домашнее хранилище");
        }
    }

    public void dropFromHand(double chance){
        if (Math.random()< chance) {
            handLoad.clear();
            System.out.println(name + " выронил все фрукты из рук");
        }
        else {
            System.out.println(name + " чуть не выронил все фрукты из рук");
        }
    }

    public NextSeason detectNextSeason(double rainyChance){
        if (Math.random() < rainyChance){
            System.out.println(name +" предстояло теперь много работы со сбором плодов и переноской их домой, так как " +name + " решил запастись виноградом и лимонами на приближавшееся дождливое время года.");
            return NextSeason.RAINY;
        }
        else{
            System.out.println(name +" предстояло теперь немного работы со сбором плодов и переноской их домой, так как " +name + " решил немного запастись виноградом и лимонами на приближавшееся солнечное время года.");
            return NextSeason.SUNNY;
        }

    }

    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != Gatherer.class){
            return false;
        }
        Gatherer anotherGatherer = (Gatherer) another;
        return java.util.Objects.equals(name, anotherGatherer.name) && location == anotherGatherer.location && handLoad.equals(anotherGatherer.handLoad) && java.util.Objects.equals(bag, anotherGatherer.bag);

    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, location, handLoad, bag);
    }

    @Override
    public String toString(){
        return name;
    }


}



