package com.zbiir.loundry.service;

import com.zbiir.loundry.model.OrderArchive;
import com.zbiir.loundry.model.OrderArchiveDTO;
import com.zbiir.loundry.repositories.OrderArchiveRepository;
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

    public OrderArchive getOrderArchive(Long id) {
        Optional<OrderArchive> results = orderArchiveRepository.findById(id);
        if (results.isPresent()) {
            return results.get();
        } else {
            return null;
        }
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

    public void restore(Long id) {

    }
}
