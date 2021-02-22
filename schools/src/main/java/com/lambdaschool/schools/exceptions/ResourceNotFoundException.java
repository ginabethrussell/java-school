package com.lambdaschool.schools.exceptions;

import ch.qos.logback.core.recovery.ResilientOutputStreamBase;

// extends Java Language exception - EntityNotFoundException
public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Found an error with School: " + message);
    }
}
