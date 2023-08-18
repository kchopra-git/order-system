package com.order.system.service;

import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private  final Status statusDefatulValue=Status.PENDING;
    @Autowired
    OrderRepository orederRepository;
    @Autowired
    ItemRepository itemRepository;

    public OrderDTO saveOrderWithItems(OrderDTO orderDTO) {
        Order order=new Order();
        BeanUtils.copyProperties(orderDTO,order);
        order.setStatus(statusDefatulValue);
         orederRepository.save(order);
         return orderDTO;

    }


    // BeanUtils.copyProperties(item,itemDTO);


    public List<OrderDTO> getOrderWithItems() {
        List<OrderDTO>orderDTOList=new ArrayList<>();
        List<Order> orderList= orederRepository.findAll();
        BeanUtils.copyProperties(orderList,orderDTOList);
        return orderDTOList;
    }

    public OrderDTO getOrderWithId(Long id) {
        OrderDTO orderDTO=new OrderDTO();
        Order order= orederRepository.findByOrderId(id);
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

    public String updateOrderWithItems(Long orderId, Long itemId) {
        Order getExistOrder = orederRepository.findByOrderId(orderId);
        Order orderUpdate = new Order();
        if (getExistOrder != null) {
            orderUpdate.setOrderId(orderId);
            orderUpdate.setOrderAmount(getExistOrder.getOrderAmount());
            orderUpdate.setOrderDate(getExistOrder.getOrderDate());
            orderUpdate.setCustomerName(getExistOrder.getCustomerName());
            orderUpdate.setCustomerAddress(getExistOrder.getCustomerAddress());
            orderUpdate.setNumberOfItems(getExistOrder.getNumberOfItems());
            //  o.setItemList(order.getItemList());
            orederRepository.save(orderUpdate);
            return "Order Updated Successfully";
        }
        return "Order Not Exist";
    }

    public String deleteeOrderWithItems(Long id) {
        orederRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public OrderByItem getOrderList(Long itemId) {
        return null;
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
