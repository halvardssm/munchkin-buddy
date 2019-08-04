package com.simplyundoable.munchkinbuddy

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

	data class RetrievableTextView(var showTextView: TextView, var counter: Int)

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

	private fun retrieveIdFromView(view: View): String {
		val indexOfIdStart = view.toString().indexOf("app:id/") + 7
		var indexOfIdEnd = view.toString().indexOf(" ", indexOfIdStart)

		if (indexOfIdEnd == -1) {
			indexOfIdEnd = view.toString().indexOf("}", indexOfIdStart) - 1
		}

		val viewId: String = view.toString().slice(indexOfIdStart..indexOfIdEnd)

		return viewId
	}

	fun retrieveTextView(id: Int): RetrievableTextView {
		val showTextView = findViewById<TextView>(id)
		val baseString = showTextView.text.toString()
		val level: Int = Integer.parseInt(baseString)

		return RetrievableTextView(showTextView, level)
	}

	fun counterHandler(counterView: TextView, counter: Int) {
		counterView.text = counter.toString()
	}

	fun baseHandler(view: View) {
		val viewId: String = retrieveIdFromView(view)
		var (showMainTextView, levelMain) = retrieveTextView(R.id.mainLevel)
		var (showTextView, level) = retrieveTextView(R.id.baseLevel)

		if (viewId.indexOf("Add") != -1) {
			level++
			levelMain++

		} else if (viewId.indexOf("Sub") != -1) {
			if (level > 1) {
				level--
				levelMain--
			}

		} else {
			exitProcess(1)
		}

		counterHandler(showTextView, level)
		counterHandler(showMainTextView, levelMain)
	}

	fun equipmentHandler(view: View) {
		val viewId: String = retrieveIdFromView(view)
		var (showMainTextView, levelMain) = retrieveTextView(R.id.mainLevel)
		var (showTextView, level) = retrieveTextView(R.id.equipLevel)

		if (viewId.indexOf("Add") != -1) {
			level++
			levelMain++

		} else if (viewId.indexOf("Sub") != -1) {
			if (level > 0) {
				level--
				levelMain--
			}

		} else {
			exitProcess(1)
		}

		counterHandler(showTextView, level)
		counterHandler(showMainTextView, levelMain)
	}

	fun moneyHandler(view: View) {
		val viewId: String = retrieveIdFromView(view)
		var (showTextView, level) = retrieveTextView(R.id.money)
		val showMoneyLevelUpView = findViewById<TextView>(R.id.moneyLevelUp)

		if (viewId.indexOf("Add") != -1) {
			level += 100

		} else if (viewId.indexOf("Sub") != -1) {
			if (level >= 100) {
				level -= 100
			}

		} else {
			exitProcess(1)
		}

		if (level >= 1000) {
			showMoneyLevelUpView.visibility = View.VISIBLE
		} else {
			showMoneyLevelUpView.visibility = View.INVISIBLE
		}

		counterHandler(showTextView, level)
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

	fun raceMunchToggle(view: View) {
		// Get the text view
		val showRaceView = findViewById<Spinner>(R.id.raceSecond)

		if (showRaceView.visibility == View.INVISIBLE) {
			showRaceView.visibility = View.VISIBLE
		} else {
			showRaceView.visibility = View.INVISIBLE
		}
	}

	fun classMunchToggle(view: View) {
		// Get the text view
		val showRaceView = findViewById<Spinner>(R.id.raceSecond)
		val showClassView = findViewById<Spinner>(R.id.classSecond)

		if (showClassView.visibility == View.INVISIBLE) {
			showClassView.visibility = View.VISIBLE
		} else {
			showClassView.visibility = View.INVISIBLE
		}
	}
}
