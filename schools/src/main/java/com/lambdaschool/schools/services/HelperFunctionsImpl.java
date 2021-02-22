package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value ="helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions
{

    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause)
    {
        // need to loop through the cause to check for validation errors
        // validation errors can happen JSON <-> JACKSON <-> OBJECT <-> HIBERNATE <-> RDBMS
        while((cause != null) && !(cause instanceof org.hibernate.exception.ConstraintViolationException || cause instanceof MethodArgumentNotValidException))
        {
            cause = cause.getCause();
        }
        List<ValidationError> listVE = new ArrayList<>();
        if (cause != null)
        {
            // hibernate only gives us one at a time
            if(cause instanceof org.hibernate.exception.ConstraintViolationException)
            {
                org.hibernate.exception.ConstraintViolationException ex = (org.hibernate.exception.ConstraintViolationException) cause;
                ValidationError newVe = new ValidationError();
                newVe.setCode(ex.getMessage());
                newVe.setMessage(ex.getConstraintName());
                listVE.add(newVe);
            }else {
                // now deal with Jackson Errors, Jackson will give us a list we need to loop through
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for (FieldError err : fieldErrors)
                {
                    ValidationError newVe = new ValidationError();
                    newVe.setCode(err.getField());
                    newVe.setMessage(err.getDefaultMessage());
                    listVE.add(newVe);
                }

            }
        }
        return listVE;
    }
}
