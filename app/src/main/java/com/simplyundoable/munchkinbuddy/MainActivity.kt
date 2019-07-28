package com.simplyundoable.munchkinbuddy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val raceSpinnerMain: Spinner = findViewById(R.id.raceMain)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.race_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            raceSpinnerMain.adapter = adapter
        }

        val raceSpinnerSecond: Spinner = findViewById(R.id.raceSecond)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.race_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            raceSpinnerSecond.adapter = adapter
        }

        val classSpinnerMain: Spinner = findViewById(R.id.classMain)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.class_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            classSpinnerMain.adapter = adapter
        }

        val classSpinnerSecond: Spinner = findViewById(R.id.classSecond)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.class_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            classSpinnerSecond.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun changeGender(view: View) {
        // Get the text view
        val showGenderTextView = findViewById<TextView>(R.id.genderButton)

        // Get the value of the text view.
        val genderString = showGenderTextView.text.toString()

        //Get new gender string and display the new value in the text view
        if (genderString == getString(R.string.maleGender)) {
            val newGender: String = getString(R.string.femaleGender)
            showGenderTextView.text = newGender
        } else {
            val newGender: String = getString(R.string.maleGender)
            showGenderTextView.text = newGender
        }
    }

    fun baseIncrement(view: View) {
        // Get the text view
        val showBaseTextView = findViewById<TextView>(R.id.baseLevel)
        val showMainTextView = findViewById<TextView>(R.id.mainLevel)

        // Get the value of the text view.
        val baseString = showBaseTextView.text.toString()
        val mainString = showMainTextView.text.toString()

        // Convert value to a number and increment it
        var levelBase: Int = Integer.parseInt(baseString)
        var levelMain: Int = Integer.parseInt(mainString)
        levelBase++
        levelMain++

        // Display the new value in the text view.
        showBaseTextView.text = levelBase.toString()
        showMainTextView.text = levelMain.toString()
    }

    fun baseDecrement(view: View) {
        // Get the text view
        val showBaseTextView = findViewById<TextView>(R.id.baseLevel)
        val showMainTextView = findViewById<TextView>(R.id.mainLevel)

        // Get the value of the text view.
        val baseString = showBaseTextView.text.toString()
        val mainString = showMainTextView.text.toString()

        // Convert value to a number and increment it
        var levelBase: Int = Integer.parseInt(baseString)
        var levelMain: Int = Integer.parseInt(mainString)

        //Check if value is greater than 1
        if (levelBase > 1) {
            levelBase--
            levelMain--
            // Display the new value in the text view.
            showBaseTextView.text = levelBase.toString()
            showMainTextView.text = levelMain.toString()
        }

    }

    fun equipIncrement(view: View) {
        // Get the text view
        val showEquipTextView = findViewById<TextView>(R.id.equipLevel)
        val showMainTextView = findViewById<TextView>(R.id.mainLevel)

        // Get the value of the text view.
        val equipString = showEquipTextView.text.toString()
        val mainString = showMainTextView.text.toString()

        // Convert value to a number and increment it
        var levelEquip: Int = Integer.parseInt(equipString)
        var levelMain: Int = Integer.parseInt(mainString)
        levelEquip++
        levelMain++

        // Display the new value in the text view.
        showEquipTextView.text = levelEquip.toString()
        showMainTextView.text = levelMain.toString()

    }

    fun equipDecrement(view: View) {
        // Get the text view
        val showEquipTextView = findViewById<TextView>(R.id.equipLevel)
        val showMainTextView = findViewById<TextView>(R.id.mainLevel)

        // Get the value of the text view.
        val equipString = showEquipTextView.text.toString()
        val mainString = showMainTextView.text.toString()

        // Convert value to a number and increment it
        var levelEquip: Int = Integer.parseInt(equipString)
        var levelMain: Int = Integer.parseInt(mainString)

        //Check if value is greater than 0
        if (levelEquip > 0) {
            levelEquip--
            levelMain--
            // Display the new value in the text view.
            showEquipTextView.text = levelEquip.toString()
            showMainTextView.text = levelMain.toString()
        }
    }

    fun moneyIncrement(view: View) {
        // Get the text view
        val showMoneyTextView = findViewById<TextView>(R.id.money)

        // Get the value of the text view.
        val moneyString = showMoneyTextView.text.toString()

        // Convert value to a number and increment it
        var levelMoney: Int = Integer.parseInt(moneyString)

        levelMoney += 100

        if (levelMoney >= 1000) {
            // Code to show element
            val showMoneyLevelUpView = findViewById<TextView>(R.id.moneyLevelUp)
            showMoneyLevelUpView.visibility = View.VISIBLE
        }
        // Display the new value in the text view.
        showMoneyTextView.text = levelMoney.toString()

    }

    fun moneyDecrement(view: View) {
        // Get the text view
        val showMoneyTextView = findViewById<TextView>(R.id.money)

        // Get the value of the text view.
        val moneyString = showMoneyTextView.text.toString()

        // Convert value to a number and increment it
        var levelMoney: Int = Integer.parseInt(moneyString)
        //Check if value is greater than 0
        if (levelMoney >= 100) {
            levelMoney -= 100

            // Display the new value in the text view
            showMoneyTextView.text = levelMoney.toString()
        }

        if (levelMoney < 1000) {
            // Code to show element
            val showMoneyLevelUpView = findViewById<TextView>(R.id.moneyLevelUp)
            showMoneyLevelUpView.visibility = View.INVISIBLE
        }
    }

    fun moneyLevelUp(view: View) {
        // Get the text view
        val showMainTextView = findViewById<TextView>(R.id.mainLevel)
        val showBaseTextView = findViewById<TextView>(R.id.baseLevel)
        val showMoneyTextView = findViewById<TextView>(R.id.money)

        // Get the value of the text view.
        val moneyString = showMoneyTextView.text.toString()
        val mainString = showMainTextView.text.toString()
        val baseString = showBaseTextView.text.toString()

        // Convert value to a number and increment it
        var levelMoney: Int = Integer.parseInt(moneyString)
        var levelMain: Int = Integer.parseInt(mainString)
        var levelBase: Int = Integer.parseInt(baseString)

        //Check if value is greater than 1000
        if (levelMoney >= 1000) {
            levelMoney -= 1000
            levelBase++
            levelMain++

            // Display the new value in the text view
            showMoneyTextView.text = levelMoney.toString()
            showBaseTextView.text = levelBase.toString()
            showMainTextView.text = levelMain.toString()
        }

        if (levelMoney < 1000) {
            // Code to show element
            val showMoneyLevelUpView = findViewById<TextView>(R.id.moneyLevelUp)
            showMoneyLevelUpView.visibility = View.INVISIBLE
        }
    }
}
