package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrederRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {
    private  final Status statusDefatulValue=Status.PENDING;
    @Autowired
    OrederRepository orederRepository;
    @Autowired
    ItemRepository itemRepository;

    public Order saveOrderWithItems(Order order) {
        order.setStatus(statusDefatulValue);
        return orederRepository.save(order);

    }

    public Set<Item> saveItem(Set<Item> item, Long id) {
        //  ItemDTO itemDTO=new ItemDTO();
        Order order = orederRepository.findByOrderId(id);
        //order.setOrderId(id);
        order.setItemList(item);
        orederRepository.save(order);
        return item;
        // itemRepository.save(itemWithOrderId);
    }

    // BeanUtils.copyProperties(item,itemDTO);


    public List<Order> getOrderWithItems() {

        return orederRepository.findAll();
    }

    public Order getOrderWithId(Long id) {
        return orederRepository.findByOrderId(id);
    }

    public Order updateOrderWithItems(Order order, Long id) {
        Order o = orederRepository.findByOrderId(id);
        Order orderUpdate = new Order();
        if (order != null) {
            o.setOrderId(id);
            o.setOrderAmount(order.getOrderAmount());
            o.setOrderDate(order.getOrderDate());
            o.setCustomerName(order.getCustomerName());
            o.setCustomerAddress(order.getCustomerAddress());
            o.setNumberOfItems(order.getNumberOfItems());
            //  o.setItemList(order.getItemList());
            orederRepository.save(o);

        }
        return order;
    }

    public String deleteeOrderWithItems(Long id) {
        orederRepository.deleteById(id);
        return "Deleted";
    }

    public List<Item> getAllListOfOrderedItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllListOfOrderedItemsById(Long id) {
        Item item = new Item();
        List<Item> itemSet = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();
        for (Item it : itemList) {
            if (it.getItemId().equals(id)) {

                itemSet.add(it);
                return itemSet;
            }
        }
        return itemSet;
    }


    public ItemDTO updateExistingOrderItem(Item item, Long orderid) throws SQLException {
        Set<Item> itemSet = new HashSet<>();
        ItemDTO itemDTO = new ItemDTO();
        Order order = orederRepository.findByOrderId(orderid);
        if (order != null) {
          //  Order orderWithItemList = getOrderWithId(orderid);
            Set<Item> itemList = order.getItemList().stream().collect(Collectors.toSet());
            Set<Long> item1 = itemList.stream().map(x -> x.getItemId()).collect(Collectors.toSet());

            for (Item it : itemList) {
                if (item.getItemId().equals(it.getItemId())) {


                    Item it2 = itemRepository.findByItemId(it.getItemId());
                    if (it2 != null) {
                        if (item1.contains(it2.getItemId())) {
                            it2.setItemId(item.getItemId());
                            it2.setItemName(item.getItemName());
                            it2.setItemQuantity(item.getItemQuantity());
                            it2.setItemPrice(item.getItemPrice());
                            it2.setShippedDate(item.getShippedDate());
                            itemRepository.save(it2);
                            BeanUtils.copyProperties(item, itemDTO);
                            return itemDTO;
                        }
                    }
                }
            }
            Set<Item> itemSet1 = new HashSet<>();
            itemSet1.add(item);
            saveItem(itemSet1, orderid);
            //return itemDTO;
           // itemRepository.save(item);
        }
        return itemDTO;
    }

    public String deleteOrderedItemById(Long id) {
        itemRepository.deleteById(id);
        return "Item deleted";
    }



    public OrderByItem getOrderList(Long itemId) {
        OrderByItem orderByItem =new OrderByItem();
        List<Long>ids= orederRepository.findOrdersByItemId(itemId);
        orderByItem.setItemId(itemId);
        orderByItem.setOrderIdList(ids);
        return orderByItem;
    }
    public String updateOrderStatus(Long orderId, String newStatus) {
        Order order = orederRepository.findByOrderId(orderId);
        if(order!=null ) {
            order.setStatus(Status.valueOf(newStatus));
            orederRepository.save(order);
            return "Status Updated";
        }
        return "Status Updated";
    }
}
