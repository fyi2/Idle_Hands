package org.sherman.magic.idlehands.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View.OnClickListener
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.TwitterAuthProvider
import com.google.firebase.auth.UserInfo
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import org.sherman.magic.idlehands.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]
    //private var mStatusTextView: TextView? = null
    //private var mDetailTextView: TextView? = null

    private val TAG = "TwitterLogin"
    private var mLoginButton: TwitterLoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configure Twitter SDK
        val authConfig = TwitterAuthConfig(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret))

        val twitterConfig = TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build()

        Twitter.initialize(twitterConfig)

        // Inflate layout (must be done after Twitter is configured)

        setContentView(R.layout.activity_main)

        // Views
        //mStatusTextView = findViewById(R.id.status)
        //mDetailTextView = findViewById(R.id.detail)
        //findViewById<Button>(R.id.button_twitter_signout).setOnClickListener(this)

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        // [START initialize_twitter_login]
        mLoginButton = findViewById<TwitterLoginButton>(R.id.button_twitter_login)
        (mLoginButton as TwitterLoginButton).setCallback(object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                Log.d(TAG, "twitterLogin:success" + result)
                handleTwitterSession(result.data)
            }

            override fun failure(exception: TwitterException) {
                Log.w(TAG, "twitterLogin:failure", exception)
                updateUI(null)
            }
        })
        // [END initialize_twitter_login]

    }

    // Menu Code
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Attach the menu to the main activity
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // What to do when a specific menu item is selected.

        when (item?.itemId) {
            (R.id.menuLogout) -> {
                signOut()
            }
            else -> {
                signOut()
            }
        }

        // Both Menu forms end with this.
        return super.onOptionsItemSelected(item)
    }


    // [START auth_with_twitter]
    private fun handleTwitterSession(session: TwitterSession) {
        Log.d(TAG, "handleTwitterSession:" + session)
        // [START_EXCLUDE silent]
        //TODO: showProgressDialog()
        // [END_EXCLUDE]

        val credential = TwitterAuthProvider.getCredential(
                session.authToken.token,
                session.authToken.secret)

        mAuth?.signInWithCredential(credential)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth?.getCurrentUser()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@MainActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // [START_EXCLUDE]
                    // TODO: hideProgressDialog()
                    // [END_EXCLUDE]
                }
    }
    // [END auth_with_twitter]

    private fun signOut() {
        mAuth?.signOut()
        TwitterCore.getInstance().sessionManager.clearActiveSession()

        updateUI(null)
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()
        updateUI(currentUser)
    }

    // [START on_activity_result]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the Twitter login button.
        mLoginButton?.onActivityResult(requestCode, resultCode, data)
    }



    private fun updateUI(user: FirebaseUser?) {
        //TODO: hideProgressDialog()
        if (user != null) {
            Log.d(TAG, "<<<==== updateUI user available")
            findViewById<Button>(R.id.button_twitter_login).setVisibility(View.GONE)
        } else {
            Log.d(TAG, "<<<==== updateUI user UNavailable")
            findViewById<Button>(R.id.button_twitter_login).setVisibility(View.VISIBLE)
        }
    }



    override fun onClick(v: View?) {
        val i = v?.getId()
        if (i == R.id.button_twitter_signout) {
            signOut()
        }
    }

    fun createDisaster(v: View?){
        val intent = Intent(this, CreateDisasterActivity::class.java)
        startActivity(intent)
    }

}
