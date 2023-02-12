package com.ayushwalker.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        setCurrentFragment(firstFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(firstFragment)
                R.id.miMessages -> setCurrentFragment(secondFragment)
                R.id.miProfile -> setCurrentFragment(thirdFragment)
            }
            true // this line is very important,as it is a lambda function, it expects a boolean return value, in our case we return true as we handle the click listener
        }

        bottomNavigationView.getOrCreateBadge(R.id.miMessages).apply {
            number = 10001
            isVisible = true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}

/*
STEPS/NOTES:
1. Since the dependencies is not in the gradle, we need to add ( implementation 'com.google.android.material:material:1.9.0-alpha01' )
    in the build.gradle (:app) inside the dependencies
2. Now we need to create a menu now, to do that go:
    right click on res -> new -> android Resource Directory -> select resource type to Menu -> Click OK.
    This will create new resource file (named menu) inside the res directory
3. Now right click on menu -> new -> menu resource file -> named it (For ex: bottom_nav_menu) -> OK
4. Since we are creating three Menu bar, we need to create three image asset (icon) for that..!
5. after that add items in the menu xml file..
6. Now add FrameLayout and BottomNavigationView in the activity_main.xml file and in BottomNavigationView, add  ( app:menu="@menu/bottom_nav_menu" )
    for referencing the menu items we created..
7. Now create three fragments for three different menu options.
8. After creating fragments, we need to handle menu bar clicks and changing of fragments on the user basis.
    We done this using a function we named as setCurrentFragments for setting current fragments. and
    bottomNavigationView.setOnItemSelectedListener for click listeners,
9. Also if we want to add badge ( badge is something which is written above the icon, like we saw in unread messages, that is easy) just use
    bottomNavigationView.getOrCreateBadge to add or get the number. This function takes the id of the menu item as argument.
 */