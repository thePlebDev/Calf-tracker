package com.elliottSoftware.ecalvingtracker.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T>{
    @NonNull private final Status status;
    @NonNull private final T data;
    @NonNull private final String message;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data, @NonNull String message){
        return new Resource<>(Status.SUCCESS,data,message);
    }

    public static <T> Resource<T> loading(@Nullable T data){
        return new Resource<>(Status.LOADING,data,null);
    }
    
    public static <T> Resource<T> error(@NonNull T data, @NonNull String message){
        return new Resource<>(Status.ERROR,data,message);
    }



    public enum Status {SUCCESS,ERROR,LOADING}


    //GETTERS
    public Status getStatus(){
        return this.status;
    }
    public T getData(){
        return this.data;
    }
    public String getMessage(){
        return this.message;
    }

}
