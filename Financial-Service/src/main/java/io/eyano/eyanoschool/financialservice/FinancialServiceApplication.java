package io.eyano.eyanoschool.financialservice;

import io.eyano.eyanoschool.financialservice.dao.CurrencyRepository;
import io.eyano.eyanoschool.financialservice.dao.PaymentSystemRepository;
import io.eyano.eyanoschool.financialservice.entities.Currency;
import io.eyano.eyanoschool.financialservice.entities.PaymentSystem;
import io.eyano.eyanoschool.financialservice.enums.PaymentMethode;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class FinancialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialServiceApplication.class, args);
	}

	@Bean
	public ModelMapper metodoQueCriaUmModelMapper(){
		return new ModelMapper();

	}
	@Bean
	@Profile("!Test")
	CommandLineRunner runner(
			CurrencyRepository currencyRepository,
			PaymentSystemRepository paymentSystemRepository) {
		return args -> {
			Currency currency = Currency.builder()
					.remove(false)
					.designation("Euro")
					.symbol("€")
					.build();
			currencyRepository.save(currency);
			Currency currency2 = Currency.builder()
					.remove(false)
					.designation("Dollar")
					.symbol("$")
					.build();
			currencyRepository.save(currency2);
			Currency currency3 = Currency.builder()
					.remove(false)
					.designation("Real")
					.symbol("R$")
					.build();
			currencyRepository.save(currency3);

			paymentSystemRepository.save(
					PaymentSystem.builder()
							.isRemove(false)
							.designation("Mobile Money")
							.institutionName("Vodacom")
							.paymentDate(LocalDate.now())
							.paymentMethod(PaymentMethode.MOBILE_MONEY)
							.clientPhoneNumber("0999999999")
							.paid(true)
							.clientName("Pascal Tshingila")
							.accountNumberOrPhoneNumber("0999999999")
							.transactionNumber("123456789")
							.build()
			);
			paymentSystemRepository.save(
					PaymentSystem.builder()
							.isRemove(false)
							.designation("Bank Transfer")
							.institutionName("Standard Bank")
							.paymentDate(LocalDate.now())
							.paymentMethod(PaymentMethode.BANK_CARDS)
							.clientPhoneNumber("0999999999")
							.paid(true)
							.clientName("Pascal Tshingila")
							.accountNumberOrPhoneNumber("0999999999")
							.transactionNumber("123456789")
							.build()
			);
			paymentSystemRepository.save(
					PaymentSystem.builder()
							.isRemove(false)
							.designation("Cash")
							.institutionName("Standard Bank")
							.paymentDate(LocalDate.now())
							.paymentMethod(PaymentMethode.CASH)
							.clientPhoneNumber("0999999999")
							.paid(true)
							.clientName("Pascal Tshingila")
							.accountNumberOrPhoneNumber("0999999999")
							.transactionNumber("123456789")
							.build()
			);
			System.out.println("Finance Service is running...");
		};
	}

}
