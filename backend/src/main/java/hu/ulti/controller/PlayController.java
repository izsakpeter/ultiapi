package hu.ulti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayController {

	@PostMapping("status")
	public void keepAlive() {
	}

	@PostMapping("start")
	public void shuffle() {
	}

	@PostMapping("order")
	public void changeOrder() {
	}

	@PostMapping("startingvalue")
	public void setStartingValue() {
	}

	@PostMapping("call")
	public void call() {
	}

	@PostMapping("join")
	public void join() {
	}

	@PostMapping("sayparti")
	public void sayParti() {

	}

	@PostMapping("saykontra")
	public void saykontra() {
	}

	@PostMapping("play")
	public void play() {
	}

	@PostMapping("newgame")
	public void newGame() {
	}

}
