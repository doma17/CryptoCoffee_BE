package inu.spp.cryptocoffee.domain.order.service;

import inu.spp.cryptocoffee.auth.user.dto.CustomUserDetails;
import inu.spp.cryptocoffee.auth.user.entity.UserEntity;
import inu.spp.cryptocoffee.domain.order.dto.OrderCreateRequestDto;
import inu.spp.cryptocoffee.domain.order.dto.OrderResponseDto;
import inu.spp.cryptocoffee.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderCreateRequestDto orderCreateRequestDto) {

    }

    public List<OrderResponseDto> getOrdersList(CustomUserDetails customUserDetails, int pageNum, int pageSize, String criteria) {
        UserEntity user = customUserDetails.getUserEntity();
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, criteria));
        return orderRepository.findByCompany(user.getCompany(), pageable).stream()
                .map(OrderResponseDto::from)
                .collect(Collectors.toList());
    }
}
