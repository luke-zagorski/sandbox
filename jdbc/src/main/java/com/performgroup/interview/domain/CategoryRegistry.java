package com.performgroup.interview.domain;

import com.performgroup.interview.domain.impl.VideoCategoryImpl;

import java.util.List;

/**
 * @author : luke
 * @version : 1.0
 *
 * Registry pattern implementation for <code>VideoCategory</code> domain class related classes
 */
public interface CategoryRegistry {

    VideoCategoryImpl createCategory(String categoryName);

    Category findOrCreateCategory(String categoryName);

    List<VideoCategoryImpl> findCategoriesForVideo(Video video);

    void removeCategory(String categoryName);

}
