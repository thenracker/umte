package cz.uhk.umteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cz.uhk.umteapp.prefs.Prefs
import cz.uhk.umteapp.ws.ScheduleDTO
import cz.uhk.umteapp.ws.StagService
import cz.uhk.umteapp.ws.stagService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("key", "Hi")
            startActivityForResult(intent, 777)
        }

        // list activity
        openListButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        val appStartMillis = Prefs.getAppStart()
        Toast.makeText(this,
            "$appStartMillis",
            Toast.LENGTH_LONG).show()
        Prefs.setAppStart(System.currentTimeMillis())


        wsButton.setOnClickListener {
            val call = stagService.getHarmonogram(StagService.JSON)
            call.enqueue(object: Callback<ScheduleDTO>{
                override fun onResponse(call: Call<ScheduleDTO>, response: Response<ScheduleDTO>) {
                    println("onResponse")
                    Toast.makeText(this@MainActivity,
                        response.body()?.toString(),
                        Toast.LENGTH_LONG).show()
                }
                override fun onFailure(call: Call<ScheduleDTO>, t: Throwable) {
                    println("onFailure")
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK) {
            val s = data?.getStringExtra("result")
            Toast.makeText(this, s, Toast.LENGTH_LONG).show()
        }
    }

}


/**
 * TODO
 *
 * Dnes
 * - retrofit
 *
 * - napojení harmonogramu
 *
 * https://stagws.uhk.cz/
 *
 * - kalendar - getHarmonogramRoku
 *
 *
 */

/*
OBSAH

//11.2.	JAVA. Seznámení s Android Studiem. Struktura projektu. Malé demo.
//18.2.	Activity. Intent. Listener. Override. Toast. AlertDialog.
25.2.	UI. View. ViewGroup. Layouts. Intent, ActivityResult
(Fragment, LayoutInflater, Recycler?)
4.3.	Intent. ActivityResult. RecyclerView.
11.3.	Opakování RecyclerView
18.3.	SharedPreferences. Fragment. DialogInterface.
25.3.	AsyncTask. REST API (https://stag-demo.uhk.cz/ws/web/)
1.4.	SQLite. Google Map
8.4.	GPS. Senzors.
15.4.	Service. Notification.
22.4.	Velikonoční pondělí
29.4.	3rd party libraries
6.5.	Opakování RecyclerView

TERMÍNY

18.3.	Odevzdání zadání projektu
8.4.	Odevzdání UI / Wireframe
Zkouškové období	Prezentace projektu proběhne během zkouškového období

 */