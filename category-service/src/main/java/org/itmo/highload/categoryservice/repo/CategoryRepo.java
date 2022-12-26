package org.itmo.highload.categoryservice.repo;

import lombok.RequiredArgsConstructor;
import org.itmo.highload.categoryservice.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryRepo {
    private static final String FIND_BY_ID_QUERY = "select * from category where id = :id";
    private static final String FIND_ALL_PAGE_QUERY = "select * from category offset :offset limit :page_size";
    private static final String INSERT_QUERY = "insert into category values (:id, :name)";
    private static final String UPDATE_QUERY = "update category set name = :name";
    private static final String DELETE_QUERY = "delete from category where id = :id";

    private final CategoryMapper categoryMapper = new CategoryMapper();
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Category save(Category category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", category.getId())
                .addValue("name", category.getName());
        jdbcTemplate.update(INSERT_QUERY, parameterSource);
        return category;
    }
    public Optional<Category> findById(UUID id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(FIND_BY_ID_QUERY, parameterSource, categoryMapper)
                .stream()
                .findFirst();
    }
    public Page<Category> findAll(Pageable pageable) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("offset", pageable.getOffset())
                .addValue("page_size", pageable.getPageSize());
        return new PageImpl<>(jdbcTemplate.query(FIND_ALL_PAGE_QUERY, parameterSource, categoryMapper), pageable,jdbcTemplate.query(FIND_ALL_PAGE_QUERY, parameterSource, categoryMapper).size());
    }

    public Category update (UUID id, Category category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", category.getName());
        jdbcTemplate.update(UPDATE_QUERY, parameterSource);
        return category;
    }
    public void deleteById(UUID id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_QUERY, parameterSource);
    }
}


class CategoryMapper implements RowMapper<Category> {
    public Category mapRow(ResultSet resultSet, int num) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getObject("id", UUID.class));
        category.setName(resultSet.getObject("name", String.class));
        return category;
    }


}
