package pujaburman30github.io.zolvepoc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pujaburman30github.io.zolvepoc.model.Transactions;
import pujaburman30github.io.zolvepoc.model.User;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    List<Transactions> findByPayerOrPayee(long user,long user2);
}
