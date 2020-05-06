package com.prog39599.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prog39599.beans.Car;
import com.prog39599.beans.Customer;
import com.prog39599.beans.RentalOrder;
import com.prog39599.repositories.CarRepository;
import com.prog39599.repositories.CustomerRepository;
import com.prog39599.repositories.RentalOrderRepository;
//import com.prog39599.repositories.RentalOrderRepository;
import com.prog39599.repositories.RolesRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class HomeController implements CommandLineRunner {

	@Autowired
	private JavaMailSender javaMailSender;
	private CarRepository carRepository;
	private CustomerRepository customerRepository;
	private RentalOrderRepository rentalOrderRepository;
	private RolesRepository rolesRepository;

	@GetMapping("/")
	public String index(Model model) {
		return "user/index";
	}

	@PostMapping("/booking")
	public String booking(Model model) {
		return "user/book";
	}

	@GetMapping("/findByMake/{make}")
	public String findByMake(@PathVariable String make, Model model) {
		List<Car> car = carRepository.findByMakeLike("%" + make + "%");
		model.addAttribute("carByMake", car);
		return "user/searchCar";
	}

	@GetMapping("/user/book")
	public String book(Model model, @RequestParam(name = "getCar") Long id, Authentication authentication) {

		Car car = carRepository.findById(id).get();
		Customer c = customerRepository.findByUsername(authentication.getName());
		Customer cust = Customer.builder().id(c.getId()).username(c.getUsername())
				.encryptedPassword(c.getEncryptedPassword()).fName(c.getFName()).lName(c.getLName())
				.address(c.getAddress()).phoneNumber(c.getPhoneNumber()).enabled(c.getEnabled()).build();
		RentalOrder ro = RentalOrder.builder().car(car).customer(cust).build();
		model.addAttribute("rentorder", ro);
		return "/user/selectDate";

	}

	@PostMapping("/bookConfirm")
	public String bookConfirm(Model model, @ModelAttribute RentalOrder rentorder, @RequestParam String email) {
		
		rentalOrderRepository.save(rentorder);
		model.addAttribute("order", rentorder);
		sendEmail(email,"Confirmation","Car: " + rentorder.getCar().getMake()+ " " + rentorder.getCar().getModel()  + "\n Star Date : " + rentorder.getStartDate() + 
				"\n Drop Date : " + rentorder.getEndDate() );
		return "user/success";
	}

	// Login
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// Registration Part
	@GetMapping("/register")
	public String goRegisteration() {
		return "register";
	}

	@PostMapping("/register")
	public String doRegisteration(@RequestParam String username, @RequestParam String password,
			@RequestParam String fName, @RequestParam String lName, @RequestParam String address,
			@RequestParam Long phone) {
		Customer customer = new Customer(username, encodePassword(password), fName, lName, address, phone,
				Byte.valueOf("1"));
		customer.getRoles().add(rolesRepository.findByRolename("ROLE_USER"));
		customerRepository.save(customer);
		return "redirect:/";
	}

	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	// Contact US
	@GetMapping("/ContactUs")
	public String contactUs(Model model) {
		return "contactUs";
	}

	// Access Denied Handler
	@GetMapping("/access-denied")
	public String error() {
		return "/error/access-denied.html";
	}

//	Administrator Part
	// Admin
	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());

		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());
		return "admin/admin";
	}

	// Admin - Car
	@PostMapping("/addCar")
	public String insertCar(Model model, @ModelAttribute Car car) {
		carRepository.save(car);
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		return "admin/admin";
	}

	@GetMapping("/editCar/{id}")
	public String editCarAdmin(@PathVariable Long id, Model model) {
		Car car = carRepository.findById(id).get();
		model.addAttribute("car", car);
		return "admin/editCar";
	}

	@GetMapping("/modifyCarAdmin")
	public String modifyCarAdmin(@ModelAttribute Car car, Model model) {
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		carRepository.save(car);
		return "admin/admin";
	}

	@GetMapping("/deleteCar/{id}")
	public String deleteCar(@PathVariable Long id, Model model) {
		carRepository.deleteById(id);
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		return "admin/admin";
	}

	// Admin - Customer
	@PostMapping("/addCustomer")
	public String insertCustomer(Model model, @ModelAttribute Customer customer) {
		customerRepository.save(customer);
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		return "admin/admin";
	}

	@GetMapping("/editCustomer/{id}")
	public String editCustomerAdmin(@PathVariable Long id, Model model) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "admin/editCustomer";
	}

	@GetMapping("/modifyCustomerAdmin")
	public String modifyCustomerAdmin(@ModelAttribute Customer customer, Model model) {
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		customerRepository.save(customer);
		return "admin/admin";
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable Long id, Model model) {
		customerRepository.deleteById(id);
		model.addAttribute("carList", carRepository.findAll());
		model.addAttribute("car", new Car());

		model.addAttribute("customerList", customerRepository.findAll());
		model.addAttribute("customer", new Customer());
		return "admin/admin";
	}

	@Override
	public void run(String[] args) {
		sendEmail("exoticcarrentalspring@gmail.com","Hi Hello","hehcjisdj ijosjd");
		
		
	}

	private void sendEmail(String to, String sub, String text) {
		// TODO Auto-generated method stub
		System.out.println("Sending the message");
		SimpleMailMessage msg= new SimpleMailMessage();
		
		msg.setTo(to);
		msg.setSubject(sub);
		msg.setText(text);
		
		try {
			javaMailSender.send(msg);		
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("DOne");
	}

}
