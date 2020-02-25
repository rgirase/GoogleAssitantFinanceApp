package com.rng.googlehomebankapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

            data?.run {
                val transferAmount = getQueryParameter("transferAmount")
                val userName = getQueryParameter("userName")
                val accountName = getQueryParameter("accountName")
                val transferType = getQueryParameter("transferType")

                val arguments = Bundle().apply {
                    putString("transferAmount", transferAmount)
                    putString("userName", userName)
                    putString("accountName", accountName)
                }
                updateView(SendMoneyView::class.java, arguments)
            }


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

}
