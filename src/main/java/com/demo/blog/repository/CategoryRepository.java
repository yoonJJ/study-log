package com.demo.blog.repository;

import java.util.List;

public interface CategoryRepository {
    List<String> findAllNames();
}
