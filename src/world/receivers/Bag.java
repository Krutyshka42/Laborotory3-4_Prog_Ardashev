package world.receivers;

import world.Fruit;

public final class Bag extends FruitStorage {
    private final long capacityGrams;
    private boolean isTorn;
    private final String owner;

    public Bag(long capacityGrams, int heaviestFruit, String owner) throws IllegalArgumentException{
        if (owner == null) {
            owner = "Робинзон";
        }
        if (capacityGrams >= heaviestFruit){
            this.capacityGrams = capacityGrams;
            this.owner = owner;
            this.isTorn = false;
            System.out.println(owner + " взял новый мешок вместительностью " + capacityGrams +" грамм");

        }
        else{

            throw new IllegalArgumentException(owner + " не может взять такой мешок вместительностью: "+ capacityGrams +" грамм, так как он не сможет вместить любой фрукт");
        }
        
    }

    @Override
    public String getStorageType(){
        return "мешок";
    }

    @Override
    public boolean canAccept(Fruit fruit){
        return fruit != null && (capacityGrams>= totalMassGrams()+fruit.massGrams()) && !isTorn;
    }

    public void tear(double chance){
        if (Math.random()<chance){
        isTorn = true;
        clearFruits();
        System.out.println("Мешок порвался, все фрукты высыпались и потеряны");
        }
    }

    public void sewUp(){
        isTorn = false;
        System.out.println(owner + " подшил свой мешок");
    }

    public boolean getIsTorn(){
        return isTorn;
    }

    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != Bag.class){
            return false;
        }
        Bag anotherBag = (Bag) another;
        return fruitsContent().equals(anotherBag.fruitsContent()) && owner.equals(anotherBag.owner) && capacityGrams == anotherBag.capacityGrams && isTorn == anotherBag.isTorn;

    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(fruitsContent(),owner, capacityGrams, isTorn);
    }

    @Override
    public String toString(){
        return "Мешок, принадлежащий " +owner+ ", с запасами: " + fruitsContent();
    }


}
