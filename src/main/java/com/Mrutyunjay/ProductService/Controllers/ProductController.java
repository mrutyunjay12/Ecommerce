package com.Mrutyunjay.ProductService.Controllers;


import com.Mrutyunjay.ProductService.Exceptions.NotFoundException;
import com.Mrutyunjay.ProductService.Services.ProductService;
import com.Mrutyunjay.ProductService.dtos.ExceptionDtos;
import com.Mrutyunjay.ProductService.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
private ProductService productService;
@Autowired
public ProductController(@Qualifier ("fakeStoreProductService") ProductService productService)
{
    this.productService=productService;

}

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") long id) throws NotFoundException {

       return productService.getProductById(id);

    }
    public void updateProductById()
    {

    }
    @PostMapping()
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO  genericProductDTO)
    {
       return  productService.createProduct(genericProductDTO);
    }
    @GetMapping()
    public List<GenericProductDTO> getAllProducts()
    {
     return productService.getAllProducts();
    }
    @DeleteMapping("{id}")
    public GenericProductDTO deleteProduct(@PathVariable("id")  long id )
    {
     return  productService.deleteProduct(id);
    }
    //this is specefic to this controller
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDtos>  handleNotFoundException(NotFoundException notFoundException)
    {
     return new ResponseEntity<>(new ExceptionDtos(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }

}
