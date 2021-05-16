package com.example.infedistest.utils

data class Resource<out T>(val status:Status,val data:T?,val message:String?) {

    companion object{
        fun <T> success (data:T?):Resource<T>{
            return Resource(Status.SUCCESS,data,null)
        }

        fun <T> failed(data:T?,message: String?):Resource<T>{
            return Resource(Status.FAILED,data,message)
        }

        fun <T> loading(data:T?):Resource<T>{
            return Resource(Status.LOADING,data,null)
        }
    }
}