package problema1.model.services;

import problema1.model.entities.CarRental;
import problema1.model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private double pricePerHour;
    private double pricePerDay;
    private TaxService texService;


    public RentalService(double pricePerHour, double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.texService = taxService;
    }

    public void processesInvoice(CarRental carRental){
        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60;
        double basicPayment;

        if(hours <= 12){
            basicPayment = pricePerHour * Math.ceil(hours);
        }else {
            basicPayment = pricePerDay * Math.ceil(hours / 24.0);
        }

        double tax = texService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
