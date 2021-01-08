package com.jccsisc.basketballscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jccsisc.basketballscore.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding

    companion object {
        const val SCORE_LOCAL_KEY = "scoreLocal"
        const val SCORE_VISITOR_KEY = "scoreVisitor"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {

            val scoreLocal = intent.extras!!.getInt(SCORE_LOCAL_KEY)
            val scoreVisitor = intent.extras!!.getInt(SCORE_VISITOR_KEY)

            when {
                scoreLocal == 0 && scoreVisitor == 0 -> {  tvMessage.text = getString(R.string.score_in_zero) }
                scoreLocal > scoreVisitor -> {
                    imvLocal.setImageDrawable(ContextCompat.getDrawable(this@ScoreActivity, R.drawable.feliz))
                    imvVisitor.setImageDrawable(ContextCompat.getDrawable(this@ScoreActivity, R.drawable.conmocionado))
                    tvMessage.text = getString(R.string.winning_local)
                }
                scoreLocal < scoreVisitor -> {
                    imvLocal.setImageDrawable(ContextCompat.getDrawable(this@ScoreActivity, R.drawable.conmocionado))
                    imvVisitor.setImageDrawable(ContextCompat.getDrawable(this@ScoreActivity, R.drawable.feliz))
                    tvMessage.text = getString(R.string.winning_visitor)
                }
                scoreLocal == scoreVisitor -> {
                    imvTie.setImageDrawable(ContextCompat.getDrawable(this@ScoreActivity, R.drawable.triste))
                    tvMessage.text = getString(R.string.its_a_tie)
                }
            }
            tvScore.text = getString(R.string.result, scoreLocal, scoreVisitor)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
