package com.jasonjerome.pulsarapatientsync.service

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jasonjerome.pulsarapatientsync.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PatientEndpointFactory : KoinComponent {

    private const val BASE_URL = BuildConfig.PATIENT_ENDPOINT

    fun makeEndpointService(): PatientEndpointService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(makeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(PatientEndpointService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {

        if (BASE_URL.compareTo(BuildConfig.TEST_ENDPOINT, true) == 0) {
            val localClient: LocalClient by inject()
            return OkHttpClient.Builder()
                .addInterceptor(localClient)
                .addInterceptor(makeLoggingInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
        }

        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}

/**
 * This is a simple way to mock an endpoint for development
 * I left this here to show some of my work
 */
class LocalClient(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        val request = chain.request()
        when (url.encodedPath()) {
            "/patients" -> {
                val response = getMockDataFile("patients.json")
                return Response.Builder()
                    .code(200)
                    .message(response)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .body(ResponseBody.create(MediaType.parse("application/json"), response))
                    .addHeader("content-type", "application/json")
                    .build()
            }
            else -> {
                return chain.proceed(request)
            }
        }

    }

    private fun getMockDataFile(fileName: String): String {
        var jsonString: String
        val mockFile = "mockData/$fileName"
        this.context.assets.open(mockFile).apply {
            jsonString = this.readBytes().toString(Charsets.UTF_8)
        }.close()
        return jsonString
    }

}