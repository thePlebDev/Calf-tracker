package com.elliottSoftware.ecalvingtracker.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T>{
    private  T data;
    private  String message;

    public Resource( T data, @Nullable String message){
        this.data = data;
        this.message = message;
    }


    public static <T> Resource<T> success( T data){
        return new Resource<>(data,null);
    }

    public static <T> Resource<T> loading(T data){
        return new Resource<>(data,null);
    }

    public static <T> Resource<T> error(T data, String message){
        return new Resource<>(data,message);
    }


    //GETTERS
    public T getData(){
        return this.data;
    }
    public String getMessage(){
        return this.message;
    }

}
