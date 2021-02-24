package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;


import java.util.List;

public interface InstructorService
{
    Instructor addAdvice(long id);

    Instructor addAdvice(long id, String searchTerm);

    List<Instructor> findAll();
}
