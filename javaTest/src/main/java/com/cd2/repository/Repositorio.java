package com.cd2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd2.domain.Model;

@Repository
public interface Repositorio extends JpaRepository<Model, Integer>{

}
