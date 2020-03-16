package com.example.kotlin3

import android.content.Intent
import android.os.Bundle
import android.renderscript.RenderScript
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_marquee.*
import kotlinx.android.synthetic.main.activity_pengumuman.*
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        identitasmasjid.setOnClickListener {
            val intent = Intent(context,Identitas_Masjid::class.java)
            startActivity(intent)
        }
        jadwal.setOnClickListener {
            val intent = Intent(context,Jadwal::class.java)
            startActivity(intent)
        }

        pengumuman.setOnClickListener {
            val intent = Intent(context,Pengumuman::class.java)
            startActivity(intent)
        }
        himbauan.setOnClickListener {
            val intent = Intent(context,Marquee::class.java)
            startActivity(intent)
        }
       acara.setOnClickListener{
           val intent = Intent(context,Tagline::class.java)
           startActivity(intent)
       }

        show.setOnClickListener {
            val intent = Intent(context,SlideShow::class.java)
            startActivity(intent)
        }

        himbauan()
        isitagline()

        isihimbauan.setSelected(true)
    }

    fun himbauan() {

        AndroidNetworking.get("http://172.30.16.192/sholat/marquee_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)


                        isihimbauan.setText(jsonObject.optString("isi_marquee"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun isitagline() {

        AndroidNetworking.get("http://10.35.150.94/sholat/tagline_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) { // do anything with response
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))


                        isitagline.setText(jsonObject.optString("isi_tagline"))
                    }
                }

                override fun onError(anError: ANError) { // handle error
                    Log.i("_err", anError.toString())
                }
            })
    }

}
