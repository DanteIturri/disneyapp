package com.disneyapp.disneyapp.repository.specifications;

import com.disneyapp.disneyapp.dto.MovieFiltersDTO;
import com.disneyapp.disneyapp.entity.GenreEntity;
import com.disneyapp.disneyapp.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecifications {
    public Specification<MovieEntity> getFiltered(MovieFiltersDTO movieFilters){

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Name ==
            if(StringUtils.hasLength(movieFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // == Genre ==
            if(!CollectionUtils.isEmpty(movieFilters.getGenre())) {
                Join<MovieEntity, GenreEntity> join = root.join("movieGenres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(movieFilters.getGenre()));
            }

            query.distinct(true);

            // == Order ==
            String orderByField = "title";
            query.orderBy(
                    movieFilters.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
