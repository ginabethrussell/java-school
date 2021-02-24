package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController
{

    @Autowired
    private InstructorService instructorService;


    @GetMapping(value = "/instructors")
    public ResponseEntity<?> listAllInstructors()
    {
        List<Instructor> instructorList = new ArrayList<>();
        instructorList = instructorService.findAll();

        return new ResponseEntity<>(instructorList,
            HttpStatus.OK);
    }
    private RestTemplate restTemplate = new RestTemplate();
    @GetMapping(value = "/instructor/{instructorid}/advice")
    public ResponseEntity<?> getInstructorAdviceById(@PathVariable Long instructorid)
    {
        Instructor instructor = instructorService.addAdvice(instructorid);

        return new ResponseEntity<>(instructor,
            HttpStatus.OK);
    }

    @GetMapping(value = "/instructor/{instructorid}/advice/{searchTerm}", produces = "application/json")
    public ResponseEntity<?> getInstructorAdviceByIdandSearch(@PathVariable Long instructorid, @PathVariable String searchTerm)
    {
        Instructor instructor = instructorService.addAdvice(instructorid, searchTerm);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

}
