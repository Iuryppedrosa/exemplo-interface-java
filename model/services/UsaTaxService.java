package problema1.model.services;

public class UsaTaxService  implements TaxService{
    public double tax(double amount){
        if(amount <= 500){
            return  amount * 0.1;
        }else {
            return amount * 0.05;
        }
    }
}
