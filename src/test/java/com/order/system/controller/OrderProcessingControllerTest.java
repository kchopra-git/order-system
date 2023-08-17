package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.service.OrderProcessingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@RunWith(MockitoJUnitRunner.class)
public class OrderProcessingControllerTest {
    @InjectMocks
    OrderProcessingController orderProcessingController;
    @Mock
    OrderProcessingServiceImpl orderProcessingService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);


    }
@Test
    void testAddOrderWithItems() throws SQLException {
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
   Mockito.when(orderProcessingService.saveOrderWithItems(order)).thenReturn(order) ;
   orderProcessingController.addOrderWithItems(order);

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
    //order.setItemList(List.of());
    orderList.add(order);
    Mockito.when(orderProcessingService.getOrderWithItems()).thenReturn(orderList) ;
    orderProcessingController.getOrderWithItems();

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
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.getOrderWithId( id)).thenReturn(order);
        orderProcessingController.getOrderWithId(id);

    }
    @Test
    void testUpdateOrderWithItems() throws SQLException {
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
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.updateOrderWithItems( order,id)).thenReturn(order);
        orderProcessingController.updateOrderWithItems(order,id);

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
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.deleteeOrderWithItems( id)).thenReturn("deleted");
        orderProcessingController.deleteeOrderWithItems(id);

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
        Mockito.when(orderProcessingService.getAllListOfOrderedItems()).thenReturn(itemList) ;
        orderProcessingController.getAllListOfOrderedItems();

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
        Mockito.when(orderProcessingService.getAllListOfOrderedItemsById(id)).thenReturn(itemList) ;
        orderProcessingController.getAllListOfOrderedItemsById(id);

    }
    @Test
    void testUpdateExistingOrderItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        ItemDTO itemDTO=new ItemDTO();
       // ItemDTO itemDTO=new HashSet<>();
        Item item=new Item();
        Long itemid;
        Long orderid;
        itemid=3L;
        orderid=1L;
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(itemid);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
     //   itemList.add(item);
        BeanUtils.copyProperties(item,itemDTO);
        Mockito.when(orderProcessingService.updateExistingOrderItem(item ,orderid)).thenReturn(itemDTO) ;
        orderProcessingController.updateExistingOrderItem(item,orderid);

    }
    @Test
    void testDeleteOrderedItemById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        String s1 = orderProcessingService.deleteOrderedItemById(id) ;
        orderProcessingController.deleteOrderedItemById(id);

    }
    @Test
    void testAddOrderedItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        Mockito.when(orderProcessingService.saveItem(itemList,id)).thenReturn(itemList);
        orderProcessingController.addOrderedItem(itemList,id);

    }
    @Test
    void testGetOrdersByItemId() throws SQLException {
        MockitoAnnotations.initMocks(this);
        OrderByItem orderByItem=new OrderByItem();
        List<Long> ids=List.of(1L,2L,3L);
        orderByItem.setItemId(1L);
        orderByItem.setOrderIdList(ids);
        Mockito.when(orderProcessingService.getOrderList(1L)).thenReturn(orderByItem) ;
        orderProcessingController.getOrderList(1L);

    }
    @Test
    void testUpdateOrderStatus() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        String status="UPDATED";

        Mockito.when(orderProcessingService.updateOrderStatus(id,status)).thenReturn("UPDATED");
        orderProcessingController.updateOrderStatus(id,"itemList");

    }
}
