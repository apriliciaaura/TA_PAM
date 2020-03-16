package com.example.kotlin3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_ashar.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_jadwal.balik
import kotlinx.android.synthetic.main.activity_shubuh.*
import kotlinx.android.synthetic.main.activity_shubuh.simpan
import org.json.JSONObject

class Ashar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ashar)
        val context = this
        balik.setOnClickListener {
            val intent = Intent(context,Jadwal::class.java)
            startActivity(intent)
        }


        getdariserver()

        simpan.setOnClickListener{

            var data_waktuashar = waktuashar.text.toString()

            postkeserver(data_waktuashar)

            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)

        }
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

                        waktu_ashar.setText(jsonObject.optString("ashar"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String){


        AndroidNetworking.post("http://172.30.16.192/sholat/ashar-proses.php")
            .addBodyParameter("ashar", data1)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {


                }

                override fun onError(anError: ANError) { // handle error

                }
            })
    }
}
