package com.bootcamp.quickstart;

import java.util.List;

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


@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/{accountnumber}")
	public Account getById(@PathVariable("accountnumber") String accountnumber) throws CustomException {
		return accountDao.getById(accountnumber);
	}
	
	@GetMapping("/list")
	public List<Account> getList(@RequestParam(name="customer", defaultValue="")String customer) throws CustomException{
		if(!StringUtils.isEmpty(customer)) {
			Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
			if(checkCustomer==null) {
				throw new CustomException("customer tidak ditemukan");
			}
			return accountDao.getListByCustomer(checkCustomer);
		}else {
			return accountDao.getList();
		}
	}
	
	@PutMapping("")
	public Account update(@RequestBody Account account) throws CustomException{
		return accountDao.save(account);
	}
	
	@PostMapping("")
	public Account save(@RequestBody Account account) throws CustomException{
		return accountDao.save(account);
	}
	
	@DeleteMapping("")
	public String delete(@RequestParam(value="id", defaultValue="") Account account) throws CustomException{
		try {
			accountDao.delete(account);
			return "Data berhasil dihapus";
		} catch (CustomException e) {
			return "Data gagal dihapus";
		}
	}
	
}
