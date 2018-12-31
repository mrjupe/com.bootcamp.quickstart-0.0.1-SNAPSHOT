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
import com.bootcamp.dao.TransactionDao;
import com.bootcamp.model.Account;
import com.bootcamp.model.Transaction;
import com.bootcamp.model.dto.CommonResponse;
import com.bootcamp.model.dto.TransactionDto;

@RestController
@RequestMapping("/transaction")
@SuppressWarnings("rawtypes")
public class TransactonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private AccountDao accountDao;

	@GetMapping("/{id}")
	public CommonResponse getById(@PathVariable("id") int id) throws CustomException {
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(id);
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
	public CommonResponse getList(@RequestParam(name = "accountnumber", defaultValue = "") String account)
			throws CustomException {
		try {
			LOGGER.info("Account get list, params : {} ", account);
			if (!StringUtils.isEmpty(account)) {
				Account checkAccount = accountDao.getById(Integer.parseInt(account));
				if (checkAccount == null) {
					return new CommonResponse("06", "Data Account tidak ditemukan");
				}
				List<Transaction> transactions = transactionDao.getListByAccount(checkAccount);
				return new CommonResponse<List<TransactionDto>>(
						transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class))
								.collect(Collectors.toList()));
			} else {
				List<Transaction> transactions = transactionDao.getList();
				return new CommonResponse<List<TransactionDto>>(
						transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class))
								.collect(Collectors.toList()));
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

	@PostMapping("")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws CustomException {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction = transactionDao.save(transaction);

			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PutMapping("")
	public CommonResponse update(@RequestBody TransactionDto transaction) throws CustomException {
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getId());
			if (checkTransaction == null) {
				return new CommonResponse("14", "transaction tidak ditemukan");
			}
			if (transaction.getType() != null) {
				checkTransaction.setType(transaction.getType());
			}
			if (transaction.getAmount() != 0) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if (transaction.getAmountsign() != null) {
				checkTransaction.setAmountsign(transaction.getAmountsign());
			}
			if (transaction.getAccount() != null) {
				checkTransaction.setAccount(transaction.getAccount());
			}

			checkTransaction = transactionDao.save(checkTransaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));

		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@DeleteMapping("/{transaction}")
	public CommonResponse delete(@PathVariable("transaction") Integer transaction) throws CustomException {

		try {
			Transaction checkTransaction = transactionDao.getById(transaction);
			if(checkTransaction == null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

}
