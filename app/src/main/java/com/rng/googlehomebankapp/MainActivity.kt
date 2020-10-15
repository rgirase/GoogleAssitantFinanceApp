package com.rng.googlehomebankapp

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent?.handleIntent()
    }


    private fun Intent.handleIntent() {
        when (action) {
            // When the action is triggered by a deep-link, Intent.Action_VIEW will be used
            Intent.ACTION_VIEW -> handleDeepLink(data)
            // When the action is triggered by the Google search action, the ACTION_SEARCH will be used
            // Otherwise start the app as you would normally do.
            else -> showDefaultView()
        }
    }

    private fun showDefaultView() {
        val fragment = LoginView()
        supportFragmentManager?.beginTransaction()!!.run {
            replace(R.id.container, fragment)
            commit()
        }
    }

    private fun handleDeepLink(data: Uri?) {

        when (data?.path) {
            DeepLink.SEND -> {
                val transferAmount = data.getQueryParameter(TRANSFER_MONEY)
                val userName = data.getQueryParameter(USER_NAME)

                val arguments = Bundle().apply {
                    putString(TRANSFER_MONEY, transferAmount)
                    putString(USER_NAME, userName)
                }
                updateView(SendMoneyView::class.java, arguments)
            }
            DeepLink.VERIFY -> {
                val verifyString = data.getQueryParameter("appFeature").toString()
                if (verifyString.contains("disney")) {
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.container, CongratulationView.newInstance())
                        addToBackStack(null)
                        commit()
                    }
                } else {
                    showDialog()
                }
            }
        }


    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        // Get the layout inflater
        val inflater = this.layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialogiew, null))
            // Add action buttons
            .setPositiveButton("Okay",
                DialogInterface.OnClickListener { dialog, id ->
                    val fragment = VerifyIdentityView.newInstance()
                    supportFragmentManager?.beginTransaction()!!.run {
                        replace(R.id.container, fragment)
                        commit()
                    }
                })

        builder.create()
        builder.show()
    }


    private fun updateView(
        newFragmentClass: Class<out Fragment>,
        arguments: Bundle? = null,
        toBackStack: Boolean = false
    ) {
        val currentFragment = supportFragmentManager.fragments.firstOrNull()
        if (currentFragment != null && currentFragment::class.java == newFragmentClass) {
            return
        }
        val fragment = SendMoneyView.newInstance()
        fragment.arguments = arguments

        supportFragmentManager.beginTransaction().run {
            replace(R.id.container, fragment)
            if (toBackStack) {
                addToBackStack(null)
            }
            commit()
        }
    }

    companion object {
        const val USER_NAME = "userName"
        const val TRANSFER_MONEY = "transferAmount"
        const val ACCOUNT_NAME = "accountName"
        var transferAmount = ""
    }

}
