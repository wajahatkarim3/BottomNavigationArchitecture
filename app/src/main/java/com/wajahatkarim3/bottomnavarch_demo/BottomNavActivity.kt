package com.wajahatkarim3.bottomnavarch_demo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_bottom_nav.*

class BottomNavActivity : AppCompatActivity() {

    val sectionHomeWrapper: FrameLayout by lazy { section_home_wrapper }
    val sectionDashboardWrapper: FrameLayout by lazy { section_dashboard_wrapper }
    val sectionNotificationsWrapper: FrameLayout by lazy { section_notification_wrapper }

    val navHomeController: NavController by lazy { findNavController(R.id.section_home) }
    val navHomeFragment: Fragment by lazy { section_home }
    val navDashboardController: NavController by lazy { findNavController(R.id.section_dashboard) }
    val navDashboardFragment: Fragment by lazy { section_dashboard }
    val navNotificationController: NavController by lazy { findNavController(R.id.section_notification) }
    val navNotificationFragment: Fragment by lazy { section_notification }

    var currentController: NavController? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                currentController = navHomeController

                sectionHomeWrapper.visibility = View.VISIBLE
                sectionDashboardWrapper.visibility = View.INVISIBLE
                sectionNotificationsWrapper.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                currentController = navDashboardController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionDashboardWrapper.visibility = View.VISIBLE
                sectionNotificationsWrapper.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                currentController = navNotificationController

                sectionHomeWrapper.visibility = View.INVISIBLE
                sectionDashboardWrapper.visibility = View.INVISIBLE
                sectionNotificationsWrapper.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        currentController?.navigateUp()
    }

    override fun onBackPressed() {
        currentController
            ?.let { if (it.popBackStack().not()) finish() }
            .or { finish() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        sectionHomeWrapper.visibility = View.VISIBLE
        sectionDashboardWrapper.visibility = View.INVISIBLE
        sectionNotificationsWrapper.visibility = View.INVISIBLE
    }
}

fun <T> T?.or(compute: () -> T): T = this ?: compute()
