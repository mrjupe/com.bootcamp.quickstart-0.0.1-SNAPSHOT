package com.bootcamp.quickstart;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dao.CustomerDao;
import com.bootcamp.model.Customer;
import com.bootcamp.model.dto.CommonResponse;
import com.bootcamp.model.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
@SuppressWarnings("rawtypes")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerDao customerDao;

	@GetMapping("/{customernumber}")
	public CommonResponse getById(@PathVariable("customernumber") String customernumber) throws CustomException {
		LOGGER.info("customernumber : {}", customernumber);
		try {
			Customer customer = customerDao.getById(Integer.parseInt(customernumber));
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@GetMapping("/list")
	public CommonResponse getList(@RequestParam(name = "customer", defaultValue = "") String customer) throws CustomException {
		try {
			LOGGER.info("customer get list, params : {}", customer);
			List<Customer> customers = customerDao.getList();
			return new CommonResponse<List<CustomerDto>>(customers.stream()
					.map(cust -> modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		} catch (CustomException e) {
			throw e;
		} catch (NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}

	@PutMapping("")
	public CommonResponse update(@RequestBody CustomerDto customer) throws CustomException {
		try {
			Customer checkCustomer = customerDao.getById(customer.getCustomernumber());
			if (checkCustomer == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			if (customer.getFirstname() != null) {
				checkCustomer.setFirstname(customer.getFirstname());
			}
			if (customer.getLastname() != null) {
				checkCustomer.setLastname(customer.getLastname());
			}
			if (customer.getBirthdate() != null) {
				checkCustomer.setBirthdate(customer.getBirthdate());
			}
			if (customer.getPhonenumber() != null) {
				checkCustomer.setPhonenumber(customer.getPhonenumber());
			}
			if (customer.getTypephone() != null) {
				checkCustomer.setTypephone(customer.getTypephone());
			}
			if (customer.getUsername() != null) {
				checkCustomer.setUsername(customer.getUsername());
			}
			if (customer.getPassword() != null) {
				checkCustomer.setPassword(customer.getPassword());
			}

			checkCustomer = customerDao.save(checkCustomer);

			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, CustomerDto.class));

		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}

	@DeleteMapping("/{customer}")
	public CommonResponse delete(@PathVariable("customer") Integer customernumber) throws CustomException {
		try {
			Customer checkCustomer = customerDao.getById(customernumber);
			if(checkCustomer == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();

		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}

	@PostMapping("")
	public CommonResponse insert(@RequestBody CustomerDto customerDto) throws CustomException {
		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setCustomernumber(0);
			customer = customerDao.save(customer);

			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}

}
