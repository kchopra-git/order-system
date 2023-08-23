package com.order.system.integration;

import com.order.system.dto.ItemDTO;

import com.order.system.dto.OrderDTO;
import com.order.system.integration.entity.OrderTest;
import com.order.system.integration.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderSystemApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TestRepository testRepository;

    @Test
    public void testAddOrderWithItems() {
        // Create a new order
        OrderTest order = new OrderTest(/* initialize order properties */);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        OrderTest createdOrder = createResponse.getBody();
        Long orderId = createdOrder.getOrderId();
    }


    // Retrieve the created order
    @Test
    public void testGetOrderWithItems() {
        OrderTest order = new OrderTest(/* initialize order properties */);
//          order.setOrderId(2L);
          order.setCustomerName("C1");
          order.setNumberOfItems(33);
          order.setOrderAmount(37);
          order.setCustomerAddress("CA1");
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        OrderTest createOrder=createResponse.getBody();
          Long orderId = createOrder.getOrderId();

        ResponseEntity<OrderTest> getResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/orders/"+orderId , OrderTest.class,order);
        assertEquals(2, orderId);

        OrderTest retrievedOrder = getResponse.getBody();
    }
    // Add assertions to verify the retrieved order

    // Update the order
    @Test
    void testUpdateOrderWithItems() {
        OrderDTO order = new OrderDTO(/* initialize order properties */);
        order.setOrderId(1L);
        order.setCustomerName("C1");
        order.setNumberOfItems(33);
        order.setOrderAmount(37);
        order.setCustomerAddress("CA1");
        ResponseEntity<OrderDTO> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderDTO.class);

        OrderDTO retrievedOrder = createResponse.getBody();
        retrievedOrder.setCustomerName("Updated Customer");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderDTO> updateEntity = new HttpEntity<>(retrievedOrder, headers);
       ResponseEntity<String> updateResponse = restTemplate.exchange("http://localhost:" + port + "/api/orders/" + order.getOrderId(), HttpMethod.PUT, updateEntity, String.class,1L);

// Verify the response status code
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

// Optionally, you can also assert the response content
// For example, if your controller returns "Order Updated Successfully" in the response body:
        assertEquals("Order Updated Successfully", updateResponse.getBody());

        String updatedOrder = updateResponse.getBody();

        // Verify the response status code and content
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals("Order Updated Successfully", updateResponse.getBody());
    }



    // Delete the order
    @Test
    void testDeleteeOrderWithItems() { OrderTest order = new OrderTest(/* initialize order properties */);
        order.setOrderId(1L);
        order.setCustomerName("C1");
        order.setNumberOfItems(33);
        order.setOrderAmount(37);
        order.setCustomerAddress("CA1");
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);

        ResponseEntity<Void> deleteResponse = restTemplate.exchange("http://localhost:" + port + "/api/orders/" + order.getOrderId(), HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

        // Verify that the order is deleted
        ResponseEntity<OrderTest> deletedGetResponse = restTemplate.getForEntity("http://localhost:" + port + "/api/orders/" + order.getOrderId(), OrderTest.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, deletedGetResponse.getStatusCode());
    }

}
