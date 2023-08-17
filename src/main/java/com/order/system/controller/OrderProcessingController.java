package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.service.OrderProcessingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class OrderProcessingController {
    @Autowired
    OrderProcessingServiceImpl orderProcessingService;


    @PostMapping(value="/orders", consumes = "application/json")
    public ResponseEntity<Order> addOrderWithItems(@RequestBody Order order) throws SQLException {
        Order Order=	orderProcessingService.saveOrderWithItems(order);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @GetMapping (value="/orders")
    public ResponseEntity<List<Order>> getOrderWithItems() throws SQLException {
       List<Order> OrderList=	orderProcessingService.getOrderWithItems();
        return new ResponseEntity<>(OrderList, HttpStatus.OK);
    }
    @GetMapping (value="/orders/{id}")
    public ResponseEntity<Order> getOrderWithId(@PathVariable Long id) throws SQLException {
        Order Order=	orderProcessingService.getOrderWithId(id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @PutMapping (value="/orders/{id}")
    public ResponseEntity<Order> updateOrderWithItems(@RequestBody Order order ,@PathVariable Long id) throws SQLException {
        Order Order=	orderProcessingService.updateOrderWithItems(order,id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @DeleteMapping (value="/orders/{id}")
    public ResponseEntity<String> deleteeOrderWithItems(@PathVariable Long id) throws SQLException {
        orderProcessingService.deleteeOrderWithItems(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
    @GetMapping (value="/order-items")
    public ResponseEntity<List<Item>> getAllListOfOrderedItems() throws SQLException {
        List<Item> itemList=	orderProcessingService.getAllListOfOrderedItems();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
    @GetMapping (value="/order-items/{id}")
    public ResponseEntity<List<Item>> getAllListOfOrderedItemsById(@PathVariable Long id) throws SQLException {
        List<Item> itemList=	orderProcessingService.getAllListOfOrderedItemsById(id);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
    @PutMapping (value="/order-items/{orderid}")
    public ResponseEntity<ItemDTO> updateExistingOrderItem(@RequestBody Item Item , @PathVariable Long orderid) throws SQLException {
       ItemDTO item=	orderProcessingService.updateExistingOrderItem(Item,orderid);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @DeleteMapping (value="/order-items/{id}")
    public ResponseEntity<String> deleteOrderedItemById(@PathVariable Long id) throws SQLException {
        orderProcessingService.deleteOrderedItemById(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
    @PostMapping (value="/order-items/{id}")
    public ResponseEntity<Set<Item>> addOrderedItem(@RequestBody Set<Item> item, @PathVariable Long id ) throws SQLException {
        Order order=new Order();
        Set<Item> i=orderProcessingService.saveItem(item,id);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
//    @GetMapping("/orders-by-item/{itemId}")
//    public OrderByItem getOrdersByItemId(@PathVariable Long itemId) {
//        //return orderProcessingService.findOrdersByItemId(itemId);
//        OrderByItem orderByItem=orderProcessingService.findOrdersByItemId(itemId);
//        return orderByItem;
//    }
    @GetMapping("/orders-by-item/{itemId}")
    public OrderByItem getOrderList(@PathVariable Long itemId) {
        //return orderProcessingService.findOrdersByItemId(itemId);
        OrderByItem orderByItem=orderProcessingService.getOrderList(itemId);
        return orderByItem;
    }

    @PutMapping("/{orderId}/{status}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String status) {
        orderProcessingService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated");
    }
}
