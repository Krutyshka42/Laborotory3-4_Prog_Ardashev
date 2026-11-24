package world.receivers;

import addedexceptions.FruitRejectedException;
import world.Fruit;

import java.util.ArrayList;
import java.util.List;

public abstract class FruitStorage implements FruitReceiver {
    protected abstract String getStorageType();
    private final ArrayList <Fruit> fruits =  new ArrayList <Fruit> ();

    @Override
    public final void add(Fruit fruit) throws FruitRejectedException{
        if (fruit != null && canAccept(fruit)){
            fruits.add(fruit);
        }
        else{
            throw new FruitRejectedException("Хранилище " + getStorageType() + " не может принять фрукт: " + fruit);
        }
    }

    public boolean isEmpty(){
        return fruits.isEmpty();
    }

    public long totalMassGrams(){
        long currentMass = 0;
        for (Fruit fruit : fruits){
            currentMass+=fruit.massGrams();
        }
        return currentMass;
    }

    public int heaviestFruitMassGrams(){
        int currentMaxMass = 0;
        for (Fruit fruit : fruits){
            currentMaxMass = Math.max(currentMaxMass,fruit.massGrams());
        }
        return currentMaxMass;
    }

    public Fruit peek(String peeker){
        if (isEmpty()){
            System.out.println(peeker+" обнаружил, что хранилище " + getStorageType() + " пусто");
            return null;
        }
        else{
            System.out.println(peeker+" обнаружил, что в хранилище " + getStorageType() + " осталось фруктов: " + fruits.size());
            return fruits.get(fruits.size()-1);
        }
    }

    public Fruit take(String peeker){
        if (isEmpty()){
            System.out.println(peeker+" попытался взять фрукт из пустого хранилища " + getStorageType());
            return null;
        }
        else{
            Fruit fruit = fruits.remove(fruits.size()-1);
            System.out.println(peeker+" забрал из хранилища " + getStorageType() + " один фрукт " + fruit.kind());
            return fruit;
        }
    }

    protected List <Fruit> fruitsContent(){
        return List.copyOf(fruits);
    }
    protected void clearFruits(){
        fruits.clear();
    }


}
