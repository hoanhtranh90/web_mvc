package com.cnpm.webadmin.controller;

import com.cnpm.webadmin.authentication.AuthUntil;
import com.cnpm.webadmin.entity.Pos;
import com.cnpm.webadmin.entity.Product;
import com.cnpm.webadmin.entity.User;
import com.cnpm.webadmin.model.CreatePosDTO;
import com.cnpm.webadmin.service.PosService;
import com.cnpm.webadmin.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PosService posService;

    @GetMapping(value =  "/home")
    public String homepage(Model model) {
        model.addAttribute("name", "Nguyen Van A");
        return "home";
    }

    //login
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping(value = "createOrder")
    public String createOrder(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);


        model.addAttribute("pos", new Pos());
        return "createOrder";
    }

    //customer-manager
    @GetMapping(value = "customer-manager")
    public String customerManager() {
        return "customer-manager";
    }

    //order-manager
    @GetMapping(value = "order-manager")
    public String orderManager(@RequestParam(name = "page",
            defaultValue = "1") int page,
                               @RequestParam(name = "size",
                                       defaultValue = "10") int size,
                               @RequestParam(
                                       name = "properties", defaultValue = "id", required = true) String sortByProperties,
                               @RequestParam(name = "sortBy",
                                       defaultValue = "asc") String sortBy,
                               @RequestParam(name = "keyword",
                                       required = false) String keyword,
            Model model) {
        Page<Pos> poss = posService.findAll(page, size, sortByProperties, sortBy, keyword);
        model.addAttribute("listPos", poss);
        return "order-manager";
    }

    //receiving-manager
    @GetMapping(value = "receiving-manager")
    public String receivingManager() {
        return "receiving-manager";
    }

    //suppliers-manager
    @GetMapping(value = "suppliers-manager")
    public String suppliersManager() {
        return "suppliers-manager";
    }

    //product-manager
    @GetMapping(value = "product-manager")
    public String productManager(Model model) {

        List<Product> products = productService.findAll();


        model.addAttribute("products", products);
        model.addAttribute("product", new Product());

        return "product-manager";
    }


    //c-create-product
    @PostMapping(value = "c-create-product")
    public String cCreateProduct(@ModelAttribute Product product, Model model) {
        productService.save(product);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

//        return "product-manager";
        return "redirect:/product-manager";
    }

    //c-pos
    @PostMapping(value = "c-create-pos")
    public ResponseEntity<?> cCreatePos(@RequestBody CreatePosDTO pos, Model model) {
        posService.save(pos);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        User user = AuthUntil.getCurrentUser();
        return ResponseEntity.ok("ok");
    }


}
