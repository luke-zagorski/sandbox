package com.performgroup.interview.dao.impl;

import com.performgroup.interview.dao.CategoryDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.dao.domain.VideoEntity;
import org.apache.log4j.Category;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author : luke
 * @version : 1.0
 */
public class CategoryHibernateDAO extends HibernateDaoSupport implements CategoryDAO {

    public CategoryEntity findById(Long categoryId) {
        return (CategoryEntity) getHibernateTemplate().load(CategoryEntity.class, categoryId);
    }

    public void delete(CategoryEntity categoryEntity) {
        getHibernateTemplate().delete(categoryEntity);
    }

    public void save(CategoryEntity categoryEntity) {
        getHibernateTemplate().save(categoryEntity);
    }

    public void deleteByName(String categoryName) {
        delete(findByName(categoryName));
    }


    @SuppressWarnings("unchecked")
    public List<CategoryEntity> findByVideo(VideoEntity videoEntity){

        return getHibernateTemplate().findByNamedQueryAndNamedParam("CategoryEntity.findByVideo", "VID", videoEntity);
    }

    public CategoryEntity findByName(String categoryName) {

        @SuppressWarnings("unchecked")
        List<CategoryEntity> categoryEntities = getHibernateTemplate().findByNamedQueryAndNamedParam("CategoryEntity.findByName", "NAME", categoryName);

        if (categoryEntities.size() == 0) {
            return null;
        } else if (categoryEntities.size() != 1) {
            throw new RuntimeException("Too many VideoCategory elements with specific name , should by one");
        }

        return categoryEntities.get(0);
    }

    public CategoryEntity createCategory(String categoryName) {

        CategoryEntity categoryEntity = new CategoryEntity(categoryName);
        getHibernateTemplate().save(categoryEntity);

        return categoryEntity;
    }

}
