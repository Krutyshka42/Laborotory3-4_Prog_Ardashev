package world.receivers;

import world.Fruit;

public final class HomeStock extends FruitStorage {

    @Override
    public String getStorageType(){
        return "домашнее хранилище";
    }

    @Override
    public boolean canAccept(Fruit fruit){
        return fruit != null;

    }

    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != HomeStock.class){
            return false;
        }
        HomeStock anotherHomeStock = (HomeStock) another;
        return fruitsContent().equals(anotherHomeStock.fruitsContent());

    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(fruitsContent());
    }

    @Override
    public String toString(){
        return "Домашнее хранилище с запасами: " + fruitsContent();
    }


}
