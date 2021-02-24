package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


/**
 * The entity allowing interaction with the courses table
 */
@Entity
@Table(name = "courses")
public class Course
    extends Auditable
{
    /**
     * Primary key (long) for this course
     */
    // custom swagger doc
    @ApiModelProperty(name = "course id",
        value = "primary key for course",
        required = true,
        example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseid;

    /**
     * Name (String) of this Course. Cannot be null and must be unique
     */
    @ApiModelProperty(name = "Course Name",
        value = "The name of the course",
        required = true,
        example = "Some Course Name")
    @NotNull(message = "Course name is required") // for Jackson
    @Size(min = 2, max = 50, message = "Course name length must be between 2 and 50 characters")
    @Column(nullable = true,
        unique = true) // this is for Hibernate
    private String coursename;

    /**
     * The instructor object (Instructor) of this course
     * <br>
     * Forms a Many to one relationship between course and instructor.
     * An instructor has many courses!
     */
    @ApiModelProperty(name = "Instructor",
        value = "The instructor for this course",
        required = true)
    @ManyToOne
    @JoinColumn(name = "instructorid",
        nullable = false)
    @JsonIgnoreProperties(value = "courses",
        allowSetters = true)
    private Instructor instructor;

    /**
     * Part of the join relationship between course and students
     * connects course to a course student combination
     */
    @ApiModelProperty(name = "students",
    value = "list of students enrolled in the course")
    @OneToMany(mappedBy = "course",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "course",
        allowSetters = true)
    private Set<StudCourses> students = new HashSet<>();

    /**
     * Getter for primary key of this course
     *
     * @return the primary key (long) for this course
     */
    public long getCourseid()
    {
        return courseid;
    }

    /**
     * Setter for the primary key of this course
     *
     * @param courseid the new primary key (long) for this course
     */
    public void setCourseid(long courseid)
    {
        this.courseid = courseid;
    }

    /**
     * Getter for the name of this course
     *
     * @return The name (String) of this course
     */
    public String getCoursename()
    {
        return coursename;
    }

    /**
     * Setter for the name of this course
     *
     * @param coursename the new name (String) for this course
     */
    public void setCoursename(String coursename)
    {
        this.coursename = coursename;
    }

    /**
     * Getter for the course student combinations for this course
     *
     * @return A list of course student combinations for this course
     */
    public Set<StudCourses> getStudents()
    {
        return students;
    }

    /**
     * Setter for the course student combinations for this course
     *
     * @param students A new list of course student combinations associated with course
     */
    public void setStudents(Set<StudCourses> students)
    {
        this.students = students;
    }

    /**
     * Getter for the instructor assigned to this course
     *
     * @return the full instructor object assigned to this course.
     */
    public Instructor getInstructor()
    {
        return instructor;
    }

    /**
     * Setter for instructor
     *
     * @param instructor the new instructor for this course
     */
    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }
}
