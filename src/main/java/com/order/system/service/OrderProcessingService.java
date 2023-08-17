package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.entity.Item;
import com.order.system.entity.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderProcessingService {
    public OrderDTO saveOrderWithItems(OrderDTO order);
    public Set<Item> saveItem(Set<Item> item,Long id);
    public List<OrderDTO> getOrderWithItems();
    public OrderDTO getOrderWithId(Long id);
    public String updateOrderWithItems(Long orderId, Long itemId);
    public String deleteeOrderWithItems(Long id);
    public List<ItemDTO> getAllListOfOrderedItems();
    public List<ItemDTO> getAllListOfOrderedItemsById(Long id);
    public ItemDTO updateExistingOrderItem(Item item, Long orderid) throws SQLException;
    public String deleteOrderedItemById(Long id);

   // Set<Order> getAllListOfOrderByItemId(Long id);
   public OrderByItem getOrderList(Long itemId);
   // OrderByItem findOrdersByItemId(Long itemId);
    public String updateOrderStatus(Long orderId, String newStatus);
}
