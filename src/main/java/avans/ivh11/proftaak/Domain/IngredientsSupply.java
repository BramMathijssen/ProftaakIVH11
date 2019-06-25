package avans.ivh11.proftaak.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class IngredientsSupply {

    @Id
    @GeneratedValue
    private long id;
    private String ingredientName;
    private String ingredientType;
    private int pieces;
    private int amountMl;
    private int grams;

    //No args constructor set on private so new IngredientSupplies can only be made trough the build() method
    private IngredientsSupply(){

    }

    public static class Builder{

        private long id;

        private String ingredientName;
        private String ingredientType;
        private int pieces;
        private int amountMl;
        private int grams;


        public Builder() {

        }

        public Builder ingredientName(String ingredientName){
            this.ingredientName = ingredientName;
            return this;
        }

        public Builder ingredientType(String ingredientType){
            this.ingredientType = ingredientType;
            return this;
        }

        public Builder pieces(int pieces){
            this.pieces = pieces;
            return this;
        }

        public Builder amountMl(int amountMl){
            this.amountMl = amountMl;
            return this;
        }

        public Builder grams(int grams){
            this.grams = grams;
            return this;
        }

        public IngredientsSupply build(){
            IngredientsSupply supply = new IngredientsSupply();

            supply.id = this.id;
            supply.ingredientName = this.ingredientName;
            supply.ingredientType = this.ingredientType;
            supply.pieces = this.pieces;
            supply.amountMl = this.amountMl;
            supply.grams = this.grams;
            return supply;
        }

    }


}

