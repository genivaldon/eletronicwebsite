package org.acme.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.acme.dto.CustomerDTO;
import org.acme.service.CustomerService;
import org.jboss.logging.annotations.Param;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/customers")
public class customerController {
    
    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDTO customerDTO){
        try{
            customerService.createNewCostumer(customerDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeCustomer(@PathParam("id") long id, CustomerDTO custumerDTO){

        try {
            customerService.changeCustomer(id, custumerDTO);
            return Response.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response DeleteCustomer (@PathParam("id") long id){

        try{
            customerService.deleteCustomer(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();

        }
}
@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public CustomerDTO findCustomerById (@PathParam("id") Long id ){
    
        CustomerDTO customer = customerService.findCustomerById(id);
        return customer;
   
}
}
