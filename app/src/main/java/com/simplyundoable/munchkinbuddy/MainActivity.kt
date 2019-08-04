package com.simplyundoable.munchkinbuddy

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_dice.*
import kotlin.random.Random
import kotlin.system.exitProcess

class DiceDialogFragment : DialogFragment() {

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		return activity?.let {
			val builder = AlertDialog.Builder(it)
			// Get the layout inflater
			val inflater = requireActivity().layoutInflater
			// Pass null as the parent view because its going in the dialog layout
			builder.run {
				setView(inflater.inflate(R.layout.dialog_dice, null))
				create()
			}

		} ?: throw IllegalStateException("Activity cannot be null")
	}
}

class MainActivity : AppCompatActivity() {

	private var baseLevelStore = 1
	private var equipLevelStore = 0
	private var moneyLevelStore = 0
	private var race1Store = 0
	private var race2Store = 0
	private var class1Store = 0
	private var class2Store = 0

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

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_dice -> {
				menuDice()
				true
			}

			R.id.action_reset -> {
				menuReset(mainActivityView)
				true
			}

			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun retrieveIdFromView(view: View): String {
		val indexOfIdStart = view.toString().indexOf("app:id/") + 7
		var indexOfIdEnd = view.toString().indexOf(" ", indexOfIdStart)

		if (indexOfIdEnd == -1) {
			indexOfIdEnd = view.toString().indexOf("}", indexOfIdStart) - 1
		}

		return view.toString().slice(indexOfIdStart..indexOfIdEnd)
	}

	private fun retrieveTextView(id: Int): RetrievableTextView {
		val showTextView = findViewById<TextView>(id)
		val baseString = showTextView.text.toString()
		val level: Int = Integer.parseInt(baseString)

		return RetrievableTextView(showTextView, level)
	}

	private fun counterHandler(counterView: TextView, counter: Int) {
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
		var (mainView, mainLevel) = retrieveTextView(R.id.mainLevel)
		var (baseView, baseLevel) = retrieveTextView(R.id.baseLevel)
		var (moneyView, moneyLevel) = retrieveTextView(R.id.money)

		//Check if value is greater than 1000
		if (moneyLevel >= 1000) {
			moneyLevel -= 1000
			baseLevel++
			mainLevel++

			// Display the new value in the text view
			counterHandler(moneyView, moneyLevel)
			counterHandler(baseView, baseLevel)
			counterHandler(mainView, mainLevel)
		}

		if (moneyLevel < 1000) {
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
		val showClassView = findViewById<Spinner>(R.id.classSecond)

		if (showClassView.visibility == View.INVISIBLE) {
			showClassView.visibility = View.VISIBLE
		} else {
			showClassView.visibility = View.INVISIBLE
		}
	}

	private fun menuDice() {
		val newFragment = DiceDialogFragment()
		newFragment.show(supportFragmentManager, "dice")
		val diceImageView: ImageView = diceDialog.findViewById(R.id.diceImage) // this line of code crashes the app
		val drawableRes = when (Random.nextInt(1, 6)) {
			1 -> R.drawable.dice1
			else -> R.drawable.dice2
		}

		diceImageView.setImageResource(drawableRes)
	}

	fun menuReset(v: View) {
		val showBaseTextView = findViewById<TextView>(R.id.baseLevel)
		val baseString = showBaseTextView.text.toString()
		var levelBase: Int = Integer.parseInt(baseString)
		baseLevelStore = levelBase
		levelBase = 1
		showBaseTextView.text = levelBase.toString()
		val showMainTextView = findViewById<TextView>(R.id.mainLevel)
		showMainTextView.text = levelBase.toString()

		val showEquipTextView = findViewById<TextView>(R.id.equipLevel)
		val equipString = showEquipTextView.text.toString()
		var levelEquip: Int = Integer.parseInt(equipString)
		equipLevelStore = levelEquip
		levelEquip = 0
		showEquipTextView.text = levelEquip.toString()

		val showMoneyTextView = findViewById<TextView>(R.id.money)
		val moneyString = showMoneyTextView.text.toString()
		var levelMoney: Int = Integer.parseInt(moneyString)
		moneyLevelStore = levelMoney
		levelMoney = 0
		showMoneyTextView.text = levelMoney.toString()

		val showMoneyLevelUpTextView = findViewById<TextView>(R.id.moneyLevelUp)
		showMoneyLevelUpTextView.visibility = View.INVISIBLE

		val showRace1Spinner = findViewById<Spinner>(R.id.raceMain)
		race1Store = showRace1Spinner.selectedItemPosition
		showRace1Spinner.setSelection(0)

		val showRace2Spinner = findViewById<Spinner>(R.id.raceSecond)
		race2Store = showRace2Spinner.selectedItemPosition
		showRace2Spinner.setSelection(0)
		showRace2Spinner.visibility = View.INVISIBLE

		val showClass1Spinner = findViewById<Spinner>(R.id.classMain)
		class1Store = showClass1Spinner.selectedItemPosition
		showClass1Spinner.setSelection(0)

		val showClass2Spinner = findViewById<Spinner>(R.id.classSecond)
		class2Store = showClass2Spinner.selectedItemPosition
		showClass2Spinner.setSelection(0)
		showClass2Spinner.visibility = View.INVISIBLE

		Snackbar.make(mainActivityView, getString(R.string.action_resetMessage), Snackbar.LENGTH_LONG)
			.setAction(getString(R.string.action_resetUndo)) {
				resetUndo()
			}.show()
	}

	private fun resetUndo() {
		val showBaseTextView = findViewById<TextView>(R.id.baseLevel)
		showBaseTextView.text = baseLevelStore.toString()

		val showEquipTextView = findViewById<TextView>(R.id.equipLevel)
		showEquipTextView.text = equipLevelStore.toString()

		val showMainTextView = findViewById<TextView>(R.id.mainLevel)
		val mainTotal = baseLevelStore + equipLevelStore
		showMainTextView.text = mainTotal.toString()

		val showMoneyTextView = findViewById<TextView>(R.id.money)
		showMoneyTextView.text = moneyLevelStore.toString()
		val showMoneyLevelUpView = findViewById<TextView>(R.id.moneyLevelUp)
		if (moneyLevelStore >= 1000) {
			showMoneyLevelUpView.visibility = View.VISIBLE
		} else {
			showMoneyLevelUpView.visibility = View.INVISIBLE
		}

		val showRace1Spinner = findViewById<Spinner>(R.id.raceMain)
		showRace1Spinner.setSelection(race1Store)

		val showRace2Spinner = findViewById<Spinner>(R.id.raceSecond)
		showRace2Spinner.setSelection(race2Store)

		val showClass1Spinner = findViewById<Spinner>(R.id.classMain)
		showClass1Spinner.setSelection(class1Store)

		val showClass2Spinner = findViewById<Spinner>(R.id.classSecond)
		showClass2Spinner.setSelection(class2Store)

		val snackbar = Snackbar.make(mainActivityView, getString(R.string.action_resetUndoMessage), LENGTH_LONG)
		snackbar.show()
	}
}
