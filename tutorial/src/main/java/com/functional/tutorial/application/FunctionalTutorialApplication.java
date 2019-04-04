package com.functional.tutorial.application;

import cyclops.control.Option;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class FunctionalTutorialApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FunctionalTutorialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Option<Integer> opInteger1 = Option.of(10).map(i -> i*10);
		Option<Integer> opInteger2 = Option.of(5).map(i -> i*2);
		int result = opInteger1.flatMap(op1 -> opInteger2.flatMap(op2 -> Option.of(op1*op2))).orElse(0);
		System.out.println("Result of Option Composition: "+result);

		Option<Integer> opEmpty = Option.none();
		Option<Integer> opResultEmpty = opInteger1.flatMap(op1 -> opEmpty.flatMap(op2-> Option.of(op1*op2)))
												  .map(opResult -> opResult/10)
												  .filter(opDivided -> opDivided > 10);

		int resultEmpty = opResultEmpty.orElse(10000);

		System.out.println("Result of Option composed by an empty Option: "+resultEmpty);
		System.out.println("Option isPresent: "+opResultEmpty.isPresent());

		theProblemWithOptional();
	}

	private void theProblemWithOptional() {
		Optional<Integer> opPresent = Optional.of(10);
		System.out.println(opPresent.get());
		Optional<Integer> opEmpty = Optional.empty();
		//System.out.println(opEmpty.get());
	}
}
