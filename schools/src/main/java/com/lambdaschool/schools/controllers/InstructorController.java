package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.InstructorAdvice;
import com.lambdaschool.schools.models.SlipData;
import com.lambdaschool.schools.models.SlipReturnData;
import com.lambdaschool.schools.services.HelperFunctionsImpl;
import com.lambdaschool.schools.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/instructors")
public class InstructorController
{

    @Autowired
    private InstructorService instructorService;

    private RestTemplate restTemplate = new RestTemplate();
    @GetMapping(value = "/instructor/{instructorid}/advice")
    public ResponseEntity<?> getInstructorAdvice(@PathVariable long instructorid)
    {
        Instructor instructor = instructorService.findInstructorById(instructorid);

        // we need to tell our RestTemplate what format to expect
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        // create the url to access the API
        String requestURL = "https://api.adviceslip.com/advice";
        // create the responseType expected. Notice the IssPositionReturnData is the data type we are expecting back from the API!
        ParameterizedTypeReference<SlipReturnData> responseType = new ParameterizedTypeReference<>()
        {
        };

        // create the response entity. do the get and get back information
        ResponseEntity<SlipReturnData> responseEntity = restTemplate.exchange(requestURL,
            HttpMethod.GET,
            null,
            responseType);
        // putting the data into its own object first, prevents the data from being reported to client inside of
        // an embedded. So the response will look more like our clients are used to!
        SlipData advice = responseEntity.getBody().getSlip();

        InstructorAdvice instructorAdvice = new InstructorAdvice();
        instructorAdvice.setInstructor(instructor);
        instructorAdvice.setAdvice(advice);
        return new ResponseEntity<>(instructorAdvice,
            HttpStatus.OK);

    }
}
