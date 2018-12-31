package com.bootcamp.quickstart;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dao.AccountDao;
import com.bootcamp.dao.CustomerDao;
import com.bootcamp.model.Account;
import com.bootcamp.model.Customer;
import com.bootcamp.model.dto.AccountDto;
import com.bootcamp.model.dto.CommonResponse;

@RestController
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CustomerDao customerDao;

	@GetMapping("/{accountnumber}")
	public CommonResponse getById(@PathVariable("accountnumber") int accountnumber) throws CustomException {
		LOGGER.info("accountnumber : {}", accountnumber);
		try {
			Account account = accountDao.getById(accountnumber);
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
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
	public CommonResponse getList(@RequestParam(name = "customerid", defaultValue = "") String customer)
			throws CustomException {
		try {
			LOGGER.info("Customer get list, params : {} ", customer);

			if (!StringUtils.isEmpty(customer)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
				if (checkCustomer == null) {
					return new CommonResponse("06", "Data customer tidak ditemukan");
				}
				List<Account> accounts = accountDao.getListByCustomer(checkCustomer);
				return new CommonResponse<List<AccountDto>>(accounts.stream()
						.map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList()));
			} else {
				List<Account> accounts = accountDao.getList();
				return new CommonResponse<List<AccountDto>>(accounts.stream()
						.map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList()));
			}

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

	@PutMapping("")
	public CommonResponse update(@RequestBody AccountDto account) throws CustomException {
		try {
			Account checkAccount = accountDao.getById(account.getAccountnumber());
			if (checkAccount == null) {
				return new CommonResponse("14", "account tidak ditemukan");
			}
			if (account.getOpendate() != null) {
				checkAccount.setOpendate(account.getOpendate());
			}
			if (account.getBalance() != 0) {
				checkAccount.setBalance(account.getBalance());
			}
			if (account.getCustomer() != null) {
				checkAccount.setCustomer(account.getCustomer());
			}

			checkAccount = accountDao.save(checkAccount);
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PostMapping("")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws CustomException {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account.setAccountnumber(account.getAccountnumber());
			account = accountDao.save(account);

			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@DeleteMapping("/{account}")
	public CommonResponse delete(@PathVariable("account") Integer account) throws CustomException {
		try {
			Account checkAccount = accountDao.getById(account);
			if (checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}

			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

}
