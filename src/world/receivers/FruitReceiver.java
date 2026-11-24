package world.receivers;

import world.Fruit;
import addedexceptions.FruitRejectedException;

public interface FruitReceiver {
    void add(Fruit fruit);

    boolean canAccept(Fruit fruit) throws FruitRejectedException;
}
