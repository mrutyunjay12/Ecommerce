package com.Mrutyunjay.ProductService.Services;

import com.Mrutyunjay.ProductService.Exceptions.NotFoundException;
import com.Mrutyunjay.ProductService.ThirdPartyClients.fakestore.FakeStoreProductClient;
import com.Mrutyunjay.ProductService.ThirdPartyClients.fakestore.dtos.FakeStoreProductDTO;
import com.Mrutyunjay.ProductService.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
  //@Primary (if you are not using qualifier then this also  can use )
@Service("fakeStoreProductService")
public class FakeStorProductService implements ProductService{
private FakeStoreProductClient  fakeStoreProductClient;
@Autowired
public FakeStorProductService(FakeStoreProductClient fakeStoreProductClient )
{
    this.fakeStoreProductClient=fakeStoreProductClient;

}
public GenericProductDTO convertFakeStoreDtoToGenericProductDto(FakeStoreProductDTO fakeStoreProductDTO)
{
    GenericProductDTO genericProductDTO=new GenericProductDTO();
    genericProductDTO.setId(fakeStoreProductDTO.getId());
    genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
    genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
    genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
    genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
    genericProductDTO.setImage(fakeStoreProductDTO.getImage());
    return genericProductDTO;}

    @Override
    public GenericProductDTO getProductById(long id) throws NotFoundException {

        return convertFakeStoreDtoToGenericProductDto
                (fakeStoreProductClient.getProductById(id));

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.createProduct(product));


    }



    private GenericProductDTO getGenericProductDTO(ResponseEntity<FakeStoreProductDTO> response) {
        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();
        GenericProductDTO genericProductDTO=convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);

        return genericProductDTO;
    }
    @Override
    public List<GenericProductDTO> getAllProducts() {
    List<FakeStoreProductDTO> fakeStoreProductDTOS=fakeStoreProductClient.getAllProducts();


        List<GenericProductDTO> genericProductDtos=new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO:fakeStoreProductDTOS)
        {
            GenericProductDTO genericProductDTO=convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);
            genericProductDtos.add(genericProductDTO);

        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDTO deleteProduct(long id) {

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.deleteProduct(id));
    }
}
