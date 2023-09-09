package problema1.application;

import problema1.model.entities.CarRental;
import problema1.model.entities.Vehicle;
import problema1.model.services.BrazilTaxService;
import problema1.model.services.RentalService;
import problema1.model.services.UsaTaxService;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Dados aluguel: ");
        System.out.print("Modelo carro: ");
        String carModel = sc.nextLine(); //le tudo ate a quebra de linha

        System.out.println("Retirada: ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);


        System.out.println("Entrega: ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com preco por hora: ");
        double pricePerHour = sc.nextDouble();

        System.out.print("Entre com preco por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new UsaTaxService());

        rentalService.processesInvoice(cr);

        System.out.println("FATURA: ");
        System.out.println("Pagamento basico: " + cr.getInvoice().getBasicPayment());
        System.out.println("Imposto: " + cr.getInvoice().getTax());
        System.out.println("Pagamento total: " + cr.getInvoice().getTotalPayment());
    }
}
