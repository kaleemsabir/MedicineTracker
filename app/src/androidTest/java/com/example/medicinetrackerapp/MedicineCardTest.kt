package com.example.medicinetrackerapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.medicinetrackerapp.data.model.Medicine
import com.example.medicinetrackerapp.ui.screens.medicineList.MedicineCard
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MedicineCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Test data
    private val testMedicine = Medicine(
        id = 1,
        name = "Aspirin",
        dose = "500mg",
        strength = "Medium"
    )

    @Composable
    fun TestableMedicineCard() {
        MedicineCard(medicine = testMedicine, onClick = { /* do nothing */ })
    }

    @Test
    fun testMedicineCardDisplaysCorrectInformation() {
        // Set the composable content to test
        composeTestRule.setContent {
            TestableMedicineCard()
        }

        // Verify that the MedicineCard displays the correct information
        composeTestRule.onNodeWithText("Name: Aspirin").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dose: 500mg").assertIsDisplayed()
        composeTestRule.onNodeWithText("Strength: Medium").assertIsDisplayed()
    }

    @Test
    fun testMedicineCardButtonDisplaysCorrectText() {
        // Set the composable content to test
        composeTestRule.setContent {
            TestableMedicineCard()
        }

        // Verify that the button text is correct
        composeTestRule.onNodeWithText("View Details").assertIsDisplayed()
    }

    @Test
    fun testMedicineCardButtonClickTriggersAction() {
        var clicked = false

        // Set the composable content to test
        composeTestRule.setContent {
            MedicineCard(
                medicine = testMedicine,
                onClick = { clicked = true }
            )
        }

        // Perform a click on the button
        composeTestRule.onNodeWithText("View Details").performClick()

        // Assert that the click action was triggered
        assertTrue("Button click should trigger action", clicked)
    }
}
