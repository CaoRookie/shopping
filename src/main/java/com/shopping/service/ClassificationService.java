package com.shopping.service;

import com.shopping.domain.Product;
import com.shopping.domain.ProductType;

import java.util.List;
import java.util.Map;

public interface ClassificationService {

    public List<ProductType> getProTypeList();

    public List<Product> getProductByType(int productType);

    public List<Map<String,Object>> getProductBySreach(String sreach);
}
