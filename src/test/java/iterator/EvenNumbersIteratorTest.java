package iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test EvenNumbersIterator.
 */
public class EvenNumbersIteratorTest {
    /**
     * Standard iterator interface.
     */
    private Iterator<Integer> it;

    /**
     * Initialize the class EvenNumberIterator.
     */
    @Before
    public void setUp() {
        it = new EvenNumbersIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    /**
     * If the item does not exist, an exception is thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Wrong order of invocation.
     */
    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
        it.next();
    }

    /**
     * Testing hasNext .
     */
    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1,3,5});
        assertThat(it.hasNext(), is(false));
    }

    /**
     * All numbers are even.
     */
    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[]{2, 3, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }

    /**
     * case empty array
     */
    @Test
    public void emptyArray() {
        it = new EvenNumbersIterator(new int[]{});
        assertThat(it.hasNext(), is(false));
    }

    /**
     *  both even and not even numbers
     */
    @Test
    public void differentNumbers() {
        it = new EvenNumbersIterator(new int[]{2,3,4,5});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
    }

    /**
     * Multiply invocation of hasNext return the same values
     */
    @Test
    public void hasNextReturnTheSameValues() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    /**
     * Multiply invocation of next return only even numbers
     */
    @Test (expected = NoSuchElementException.class)
    public void nextOnlyReturnAllEvenValues(){
        assertThat(it.next(),is(2));
        assertThat(it.next(),is(4));
        assertThat(it.next(), is(6));
        it.next();
    }

    /**
     * hasNext which invocated after next check the next element of array
     */
    @Test(expected = NoSuchElementException.class)
    public void firstNextAfterHasNextUseNextIndex() {
        assertThat(it.next(),is(2));
        assertThat(it.next(),is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(),is(6));
        assertThat(it.hasNext(),is(false));
        it.next();
    }
}



