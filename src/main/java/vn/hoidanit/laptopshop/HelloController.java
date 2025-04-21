package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
	public String index() {
		return "Hello World hihi hha";
	}

	@GetMapping("/user")
	public String user() {
		return "This is route for user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "This is route for admin";
	}

}
