package com.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.app.model.Product;
import com.app.repo.ProductRepository;

public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	@Override
	public void run(String... args) throws Exception {

		//Save Opertation.
		repo.save(new Product(10,"A",3.3));
		repo.save(new Product(11,"B",4.4));
		repo.save(new Product(12,"C",5.5));
		repo.save(new Product(13,"D",6.6));

	}

}
