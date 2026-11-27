import java.util.List;
import java.util.Random;

import addedexceptions.FruitRejectedException;
import world.*;
import world.enums.*;
import world.people.Gatherer;
import world.receivers.Bag;
import world.receivers.FruitPile;
import world.receivers.HomeStock;

public  class Main {

    public static void main(String[] args){
        Random random = new Random();

        Gatherer robinson = new Gatherer("Робинзон", Location.HOME);
        HomeStock stock = new HomeStock();

        NextSeason season = robinson.detectNextSeason(0.8);
        int largePileSize;
        int smallPileSize;
        switch (season){
            case RAINY:
                largePileSize = 7;
                smallPileSize = 4;
                break;
            case SUNNY:
                largePileSize = 5;
                smallPileSize = 3;
                break;
            default:
                largePileSize = 7;
                smallPileSize = 4;
        }

        robinson.moveTo(Location.FIRST_SITE);
        FruitPile largePile = new FruitPile(Location.FIRST_SITE,FruitKind.GRAPES,robinson.toString());
        try{
            for (int i=0; i< largePileSize; i++){
                Fruit fruit = new Fruit(FruitKind.GRAPES, random.nextInt(80,201));
                robinson.collect(fruit,largePile);
            }
        } catch (FruitRejectedException e) {
            System.out.println(robinson + " больше не может собирать фрукты в эту кучу. " + e.getMessage());
        }


        robinson.moveTo(Location.SECOND_SITE);
        FruitPile firstSmallPile = new FruitPile(Location.SECOND_SITE,FruitKind.GRAPES,robinson.toString());
        try{
            for (int i=0; i< smallPileSize; i++){
                Fruit fruit = new Fruit(FruitKind.GRAPES, random.nextInt(80,201));
                robinson.collect(fruit,firstSmallPile);
            }
        } catch (FruitRejectedException e) {
            System.out.println(robinson + " больше не может собирать фрукты в эту кучу. " + e.getMessage());
        }

        robinson.moveTo(Location.THIRD_SITE);
        FruitPile secondSmallPile = new FruitPile(Location.THIRD_SITE,FruitKind.LEMON,robinson.toString());
        try{
            for (int i=0; i< smallPileSize; i++){
                Fruit fruit = new Fruit(FruitKind.LEMON, random.nextInt(80,201));
                robinson.collect(fruit,secondSmallPile);
            }
        } catch (FruitRejectedException e) {
            System.out.println(robinson + " больше не может собирать фрукты в эту кучу. " + e.getMessage());
        }

        robinson.takeByHand(secondSmallPile);
        robinson.dropFromHand(0.5);
        robinson.moveTo(Location.SECOND_SITE);
        robinson.takeByHand(firstSmallPile);
        robinson.dropFromHand(0.5);
        robinson.moveTo(Location.HOME);
        try {
            robinson.unloadAtHome(stock);
        } catch( FruitRejectedException e){
            System.out.println(robinson + " не смог разгрузить фрукты дома");
        }


        int heaviestFruit = Math.max(largePile.heaviestFruitMassGrams(),Math.max(firstSmallPile.heaviestFruitMassGrams(), secondSmallPile.heaviestFruitMassGrams()));
        int capacityGrams = random.nextInt(100,501);
        Bag bag;

        try {
            bag = new Bag(capacityGrams, heaviestFruit, robinson.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            bag = new Bag(heaviestFruit+ 1, heaviestFruit, robinson.toString());
        }

        robinson.pickUpBag(bag);

        List<FruitPile> piles = List.of(largePile, firstSmallPile, secondSmallPile);
        for (FruitPile pile : piles){
            while (!pile.isEmpty()){
                robinson.moveTo(pile.getLocation());

                try{
                    robinson.loadBagFrom(pile);
                } catch (FruitRejectedException e){
                    System.out.println(e.getMessage());
                }
                bag.tear(0.5);
                robinson.moveTo(Location.HOME);
                try {
                    robinson.unloadAtHome(stock);
                } catch (FruitRejectedException e){
                    System.out.println(robinson + " не смог разгрузить фрукты дома");
                }
                if (bag.getIsTorn()){
                    bag.sewUp();
                }
            }
        }

        System.out.println(robinson + " пополнил свои запасы: " + stock.totalMassGrams() + " грамм фруктов");

    }
}