package inu.spp.cryptocoffee.domain.controller;

import inu.spp.cryptocoffee.domain.dto.OrderRequestDto;
import inu.spp.cryptocoffee.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Order API")
@RequestMapping("/v1/api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "주문 생성")
    @PostMapping
    public ResponseEntity<?> createOrder(OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "주문 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<?> getOrderList() {
        return new ResponseEntity<>(orderService.getOrderList(), HttpStatus.OK);
    }

    @Operation(summary = "주문 조회")
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
    }

    @Operation(summary = "주문 수정")
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequestDto orderRequestDto, @PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.updateOrder(orderRequestDto, orderId), HttpStatus.OK);
    }

    @Operation(summary = "주문 삭제")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }
}
