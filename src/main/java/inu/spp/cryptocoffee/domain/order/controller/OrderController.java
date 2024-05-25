package inu.spp.cryptocoffee.domain.order.controller;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.domain.order.dto.OrderCreateRequestDto;
import inu.spp.cryptocoffee.domain.order.dto.OrderResponseDto;
import inu.spp.cryptocoffee.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Order API - apiKey 필요")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성 - 로그인 인증 X")
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        orderService.createOrder(orderCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "사내 회원 주문 조회 - 페이징")
    @GetMapping("/members")
    public ResponseEntity<List<OrderResponseDto>> getMemberOrders(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam(defaultValue = "0", value = "page") int pageNum,
            @RequestParam(defaultValue = "10", value = "pageSize") int pageSize,
            @RequestParam(defaultValue = "createdAt", value = "sort") String criteria
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersList(customUserDetails, pageNum, pageSize, criteria));
    }
}
