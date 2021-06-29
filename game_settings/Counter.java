package game_settings;

public class Counter {

    private int counter;

    /***
     * this is the constructor.
     * @param number - the number.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /***
     * add number to current count.
     * @param number - the number.
     */
    public void increase(int number) {
        counter += number;
    }

    /***
     * subtract number from current count.
     * @param number -
     */
    public void decrease(int number) {
        counter -= number;
    }

    /***
     * get current count.
     * @return counter value.
     */
    public int getValue() {
        return this.counter;
    }
}