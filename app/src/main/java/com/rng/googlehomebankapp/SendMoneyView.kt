package com.rng.googlehomebankapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rng.googlehomebankapp.MainActivity.Companion.TRANSFER_MONEY
import com.rng.googlehomebankapp.MainActivity.Companion.USER_NAME

class SendMoneyView : Fragment() {
    private var userName: String? = "John Doe"
    private var transferMoney: String? = "$50"
    private var accountName: String? = "Bank of America- XXXX8899"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = if (it.getString(USER_NAME) == null) {
                " John Doe"
            } else {
                it.getString(USER_NAME)
            }
            transferMoney = if (it.getString(TRANSFER_MONEY) == null) {
                " $100"
            } else {
                it.getString(TRANSFER_MONEY)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_money_view, container, false)
        val userNameView = view.findViewById<TextView>(R.id.personNameValue)
        val transferMoneyView = view.findViewById<TextView>(R.id.transferAmountValue)
        val accountNameView = view.findViewById<TextView>(R.id.transferAccountValue)
        val sendMoneyViewButton = view.findViewById<Button>(R.id.sendMoney)
        val cancelViewButton = view.findViewById<Button>(R.id.cancel_action)

        userNameView.text = userName
        transferMoneyView.text = transferMoney
        accountNameView.text = accountName

        sendMoneyViewButton.setOnClickListener {
            loadVerifyIdentityView()
        }

        cancelViewButton.setOnClickListener {
        }
        return view
    }

    private fun loadCongratulationsView() {
        val fragment = CongratulationView.newInstance()
        activity?.supportFragmentManager?.beginTransaction()!!.run {
            replace(R.id.container, fragment)
            commit()
        }
    }

    private fun loadVerifyIdentityView() {
        val fragment = VerifyIdentityView.newInstance()
        activity?.supportFragmentManager?.beginTransaction()!!.run {
            replace(R.id.container, fragment)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SendMoneyView()
    }

}
