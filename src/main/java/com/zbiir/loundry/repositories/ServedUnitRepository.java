package com.zbiir.loundry.repositories;

import com.zbiir.loundry.model.ServedUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServedUnitRepository extends JpaRepository<ServedUnit, Long> {

}
