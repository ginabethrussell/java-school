package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;


import java.util.List;

public interface InstructorService
{
    Instructor findInstructorById(long id);

    List<Instructor> findAll();
}
