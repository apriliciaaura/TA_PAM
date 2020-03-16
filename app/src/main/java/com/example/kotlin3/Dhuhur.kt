package com.example.kotlin3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dhuhur.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_jadwal.balik
import kotlinx.android.synthetic.main.activity_shubuh.*
import kotlinx.android.synthetic.main.activity_shubuh.simpan
import org.json.JSONObject

class Dhuhur : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dhuhur)

        val context = this

        balik.setOnClickListener {
            val intent = Intent(context,Jadwal::class.java)
            startActivity(intent)
        }


        getdariserver()

        simpan.setOnClickListener{

            var data_waktudhuhur = waktudhuhur.text.toString()

            postkeserver(data_waktudhuhur)

            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.35.150.94/sholat/jadwal_sholat_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                        waktu_dhuhur.setText(jsonObject.optString("dhuhur"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String){


        AndroidNetworking.post("http://10.35.150.94/sholat/dhuhur-proses.php")
            .addBodyParameter("dhuhur", data1)
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
