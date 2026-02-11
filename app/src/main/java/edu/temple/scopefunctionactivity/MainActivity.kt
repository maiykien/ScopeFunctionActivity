package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("function out", getTestDataArray().toString())

        Log.d("testArray", getTestDataArray().toString())

        Log.d("avgVsMedian",
            averageLessThanMedian(listOf(1.0, 2.0, 3.0, 4.0)).toString()
        )
        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

        val data = listOf(1, 2, 3)

        val v1 = getView(1, null, data, this)

        val v2 = getView(1, v1, data, this)

        if (v1 === v2) {
            Log.d("Reused", (v1 as TextView).text.toString())
        }

        if ((v2 as TextView).text.toString() == "2") {
            Log.d("Recycle", v2.text.toString())
        }


    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    /*
    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> {
        val testArray = MutableList(10){ Random.nextInt()}
        testArray.sort()
        return testArray
    }*/

    // Converted to Scope Function.

    private fun getTestDataArray() =
        MutableList(10) {
            Random.nextInt() }.sorted()

    /*
    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
        val avg = listOfNumbers.average()
        val sortedList = listOfNumbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]

        return avg < median
    }*/

    // Converted Version
    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.run {
            val avg = average()
            val sortedList = sorted()
            val median =

                if (sortedList.size % 2 == 0)
                    (sortedList[sortedList.size / 2] +
                            sortedList[(sortedList.size - 1) / 2]) / 2
                else
                    sortedList[sortedList.size / 2]

            avg < median


        }




    /*
    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
        val textView: TextView

        if (recycledView != null) {
            textView = recycledView as TextView
        } else {
            textView = TextView(context)
            textView.setPadding(5, 10, 10, 0)
            textView.textSize = 22f
        }

        textView.text = collection[position].toString()

        return textView
    }*/

    // Converted Version for GetView
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }

}