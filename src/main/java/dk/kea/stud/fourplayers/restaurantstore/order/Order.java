package dk.kea.stud.fourplayers.restaurantstore.order;

import dk.kea.stud.fourplayers.restaurantstore.model.BaseEntity;
import dk.kea.stud.fourplayers.restaurantstore.security.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order extends BaseEntity {
    public enum Status {
        PENDING,
        ACCEPTED,
        DECLINED
    }
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;
    @Column(name = "order_timestamp")
    private LocalDateTime orderTimestamp;
    @Column(name = "processed_timestamp")
    private LocalDateTime processedTimestamp;
    @Column(name = "delivery_timestamp")
    private LocalDateTime deliveryTimestamp;
    @Column(name = "order_status")
    private Status status;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "recipient_name")
    private String recipientName;
    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL)
    private List<OrderItem> itemList;

    public Order(User user, LocalDateTime orderTimestamp, LocalDateTime processedTimestamp, LocalDateTime deliveryTimestamp, Status status, String deliveryAddress, String recipientName, List<OrderItem> itemList) {
        this.user = user;
        this.orderTimestamp = orderTimestamp;
        this.processedTimestamp = processedTimestamp;
        this.deliveryTimestamp = deliveryTimestamp;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.recipientName = recipientName;
        this.itemList = itemList;
    }

    public Order() {
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public LocalDateTime getProcessedTimestamp() {
        return processedTimestamp;
    }

    public void setProcessedTimestamp(LocalDateTime processedTimestamp) {
        this.processedTimestamp = processedTimestamp;
    }

    public LocalDateTime getDeliveryTimestamp() {
        return deliveryTimestamp;
    }

    public void setDeliveryTimestamp(LocalDateTime deliveryTimestamp) {
        this.deliveryTimestamp = deliveryTimestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderTimestamp=" + orderTimestamp +
                ", processedTimestamp=" + processedTimestamp +
                ", deliveryTimestamp=" + deliveryTimestamp +
                ", status=" + status +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
