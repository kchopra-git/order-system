package com.order.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "item_detail")

public class Item  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="item_id")
    private Long itemId;
    @Column(name="item_name")
    private String itemName;
    @Column(name="item_price")
    private float itemPrice;
    @Column(name="item_quantity")
    private Integer itemQuantity;
    @Column(name="shipped_date")
    private String shippedDate;

//    @ManyToOne(cascade=CascadeType.ALL)
//  // @JoinColumn(name = "item_id",referencedColumnName ="item_id" )
//   // @JoinColumn(name = "order_id")
//    private List<Order> order;

    @OneToMany(cascade=CascadeType.ALL)
  //  @JoinColumn(name = "order_id")
    private Set<Order> order;

}
