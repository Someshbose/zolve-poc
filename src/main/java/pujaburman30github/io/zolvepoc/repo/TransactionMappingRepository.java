package pujaburman30github.io.zolvepoc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pujaburman30github.io.zolvepoc.model.TransactionTypeMapping;

@Repository
public interface TransactionMappingRepository extends JpaRepository<TransactionTypeMapping,Long> {

}
