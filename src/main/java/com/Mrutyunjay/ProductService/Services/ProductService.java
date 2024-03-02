package com.Mrutyunjay.ProductService.Services;

import com.Mrutyunjay.ProductService.Exceptions.NotFoundException;
import com.Mrutyunjay.ProductService.dtos.GenericProductDTO;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface ProductService {
    public GenericProductDTO getProductById(long id) throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public List<GenericProductDTO> getAllProducts();
    public GenericProductDTO deleteProduct(long id );


}
