package com.madpickle.timepills.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.madpickle.timepills.R
import com.madpickle.timepills.databinding.ActivityMainBinding
import com.madpickle.timepills.utils.EventObserver
import com.madpickle.timepills.utils.safeNavigate
import com.madpickle.timepills.utils.slideDown
import com.madpickle.timepills.utils.slideUp
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import androidx.viewpager2.widget.ViewPager2
import com.madpickle.timepills.SettingsFragment
import com.madpickle.timepills.reminder.ReminderListFragment

import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

import com.google.android.material.bottomnavigation.BottomNavigationView





@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityVM by viewModels()

    companion object{
        const val LOGCAT_TAG = "TIME_PILLS_TAG"
        const val INTRO_FRAGMENT = "fragment_intro"
        const val CREATE_REMINDER_FRAGMENT = "fragment_create_reminder"
        const val SETTINGS_FRAGMENT = "fragment_settings"
        const val REMINDER_LIST_FRAGMENT = "fragment_reminder_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setSelectedNavItem(R.drawable.ic_reminder_pressed)

        binding.viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> setSelectedNavItem(R.drawable.ic_reminder_pressed)
                    1 -> setSelectedNavItem(R.drawable.ic_settings_pressed)
                }
            }
        })
        setupViewPager()
    }

    override fun onStart() {
        super.onStart()
        observeNavigation()
        selectFragment()
    }
    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val firstFragment = ReminderListFragment()
        val secondFragment = SettingsFragment()
        adapter.addFragment(firstFragment)
        adapter.addFragment(secondFragment)
        binding.viewpager2.adapter = adapter
        binding.viewpager2.isUserInputEnabled = false
    }
    private fun observeNavigation() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Timber.d("$LOGCAT_TAG -- DESTINATION: ${destination.label}")
            when{
                destination.label == INTRO_FRAGMENT -> {
                    hideToolBar()
                    hideBottomNavBar()
                }
                destination.label == CREATE_REMINDER_FRAGMENT -> {
                    showToolBar(getString(R.string.create_reminder_title), true)
                    hideBottomNavBarAnimate()
                }
                destination.label == SETTINGS_FRAGMENT -> {
                    showToolBar(getString(R.string.settings_title), false)
                    setSelectedNavItem(R.drawable.ic_settings_pressed)
                    showBottomNavBar()
                }
                destination.label == REMINDER_LIST_FRAGMENT -> {
                    showToolBar(getString(R.string.reminders_title), false)
                    setSelectedNavItem(R.drawable.ic_reminder_pressed)
                    showBottomNavBar()
                }
                destination.label == null -> {                  //равнозначно возвращению на рутовый фрагмент REMINDER_LIST_FRAGMENT
                    showToolBar(getString(R.string.reminders_title), false)
                    showBottomNavBar()
                    setSelectedNavItem(R.drawable.ic_reminder_pressed)
                }
            }
        }
    }

    private fun selectFragment() {
        viewModel.showIntroFragment.observe(this, EventObserver { isFirstRun ->
            Timber.i("$LOGCAT_TAG -- FIRST RUN $isFirstRun")
            if(isFirstRun){
                navController.safeNavigate(R.id.action_global_introFragment)
            }else{
                navController.safeNavigate(R.id.action_global_reminderListFragment)
            }
        })
    }

    private fun initViews() {
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment)

        //Xml settings not working on BottomAppBar view
        //Corner radius
        val radius = resources.getDimension(R.dimen.bottom_corner_radius)   //16dp
        val bottomBarBackground = binding.bottomNavBar.background as MaterialShapeDrawable
        bottomBarBackground.shapeAppearanceModel = bottomBarBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .build()
        binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_add_task))
        binding.fab.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))

        binding.fab.setOnClickListener {
            hideBottomNavBar()
            binding.popUpButton.visibility = View.VISIBLE
            navController.safeNavigate(R.id.action_global_createReminderFragment)
        }
        binding.popUpButton.setOnClickListener {
            navController.popBackStack()
            showBottomNavBarAnimate()
        }

        binding.settingsNavItem.setOnClickListener {
//            binding.viewpager2.setCurrentItem(0,false);
            navController.safeNavigate(R.id.action_reminderListFragment_to_settingsFragment)
        }
        binding.remindersNavItem.setOnClickListener {
//            binding.viewpager2.setCurrentItem(1,false);

            navController.safeNavigate(R.id.action_global_reminderListFragment)
        }
    }

    private fun setSelectedNavItem(id: Int){
        when (id) {
            R.drawable.ic_reminder_pressed -> {
                binding.remindersNavItem.setImageResource(R.drawable.ic_reminder_pressed)
                binding.settingsNavItem.setImageResource(R.drawable.ic_settings_normal)
            }
            R.drawable.ic_settings_pressed -> {
                binding.remindersNavItem.setImageResource(R.drawable.ic_reminder_normal)
                binding.settingsNavItem.setImageResource(R.drawable.ic_settings_pressed)
            }
        }
    }

    fun hideToolBar() {
        binding.appBarLayout.visibility = View.GONE
    }

    fun showToolBar(title: String, backIsVisible: Boolean) {
        binding.appBarLayout.visibility = View.VISIBLE
        binding.toolbarTextView.text = title
        binding.popUpButton.visibility = if( backIsVisible ) View.VISIBLE else View.GONE
    }
    fun hideBottomNavBar(){
        binding.bottomNavBar.visibility = View.GONE
        binding.fab.hide()
    }
    fun hideBottomNavBarAnimate(){
        binding.bottomNavBar.slideDown()
        binding.fab.hide()
    }
    fun showBottomNavBar(){
        binding.fab.show()
        binding.bottomNavBar.visibility = View.VISIBLE
    }

    fun showBottomNavBarAnimate(){
        binding.fab.show()
        binding.bottomNavBar.slideUp()
    }
    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()

    }
}