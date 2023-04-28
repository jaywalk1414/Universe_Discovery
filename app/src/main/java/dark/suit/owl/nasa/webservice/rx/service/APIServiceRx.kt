package dark.suit.owl.nasa.webservice.rx.service

import dark.suit.owl.nasa.data.remote.APODModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceRx {

    //A

    @GET("planetary/apod")
    fun getAPODByRange(
        @Query("start_date") start_date: String?,
        @Query("end_date") end_date: String?
    ): Observable<APODModel>


    @GET("planetary/apod")
    fun getAPODByCount(
        @Query("count") count: Int?
    ): Observable<APODModel>


}