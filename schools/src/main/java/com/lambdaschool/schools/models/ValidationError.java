package com.lambdaschool.schools.models;

// POJO to hold validation error data
public class ValidationError
{
    // will hold field of the problem
    private String code;
    // cause of the problem
    private String message;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ValidationError{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
