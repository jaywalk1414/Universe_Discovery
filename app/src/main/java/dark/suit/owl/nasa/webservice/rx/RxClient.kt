package dark.suit.owl.nasa.webservice.rx

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dark.suit.owl.nasa.webservice.MSSLSocketFactory
import dark.suit.owl.nasa.webservice.rx.service.APIServiceRx
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RxClient {
    private val isExecutable = false
    private val retryCount = 0
    private val api: APIServiceRx? = null

    private val baseUrl = "https://api.nasa.gov/"


    val apiServiceRx: APIServiceRx?
        get() {
            if (apiServiceRxInstance == null) apiServiceRxInstance = createService(
                APIServiceRx::class.java, baseUrl
            )
            return apiServiceRxInstance
        }

    companion object {

        private var apiServiceRxInstance: APIServiceRx? = null
        const val TIME_OUT = 2

        private const val token = "OQc8Q6796WcCP9Fr153dpD0qRFUgLAe5QKdgnGgO";

        var instance: RxClient? = null
            get() {
                if (field == null) {
                    field = RxClient()
                }
                return field
            }
            private set

        private fun <S> createService(serviceClass: Class<S>, baseURL: String): S {
            val interceptor = Interceptor {
                    chain ->
                val newRequest: Request = chain.request().newBuilder()
                        .addHeader("api_key", "Bearer $token")
                        .build()
                    chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .sslSocketFactory(MSSLSocketFactory.provideSSLFactory())
                .addInterceptor(interceptor)
                .build()

            val builder = Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

            val retrofit = builder.build()
            return retrofit.create(serviceClass)
        }
    }
}