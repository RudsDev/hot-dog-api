package dev.ruds.hotdog.domain.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import dev.ruds.hotdog.domain.models.Lanche;

@Repository
public interface LanchesRepository extends JpaRepository<Lanche, Long>, JpaSpecificationExecutor<Lanche> {}
