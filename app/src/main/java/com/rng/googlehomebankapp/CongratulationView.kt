package com.rng.googlehomebankapp


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_congratulation_view.*
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class CongratulationView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_congratulation_view, container, false)

        val viewKonfetti = view.findViewById<KonfettiView>(R.id.viewKonfetti)
        viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)

        val doneButton = view.findViewById<Button>(R.id.doneButton)
        val sendMonetText = view.findViewById<TextView>(R.id.accountsend)


        doneButton.setOnClickListener {
            loadLoginView()
        }

        return view
    }

    private fun loadLoginView() {
        val fragment = LoginView()
        activity?.supportFragmentManager?.beginTransaction()!!.run {
            replace(R.id.container, fragment)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CongratulationView()
    }
}
