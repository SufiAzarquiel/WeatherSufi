package net.azarquiel.weathersufi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.recyclerviewdays.adapter.Adapter
import net.azarquiel.weathersufi.model.Result
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private lateinit var rvDays: RecyclerView

    // 5 steps to implement the RecyclerView
    // 1. Create the RecyclerView in the layout || done
    // 2. Create the item or row layout || done
    // 3. Create the adapter || done
    // 4. Initialize the RecyclerView
    // 5. Pass the data to the RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDays = findViewById(R.id.rvDays)

        initRv()
        getData()
    }

    private fun initRv() {
        adapter = Adapter(this, R.layout.rowday)
        rvDays.adapter = adapter
        rvDays.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        GlobalScope.launch {
            // Get JSON data from the internet REMEMBER TO ASK FOR INTERNET PERMISSION
            val jsontxt = URL(
                "https://api.openweathermap.org/data/2.5/forecast?q=Toledo,es&APPID=601c9db344b44f9774ef76a4c07979b1&lang=sp&units=metric"
            ).readText(Charsets.UTF_8)
            // Parse JSON data with GSON library and pass it to the adapter
            launch(Main) {
                var result = Gson().fromJson(jsontxt, Result::class.java)
                for (day in result.days) {
                    Log.d("DIA", day.weather[0].icon)
                }
                adapter.setDays(result.days)
            }
        }
    }
}