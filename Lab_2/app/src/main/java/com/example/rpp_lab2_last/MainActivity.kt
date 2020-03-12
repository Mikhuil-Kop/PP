package com.example.rpp_lab2_last

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import android.content.Intent
import android.view.View
import android.widget.AdapterView.OnItemClickListener

class MainActivity : AppCompatActivity() {

    var list = ArrayList<Technology>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"
        AsyncTaskHandleJson().execute(url)

        technologies_list.setOnItemClickListener(object : OnItemClickListener {
           override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                 val i = Intent(view!!.context, ViewPager::class.java)
                 var prevPosition = position - 1
                 var nextPosition = position + 1

                 if(position == 0) prevPosition = list.lastIndex
                 if(position == list.lastIndex) nextPosition = 0

                 i.putExtra("previousImage", list[prevPosition].graphic)
                 i.putExtra("currentImage", list[position].graphic)
                 i.putExtra("nextImage", list[nextPosition].graphic)

                 startActivity(i)
            }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            var text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try{
                connection.connect()
                text = connection.inputStream.use { it.reader().use{reader -> reader.readText()} }
            }finally {
                connection.disconnect()
            }

            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    public fun handleJson(jsonString: String?) {

        val jsonArray = JSONArray(jsonString)

        var x = 1
        while(x < jsonArray.length())
        {
            val jsonObject = jsonArray.getJSONObject(x)
            var helptext = ""
            if(jsonObject.has("helptext")) helptext = jsonObject.getString("helptext")

            list.add(
                Technology(
                    jsonObject.getString("graphic"),
                    jsonObject.getString("name"),
                    helptext
                )
            )
            x++

        }
        val adapter = ListAdapter(this, list)
        technologies_list.adapter = adapter
    }
}
    }
}

