package com.example.httpmethodsretrofitexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.io.File
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        post_button.setOnClickListener {
            postMethonTest()
        }

        get_button.setOnClickListener {
            //getMethod()
            getMethodTest()
        }

        get_button_page_2.setOnClickListener {
            //getMethod()
            getMethodTest2()
        }

        put_button.setOnClickListener {
            //putMethod()
            putMethodTest()
        }

        delete_button.setOnClickListener { deleteMethod() }

    }

    private fun postMethonTest()  {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", "morpheus")
        jsonObject.put("job", "leader")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.postTest(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.e("Pretty Printed JSON :", prettyJson)

                    //把json转为一个Data类的对象
                    val jsonData: JsonData = gson.fromJson(prettyJson, JsonData::class.java)
                    Log.e(TAG, "jsonData.name: "+jsonData.name )
                    Log.e(TAG, "jsonData.job: "+jsonData.job )
                    Log.e(TAG, "jsonData.id: "+jsonData.id )
                    Log.e(TAG, "jsonData.createdAt: "+jsonData.createdAt )

                    val testJson = gson.toJson(jsonData)
                    Log.e(TAG, "testJson: $testJson")

                    Log.e(TAG, "postTest: response.code:"+response.code() )
                    val json_code_textview = response.code()
                    Log.e(TAG, "postTest: json_code_textview:$json_code_textview")

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_code_textview", json_code_textview)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    private fun getMethodTest() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = service.getTest()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    private fun getMethodTest2() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = service.getTest2("2")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    private fun getMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dummy.restapiexample.com")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = service.getEmployees()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    private fun putMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", "Nicole")
        jsonObject.put("job", "iOS Developer")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {

            // Do the PUT request and get response
            val response = service.updateEmployee(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    //https://reqres.in/api/users/2
    private fun putMethodTest() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .build()

        //https://reqres.in/api/users/2
        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("users", "2")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {

            // Do the PUT request and get response
            val response = service.updateTest(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    private fun deleteMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the DELETE request and get response

            val response = service.deleteEmployee()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@MainActivity.startActivity(intent)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }


    private fun getFileFromAssets(context: Context, fileName: String): File =
        File(context.cacheDir, fileName)
            .also {
                if (!it.exists()) {
                    it.outputStream().use { cache ->
                        context.assets.open(fileName).use { inputStream ->
                            inputStream.copyTo(cache)
                        }
                    }
                }
            }
}