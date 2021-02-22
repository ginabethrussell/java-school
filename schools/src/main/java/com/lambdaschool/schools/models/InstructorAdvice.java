package com.lambdaschool.schools.models;

public class InstructorAdvice
{
    private Instructor instructor;

    private SlipData advice;

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public SlipData getAdvice()
    {
        return advice;
    }

    public void setAdvice(SlipData advice)
    {
        this.advice = advice;
    }
}
