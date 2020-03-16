package com.example.kotlin3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas__masjid.*
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class Identitas_Masjid : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas__masjid)



        getdariserver()

        simpan.setOnClickListener{

            var data_namamasjid = namamasjid.text.toString()
            var data_alamatmasjid = alamatmasjid.text.toString()

            postkeserver(data_namamasjid,data_alamatmasjid)

            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.35.150.94/sholat/masjid_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray= response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject= jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_masjid"))


                        nama.setText(jsonObject.optString("nama_masjid"))
                        alamat.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String, data2:String){


        AndroidNetworking.post("http://10.35.150.94/sholat/proses-identitas.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
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

