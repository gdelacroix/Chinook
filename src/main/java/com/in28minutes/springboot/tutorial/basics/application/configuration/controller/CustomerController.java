package com.in28minutes.springboot.tutorial.basics.application.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.Customer;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.ICustomerService;
import com.in28minutes.springboot.tutorial.basics.application.configuration.service.IEmployeeService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService; // service pour réucpérer les employés

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - yyyy-MM-dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/customers")
    public String listAll(ModelMap model) {
        model.put("customers", customerService.getCustomers());
        return "customers"; // Vue JSP: customers.jsp
    }

    @RequestMapping("/customers/add")
    public String showAddCustomerPage(ModelMap model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("employees", employeeService.getEmployees()); // Liste des employés
        model.addAttribute("title", "Ajouter Client");
        return "addcustomer"; // Vue JSP: addcustomer.jsp
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String addCustomer(ModelMap model, Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getEmployees()); // Liste des employés
            return "addcustomer";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customers/update", method = RequestMethod.GET)
    public String showUpdateCustomerPage(@RequestParam long id, ModelMap model) {
        Customer customer = customerService.getCustomerById(id).orElse(null);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.put("customer", customer);
        model.addAttribute("employees", employeeService.getEmployees()); // Liste des employés
        model.addAttribute("title", "Mettre à jour Client");
        return "addcustomer"; // Réutilise le même formulaire
    }

    @RequestMapping(value = "/customers/update", method = RequestMethod.POST)
    public String updateCustomer(ModelMap model, Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getEmployees()); // Liste des employés
            return "addcustomer";
        }
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customers/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
