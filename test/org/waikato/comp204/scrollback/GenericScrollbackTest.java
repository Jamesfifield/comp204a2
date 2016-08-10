package org.waikato.comp204.scrollback;

import org.junit.Assert;
import org.junit.Test;
import org.waikato.comp204.misc.Pokemon;
import org.waikato.comp204.misc.PokemonEncounter;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Tests for your String GenericScrollback implementation
 * notes: the current implementation test for string type when comparing two string types before adding them to the list, so I wrote test for that condition even though the scrollback
 * should not be responsible for determining how to compare each object.
 */
public class GenericScrollbackTest {

    //new Generic Specific

    @Test
    public void add_samePokemonEncounterTwice_countIncrementedOnlyOnce() throws Exception {
        //Arrange
        GenericScrollback<PokemonEncounter> testList = new GenericScrollback<PokemonEncounter>();
        PokemonEncounter testPokemon = new PokemonEncounter(2,4, Pokemon.Bulbasaur);
        //Act
        testList.add(new PokemonEncounter(2,2, Pokemon.Abra));
        testList.add(new PokemonEncounter(5,2, Pokemon.Beedrill));
        testList.add(testPokemon);
        testList.add(new PokemonEncounter(2,4, Pokemon.Bulbasaur));
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(3,result);
        Assert.assertEquals(testPokemon, testList.getLast());

    }

    @Test
    public void add_AddItemTwice_countIncrementedOnlyOnce() throws Exception {
        //Arrange
        GenericScrollback<Date> testList = new GenericScrollback<Date>();
        Date testDate = new Date();
        //Act
        testList.add(testDate);
        testList.add(new Date());
        testList.add(testDate);
        testList.add(testDate);
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(result, 3);
    }


    //Currently T does not compare/validate itself.
    @Test
    public void add_InvalidStringEmpty_ShouldNotBeAddedToScrollBack() throws Exception {
        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
        //Act
        testList.add("         ");
        //Assert
        Assert.assertTrue(testList.getCount() == 0);
    }





    //Older Test
    @Test
    public void contructor_doNotSpecifyListSize_capacitySetToDefault() throws Exception {

        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
        //Act
        //Assert
        Assert.assertTrue("The default size was set to 10", testList.getCapacity() == 10);
    }

    @Test
    public void contructor_specifyListSize_capacitySetToSpecifiedInt() throws Exception {

        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>(22);
        //Act
        //Assert
        Assert.assertTrue("The default size was set to 10", testList.getCapacity() == 22);
    }



    @Test(expected = IllegalArgumentException.class)
     public void add_NullItem_ShouldThrowException() throws Exception {
        //Arrange
        GenericScrollback<PokemonEncounter> testList = new GenericScrollback<PokemonEncounter>();
        //Act
        testList.add(null);
    }




    @Test
    public void add_exceedCapacity_ShouldRemoveOldestItem() throws Exception {
        //Arrange
        int size = 3;
        GenericScrollback<String> testList = new GenericScrollback<String>(size);
        //Act
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");

        Assert.assertEquals(size, testList.getCount());
        Assert.assertTrue(testList.getPrevious(0).equals("2"));
    }

    //Currently T does not compare/validate itself.
    @Test
    public void add_AddStringItemTwice_countIncrementedOnlyOnce() throws Exception {
        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
        //Act
        testList.add("first");
        testList.add("First");
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(result, 1);
    }




    //Decide what will happen if getLast() is called with no suitable elements available (Use a test to demonstrate this).
    //
    @Test
    public void getPrevious_negativeOutOfBoundIndex_shouldProvideOldestItem() throws Exception {

        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        //Act
        String result = testList.getPrevious(-2);
        //Assert
        Assert.assertEquals(result,"1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLast_lastItemWhenListIsEmpty_throwException() throws Exception {
        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
        //Act
        String result = testList.getLast();
        //Assert

    }

    @Test
    public void getLast_ListContainsValidData_returnLastItem() throws Exception {
        //Arrange
        GenericScrollback<String> testList = new GenericScrollback<String>();
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
        GenericScrollback<String> testList = new GenericScrollback<String>();
        String testItem = "Test Item";
        //Act
        testList.add(testItem);
        testList.clear();
        int result = testList.getCount();
        //Assert
        Assert.assertEquals(result, 0);
    }



}