package com.rooftop.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.rooftop.challenge.model.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

    Optional<Text> findByIdAndDeleted(Long id, Boolean deleted);

    Optional<Text> findByHashAndDeleted(String hashname, Boolean deleted);

    Page<Text> findAllByCharsAndDeleted(Integer chars, Boolean deleted,Pageable pageable);

    List<Text> findAllByCharsAndDeleted(Integer chars, Boolean deleted);
    
}
