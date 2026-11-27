package world.people;

import java.util.ArrayList;
import addedexceptions.FruitRejectedException;
import world.Fruit;
import world.enums.Location;
import world.enums.NextSeason;
import world.receivers.Bag;
import world.receivers.FruitPile;
import world.receivers.HomeStock;

public final class Gatherer extends Person{
    private final HandLoad handLoad = new HandLoad();
    private Bag bag;
    private final class HandLoad {
        private final ArrayList<Fruit> fruits = new ArrayList<>();

        private void takeFrom(FruitPile pile){
            Fruit fruit = pile.take(getName());

            if (java.util.Objects.nonNull(fruit)){  //изменение!
                fruits.add(fruit);
            }
        }

        private void unloadTo(HomeStock stock) throws FruitRejectedException{
            if (fruits.isEmpty()){
                return;
            }

            while (!fruits.isEmpty()){

                int lastInd = fruits.size()-1;
                stock.add(fruits.get(lastInd));
                fruits.remove(lastInd);

            }

            System.out.println(getName() + " выложил всё из рук в домашнее хранилище");

        }

        private void clear(){
            fruits.clear();
        }
    }

    public Gatherer(String name, Location location){
        super(name,location);
    }


    @Override
    public void collect(Fruit fruit, FruitPile pile) throws FruitRejectedException {
        pile.add(fruit);
        System.out.println(getName() + " собрал " + fruit + " и положил в " + pile);
    }

    public void takeByHand(FruitPile pile){
        handLoad.takeFrom(pile);

    }

    public void pickUpBag(Bag bag){
        this.bag = bag;
    }

    public void loadBagFrom(FruitPile pile) throws FruitRejectedException{

        while (!pile.isEmpty()) {
            Fruit fruit = pile.peek(getName());
            bag.add(fruit);
            pile.take(getName());
            System.out.println(getName() + " положил " + fruit + " в мешок");
        }
    }

    public void unloadAtHome(HomeStock stock) throws FruitRejectedException{

        handLoad.unloadTo(stock);

        boolean flag = false;
        while (bag != null && !bag.isEmpty()){
            Fruit fruit = bag.peek(getName());
            stock.add(fruit);
            bag.take(getName());
            flag = true;
        }
        if (flag){
            System.out.println(getName() + " разгрузил мешок в домашнее хранилище");
        }
    }

    public void dropFromHand(double chance){
        if (Math.random()< chance) {
            handLoad.clear();
            System.out.println(getName() + " выронил все фрукты из рук");
        }
        else {
            System.out.println(getName() + " чуть не выронил все фрукты из рук");
        }
    }

    public NextSeason detectNextSeason(double rainyChance){
        if (Math.random() < rainyChance){
            System.out.println(getName() +" предстояло теперь много работы со сбором плодов и переноской их домой, так как " +getName() + " решил запастись виноградом и лимонами на приближавшееся дождливое время года.");
            return NextSeason.RAINY;
        }
        else{
            System.out.println(getName()+" предстояло теперь немного работы со сбором плодов и переноской их домой, так как " +getName() + " решил немного запастись виноградом и лимонами на приближавшееся солнечное время года.");
            return NextSeason.SUNNY;
        }

    }

    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != Gatherer.class){
            return false;
        }
        Gatherer anotherGatherer = (Gatherer) another;
        return java.util.Objects.equals(getName(), anotherGatherer.getName()) && getLocation() == anotherGatherer.getLocation() && handLoad.fruits.equals(anotherGatherer.handLoad.fruits) && java.util.Objects.equals(bag, anotherGatherer.bag);

    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getName(), getLocation(), handLoad.fruits, bag);
    }

    @Override
    public String toString(){
        return getName();
    }


}



