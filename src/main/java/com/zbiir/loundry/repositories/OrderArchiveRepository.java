package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.OrderArchive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderArchiveRepository extends JpaRepository <OrderArchive,Long> {


}
