package com.bootcamp.quickstart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dao.AccountDao;
import com.bootcamp.dao.TransactionDao;
import com.bootcamp.model.Account;
import com.bootcamp.model.Transaction;

@RestController
@RequestMapping("/transaction")
public class TransactonController {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;
	
	
	@PostMapping("")
	public Transaction save(@RequestBody Transaction transaction)throws CustomException{
		return transactionDao.save(transaction);
	}
	
	@PutMapping("")
	public Transaction update(@RequestBody Transaction transaction) throws CustomException{
		return transactionDao.save(transaction);
	}
	
	@DeleteMapping("")
	public String delete(@RequestParam(value="id", defaultValue="") Transaction transaction) throws CustomException{
		try {
			transactionDao.delete(transaction);
			return "Data berhasil dihapus";
		} catch (Exception e) {
			return "Data gagal dihaspus";
		}
	}
	
	@GetMapping("/list")
	public List<Transaction> getList(@RequestParam(name="account", defaultValue="")String id) throws CustomException{
		if(!StringUtils.isEmpty(id)) {
			Account checkAccount = accountDao.getById(Integer.parseInt(id));
			if(checkAccount==null) {
				throw new CustomException("account tidak ditemukan");
			}
			return transactionDao.getListByAccount(checkAccount);
			
		}else {
			return transactionDao.getList();
		}
	}
}
