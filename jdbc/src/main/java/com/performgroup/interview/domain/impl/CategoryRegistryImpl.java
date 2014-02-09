package com.performgroup.interview.domain.impl;

import com.performgroup.interview.dao.CategoryDAO;
import com.performgroup.interview.dao.domain.CategoryEntity;
import com.performgroup.interview.domain.Category;
import com.performgroup.interview.domain.CategoryRegistry;
import com.performgroup.interview.domain.Video;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : luke
 * @version : 1.0
 */
public class CategoryRegistryImpl implements CategoryRegistry {

    private CategoryDAO categoryDAO;

    public VideoCategoryImpl createCategory(String categoryName) {

        CategoryEntity categoryEntity = categoryDAO.createCategory(categoryName);

        return getCategory(categoryEntity);
    }

    public void removeCategory(String categoryName) {

        categoryDAO.deleteByName(categoryName);
    }

    public List<VideoCategoryImpl> findCategoriesForVideo(Video video) {

        List<VideoCategoryImpl> videoCategoryList = new ArrayList<VideoCategoryImpl>();

        for (CategoryEntity categoryEntity : categoryDAO.findByVideo(video.getEntity())) {
            videoCategoryList.add(getCategory(categoryEntity));
        }

        return videoCategoryList;
    }

    public Category findOrCreateCategory(String categoryName) {

        CategoryEntity categoryEntity = categoryDAO.findByName(categoryName);

        if (categoryEntity == null) {
            categoryEntity = categoryDAO.createCategory(categoryName);
        }

        return getCategory(categoryEntity);
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Resource
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    private VideoCategoryImpl getCategory(CategoryEntity categoryEntity) {
        return new VideoCategoryImpl(categoryEntity.getId(), categoryDAO);
    }
}
