package dev.ruds.hotdog.domain.respositorys;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.ruds.hotdog.domain.models.Ingrediente;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingrediente, Long>, JpaSpecificationExecutor<Ingrediente> {
    
@Transactional
@Modifying
@Query("update Ingrediente i set i.preco = :preco where i.id = :id")
void updatePreco(@Param(value = "id") long id, @Param(value = "preco") BigDecimal preco);
}
