package com.shopping.repository;


	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Customer;
import com.shopping.model.Products;

		
		@Repository
		public interface CustomerRepository extends JpaRepository<Customer, Long>{
			List<Customer> findByName(String name);
			List<Customer> findByEmail(String name);
			List<Customer> findByBackupemail(String name);
			List<Customer> findByPhonenumber(String name);
			List<Customer> findFirstByOrderByPicnumDesc();
		}
