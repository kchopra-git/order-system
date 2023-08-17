package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderProcessingService {
    public Order saveOrderWithItems(Order order);
    public Set<Item> saveItem(Set<Item> item,Long id);
    public List<Order> getOrderWithItems();
    public Order getOrderWithId(Long id);
    public Order updateOrderWithItems(Order order, Long id);
    public String deleteeOrderWithItems(Long id);
    public List<Item> getAllListOfOrderedItems();
    public List<Item> getAllListOfOrderedItemsById(Long id);
    public ItemDTO updateExistingOrderItem(Item item, Long orderid) throws SQLException;
    public String deleteOrderedItemById(Long id);

   // Set<Order> getAllListOfOrderByItemId(Long id);
   public OrderByItem getOrderList(Long itemId);
   // OrderByItem findOrdersByItemId(Long itemId);
    public String updateOrderStatus(Long orderId, String newStatus);
}
