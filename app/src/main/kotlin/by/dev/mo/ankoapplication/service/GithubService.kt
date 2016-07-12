package by.dev.mo.ankoapplication.service

import by.dev.mo.ankoapplication.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface GithubService {
    @GET("/users")
    fun listUsers(@Query("since") offset: Int): Observable<List<GithubUser>>
}