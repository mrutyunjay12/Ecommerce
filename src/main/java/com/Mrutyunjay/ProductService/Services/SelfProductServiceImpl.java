package com.Mrutyunjay.ProductService.Services;

import com.Mrutyunjay.ProductService.dtos.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements  ProductService{
    @Override
    public GenericProductDTO getProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO deleteProduct(long id) {
        return null;
    }
}
