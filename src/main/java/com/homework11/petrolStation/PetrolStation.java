package main.java.com.homework11.petrolStation;

public class PetrolStation implements FuelStation{
    private int amount;
    public PetrolStation(int amount) {
        this.amount = amount;
    }

    @Override
    public int doRefuel(int fuelNeed) {
        if(amount < fuelNeed){
            System.out.println("There is no petrol");
            return 0;
        }

        return amount - fuelNeed;
    }
}
