package ruds.desafio.alterdata.servicos.hotdog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>, JpaSpecificationExecutor<Ingrediente> {

}
