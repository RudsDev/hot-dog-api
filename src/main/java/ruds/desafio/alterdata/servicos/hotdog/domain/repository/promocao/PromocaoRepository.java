package ruds.desafio.alterdata.servicos.hotdog.domain.repository.promocao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.promocao.Promocao;

@Repository
public interface PromocaoRepository 
    extends JpaRepository<Promocao, Long>, PromocaoRepositoryQueries, JpaSpecificationExecutor<Promocao> {}
