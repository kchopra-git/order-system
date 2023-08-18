package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Set<ItemDTO> saveItem(Set<ItemDTO> itemDTO, Long id) {
        //  ItemDTO itemDTO=new ItemDTO();
        Set<Item>itemSet=new HashSet<>();
        BeanUtils.copyProperties(itemDTO,itemSet);
        Order order = orderRepository.findByOrderId(id);
        //order.setOrderId(id);
        order.setItemList(itemSet);
        orderRepository.save(order);
        return itemDTO;
        // itemRepository.save(itemWithOrderId);
    }


    @Override
    public List<ItemDTO> getAllListOfOrderedItems()
    {
        List<ItemDTO> itemDTOList=new ArrayList<>();
        List<Item> itemList= itemRepository.findAll();
        BeanUtils.copyProperties(itemList,itemDTOList);
        return itemDTOList;
    }
    @Override
    public List<ItemDTO> getAllListOfOrderedItemsById(Long id) {
        //  Item item = new Item();
        List<ItemDTO>itemDTOList=new ArrayList<>();
        List<Item> itemSet = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {
            if (item.getItemId().equals(id)) {

                itemSet.add(item);
                BeanUtils.copyProperties(itemSet,itemDTOList);
                return itemDTOList;
            }
        }
        return itemDTOList;
    }



    @Override
    public ItemDTO updateExistingOrderItem(Long itemId, Long orderid) throws SQLException {
        Set<Item> itemSet = new HashSet<>();
        ItemDTO itemDTO = new ItemDTO();
        Order order = orderRepository.findByOrderId(orderid);
        if (order != null) {
            //  Order orderWithItemList = getOrderWithId(orderid);
            Set<Item> itemList = order.getItemList().stream().collect(Collectors.toSet());
            Set<Long> itemIdList = itemList.stream().map(x -> x.getItemId()).collect(Collectors.toSet());

            for (Item it : itemList) {
                if (itemId.equals(it.getItemId())) {


                    Item it2 = itemRepository.findByItemId(it.getItemId());
                    if (it2 != null) {
                        if (itemIdList.contains(it2.getItemId())) {
                            it2.setItemId(it.getItemId());
                            it2.setItemName(it.getItemName());
                            it2.setItemQuantity(it.getItemQuantity());
                            it2.setItemPrice(it.getItemPrice());
                            it2.setShippedDate(it.getShippedDate());
                            itemRepository.save(it2);
                            BeanUtils.copyProperties(it, itemDTO);
                            return itemDTO;
                        }
                    }
                }
            }

        }
        return itemDTO;
    }
    @Override
    public String deleteOrderedItemById(Long id) {
        itemRepository.deleteById(id);
        return "Item deleted";
    }


    @Override
    public OrderByItem getOrderList(Long itemId) {
        OrderByItem orderByItem =new OrderByItem();
        List<Long>ids= orderRepository.findOrdersByItemId(itemId);
        orderByItem.setItemId(itemId);
        orderByItem.setOrderIdList(ids);
        return orderByItem;
    }
}
