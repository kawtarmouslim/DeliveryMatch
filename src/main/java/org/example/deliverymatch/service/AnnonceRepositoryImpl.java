package org.example.deliverymatch.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.model.QAnnonce;
import org.example.deliverymatch.repository.AnnonceRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class  AnnonceRepositoryImpl  implements AnnonceRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public AnnonceRepositoryImpl(EntityManager em) {
     this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Annonce> searchAnnonces(String destination, String typeColis ){
       QAnnonce annonce = QAnnonce.annonce;

        return queryFactory.selectFrom(annonce)
                .where(
                        destination != null ? annonce.destination.eq(destination) : null,
                        typeColis != null ? annonce.typeColis.eq(typeColis) : null
                )
                .fetch();
    }
}
