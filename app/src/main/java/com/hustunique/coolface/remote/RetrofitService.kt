package com.hustunique.coolface.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author  : Xiao Yuxuan
 * @date    : 6/18/19
 */
class RetrofitService {
    companion object {
        val Instance = RetrofitService()
    }

    val facePPRetrofit = Retrofit.Builder()
        .baseUrl(NetConfig.facePPBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClients.facepp)
        .build()
}