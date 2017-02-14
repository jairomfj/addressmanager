package br.com.addressmanager;

import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.input.EnderecoInput;
import br.com.addressmanager.model.output.EnderecoOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/endereco/{enderecoId}", method = RequestMethod.DELETE)
    public ResponseEntity<EnderecoOutput> delete(@PathVariable Long enderecoId) {

        ResponseEntity<EnderecoOutput> responseEntity;
        try {
            addressService.delete(enderecoId);
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildSuccess(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildEnderecoNotFound(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Error while deleting address: ", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/endereco", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoOutput> update(@RequestBody EnderecoInput endereco) {

        ResponseEntity<EnderecoOutput> responseEntity;
        try {
            addressService.update(buildAddress(endereco));
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildSuccess(endereco), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildInvalidEndereco(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Error while updating address: ", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/endereco/{enderecoId}", method = RequestMethod.GET)
    public ResponseEntity<EnderecoOutput> get(@PathVariable Long enderecoId) {

        ResponseEntity<EnderecoOutput> responseEntity;

        try {
            Address persistedAddress = addressService.get(enderecoId);
            EnderecoInput endereco = buildEndereco(persistedAddress);
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildSuccess(endereco), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildEnderecoNotFound(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Error while getting address: ", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/endereco/listar/{userId}", method = RequestMethod.GET)
    public ResponseEntity<EnderecoOutput> list(@PathVariable Long userId) {

        ResponseEntity<EnderecoOutput> responseEntity;
        try {
            List<Address> persistedAddress = addressService.list(userId);
            List enderecoIdInputList = persistedAddress.stream().map(this::buildEndereco).collect(Collectors.toList());
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildSuccess(enderecoIdInputList), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            responseEntity = new ResponseEntity<>(EnderecoOutput.buildInvalidEndereco(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Error while listing addresses: ", e);
        }

        return responseEntity;
    }

    private EnderecoInput buildEndereco(Address address) {
        EnderecoInput enderecoInput = new EnderecoInput();
        enderecoInput.setId(address.getId());
        enderecoInput.setRua(address.getStreet());
        enderecoInput.setCep(address.getCep());
        enderecoInput.setCidade(address.getCity());
        enderecoInput.setComplemento(address.getComplement());
        enderecoInput.setBairro(address.getNeighborhood());
        enderecoInput.setNumero(address.getNumber());
        enderecoInput.setEstado(address.getState());
        enderecoInput.setUserId(address.getUserId());
        return enderecoInput;
    }

    private Address buildAddress(EnderecoInput endereco) {
        Address address = new Address();
        address.setId(endereco.getId());
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
