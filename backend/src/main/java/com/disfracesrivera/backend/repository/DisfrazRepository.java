package com.disfracesrivera.backend.repository;

import com.disfracesrivera.backend.model.Disfraz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisfrazRepository extends JpaRepository<Disfraz, Integer> {
    List<Disfraz> findByNombreContainingIgnoreCase(String nombre);
}
