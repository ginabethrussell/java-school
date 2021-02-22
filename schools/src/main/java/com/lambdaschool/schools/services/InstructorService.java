package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.Student;

public interface InstructorService
{
    Instructor findInstructorById(long id);
}
