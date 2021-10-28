package com.elliottSoftware.ecalvingtracker.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T>{
    @NonNull private final T data;
    @NonNull private final String message;

    public Resource( @Nullable T data, @Nullable String message){
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data, @NonNull String message){
        return new Resource<>(data,message);
    }

    public static <T> Resource<T> loading(@Nullable T data,String message){
        return new Resource<>(data,message);
    }

    public static <T> Resource<T> error(@NonNull T data, @NonNull String message){
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
