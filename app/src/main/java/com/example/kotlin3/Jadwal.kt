package com.example.kotlin3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class Jadwal : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)


        balik.setOnClickListener {
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }
        shubuh.setOnClickListener {
            val intent = Intent(context,com.example.kotlin3.shubuh::class.java)
            startActivity(intent)
        }
        dhuha.setOnClickListener {
            val intent = Intent(context,Dhuha::class.java)
            startActivity(intent)
        }
        dhuhur.setOnClickListener {
            val intent = Intent(context,Dhuhur::class.java)
            startActivity(intent)
        }
        ashar.setOnClickListener {
            val intent = Intent(context,Ashar::class.java)
            startActivity(intent)
        }
        maghrib.setOnClickListener {
            val intent = Intent(context,Maghrib::class.java)
            startActivity(intent)
        }
        isya.setOnClickListener {
            val intent = Intent(context,Isha::class.java)
            startActivity(intent)
        }


        getdariserver()
    }

    fun getdariserver(){

        AndroidNetworking.get("http://172.30.16.192/sholat/jadwal_sholat_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                        txt1.setText(jsonObject.optString("shubuh"))
                        txt2.setText(jsonObject.optString("dhuhur"))
                        txt3.setText(jsonObject.optString("ashar"))
                        txt4.setText(jsonObject.optString("maghrib"))
                        txt5.setText(jsonObject.optString("isha"))
                        txt6.setText(jsonObject.optString("dhuha"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }


}
