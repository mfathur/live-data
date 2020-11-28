package com.mfathurz.livedataoti

import androidx.lifecycle.*

class MainViewModel : ViewModel() {
    //MutableLiveData => data nya masih bisa diubah
    /**
     * jika waktu define mutable livedata tanpa
     */

    //LiveData => tidak bisa diubah
    private val _name1 = MutableLiveData("kosong1")
    private val _name2 = MutableLiveData("kosong2")

    private val _age = MutableLiveData(0)
    private val _status = MutableLiveData("Anak-anak")

    //MediatorLiveData
    /**
     * digunain jika ada data source dari livedata berasal dari lebih dari 1 live data
     */
    private val _mediatorName = MediatorLiveData<String>()

    init {
        _mediatorName.addSource(_name1) {
           _mediatorName.value ="From name1:" + _name1.value
        }

        _mediatorName.addSource(_name2) {
            _mediatorName.value = "From name2:" + _name2.value
        }
    }

    //Transformation map digunain buat ngubah nilai nya live data sebelum  dikirim ke observer
    val name1: LiveData<String>
        get() = Transformations.map(_name1) {
            "Hello ${_name1.value}"
        }

    val name: LiveData<String>
        get() = _mediatorName

    val age: LiveData<Int>
        get() = _age

    /**
     * Transformation switch map digunain pas ada suatu live data
     * yang nilai nya bergantung dengan nilai dari live data
     * lain
     */
    val status: LiveData<String>
        get() = Transformations.switchMap(_age) { age ->
            when {
                age < 14 -> {
                    _status.value = "Anak-anak"
                }
                age < 40 -> {
                    _status.value = "Remaja"
                }
                age < 60 -> {
                    _status.value = "Dewasa"
                }
                else -> {
                    _status.value = "Tua"
                }
            }
            _status
        }

    fun setName1(newName: String) {
        _name1.value = newName
//        _name.postValue("asdasd")
    }

    fun setName2(newName: String) {
        _name2.value = newName
    }

    fun setAge(newAge: Int) {
        _age.value = newAge
    }

    //  penjelasan lebih lengkap nya bisa cek dokumentasi resmi livedata di developer.android.com
}



