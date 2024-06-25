package com.zbiir.loundry.service;

import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.OrderArchive;
import com.zbiir.loundry.model.OrderArchiveDTO;
import com.zbiir.loundry.repositories.OrderArchiveRepository;
import com.zbiir.loundry.repositories.OrderRepository;
import com.zbiir.loundry.repositories.UnitOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderArchiveService {
    @Autowired
    private OrderArchiveRepository orderArchiveRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UnitOrderRepository unitOrderRepository;

    public OrderArchive getOrderArchive(Long id) {
        Optional<OrderArchive> results = orderArchiveRepository.findById(id);
        return results.orElse(null);
    }

    public List<OrderArchive> getAll() {
        return orderArchiveRepository.findAll();

    }

    public Page<OrderArchiveDTO> getPageResult(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 0)
            pageNumber = 0;
        Page<OrderArchive> result = orderArchiveRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id").descending()));
        return result.map((e) -> (new OrderArchiveDTO(e)));
    }

    public void restore(Long id) throws OrderExistException {

        Optional<OrderArchive> orderArchiveOptional = orderArchiveRepository.findById(id);
        if (orderArchiveOptional.isEmpty())
            throw new OrderExistException("Blad archiwum, zamowienie nie istnieje");

        OrderArchive orderArchive = orderArchiveOptional.get();
        orderRepository.restore(
                orderArchive.getId(),
                orderArchive.getCustomer().getId(),
                orderArchive.getPrice(),
                orderArchive.getStartDate(),
                orderArchive.getPlanedFinishDate(),
                true,
                orderArchive.getIsPaid());

        orderArchive.getUnitOrdersArchive().forEach(n ->
                unitOrderRepository.restore(
                        n.getId(),
                        orderArchive.getId(),
                        n.getType().getId(),
                        n.getTagLabel(),
                        n.getTagLabelNo(),
                        n.getComment(),
                        n.getUnitPrice(),
                        n.getFinishDate()));

        orderArchiveRepository.delete(orderArchive);

    }
}
