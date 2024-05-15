package inu.spp.cryptocoffee.domain.service;

import inu.spp.cryptocoffee.domain.dto.OrderRequestDto;
import inu.spp.cryptocoffee.domain.entity.OrderEntity;
import inu.spp.cryptocoffee.domain.repository.ItemRepository;
import inu.spp.cryptocoffee.domain.repository.OrderRepository;
import inu.spp.cryptocoffee.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public String createOrder(OrderRequestDto orderRequestDto) {
        log.info("[createOrder] itemId : {}. userId : {}", orderRequestDto.getItemId(), orderRequestDto.getUserId());

        OrderEntity order = OrderEntity.builder()
                .item(itemRepository.findById(orderRequestDto.getItemId()).orElseThrow(() -> new EntityNotFoundException("Item not found")))
                .user(userRepository.findById(orderRequestDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found")))
                .build();
        orderRepository.save(order);

        return "Order created successfully";
    }

    public List<OrderEntity> getOrderList() {
        List<OrderEntity> orderList = orderRepository.findAll();
        return orderList;
    }

    public OrderEntity getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public String updateOrder(OrderRequestDto orderRequestDto, Long orderId) {
        log.info("[updateOrder] orderId : {}. itemId : {}. userId : {}", orderId, orderRequestDto.getItemId(), orderRequestDto.getUserId());

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.updateOrder(
                itemRepository.findById(orderRequestDto.getItemId()).orElseThrow(() -> new EntityNotFoundException("Item not found")),
                userRepository.findById(orderRequestDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"))
        );

        return "Order updated successfully";
    }

    public String deleteOrder(Long orderId) {
        log.info("[deleteOrder] orderId : {}", orderId);

        orderRepository.deleteById(orderId);

        return "Order deleted successfully";
    }
}
