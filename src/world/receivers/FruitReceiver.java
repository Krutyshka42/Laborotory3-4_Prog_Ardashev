package world.receivers;

import world.Fruit;
import addedexceptions.FruitRejectedException;

public interface FruitReceiver {
    void add(Fruit fruit)throws FruitRejectedException;

    boolean canAccept(Fruit fruit) ;
}
