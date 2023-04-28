package dark.suit.owl.nasa.webservice

import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object MSSLSocketFactory {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }

        @Throws(CertificateException::class)
        override fun checkClientTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {
        }
    })

    fun provideSSLFactory(): SSLSocketFactory {
        return try {
            // Install the all-trusting trust manager
            val ssl = SSLContext.getInstance("TLSv1")
            ssl.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            ssl.socketFactory
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}