package com.Mrutyunjay.ProductService.ThirdPartyClients.fakestore;

import ch.qos.logback.core.CoreConstants;
import com.Mrutyunjay.ProductService.Exceptions.NotFoundException;
import com.Mrutyunjay.ProductService.ThirdPartyClients.fakestore.dtos.FakeStoreProductDTO;
import com.Mrutyunjay.ProductService.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Component
public class FakeStoreProductClient {
    @Value("${fakestore.api.baseuri}")
     private String fakeStoreAPiBaseURL;
    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;
    private final String productPath="/products";
    private String productUrl=fakeStoreAPiBaseURL+productPath+"/{id}";
    private String productRequestURl=fakeStoreAPiBaseURL+fakeStoreProductPath;

    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder )
    {
        this.restTemplateBuilder=restTemplateBuilder;

    }


    public FakeStoreProductDTO getProductById(long id) throws NotFoundException {
        System.out.println(fakeStoreAPiBaseURL);
        System.out.println(productUrl);
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response=restTemplate.getForEntity(productUrl, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();
        if(fakeStoreProductDTO==null)
        {
            throw new NotFoundException("Product with id"+ id+" not found");

        }
        return fakeStoreProductDTO;

    }


    public FakeStoreProductDTO createProduct(GenericProductDTO product) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.
                postForEntity
                        (productRequestURl,
                                product,
                                FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return fakeStoreProductDTO;

    }




    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]>response=restTemplate.getForEntity(productRequestURl,FakeStoreProductDTO[].class);
        // ParameterizedTypeReference<List<FakeStoreProductDTO>>
        FakeStoreProductDTO[] fakeStoreProductDTOS=response.getBody();
        return Arrays.asList(fakeStoreProductDTOS);


    }


    public FakeStoreProductDTO deleteProduct(long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response= restTemplate.exchange(productUrl, HttpMethod.DELETE,null,FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return fakeStoreProductDTO;
    }
}


