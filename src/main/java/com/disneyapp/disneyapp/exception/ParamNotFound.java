package com.disneyapp.disneyapp.exception;


@SuppressWarnings("serial")
public class ParamNotFound extends RuntimeException {

    public ParamNotFound(String error) {
        super(error);
    }
}
