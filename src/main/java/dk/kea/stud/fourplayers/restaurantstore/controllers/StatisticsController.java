package dk.kea.stud.fourplayers.restaurantstore.controllers;

import dk.kea.stud.fourplayers.restaurantstore.order.OrderItemRepository;
import dk.kea.stud.fourplayers.restaurantstore.order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class StatisticsController {

    private final OrderItemRepository orderItems;
    private final OrderRepository orders;

    public StatisticsController(OrderItemRepository orderItems, OrderRepository orders) {
        this.orderItems = orderItems;
        this.orders = orders;
    }

    @GetMapping("/admin/statistics/products")
    public String bestSellingProducts(Model model){
        Collection<List<String>> bestSelling = new LinkedHashSet<>();
        bestSelling.addAll(orderItems.findBestSellingProducts());
        model.addAttribute("bestSelling", bestSelling);
        return "statistics/bestSellingProducts";
    }

    @GetMapping("/admin/statistics/categories")
    public String bestSellingCategories(Model model){
        Collection<List<String>> bestSelling = new LinkedHashSet<>();
        bestSelling.addAll(orderItems.findBestSellingCategories());
        model.addAttribute("bestSelling", bestSelling);
        return "statistics/bestSellingCategories";
    }

    @GetMapping("/admin/statistics/customers")
    public String bestCustomers(Model model){
        Collection<List<String>> bestCustomers = new LinkedHashSet<>();
        bestCustomers.addAll(orders.findBestCustomers());
        model.addAttribute("bestCustomers", bestCustomers);
        return "statistics/bestCustomers";
    }
}
