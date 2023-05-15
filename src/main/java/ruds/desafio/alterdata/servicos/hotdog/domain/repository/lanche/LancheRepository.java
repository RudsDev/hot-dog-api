package ruds.desafio.alterdata.servicos.hotdog.domain.repository.lanche;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ruds.desafio.alterdata.servicos.hotdog.domain.models.Lanche;

@Repository
public interface LancheRepository 
    extends JpaRepository<Lanche, Long>, LancheRepositoryQueries, JpaSpecificationExecutor<Lanche> {}
