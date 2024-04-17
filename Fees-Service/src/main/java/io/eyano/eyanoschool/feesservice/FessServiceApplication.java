package io.eyano.eyanoschool.feesservice;

import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.ClassFees;
import io.eyano.eyanoschool.feesservice.entitiesExt.Currency;
import io.eyano.eyanoschool.feesservice.entitiesExt.PaymentSystem;
import io.eyano.eyanoschool.feesservice.entitiesExt.SchoolYear;
import io.eyano.eyanoschool.feesservice.entitiesService.FeesAllocationService;
import io.eyano.eyanoschool.feesservice.entitiesService.FeesService;
import io.eyano.eyanoschool.feesservice.entitiesService.PaymentService;
import io.eyano.eyanoschool.feesservice.entitiesService.SliceFeesService;
import io.eyano.eyanoschool.feesservice.mappers.TypeFeesMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

import static io.eyano.eyanoschool.feesservice.enums.PaymentMethode.*;

@SpringBootApplication
public class FessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FessServiceApplication.class, args);
	}

	@Bean
	public ModelMapper metodoQueCriaUmModelMapper(){
		return new ModelMapper();

	}

	@Bean
	@Profile("!Test")
	CommandLineRunner commandLineRunner(
			FeesAllocationService feesAllocationService,
			SliceFeesService sliceFeesService,
			FeesService feesService,
			PaymentService paymentService,
			TypeFeesMapper typeFeesMapper,
			TypeFeesRepository typeFeesRepository){

		return args -> {
			TypeFees typeFees = new TypeFees();
			typeFees.setDesignation("Inscription");
			typeFees.setRemove(false);

			TypeFees typeFees2 = new TypeFees();
			typeFees2.setDesignation("Scolaire");
			typeFees2.setRemove(false);

			TypeFees typeFees3 = new TypeFees();
			typeFees3.setDesignation("Etatique");
			typeFees3.setRemove(false);

			FeesDto feesDto = new FeesDto();
			feesDto.setDesignation("Frais d'inscription");
			feesDto.setAmount(100000);
			feesDto.setRemove(false);
			feesDto.setIdSchoolYear(2L);
			feesDto.setIdClassFess(3L);
			feesDto.setTypeFees(
					typeFeesMapper.entityFromDTO(
							typeFeesRepository.save(
									typeFees
							)
					)
			);
			feesDto.setClassFees(ClassFees.builder()
					.designation("6ème")
					.ability(30)
					.local("Salle 1")
					.cycle("Secondaire")
					.nameFirstnameFullProfessor("M. Jean")
					.idFullProfessor(1L)
					.build());
			feesDto.setSchoolYear(SchoolYear.builder()
					.designation("2021-2022")
					.startDate(LocalDate.of(2021,9,1))
					.endDAte(LocalDate.of(2022,7,1))
					.isCurrent(true)
					.build());
			SliceFeesDto sliceFees = new SliceFeesDto();
			sliceFees.setDate(LocalDate.of(2021,9,1));
			sliceFees.setDesignation("Tranche 1");
			sliceFees.setPercentage(30);
			sliceFees.setRemove(false);
			sliceFees.setEndDatePayment(LocalDate.of(2021,9,30));
			sliceFees.setDatePayment(LocalDate.of(2021,9,1));

			feesDto.setSliceFees(sliceFeesService.save(sliceFees));

			feesService.save(feesDto);


			FeesDto feesDto2 = new FeesDto();
			feesDto2.setDesignation("1ere trimestre");
			feesDto2.setAmount(234);
			feesDto2.setRemove(false);
			feesDto2.setIdSchoolYear(2L);
			feesDto2.setIdClassFess(3L);
			feesDto2.setTypeFees(
					typeFeesMapper.entityFromDTO(
							typeFeesRepository.save(
									typeFees2
							)
					)
			);
			feesDto2.setClassFees(ClassFees.builder()
					.designation("3ème")
					.ability(30)
					.local("Salle 3")
					.cycle("Secondaire")
					.nameFirstnameFullProfessor("M. Paul")
					.idFullProfessor(1L)
					.build());
			feesDto2.setSchoolYear(SchoolYear.builder()
					.designation("2021-2022")
					.startDate(LocalDate.of(2021,9,1))
					.endDAte(LocalDate.of(2022,7,1))
					.isCurrent(true)
					.build());
			SliceFeesDto sliceFees2 = new SliceFeesDto();
			sliceFees2.setDate(LocalDate.of(2021,9,1));
			sliceFees2.setDesignation("Tranche 2");
			sliceFees2.setPercentage(30);
			sliceFees2.setRemove(false);
			sliceFees2.setEndDatePayment(LocalDate.of(2021,9,30));
			sliceFees2.setDatePayment(LocalDate.of(2021,9,1));

			feesDto2.setSliceFees(sliceFeesService.save(sliceFees2));
			feesService.save(feesDto2);

			FeesDto feesDto3 = new FeesDto();
			feesDto3.setDesignation("2ième Semestre");
			feesDto3.setAmount(34);
			feesDto3.setRemove(false);
			feesDto3.setIdSchoolYear(1L);
			feesDto3.setIdClassFess(1L);
			feesDto3.setTypeFees(
					typeFeesMapper.entityFromDTO(
							typeFeesRepository.save(
									typeFees3
							)
					)
			);
			feesDto3.setClassFees(ClassFees.builder()
					.designation("6ème")
					.ability(30)
					.local("Salle 1")
					.cycle("Primaire")
					.nameFirstnameFullProfessor("M. Jean")
					.idFullProfessor(1L)
					.build());
			feesDto3.setSchoolYear(SchoolYear.builder()
					.designation("2021-2022")
					.startDate(LocalDate.of(2021,9,1))
					.endDAte(LocalDate.of(2022,7,1))
					.isCurrent(true)
					.build());
			SliceFeesDto sliceFees3 = new SliceFeesDto();
			sliceFees3.setDate(LocalDate.of(2021,9,1));
			sliceFees3.setDesignation("Tranche 3");
			sliceFees3.setPercentage(30);
			sliceFees3.setRemove(false);
			sliceFees3.setEndDatePayment(LocalDate.of(2021,9,30));
			sliceFees3.setDatePayment(LocalDate.of(2021,9,1));

			feesDto3.setSliceFees(sliceFeesService.save(sliceFees3));

			PaymentSystem paymentSystem = PaymentSystem.builder()
					.paymentMethod(CASH)
					.designation(null)
					.clientName("Francis MAYALA")
					.accountNumberOrPhoneNumber(null)
					.clientPhoneNumber("0999999999")
					.institutionName(null)
					.transactionNumber(null)
					.id(1L)
					.build();

			PaymentSystem paymentSystem2 = PaymentSystem.builder()
					.paymentMethod(MOBILE_MONEY)
					.designation("M-PESA")
					.clientName("Idris MANDE")
					.accountNumberOrPhoneNumber("0823456554")
					.clientPhoneNumber("0999999999")
					.institutionName("VODACOM")
					.transactionNumber("RD45666AZ45")
					.id(2L)
					.build();

			PaymentSystem paymentSystem3 = PaymentSystem.builder()
					.paymentMethod(BANK_CARDS)
					.designation("VISA")
					.clientName("Idris MANDE")
					.accountNumberOrPhoneNumber("34567865643219")
					.clientPhoneNumber("0999999999")
					.institutionName("UBA")
					.transactionNumber("45TOFE45RZS")
					.id(2L)
					.build();

			Currency currencyFC = Currency.builder()
					.id(1L)
					.designation("Franc Congolais")
					.symbol("FC")
					.build();

			Currency currencyUSD = Currency.builder()
					.id(2L)
					.designation("Dollar Américain")
					.symbol("$")
					.build();

			PaymentDto paymentDto = new PaymentDto();
			paymentDto.setAmount(1000);
			paymentDto.setRemove(false);
			paymentDto.setDate(LocalDate.of(2021,9,1));
			paymentDto.setFees(feesService.findById(1L));
			paymentDto.setPaymentSystem(paymentSystem);
			paymentDto.setIdPaymentSystem(1L);
			paymentDto.setIdPupil(1L);
			paymentDto.setIdUser(1L);
			paymentDto.setRemove(false);
			paymentDto.setCurrency(currencyFC);
			paymentDto.setIdCurrency(currencyFC.getId());
			paymentDto.setNameFirstnameUser("pascal tshingila");
			paymentService.save(paymentDto);

			PaymentDto paymentDto2 = new PaymentDto();
			paymentDto2.setAmount(1000);
			paymentDto2.setRemove(false);
			paymentDto2.setDate(LocalDate.of(2021,9,1));
			paymentDto2.setFees(feesService.findById(1L));
			paymentDto2.setPaymentSystem(paymentSystem2);
			paymentDto2.setIdPaymentSystem(2L);
			paymentDto2.setIdPupil(1L);
			paymentDto2.setIdUser(1L);
			paymentDto2.setRemove(false);
			paymentDto2.setCurrency(currencyUSD);
			paymentDto2.setIdCurrency(currencyUSD.getId());
			paymentDto2.setNameFirstnameUser("pascal tshingila");
			paymentService.save(paymentDto2);

			PaymentDto paymentDto3 = new PaymentDto();
			paymentDto3.setAmount(1000);
			paymentDto3.setRemove(false);
			paymentDto3.setDate(LocalDate.of(2021,9,1));
			paymentDto3.setFees(feesService.findById(1L));
			paymentDto3.setPaymentSystem(paymentSystem3);
			paymentDto3.setIdPaymentSystem(3L);
			paymentDto3.setIdPupil(1L);
			paymentDto3.setIdUser(1L);
			paymentDto3.setRemove(false);
			paymentDto3.setCurrency(currencyFC);
			paymentDto3.setIdCurrency(currencyFC.getId());
			paymentDto3.setNameFirstnameUser("pascal tshingila");
			paymentService.save(paymentDto3);

			FeesAllocationDto feesAllocationDto = new FeesAllocationDto();
			feesAllocationDto.setDesignation("Charge du personnel");
			feesAllocationDto.setRemove(false);
			feesAllocationDto.setIdSchoolYear(1L);
			feesAllocationDto.setTypeFees(typeFeesMapper.entityFromDTO(typeFeesRepository.findById(1L).orElse(null)));
			feesAllocationDto.setPercentage(30);

			feesAllocationService.save(feesAllocationDto);

			FeesAllocationDto feesAllocationDto2 = new FeesAllocationDto();
			feesAllocationDto2.setDesignation("Charge fonctionnelle");
			feesAllocationDto2.setRemove(false);
			feesAllocationDto2.setIdSchoolYear(1L);
			feesAllocationDto2.setTypeFees(typeFeesMapper.entityFromDTO(typeFeesRepository.findById(2L).orElse(null)));
			feesAllocationDto2.setPercentage(30);

			feesAllocationService.save(feesAllocationDto2);

			FeesAllocationDto feesAllocationDto3 = new FeesAllocationDto();
			feesAllocationDto3.setDesignation("Charge Transport");
			feesAllocationDto3.setRemove(false);
			feesAllocationDto3.setIdSchoolYear(2L);
			feesAllocationDto3.setTypeFees(typeFeesMapper.entityFromDTO(typeFeesRepository.findById(3L).orElse(null)));
			feesAllocationDto3.setPercentage(30);

			feesAllocationService.save(feesAllocationDto3);




			System.out.println("Fees Service is running...");
		};
	}

}
