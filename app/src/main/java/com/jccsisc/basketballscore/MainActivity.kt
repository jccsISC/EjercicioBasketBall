package com.jccsisc.basketballscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jccsisc.basketballscore.databinding.ActivityMainBinding
import com.jccsisc.basketballscore.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.mainVM = viewModel

        viewModel.scoreLocal.observe(this, Observer { scoreLocal ->
            binding.tvCoreLocal.text = scoreLocal.toString()
        })

        viewModel.scoreVisitor.observe(this, Observer { scoreVisitor ->
            binding.tvCoreVisitor.text = scoreVisitor.toString()
        })

        goToResult()
    }

    private fun goToResult() {

        binding.apply {

//            btnAddOneLocal.setOnClickListener { addPointToScore(1, true) }
            btnAddTwoLocal.setOnClickListener { addPointToScore(2, true) }
            btnSubtractLocal.setOnClickListener { viewModel.decreaseLocalScore() }
            btnAddOneVisitor.setOnClickListener { addPointToScore(1, false) }
            btnAddTwoVisitor.setOnClickListener { addPointToScore(2, false) }
            btnSubtractVisitor.setOnClickListener { viewModel.decreaseVisitorScore() }
            imvButtonReset.setOnClickListener { viewModel.resetScore() }

            binding.imvButtonNext.setOnClickListener {
                val intent = Intent(this@MainActivity, ScoreActivity::class.java)
                intent.putExtra(ScoreActivity.SCORE_LOCAL_KEY, viewModel.scoreLocal.value)
                intent.putExtra(ScoreActivity.SCORE_VISITOR_KEY, viewModel.scoreVisitor.value)
                startActivity(intent)
            }
        }
    }
    private fun addPointToScore(points: Int, isLocal: Boolean) {viewModel.addPointsToScore(points, isLocal)}
}
