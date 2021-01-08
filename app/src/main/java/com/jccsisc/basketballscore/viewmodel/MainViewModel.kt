package com.jccsisc.basketballscore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _scoreLocal: MutableLiveData<Int> = MutableLiveData()
    private var _scoreVisitor: MutableLiveData<Int> = MutableLiveData()

    val scoreLocal: LiveData<Int>
        get() = _scoreLocal

    val scoreVisitor: LiveData<Int>
        get() = _scoreVisitor

    init {
        resetScore() //garantizamos que nunca van a ser nulos
    }

    fun resetScore() {
        _scoreLocal.value = 0
        _scoreVisitor.value = 0
    }

    fun addPointsToScore(points: Int, isLocal: Boolean) {
        if (isLocal) {
            _scoreLocal.value = _scoreLocal.value?.plus(points)
        } else {
            _scoreVisitor.value = _scoreVisitor.value?.plus(points)
        }
    }

    fun decreaseLocalScore() {
        if (_scoreLocal.value!! > 0) {
            _scoreLocal.value = _scoreLocal.value?.minus(1)
        }
    }

    fun decreaseVisitorScore() {
        if (_scoreVisitor.value!! > 0) {
            _scoreVisitor.value = _scoreVisitor.value?.minus(1)
        }
    }
}