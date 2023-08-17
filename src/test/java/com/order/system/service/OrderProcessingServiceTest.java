package com.order.system.service;


import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrederRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

public class OrderProcessingServiceTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    OrderProcessingServiceImpl orderProcessingService;
    @Mock
    OrederRepository orederRepository;
    @Mock
    ItemRepository itemRepository;

    @Test
    void testSaveOrderWithItems(){
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //order.setItemList(List.of());
        when(orederRepository.save(order)).thenReturn(order) ;
        orderProcessingService.saveOrderWithItems(order);
    }
    @Test
    void testGetOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        List<Order>orderList=new ArrayList<>();
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        // order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findAll()).thenReturn(orderList) ;
        orderProcessingService.getOrderWithItems();

    }
    @Test
    void testGetOrderWithId() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //  order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId( id)).thenReturn(order);
        orderProcessingService.getOrderWithId(id);

    }
    @Test
    void testUpdateOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(id);
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        // order.setOrderDate(LocalDate)"123445");
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        // order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId(id)).thenReturn(order);
        when(orederRepository.save(order)).thenReturn(order);
        orderProcessingService.updateOrderWithItems(order,id);
        // Assert.assertNotNull(order);

    }
    @Test
    void testUpdateOrderWithItemsNull() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order=null;
        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
//        order.setOrderId(Long.valueOf("01"));
//        order.setCustomerName("xyz");
//        order.setCustomerAddress("abc");
//        //  order.setOrderDate(LocalDate.parse("20230801"));
//        order.setDispatchDate("4566666");
//        order.setOrderAmount(666);
//        order.setNumberOfItems(23);
//        order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.save( order)).thenReturn(null);
        orderProcessingService.updateOrderWithItems(order,id);

    }
    @Test
    void testDeleteeOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //  order.setItemList(List.of());
        orderList.add(order);
        orederRepository.deleteById(id);
        orderProcessingService.deleteeOrderWithItems(id);

    }
    @Test
    void testGetAllListOfOrderedItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<Item>itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        orderProcessingService.getAllListOfOrderedItems();

    }
    @Test
    void testGetAllListOfOrderedItemsById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        orderProcessingService.getAllListOfOrderedItemsById(id);

    }
    @Test
    void testUpdateExistingOrderItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        Long orderId=1L;
        ItemDTO itemDTO=new ItemDTO();
        Item item=new Item();
         Long itemid=3L;
        Item item1=new Item();
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(itemid);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        order.setItemList(itemList);
        Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(order);
       // Set<Long>ids=itemList.stream().map(x->x.getItemId()).collect(Collectors.toSet());
       Mockito.when(itemRepository.findByItemId(itemid)).thenReturn(item);
        itemDTO.setItemId(itemid);
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemName(item.getItemName());
       Mockito.when(itemRepository.save(item)).thenReturn(item);
      // BeanUtils.copyProperties(item,itemDTO);

       orderProcessingService.updateExistingOrderItem(item,orderId);

    }
    @Test
    void testUpdateExistingNotinOrder() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Long orderId=1L;
        ItemDTO itemDTO=new ItemDTO();
        Item item=new Item();
        Long itemid=3L;
        Item item1=new Item();
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(itemid);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);

       // Mockito.when(itemRepository.save(item)).thenReturn(item);
        testSaveItem();
      // Mockito.when(orderProcessingService.saveItem(itemList,orderId));
      Mockito.when(orderProcessingService.updateExistingOrderItem(item,orderId)).thenReturn(itemDTO);

    }
    @Test
    void testDeleteOrderedItemById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        itemRepository.deleteById(id) ;
        String s1= orderProcessingService.deleteOrderedItemById(id);
        Assertions.assertNotNull(s1);

    }
    @Test
    void testSaveItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        Long id=1L;
        Item item=new Item();
        Set<Item>itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        Mockito.when(orederRepository.findByOrderId(id)).thenReturn(order);
       Mockito.when(itemRepository.save(item)).thenReturn(item) ;
       Assert.assertNotNull(orderProcessingService.saveItem(itemList,id));

    }
    @Test
    void testGetOrderList() throws SQLException {
        MockitoAnnotations.initMocks(this);
        OrderByItem orderByItem =new OrderByItem();
        Long id;
        id=1L;
       List<Long> ids=List.of(1L,2L,3L);
        orderByItem.setItemId(id);
        orderByItem.setOrderIdList(ids);
        when(orederRepository.findOrdersByItemId(id)).thenReturn(ids) ;
        orderProcessingService.getOrderList(id);

    }
    @Test
    void testUpdateOrderStatus() {
        Long orderId = 123L;
        String newStatus = "shipped";

        Order mockOrder = new Order();
      //  mockOrder.setOrderDate(LocalDate.parse("23-4-11"));
        mockOrder.setOrderId(orderId);
        mockOrder.setOrderAmount(23.2f);
        mockOrder.setCustomerName("name");
        mockOrder.setStatus(Status.PENDING);

      Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(mockOrder);
        mockOrder.setStatus(Status.PROCESSED);
        Mockito.when(orederRepository.save(mockOrder)).thenReturn(mockOrder);
        orderProcessingService.updateOrderStatus(orderId,Status.PROCESSED.name());
    }
    @Test
    void testUpdateOrderStatusNull() {
        Long orderId=null;
        Order mockOrder = new Order();
        //  mockOrder.setOrderDate(LocalDate.parse("23-4-11"));


        Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(null);
        mockOrder.setStatus(Status.PROCESSED);
        Mockito.when(orederRepository.save(mockOrder)).thenReturn(null);
        orderProcessingService.updateOrderStatus(orderId,Status.PROCESSED.name());
    }



}

