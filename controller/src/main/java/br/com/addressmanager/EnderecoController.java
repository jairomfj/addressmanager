package br.com.addressmanager;

import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.EnderecoInput;
import br.com.addressmanager.model.EnderecoOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {
    private static Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);


    private final AddressService addressService;

    @Autowired
    public EnderecoController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/endereco", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoOutput> create(@RequestBody EnderecoInput endereco) {

        ResponseEntity<EnderecoOutput> responseEntity;
        try {
            Address persistedAddress = addressService.create(buildAddress(endereco));
            endereco.setId(persistedAddress.getId());
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildSuccess(endereco), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildInvalidEndereco(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Error while creating address: ", e);
        }

        return responseEntity;
    }

    private Address buildAddress(EnderecoInput endereco) {
        Address address = new Address();
        address.setStreet(endereco.getRua());
        address.setCep(endereco.getCep());
        address.setCity(endereco.getCidade());
        address.setComplement(endereco.getComplemento());
        address.setNeighborhood(endereco.getBairro());
        address.setNumber(endereco.getNumero());
        address.setState(endereco.getEstado());
        address.setUserId(endereco.getUserId());
        return address;
    }
}
