package com.performgroup.interview.dao;

import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.dao.domain.VideoEntity;

import java.util.List;

/**
 * @author : luke
 * @version : 1.0
 *          <p/>
 *          Data Access Object to access CategoryEntity information
 */
public interface CategoryDAO extends AbstractDAO<CategoryEntity> {

    /**
     * Persists a <code>CategoryEntity</code> object into the system
     *
     * @param categoryEntity the <code>CategoryEntity</code> object to persist
     */
    void save(CategoryEntity categoryEntity);

    /**
     * Deletes a <code>CategoryEntity</code> object from the system
     *
     * @param categoryName the name of <code>CategoryEntity</code> object to delete
     */
    void deleteByName(String categoryName);

    /**
     * Deletes a <code>CategoryEntity</code> object from the system
     *
     * @param categoryEntity the <code>CategoryEntity</code> object to delete
     */
    void delete(CategoryEntity categoryEntity);


    /**
     * * Creates new <code>CategoryEntity</code> instance
     *
     * @param categoryName - name of category
     * @return new instance
     */
    CategoryEntity createCategory(String categoryName);

    /**
     * Finds <code>CategoryEntity<code/> instance by name
     *
     * @param categoryName - name of the category
     * @return CategoryEntity instance
     */
    CategoryEntity findByName(String categoryName);

    /**
     * Finds categories for <code>VideoEntity<code/>
     *
     * @param videoEntity - <code>VideoEntity</code> instance
     * @return - list of <code>CategoryEntity<code/> instances for Video
     */
    List<CategoryEntity> findByVideo(VideoEntity videoEntity);
}
