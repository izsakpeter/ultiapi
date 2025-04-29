package ulti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ulti.service.TableService;

@Controller
public class TableController {

	@Autowired
	private TableService tableService;

	public void createTable() {
		tableService.createTable();
	}

	public void joinTable() {
		tableService.joinTable();
	}

	public void leaveTable() {
		tableService.leaveTable();
	}

	public void deleteTable() {
		tableService.deleteTable();
	}

}
