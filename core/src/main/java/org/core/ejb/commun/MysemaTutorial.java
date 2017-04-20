package org.core.ejb.commun;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.hql.jpa.JPAUpdateClause;

public class MysemaTutorial {

	void init() {

//		JPAQueryFactory factory = new JPAQueryFactory();
//		JPAUpdateClause update = factory.update(formaliteRegistreEntity);
//		update.set(formaliteRegistreEntity.signatureRegistreEntity.identifiant,
//				signatureRegistreViewDto.getIdentifiant());
//		final BooleanBuilder builder = new BooleanBuilder();
//		builder.and(formaliteRegistreEntity.dateHeureSaisie.between(signatureRegistreViewDto.getDateHeureDebut(),
//				signatureRegistreViewDto.getDateHeureFin()));
//		builder.and(formaliteRegistreEntity.registreEntity.statutEntity.typeRegistreEntity.eq(TypeRegistreEntity.RCS));
//		builder.and(formaliteRegistreEntity.signatureRegistreEntity.identifiant.isNull());
//		update.where(builder);
//		update.execute();
	}
}
