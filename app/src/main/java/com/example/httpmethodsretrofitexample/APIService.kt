package com.example.httpmethodsretrofitexample

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface APIService {


    /*
       POST METHOD
    */


    // Raw JSON
    @POST("/api/users")
    suspend fun postTest(@Body requestBody: RequestBody): Response<ResponseBody>



    /*****************************************************************************************************************************************************/


    /*
        GET METHOD
    */

    //https://reqres.in/api/users/2
    @GET("/api/users/2")
    suspend fun getTest(): Response<ResponseBody>

    //https://reqres.in/api/users?page=2
    @GET("/api/users/")
    suspend fun getTest2(@Query("page") page: String?): Response<ResponseBody>

    //https://reqres.in/api/users/{Id}
    @GET("/api/users/")
    suspend fun getTest3(@Path("Id") employeeId: String): Response<ResponseBody>




    @GET("/api/v1/employees")
    suspend fun getEmployees(): Response<ResponseBody>


    // Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/api/users")
    suspend fun getEmployees(@Query("page") page: String?): Response<ResponseBody>


    // Request using @Path (e.g https://reqres.in/api/users/53 - This URL is just an example, it's not working)
    @GET("/api/users/{Id}")
    suspend fun getEmployee(@Path("Id") employeeId: String): Response<ResponseBody>


    /*****************************************************************************************************************************************************/

    /*
       PUT METHOD
    */

    //self test
    @PUT("/api/users/2")
    suspend fun updateTest(@Body requestBody: RequestBody): Response<ResponseBody>



    //demo
    @PUT("/api/users/2")
    suspend fun updateEmployee(@Body requestBody: RequestBody): Response<ResponseBody>


    /*****************************************************************************************************************************************************/


    /*
       DELETE METHOD
    */

    @DELETE("/typicode/demo/posts/1")
    suspend fun deleteEmployee(): Response<ResponseBody>


    /*****************************************************************************************************************************************************/

}