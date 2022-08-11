package uz.pdp.jpaapprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.jpaapprelationships.entity.Adress;


@Repository
public interface AdressRepositiry extends JpaRepository<Adress,Integer> {
}
