package world;

import world.enums.FruitKind;

public record Fruit(FruitKind kind, int massGrams) {
    @Override
    public boolean equals(Object another){
        if (another == null || another.getClass() != Fruit.class){
            return false;
        }
        Fruit anotherFruit = (Fruit) another;
        return this.kind == anotherFruit.kind() && this.massGrams == anotherFruit.massGrams();

    }
    @Override
    public int hashCode() {

        return java.util.Objects.hash(kind,massGrams);
    }

    @Override
    public String toString() {
        return  this.kind + " массой " + this.massGrams + " грамм";
    }


}
