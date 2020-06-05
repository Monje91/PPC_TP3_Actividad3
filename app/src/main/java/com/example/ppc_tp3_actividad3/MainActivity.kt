package com.example.ppc_tp3_actividad3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar
        txtView = findViewById(R.id.text_view) as TextView
        val btn = findViewById(R.id.show_button) as Button
        btn.setOnClickListener {
            i = progressBar!!.progress
            Thread(Runnable {
                while (i < 100) {
                    i += 5
                    // Actualiza la barra de progreso y muestra el valor actual
                    handler.post(Runnable {
                        progressBar!!.progress = i
                        txtView!!.text = i.toString() + "/" + progressBar!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }).start()
        }

        val progressBar = ProgressBar(this)
        //establecer altura y ancho del progressBar
        progressBar.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        //accediendo al diseño donde la barra de progreso se sumará
        val layout = findViewById<RelativeLayout>(R.id.layout)
        // Agregar el ProgressBar a nuestro diseño
        layout?.addView(progressBar)

        //Acceder al boton que manejara los eventos
        // donde la barra se mostrara o no
        val button = findViewById<Button>(R.id.button)

        //set a click listener to show/hide progressBar added in RelativeLayout.
        button?.setOnClickListener {
            val visibility = if (progressBar.visibility == View.GONE){
                View.VISIBLE
            }else
                View.GONE
            progressBar.visibility = visibility

            //setting button text
            //if we click "stop loading" button, text of button will change
            // to "start loading.." and vice versa
            val btnText = if (progressBar.visibility == View.GONE)
                "Comenzar carga..."
            else
                "Frenar Carga"
            button.text = btnText
        }

        botonCambiar.setOnClickListener {
            val intent: Intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

    }

}
