package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Course;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    InstructorRepository instructorRepository;


    @Override
    public Instructor findInstructorById(long id)
    {

        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
    }

    @Override
    public List<Instructor> findAll()
    {
        List instructorList = new ArrayList();
        instructorRepository.findAll().iterator().forEachRemaining(instructorList::add);

        return instructorList;
    }
}
