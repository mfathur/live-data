package com.mfathurz.livedataoti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // unidirectional data flow => mungkin bisa di search "unidirectional flow in android mvvm"

        /**
         * me-define active observer ke livedata2 yang ada di viewmodel
         *  agar view bisa dapet nilainya secara live
          */

        viewModel.name.observe(this,{newName->
            tv_name.text = newName
        })

        viewModel.age.observe(this,{newAge ->
            tv_age.text = newAge.toString()
        })

        viewModel.status.observe(this,{newStatus->
            tv_status.text = newStatus
        })

        btn_set_name1.setOnClickListener {
          viewModel.setName1(et_set_name1.text.toString())
        }

        btn_set_name2.setOnClickListener {
            viewModel.setName2(et_set_name2.text.toString())
        }

        btn_set_age.setOnClickListener {
            viewModel.setAge(++count)
        }
    }
}