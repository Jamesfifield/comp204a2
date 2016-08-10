package org.waikato.comp204.scrollback;

import org.junit.Assert;
import org.junit.Test;
import org.waikato.comp204.misc.Pokemon;
import org.waikato.comp204.misc.PokemonEncounter;

import static org.junit.Assert.*;

/**
 * Tests for your String Scrollback implementation
 *
 * Test Format: [Method]_[Condition]_[ExpectedResults]
 * Getters and Setters are not tested.
 */
public class ScrollbackTest {



    @Test
    public void contructor_doNotSpecifyListSize_capacitySetToDefault() throws Exception {

        //Arrange
        Scrollback testList = new Scrollback();
        //Act
        //Assert
        Assert.assertTrue("The default size was set to 10", testList.getCapacity() == 10);
    }

    @Test
    public void contructor_specifyListSize_capacitySetToSpecifiedInt() throws Exception {

        //Arrange
        Scrollback testList = new Scrollback(22);
        //Act
        //Assert
        Assert.assertTrue("The default size was set to 10", testList.getCapacity() == 22);
    }

    @Test(expected = IllegalArgumentException.class)
    public void contructor_specifyNegativeSize_argumentException() throws Exception {

        //Arrange
        Scrollback testList = new Scrollback(-1);
        //Act
        //Assert
        Assert.assertTrue(testList.getCapacity() == 22);
    }


    @Test(expected = IllegalArgumentException.class)
    public void add_NullOrEmptyStringItem_ShouldThrowException() throws Exception {
        //Arrange
        Scrollback testList = new Scrollback();
        //Act
        try{
            testList.add(null);
        }catch (Exception e){
            testList.add("      ");
        }

    }



    @Test
    public void add_exceedCapacity_ShouldRemoveOldestItem() throws Exception {
        //Arrange
        int size = 3;
        Scrollback testList = new Scrollback(size);
        //Act
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");

        Assert.assertEquals(size,testList.getCount());
        Assert.assertTrue(testList.getPrevious(0).equals("2"));
    }

    @Test
    public void add_AddItemTwice_countIncrementedOnlyOnce() throws Exception {
        //Arrange
        Scrollback testList = new Scrollback();
        //Act
        testList.add("first");
        testList.add("First");
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(result, 1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getPrevious_negativeOutOfBoundIndex_shouldProvideOldestItem() throws Exception {

        //Arrange
        Scrollback testList = new Scrollback(-1);
        testList.add("1");
        testList.add("2");
        testList.add("3");
        //Act
        String result = testList.getPrevious(-2);
        //Assert
        Assert.assertEquals(result,"1");
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void getLast_lastItemWhenListIsEmpty_throwOutOfBoundsException() throws Exception {
        //Arrange
        Scrollback testList = new Scrollback();
        //Act
        String result = testList.getLast();
        //Assert

    }

    @Test
    public void getLast_ListContainsValidData_returnLastItem() throws Exception {
        //Arrange
        Scrollback testList = new Scrollback();
        String testItem = "Test Item";
        //Act
        testList.add(testItem);
        String result = testList.getLast();
        //Assert
        Assert.assertEquals(result, testItem);
    }

    @Test
    public void clear_ListContainsValidData_returnLastItem() throws Exception {
        //Arrange
        Scrollback testList = new Scrollback();
        String testItem = "Test Item";
        //Act
        testList.add(testItem);
        testList.clear();
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(result, 0);
    }







}