package com.app.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.exception.ProductNotFoundException;
import com.app.model.Product;
import com.app.repo.ProductRepository;

@Component
public class ConsoleRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;

	@Override
	public void run(String... args) throws Exception {

		System.out.println(repo.getClass().getName());
		List<Product> prds = repo.findAll();
		prds.forEach(System.out::println);

		/** 1.a.Save Operation. **/
		// this save() method is work as save or update , if id exits then update or
		// insert operation

		repo.save(new Product(1, "A", 3.3));
		repo.save(new Product(2, "B", 4.4));
		repo.save(new Product(3, "C", 5.5));
		repo.save(new Product(4, "F", 7.5));

		// b.Update All--update -- if id exit then automatically work as update
		// operation
		repo.save(new Product(4, "GG", 99.99));

		/** 2.Bulk Insert **/
		// Insert the data in bulk size in Array as list.

		List<Product> prd = Arrays.asList(new Product(101, "SS", 50.50), new Product(102, "ST", 51.51),
				new Product(103, "SU", 52.52), new Product(104, "SV", 53.53)

				);
		repo.saveAll(prd); // perform the bulk operation.

		/*	*//** 3.To fetch one row **//*
		 * Optional<Product> product =repo.findById(108); //Here optional is return type
		 * of findBrId method which has isPresent() method.
		 * 
		 * if(product.isPresent()) {
		 * System.out.println("Product Details Are :\n"+product.get()); } else { throw
		 * new ProductNotFoundException("Product Not Exist"); //here we use the
		 * custom(user define) exception to throw it. }
		 */

		/** 4. Fetch All Rows.. **/
		//Here We fetch all rows by using findAll() method.
		System.out.println("Fetching all Rows : ");
		List<Product> list = repo.findAll();
		list.forEach(System.out::println);

		/** 5.a. Delete By Id..**/
		//This Method is used to delete data on the basis of Id only.
		repo.deleteById(3);
		System.out.println(" Deleted Sucessfully ");
		
		//print data after deleting row.
		System.out.println("Fetching all Rows : ");
		List<Product> list1 = repo.findAll();
		list1.forEach(System.out::println);
		
		/**  b. delete all rows..**/
		repo.deleteAll();  // delete the rows one by one on the basis of pk.
		repo.deleteAllInBatch(); // this will delete  all rows in time.
	}

}
