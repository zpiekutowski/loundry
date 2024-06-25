package com.zbiir.loundry.controller;

import com.zbiir.loundry.exception.OrderExistException;
import com.zbiir.loundry.model.OrderArchive;
import com.zbiir.loundry.model.OrderArchiveDTO;
import com.zbiir.loundry.service.OrderArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archive")
public class OrderArchiveControler {
    @Autowired
    private OrderArchiveService orderArchiveService;

    @RequestMapping("/order/{id}")
    public ResponseEntity<OrderArchive> getOrderArchive(@PathVariable Long id) {
        OrderArchive orderArchive = orderArchiveService.getOrderArchive(id);
        return (orderArchive != null) ? ResponseEntity.ok(orderArchive) : ResponseEntity.noContent().build();
    }


    @GetMapping("/page")
    public ResponseEntity<Page<OrderArchiveDTO>> getOrderArchivePage(@RequestParam(name = "page") Integer pageNumber, @RequestParam(name = "size") Integer pageSize) {
        Page<OrderArchiveDTO> result = orderArchiveService.getPageResult(pageNumber, pageSize);

        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderArchive>> getAll() {
        List<OrderArchive> orderArchives = orderArchiveService.getAll();
        if (orderArchives.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<OrderArchive>>(orderArchives, HttpStatus.OK);
        }

    }
    @PutMapping("/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void restoreOrder(@PathVariable Long id) throws OrderExistException {
        orderArchiveService.restore(id);
    }
}
