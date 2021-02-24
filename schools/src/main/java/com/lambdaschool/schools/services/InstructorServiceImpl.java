package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.*;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    InstructorRepository instructorRepository;


    @Override
    public List<Instructor> findAll()
    {
        List instructorList = new ArrayList();
        instructorRepository.findAll().iterator().forEachRemaining(instructorList::add);

        return instructorList;
    }

    @Override
    public Instructor addAdvice(long id)
    {
        Instructor updatedInstructor = instructorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor " + id + " not found!"));

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String requestURL = "https://api.adviceslip.com/advice";
        // create the responseType expected. Notice the IssPositionReturnData is the data type we are expecting back from the API!
        ParameterizedTypeReference<slip> responseType = new ParameterizedTypeReference<>()
        {
        };

        // create the response entity. do the get and get back information
        ResponseEntity<slip> responseEntity = restTemplate.exchange(requestURL,
                   HttpMethod.GET,
                  null,
                  responseType);
       // can now put our data into our object
        slip slipobj = responseEntity.getBody();
        updatedInstructor.setAdvice(slipobj.getAdvice());

        return updatedInstructor;
    }

    @Override
    public Instructor addAdvice(
        long id,
        String searchTerm)
    {
        Instructor updatedInstructor = instructorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor " + id + " not found!"));

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String requestURL = "https://api.adviceslip.com/advice/search/" + searchTerm;
        // create the responseType expected. Notice the IssPositionReturnData is the data type we are expecting back from the API!
        ParameterizedTypeReference<Slips> responseType = new ParameterizedTypeReference<>()
        {
        };

        // create the response entity. do the get and get back information
        ResponseEntity<Slips> responseEntity = restTemplate.exchange(requestURL,
            HttpMethod.GET,
            null,
            responseType);
        // can now put our data into our object
        SlipsElement slipsElement = responseEntity.getBody().getSlips().get(0);
        updatedInstructor.setAdvice(slipsElement.getAdvice());

        return updatedInstructor;
    }
}
