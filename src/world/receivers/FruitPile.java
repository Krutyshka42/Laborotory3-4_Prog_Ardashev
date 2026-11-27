package world.receivers;

import world.Fruit;
import world.enums.FruitKind;
import world.enums.Location;

public final class FruitPile extends FruitStorage {
    private final Location location;
    private final FruitKind kind;
    public FruitPile(Location location, FruitKind kind,String creator){
        this.location = location;
        this.kind = kind;
        System.out.println(creator+" создал фруктовую кучу для фрукта "+ kind + " на участке " + location);
    }
    @Override
    public String getStorageType(){

        return "фруктовая куча";
    }

    @Override
    public boolean canAccept(Fruit fruit){
        return fruit!=null && kind==fruit.kind();
    }

    public Location getLocation(){
        return location;
    }

    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != FruitPile.class){
            return false;
        }
        FruitPile anotherFruitPile = (FruitPile) another;
        return fruitsContent().equals(anotherFruitPile.fruitsContent()) && location== anotherFruitPile.location && kind == anotherFruitPile.kind ;

    }

    @Override
    public int hashCode() {

        return java.util.Objects.hash(fruitsContent(),location, kind);
    }

    @Override
    public String toString(){
        return "Фруктовая куча, для фруктов " + kind + ", лежащая в " + location;
    }
}
