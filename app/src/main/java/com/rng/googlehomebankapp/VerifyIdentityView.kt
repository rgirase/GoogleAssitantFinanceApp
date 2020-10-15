package com.rng.googlehomebankapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class VerifyIdentityView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_identity_view, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            VerifyIdentityView()
    }
}
