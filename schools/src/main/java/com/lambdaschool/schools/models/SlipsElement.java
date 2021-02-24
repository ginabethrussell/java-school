package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This models advice Slip from
 * <br><a href="https://api.adviceslip.com/advice/search/dog">https://api.adviceslip.com/advice/search/dog</a>
 * Note that this class is not an entity so will not get saved into the database.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlipsElement
{
    /**
     * Id (Int) of this advice slip
     */
    private int id;

    /**
     * Advice (String) of this advice slip
     */
    private String advice;

    /**
     * Getter for slip id
     * @return returns the slip id (int) of this advice slip
     */
    public int getId()
    {
        return id;
    }

    /**
     * Setter for slip id
     * @param id the new slip id (int) for this advice slip
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Getter for advice
     * @return the advice (String) of this advice slip
     */
    public String getAdvice()
    {
        return advice;
    }

    /**
     * Setter for advice
     * @param advice the new advice (String) for this advice slip
     */
    public void setAdvice(String advice)
    {
        this.advice = advice;
    }
}

