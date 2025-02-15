package com.fmattaperdomo.tiendaAPI.services;

import com.fmattaperdomo.tiendaAPI.dtos.OrderDto;
import com.fmattaperdomo.tiendaAPI.dtos.OrderResponseDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductDto;
import com.fmattaperdomo.tiendaAPI.dtos.ProductResponseDto;
import com.fmattaperdomo.tiendaAPI.entities.Category;
import com.fmattaperdomo.tiendaAPI.entities.Client;
import com.fmattaperdomo.tiendaAPI.entities.Order;
import com.fmattaperdomo.tiendaAPI.entities.Product;
import com.fmattaperdomo.tiendaAPI.exceptions.ResourceNotFoundException;
import com.fmattaperdomo.tiendaAPI.repositories.ClientRepository;
import com.fmattaperdomo.tiendaAPI.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderResponseDto getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Order> pageOrders = orderRepository.findAll(pageDetails);

        List<Order> orders = pageOrders.getContent();

        List<OrderDto> orderDtos = orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setContent(orderDtos);
        orderResponseDto.setPageNumber(pageOrders.getNumber());
        orderResponseDto.setPageSize(pageOrders.getSize());
        orderResponseDto.setTotalElements(pageOrders.getTotalElements());
        orderResponseDto.setTotalPages(pageOrders.getTotalPages());
        orderResponseDto.setLastPage(pageOrders.isLast());
        return orderResponseDto;
    }


    @Override
    public OrderDto updateOrderBystate(OrderDto orderDto, Integer orderId) {
        Order orderFromDb = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));

        orderFromDb.setState(orderDto.getState());

        Order updatedOrder = orderRepository.save(orderFromDb);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Integer clientId = orderDto.getClientId();
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", "clientId", clientId));
        order.setClientId(clientId);
        order.setOrdersItem(orderDto.getOrdersItem());

        order.setClient(client);

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);

    }
}
