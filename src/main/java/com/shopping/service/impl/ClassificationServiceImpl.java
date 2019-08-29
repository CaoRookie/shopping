package com.shopping.service.impl;

import com.shopping.dao.ClassificationDao;
import com.shopping.domain.Product;
import com.shopping.domain.ProductType;
import com.shopping.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    private ClassificationDao classificationDao;

    public List<ProductType> getProTypeList() {
        return classificationDao.getProTypeList();
    }

    public List<Product> getProductByType(int productType) {
        return classificationDao.getProductByType(productType);
    }

    public List<Map<String, Object>> getProductBySreach(String sreach) {
        return classificationDao.getProductBySreach(sreach);
    }
}
